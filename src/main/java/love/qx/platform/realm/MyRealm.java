package love.qx.platform.realm;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import love.qx.platform.dao.IUserp;
import love.qx.platform.entity.UserP;
import love.qx.platform.service.RealmService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    RealmService realmService;

    @Autowired
    IUserp userp;

    public String getName() {
        return "myRealm";
    }


    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //参数authenticationToken就是subject.login(token)传递过来的token参数
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //从token中获取用户名
        String useremail = token.getUsername();
        QueryWrapper<UserP> userPQueryWrapper=new QueryWrapper<>();
        UserP user = userp.selectOne(userPQueryWrapper.eq("user_email",useremail));
        if (user == null) return null;
        AuthenticationInfo info = new SimpleAuthenticationInfo(
                user.getUserEmail(),
                user.getPwd(), getName());
        return info;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Subject subject = SecurityUtils.getSubject();
        //获取当前登录的用户邮箱
        String user_email =(String) subject.getPrincipal();
        //查询当前用户的角色、权限列表
        Set<String> roleNames= realmService.getRoleNamesByUserId(user_email);
        Set<String> ps=realmService.getPermissionsByUserId(user_email);
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.setRoles(roleNames);
        info.setStringPermissions(ps);
        return info;
    }

}
