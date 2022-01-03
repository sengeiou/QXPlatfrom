package love.qx.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 权限信息实体
 */
@Data
public class Permission {
    @TableId(type = IdType.AUTO)
    private int permissionId;
    private String permission;
    private String description;
}
