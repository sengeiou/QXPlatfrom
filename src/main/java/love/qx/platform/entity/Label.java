package love.qx.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Label {
    @TableId(type = IdType.AUTO)
    private String labelId;
    private String labelName;
    private String labelCode;
}
