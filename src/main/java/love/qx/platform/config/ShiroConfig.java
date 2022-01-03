package love.qx.platform.config;

import love.qx.platform.realm.MyRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(securityManager);
        // 用户未登录时返回的信息
        bean.setLoginUrl("/Error/Unauthorized"); //401
        //权限验证失败返回的信息
        bean.setUnauthorizedUrl("/Error/Forbidden"); //403
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        /*
        anon : 无需认证就可以访问
        authc :  必须认证了才能访问
        user :  必须拥有 记住我 功能才能访问
        perms :  拥有对某个资源的权限才能访问
        roles : 拥有某个角色权限才能访问
         */
        //对用户信息的操作
        filterChainDefinitionMap.put("/user/login","anon");
        filterChainDefinitionMap.put("/user/update","perms[user_update]");
        filterChainDefinitionMap.put("/user/findOne","anon");
        filterChainDefinitionMap.put("/user/findByName","perms[user_list]");
        filterChainDefinitionMap.put("/user/findBySex","perms[user_list]");
        filterChainDefinitionMap.put("/user/findByAge","perms[user_list]");
        filterChainDefinitionMap.put("/user/findBySexScope","perms[user_list]");
        filterChainDefinitionMap.put("/user/addUser","anon");

        //对权限表中的信息操作
        filterChainDefinitionMap.put("/permission/findAll","perms[permission_list]");
        filterChainDefinitionMap.put("/permission/findById","perms[permission_list]");
        filterChainDefinitionMap.put("/permission/findByNameOrDescription","perms[permission_list]");
        filterChainDefinitionMap.put("/permission/updateById","perms[permission_update]");
        filterChainDefinitionMap.put("/permission/delById","perms[permission_del]");
        filterChainDefinitionMap.put("/permission/addPermission","perms[permission_add]");

        //对角色表中的信息操作
        filterChainDefinitionMap.put("/role/updateById","perms[role_update]");
        filterChainDefinitionMap.put("/role/findAll","perms[role_list]");
        filterChainDefinitionMap.put("/role/findById","perms[role_list]");
        filterChainDefinitionMap.put("/role/delById","perms[role_del]");
        filterChainDefinitionMap.put("/role/addRole","perms[role_add]");


        //对网站信息表操作
        filterChainDefinitionMap.put("/website/updateById","perms[website_update]");
        filterChainDefinitionMap.put("/website/updateIsOnLineById","perms[website_update]");
        filterChainDefinitionMap.put("/website/findAll","anon");
        filterChainDefinitionMap.put("/website/findAllIsOnLine","anon");
        filterChainDefinitionMap.put("/website/findById","perms[website_list]");
        filterChainDefinitionMap.put("/website/findByContentId","perms[website_list]");
        filterChainDefinitionMap.put("/website/findByContentIdAndOnLine","anon");
        filterChainDefinitionMap.put("/website/findByNameOrUrlOrIntroduce","anon");
        filterChainDefinitionMap.put("/website/findByDate","roles[superAdmin]");
        filterChainDefinitionMap.put("/website/findByIsOnLine","perms[website_list]");
        filterChainDefinitionMap.put("/website/delByWebsiteId","perms[website_del]");
        filterChainDefinitionMap.put("/website/delByWebsiteIds","perms[website_del]");
        filterChainDefinitionMap.put("/website/addWebsite","perms[website_add]");


        //对网站类型表操作
        filterChainDefinitionMap.put("/websiteType/addType","perms[website_type_add]");
        filterChainDefinitionMap.put("/websiteType/findAll","perms[website_type_list]");
        filterChainDefinitionMap.put("/websiteType/findAllByOnLine","anon");
        filterChainDefinitionMap.put("/websiteType/findById","perms[website_type_list]");
        filterChainDefinitionMap.put("/websiteType/findByType","perms[website_type_list]");
        filterChainDefinitionMap.put("/websiteType/findByIsOnLine","anon");
        filterChainDefinitionMap.put("/websiteType/findByDate","roles[superAdmin]");
        filterChainDefinitionMap.put("/websiteType/updateIsOnLineByTypeId","perms[website_type_update]");
        filterChainDefinitionMap.put("/websiteType/updateTypeNameByTypeId","perms[website_type_update]");
        filterChainDefinitionMap.put("/websiteType/delByTypeId","perms[website_type_del]");
        filterChainDefinitionMap.put("/websiteType/delByTypeIdList","perms[website_type_del]");

        //对类型下的网站总名称表操作
        filterChainDefinitionMap.put("/contentName/addContentName","perms[content_name_add]");
        filterChainDefinitionMap.put("/contentName/updateInfoByContentId","perms[content_name_update]");
        filterChainDefinitionMap.put("/contentName/updateIsOnLineByContentId","perms[content_name_update]");
        filterChainDefinitionMap.put("/contentName/findAll","perms[content_name_list]");
        filterChainDefinitionMap.put("/contentName/findOneByContentId","perms[content_name_list]");
        filterChainDefinitionMap.put("/contentName/findByTypeId","perms[content_name_list]");
        filterChainDefinitionMap.put("/contentName/findByTypeIdAndOnLine","anon");
        filterChainDefinitionMap.put("/contentName/findByDate","roles[superAdmin]");
        filterChainDefinitionMap.put("/contentName/findByContent","perms[content_name_list]");
        filterChainDefinitionMap.put("/contentName/delByTypeId","perms[content_name_del]");
        filterChainDefinitionMap.put("/contentName/delByContentId","perms[content_name_del]");


        //对用户上传链接信息表操作
        filterChainDefinitionMap.put("/userUrl/addUserUrl","perms[user_url_add]");
        filterChainDefinitionMap.put("/userUrl/delById","perms[user_url_del]");
        filterChainDefinitionMap.put("/userUrl/updateById","perms[user_url_update]");
        filterChainDefinitionMap.put("/userUrl/findAll","perms[user_url_list]");
        filterChainDefinitionMap.put("/userUrl/findByTime","perms[user_url_list]");
        filterChainDefinitionMap.put("/userUrl/findByUserEmail","perms[user_url_list]");
        filterChainDefinitionMap.put("/userUrl/findByNameOrUrlOrDescription","perms[user_url_list]");
        filterChainDefinitionMap.put("/userUrl/findByState","perms[user_url_list]");


        //对网站反馈信息表操作
        filterChainDefinitionMap.put("/feedback/addFeedback","perms[feedback_add]");
        filterChainDefinitionMap.put("/feedback/delByFeedbackId","perms[feedback_del]");
        filterChainDefinitionMap.put("/feedback/updateResultByFeedbackId","perms[feedback_update]");
        filterChainDefinitionMap.put("/feedback/findAll","perms[feedback_list]");
        filterChainDefinitionMap.put("/feedback/findByFeedbackId","perms[feedback_list]");
        filterChainDefinitionMap.put("/feedback/findByWebsiteIdOrEmail","perms[feedback_list]");
        filterChainDefinitionMap.put("/feedback/findByTime","perms[feedback_list]");
        filterChainDefinitionMap.put("/feedback/findByResult","perms[feedback_list]");


        //对系统通知表操作
        filterChainDefinitionMap.put("/inform/addInform","perms[inform_add]");
        filterChainDefinitionMap.put("/inform/delById","perms[inform_del]");
        filterChainDefinitionMap.put("/inform/delByTime","perms[inform_del]");
        filterChainDefinitionMap.put("/inform/delByRole","perms[inform_del]");
        filterChainDefinitionMap.put("/inform/findAll","anon");
        filterChainDefinitionMap.put("/inform/findByInformId","perms[inform_list]");
        filterChainDefinitionMap.put("/inform/findByTime","perms[inform_list]");
        filterChainDefinitionMap.put("/inform/findByRole","perms[inform_list]");
        filterChainDefinitionMap.put("/inform/findByInformation","perms[inform_list]");


        //对账户信息表操作
        filterChainDefinitionMap.put("/userp/amendPWD","perms[userp_update]");
        filterChainDefinitionMap.put("/userp/findPwdByEmail","perms[userp_list]");
        filterChainDefinitionMap.put("/userp/findAll","roles[superAdmin]");
        filterChainDefinitionMap.put("/userp/delByEmail","perms[userp_del]");
        filterChainDefinitionMap.put("/userp/delByIdList","perms[userp_del]");
        filterChainDefinitionMap.put("/userp/addUserP","perms[userp_add]");


        //邮件发送的操作
        filterChainDefinitionMap.put("/email/send","anon");


        filterChainDefinitionMap.put("/**","authc");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultSecurityManager(@Qualifier("myRealm") MyRealm realm)
    {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }



    @Bean
    MyRealm myRealm()
    {
        return new MyRealm();
    }
}