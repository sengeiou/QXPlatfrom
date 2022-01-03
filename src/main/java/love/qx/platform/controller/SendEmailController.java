package love.qx.platform.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import love.qx.platform.util.email.SendEmail;
import love.qx.platform.vo.Result;
import love.qx.platform.vo.ResultCode;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@RestController
@RequestMapping("/email")
public class SendEmailController {

    @PostMapping("/send")
    public Result send(@Valid @RequestBody SendEmailEntity sendEmailEntity){
        if (sendEmailEntity.getAuthCode()==""||sendEmailEntity.getTitle()==""||sendEmailEntity.getEmail()=="")
            return Result.failed(ResultCode.PARAMETERMISTAKE);
        FutureTask<Boolean> futureTask=new FutureTask<>(new SendEmails(sendEmailEntity.getAuthCode(),sendEmailEntity.getTitle(),sendEmailEntity.getEmail()));
        new Thread(futureTask).start();
        Boolean aBoolean;
        try {
            aBoolean = futureTask.get();
        } catch (Exception e) {
            return Result.failed(ResultCode.EMAILSENDFAILURE);
        }
        if (aBoolean)return Result.success(ResultCode.EMAILSENDSUCCESS);
        return Result.failed(ResultCode.EMAILSENDFAILURE);

    }
}
class SendEmails implements Callable<Boolean> {
    private String authCode;
    private String title;
    private String email;

    public SendEmails(String authCode,String title,String email){
        this.authCode=authCode;
        this.title=title;
        this.email=email;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Boolean call() throws Exception{
        if (authCode!=null||title!=null||email!=null){
            try {
                SendEmail.sendEmail(authCode,title,email);
            }catch (Exception e){
                return false;
            }
            return true;
        }
        return false;
    }
}
@Data
@NoArgsConstructor
class SendEmailEntity{
    @NotNull(message = "验证码不能为空")
    private String authCode;
    @NotNull(message = "标题不能为空")
    private String title;
    @Email(message = "邮箱格式错误")
    @NotNull(message = "邮箱不能为空")
    private String email;
}