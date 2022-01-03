package love.qx.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * 账户密码实体
 */
@Data
@TableName("userp")
public class UserP {
    @TableId(value = "up_id",type = IdType.AUTO)
    private int upId;
    private String userEmail;
    private String pwd;
}
