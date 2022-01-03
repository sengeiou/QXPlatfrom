package love.qx.platform.util.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Auth extends Authenticator {
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
//        return new PasswordAuthentication("获取认证的邮箱","16位授权码");
        return new PasswordAuthentication("2103653582@qq.com","bvpsffhyznctfbjc");
    }
}
