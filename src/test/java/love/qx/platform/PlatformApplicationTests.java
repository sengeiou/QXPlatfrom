package love.qx.platform;

import lombok.Data;
import love.qx.platform.util.encryption.MD5;
import love.qx.platform.vo.ResultCode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class PlatformApplicationTests {

    @Test
    void contextLoads() {
        String encryption = MD5.encryption("123456");
        System.out.println(encryption.equals(MD5.encryption("12356")));
    }

}
