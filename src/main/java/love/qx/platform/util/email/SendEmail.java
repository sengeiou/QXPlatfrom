package love.qx.platform.util.email;

import org.springframework.transaction.annotation.Transactional;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
public class SendEmail {
    //邮箱授权码
    private static final String authorizationCode="bvpsffhyznctfbjc";

    public static void sendEmail(String yzm,String title,String email) throws Exception {
        //创建一个Properties对象
        Properties properties=new Properties();
        //设置主机
        properties.setProperty("mail.host","smtp.qq.com");
        //设置传输协议
        properties.setProperty("mail.transport.protocol","smtp");
        //设置允许邮箱授权认证
        properties.setProperty("mail.smtp.auth","true");
        //邮件授权的认证（认证器）
        //创建认证器对象
        Auth auth=new Auth();
        //获取Session的会话对象
        Session session=Session.getDefaultInstance(properties,auth);
        //获取连接
        Transport ts=session.getTransport();
        //连接服务器
        ts.connect("smtp.qq.com","2103653582@qq.com",authorizationCode);
        //创建邮件对象
        MimeMessage mimeMessage=new MimeMessage(session);
        //设置发件人对象
        mimeMessage.setFrom(new InternetAddress("2103653582@qq.com"));
        //设置收件人地址
        mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(email));
        //设置邮件标题
        mimeMessage.setSubject(title);
        //设置邮件内容
        mimeMessage.setContent(yzm,"text/html;charset=utf-8");
        //发送邮件
        ts.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
        //关闭连接
        ts.close();

    }
}
