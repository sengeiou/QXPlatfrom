package love.qx.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;
import love.qx.platform.entity.Login;
import love.qx.platform.entity.RegisterUser;
import love.qx.platform.entity.User;
import love.qx.platform.entity.UserP;
import love.qx.platform.service.UserPService;
import love.qx.platform.service.UserService;
import love.qx.platform.util.encryption.MD5;
import love.qx.platform.vo.Result;
import love.qx.platform.vo.ResultCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.Style;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/user")
public class UserController {
    //用户信息服务层
    @Autowired
    UserService userService;


    //用户登录
    @PostMapping("/login")
    public Result login(@RequestBody Login login){
        String encryption = MD5.encryption(login.getPwd());
        if (encryption==null)return Result.failed(ResultCode.SYSTEMERROR,"MD5加密出错");
        login.setPwd(encryption);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(login.getEmail(),login.getPwd());
        try
        {
            subject.login(token);
            return Result.success(ResultCode.LOGINSUCCESS);
        } catch (UnknownAccountException uae) {
            return Result.failed(ResultCode.USERNOEXST);
        } catch (IncorrectCredentialsException ice) {
            return Result.failed(ResultCode.USERPWDNOCORRECT);
        } catch (LockedAccountException lae) {
            return Result.failed(ResultCode.USERLOCK);
        }
    }

    //更新用户表user信息
    @PostMapping("/update")
    public Result updateUser(@RequestBody User user){
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        boolean update = userService.update(user, userQueryWrapper.eq("user_email", user.getUserEmail()));

        if(update)return Result.success(ResultCode.UPDATEDATASUCCESS);
        else return Result.failed(ResultCode.UPDATEDATAFAILURE);
    }

    //更新用户头像
    @PostMapping("/updateHeadPortraitByEmail")
    public Result updateHeadPortraitByEmail(@RequestParam("email") String email,@RequestParam("headPortrait") String headPortrait){
        if (email==null||email==""||headPortrait==null||headPortrait=="")return Result.failed(ResultCode.PARAMETERMISTAKE);
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        User user=new User();
        user.setHeadPortrait(headPortrait);
        boolean update = userService.update(user,userQueryWrapper.eq("user_email",email));
        if(update)return Result.success(ResultCode.UPDATEDATASUCCESS);
        else return Result.failed(ResultCode.UPDATEDATAFAILURE);
    }

    //根据用户邮箱查询一个用户信息
    @GetMapping("/findOne")
    public Result findOne(@RequestParam("userEmail") String userEmail){
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        User user = userService.getOne(userQueryWrapper.eq("user_email",userEmail));
        if (user!=null)return Result.success(ResultCode.GETDATASUCCESS,user);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }

    //根据用户姓名查询用户信息
    @GetMapping("/findByName")
    public Result<List<User>> findByName(@RequestParam("userName") String userName){
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        List<User> userList = userService.list(userQueryWrapper.like("username", userName));
        if (userList.size()>0) return Result.success(ResultCode.GETDATASUCCESS,userList);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }

    //根据用户性别查询用户信息
    @GetMapping("/findBySex")
    public Result<List<User>> findBySex(@RequestParam("userSex") String userSex){
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        List<User> userList = userService.list(userQueryWrapper.eq("usersex",userSex));
        if (userList!=null) return Result.success(ResultCode.GETDATASUCCESS,userList);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }

    //根据用户固定年龄查询用户信息
    @GetMapping("/findByAge")
    public Result<List<User>> findByAge(@RequestParam("userAge") Integer userAge){
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        List<User> userList = userService.list(userQueryWrapper.eq("userage",userAge));
        if (userList!=null) return Result.success(ResultCode.GETDATASUCCESS,userList);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }

    //根据用户年龄范围查询用户信息
    // 参数解析：
    // 参数一：
    // 为0时，查询小于age1的值
    // 为1时，查询大于age1的值
    // 为2时，查询age1和age2之间的值
    // 为3时，查询age1和age2之间除外的值
    @SneakyThrows
    @GetMapping("/findBySexScope")
    public Result<List<User>> findBySexScope (
            @RequestParam(value = "judge") Integer judge,
            @RequestParam(value = "age1") Integer age1,
            @RequestParam(value = "age2",required = false) Integer age2)
    {
        List<User> userList;
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        switch (judge){
            case 0:
                userList=userService.list(userQueryWrapper.le("userage",age1));
                break;
            case 1:
                userList=userService.list(userQueryWrapper.ge("userage",age1));
                break;
            case 2:
                if (age2==null)throw new MissingServletRequestParameterException("age2","int");
                userList=userService.list(userQueryWrapper.ge("userage",age1).and(w->w.le("userage",age2)));
                break;
            case 3:
                if (age2==null)throw new MissingServletRequestParameterException("age2","int");
                userList=userService.list(userQueryWrapper.le("userage",age1).or(w->w.ge("userage",age2)));
                break;
            default:
                throw new MissingServletRequestParameterException("judge","int");
        }

        if (userList!=null) return Result.success(ResultCode.GETDATASUCCESS,userList);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }

    //根据邮箱类型查询用户信息
    @GetMapping("/findByEmail")
    public Result<List<User>> findByEmail(@RequestParam("emailType") String emailType)
    {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        List<User> list = userService.list(wrapper.like("user_email", emailType));
        return Result.success(ResultCode.GETDATASUCCESS,list);
    }

    //根据注册时间查询用户信息
    @SneakyThrows
    @GetMapping("/findByRegisterTime")
    public Result<List<User>> findByRegisterTime(
            @RequestParam("year") String year,
            @RequestParam(value = "month",required = false) String month,
            @RequestParam(value = "day",required = false) String day)
    {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        if (month==null){
            if (day!=null)throw new MissingServletRequestParameterException("month","String");
            List<User> list = userService.list(wrapper.like("register_time", year));
            return Result.success(ResultCode.GETDATASUCCESS,list);
        }
        String date;
        if (day==null){
            date=year+"-"+month;
            List<User> list = userService.list(wrapper.like("register_time", date));
            return Result.success(ResultCode.GETDATASUCCESS,list);
        }
        date=year+"-"+month+"-"+day;
        List<User> list = userService.list(wrapper.like("register_time", date));
        return Result.success(ResultCode.GETDATASUCCESS,list);
    }

    //查询所有用户
    @GetMapping("/findAll")
    public Result<List<User>> findAll(){
        List<User> list = userService.list();
        return Result.success(ResultCode.GETDATASUCCESS,list);
    }


    @Autowired
    UserPController userPController;
    //根据邮箱删除一条用户记录
    @PostMapping("/delByEmail")
    public Result delByEmail(@RequestParam("userEmail") String Email)
    {
        boolean b = userService.removeById(Email);
        if (b)return Result.success(ResultCode.DELETEDATASUCCESS);
        return Result.failed(ResultCode.DELETEDATAFAILURE);
    }

    //根据邮箱数组删除一组用户记录
    @PostMapping("/delByEmailList")
    public Result delByEmailList(@RequestBody List<String> emailList)
            throws MissingServletRequestParameterException
    {
        if (emailList.size()<=0)throw new MissingServletRequestParameterException("emailList","list");
        boolean b = userService.removeByIds(emailList);
        if (b)return Result.success(ResultCode.DELETEDATASUCCESS);
        return Result.failed(ResultCode.DELETEDATAFAILURE);
    }

    @Autowired
    UserController userController;

    //添加用户
    @PostMapping("/addUser")
    public Result addUser(@Valid @RequestBody RegisterUser registerUser){
        User user=new User();
        user.setUserEmail(registerUser.getUserEmail());
        user.setUsername(registerUser.getUsername());
        user.setUsersex(registerUser.getUsersex());
        user.setUserage(registerUser.getUserage());
        user.setRegisterTime(registerUser.getRegisterTime());
        user.setHeadPortrait("http://loveqx.top:8321/images/login.jpg");

        UserP userP=new UserP();
        userP.setUserEmail(registerUser.getUserEmail());
        userP.setPwd(registerUser.getPwd());

        boolean users = userService.save(user);
        if (!users)return Result.failed(ResultCode.ADDDATASUCCESS);
        boolean save = userPController.addUserP(userP);
        if (save)return Result.success(ResultCode.ADDDATASUCCESS);
        userService.removeById(user.getUserEmail());
        return Result.failed(ResultCode.ADDDATAFAILURE);
    }




}



