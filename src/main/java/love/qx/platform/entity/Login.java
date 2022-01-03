package love.qx.platform.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class Login {
    @Email(message = "邮箱格式错误")
    @NotNull(message = "邮箱不能为空")
    private String email;
    @NotNull(message = "密码不能为空")
    @Size(max = 20,min = 1,message = "密码长度必须在1~20")
    private String pwd;
}
