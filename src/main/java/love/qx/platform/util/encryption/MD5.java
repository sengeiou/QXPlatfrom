package love.qx.platform.util.encryption;

import java.security.MessageDigest;

public class MD5 {

    //加盐
    public static String encryption(String password){
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            byte[] digest = md5.digest(password.getBytes());
            StringBuffer stringBuffer=new StringBuffer();
            for (byte b:digest){
                int number=b&0xff;
                String str=Integer.toHexString(number);
                if (str.length()==1){
                    stringBuffer.append("0");
                }
                stringBuffer.append(str);
            }
            return stringBuffer.toString();
        }catch (Exception e){
            return null;
        }

    }
}
