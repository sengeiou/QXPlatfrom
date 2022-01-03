package love.qx.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 网站详细信息
 */
@Data
public class Website {
    @TableId(type = IdType.AUTO)
    private int websiteId;
    private int contentId;
    private String websiteName;
    private String websiteUrl;
    private String introduce;
    private String addTime;
    private int isOnLine;
    private String websiteImage;
}
