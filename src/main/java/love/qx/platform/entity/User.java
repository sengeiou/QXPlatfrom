package love.qx.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 用户信息实体
 */
@Data
public class User {
    private String userEmail;
    private String username;
    private String usersex;
    private Integer userage;
    private String registerTime;
    private String headPortrait;
}
