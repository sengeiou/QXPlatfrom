package love.qx.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import love.qx.platform.entity.Role;
import love.qx.platform.entity.UserUrl;
import love.qx.platform.entity.Website;
import love.qx.platform.service.UserUrlService;
import love.qx.platform.vo.Result;
import love.qx.platform.vo.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
用户上传链接信息表
 */
@RestController
@RequestMapping("/userUrl")
public class UserUrlController {
    @Autowired
    UserUrlService userUrlService;
    //添加
    //
    //添加一条网站链接数据
    @PostMapping("/addUserUrl")
    public Result addUserUrl(@RequestBody UserUrl userUrl){
        if (userUrl.getUserUrlId()!=0||userUrl.getWebsiteUrl()==null||
            userUrl.getCause()==null||userUrl.getState()==0||
            userUrl.getUptime()==null||userUrl.getUserEmail()==null||
            userUrl.getWebsiteDescription()==null||userUrl.getWebsiteName()==null)
            return Result.failed(ResultCode.PARAMETERMISTAKE);
        boolean b = userUrlService.save(userUrl);
        if (b)return Result.success(ResultCode.ADDDATASUCCESS);
        return Result.failed(ResultCode.ADDDATAFAILURE);
    }
    //删除
    //
    //根据用户上传链接数据id删除一条数据
    @PostMapping("/delById")
    public Result delById(@RequestParam("userUrlId") Integer userUrlId){
        if (userUrlId==0)return Result.failed(ResultCode.PARAMETERMISTAKE);
        boolean b = userUrlService.removeById(userUrlId);
        if (b)return Result.success(ResultCode.DELETEDATASUCCESS);
        return Result.failed(ResultCode.DELETEDATAFAILURE);
    }
    //修改
    //
    //根据用户上传链接数据id修改审核状态和审核未通过信息
    @PostMapping("updateById")
    public Result updateById(@RequestBody UserUrl userUrl){
        if (userUrl.getUserUrlId()==0||userUrl.getWebsiteUrl()==null||
                userUrl.getCause()==null||userUrl.getState()==0||
                userUrl.getUptime()==null||userUrl.getUserEmail()==null||
                userUrl.getWebsiteDescription()==null||userUrl.getWebsiteName()==null)
            return Result.failed(ResultCode.PARAMETERMISTAKE);
        boolean b = userUrlService.updateById(userUrl);
        if (b)return Result.success(ResultCode.UPDATEDATASUCCESS);
        return Result.failed(ResultCode.UPDATEDATAFAILURE);
    }
    //查询
    //
    //查询所有用户上传网站链接信息
    @GetMapping("/findAll")
    public Result findAll(){
        List<UserUrl> list = userUrlService.list();
        if (list!=null)return Result.success(ResultCode.GETDATASUCCESS,list);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //根据上传时间查询数据
    @GetMapping("/findByTime")
    public Result findByTime(@RequestParam("uptime") String uptime){
        if (uptime==null)return Result.failed(ResultCode.PARAMETERMISTAKE);
        QueryWrapper<UserUrl> wrapper=new QueryWrapper<>();
        List<UserUrl> list = userUrlService.list(wrapper.like("uptime",uptime));
        if (list!=null)return Result.success(ResultCode.GETDATASUCCESS,list);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //根据用户邮箱查询数据
    @GetMapping("/findByUserEmail")
    public Result findByUserEmail(@RequestParam("userEmail") String userEmail){
        if (userEmail==null)return Result.failed(ResultCode.PARAMETERMISTAKE);
        QueryWrapper<UserUrl> wrapper=new QueryWrapper<>();
        List<UserUrl> list = userUrlService.list(wrapper.eq("user_email",userEmail));
        if (list!=null)return Result.success(ResultCode.GETDATASUCCESS,list);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //根据网站名称/描述/网站链接模糊查询数据
    @GetMapping("/findByNameOrUrlOrDescription")
    public Result findByNameOrUrlOrDescription(@RequestParam("info") String info){
        if (info==null)return Result.failed(ResultCode.PARAMETERMISTAKE);
        QueryWrapper<UserUrl> wrapper=new QueryWrapper<>();
        List<UserUrl> list = userUrlService.list(wrapper.like("website_url",info).or(w->w.like("website_name",info)).or(w->w.like("website_description",info)));
        if (list!=null)return Result.success(ResultCode.GETDATASUCCESS,list);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //根据审核状态查询数据
    @GetMapping("/findByState")
    public Result findByState(@RequestParam("state") Integer state){
        if (state==0)return Result.failed(ResultCode.PARAMETERMISTAKE);
        QueryWrapper<UserUrl> wrapper=new QueryWrapper<>();
        List<UserUrl> state1 = userUrlService.list(wrapper.eq("state", state));
        if (state1!=null)return Result.success(ResultCode.GETDATASUCCESS,state1);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
}
