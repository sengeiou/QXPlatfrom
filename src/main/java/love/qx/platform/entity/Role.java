package love.qx.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 角色信息
 */
@Data
public class Role {
    @TableId(type = IdType.AUTO)
    private int roleId;
    private String role;
    private String description;
}
