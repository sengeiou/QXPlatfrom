package love.qx.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 用户上传的链接实体
 */
@Data
public class UserUrl {
    @TableId(type = IdType.AUTO)
    private int userUrlId;
    private String websiteUrl;
    private String userEmail;
    private String uptime;
    private String websiteName;
    private String websiteDescription;
    private int state;
    private String cause;
}
