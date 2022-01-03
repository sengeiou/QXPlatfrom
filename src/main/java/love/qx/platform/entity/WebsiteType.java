package love.qx.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 网站主类型实体
 */
@Data
public class WebsiteType {
    @TableId(type = IdType.AUTO)
    private int typeId;
    private String typeName;
    private String addTime;
    private int isOnLine;
    private String typeIcon;
}
