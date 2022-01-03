package love.qx.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 系统通知实体
 */
@Data
public class Inform {
    @TableId(type = IdType.AUTO)
    private int informId;
    private String role;
    private String information;
    private String time;
}
