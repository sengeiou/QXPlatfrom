package love.qx.platform.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//前段注册用户时传递过来的用户信息格式
@Data
@NoArgsConstructor
public class RegisterUser{
    @Email(message = "邮箱格式错误")
    @NotNull(message = "邮箱不能为空")
    private String userEmail;
    @NotNull(message = "用户名不能为空")
    @Size(min = 1,max = 10,message = "用户名长度必须在1~10")
    private String username;
    @NotNull(message = "性别不能为空")
    @Size(min = 1,max = 1,message = "性别可选值：男/女")
    private String usersex;
    @NotNull(message = "年龄不能为空")
    private Integer userage;
    @NotNull(message = "注册时间不能为空")
    private String registerTime;
    @NotNull(message = "密码不能为空")
    @Size(max = 20,min = 1,message = "密码长度必须在1~20")
    private String pwd;
}
