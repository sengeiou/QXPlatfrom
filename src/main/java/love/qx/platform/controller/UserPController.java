package love.qx.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import love.qx.platform.entity.UserP;
import love.qx.platform.service.UserPService;
import love.qx.platform.util.encryption.MD5;
import love.qx.platform.vo.Result;
import love.qx.platform.vo.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.image.RescaleOp;
import java.util.List;

@RestController
@RequestMapping("/userp")
public class UserPController {

    @Autowired
    UserPService userPService;

    //更新
    //
    //修改密码
    @PostMapping("/amendPWD")
    public Result amendPWD(@RequestParam("userEmail") String userEmail,
                           @RequestParam("userPwd") String userPwd){
        if (userEmail==null||userPwd==null)return Result.failed(ResultCode.PARAMETERMISTAKE);
        String encryption = MD5.encryption(userPwd);
        if (encryption==null)return Result.failed(ResultCode.UPDATEDATAFAILURE);
        int i = userPService.UpdatePwd(encryption, userEmail);
        if (i!=0)return Result.success(ResultCode.UPDATEDATASUCCESS);
        return Result.failed(ResultCode.UPDATEDATAFAILURE);
    }

    //查询
    //
    //根据邮箱查询密码
    @PostMapping("/findPwdByEmail")
    public Result findPwdByEmail(@RequestParam("userEmail") String userEmail){
        QueryWrapper<UserP> wrapper=new QueryWrapper<>();
        UserP user_email = userPService.getOne(wrapper.eq("user_email", userEmail));
        if (user_email!=null)return Result.success(ResultCode.GETDATASUCCESS,user_email.getPwd());
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //查询所有账户
    @GetMapping("/findAll")
    public Result<List<UserP>> findAll(){
        List<UserP> list = userPService.list();
        return Result.success(ResultCode.GETDATASUCCESS,list);
    }

    //删除
    //
    //根据账户id删除一条账户
    @PostMapping("/delByEmail")
    public Result delByEmail(@RequestParam("userPid") Integer userPid){
        boolean user_email = userPService.removeById(userPid);
        if(user_email)return Result.success(ResultCode.DELETEDATASUCCESS);
        return Result.failed(ResultCode.DELETEDATAFAILURE);
    }
    //根据账户id数组删除一组账户
    @PostMapping("/delByIdList")
    public Result delByIdList(@RequestBody List<Integer> idList){
        boolean b = userPService.removeByIds(idList);
        if (b)return Result.success(ResultCode.DELETEDATASUCCESS);
        return Result.failed(ResultCode.DELETEDATAFAILURE);
    }

    //添加账户信息
    @PostMapping("/addUserP")
    public Boolean addUserP(UserP userP){
        if (userP.getUserEmail()==null||userP.getPwd()==null)return false;
        String encryption = MD5.encryption(userP.getPwd());
        if (encryption==null)return false;
        userP.setPwd(encryption);
        boolean save = userPService.save(userP);
        if (save)return true;
        return false;
    }
}
