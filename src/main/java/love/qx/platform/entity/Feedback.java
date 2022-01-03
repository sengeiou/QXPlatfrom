package love.qx.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 网站反馈实体
 */
@Data
public class Feedback {
    @TableId(type = IdType.AUTO)
    private int feedbackId;
    private int websiteId;
    private String userEmail;
    private String time;
    private String result;
}
