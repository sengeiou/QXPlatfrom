package love.qx.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 同一类型分类
 */
@Data
public class ContentName {
    @TableId(type = IdType.AUTO)
    private int contentId;
    private int typeId;
    private String contentName;
    private String addTime;
    private int isOnLine;
}
