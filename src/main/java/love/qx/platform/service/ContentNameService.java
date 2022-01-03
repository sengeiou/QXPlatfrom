package love.qx.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import love.qx.platform.entity.ContentName;
import org.apache.ibatis.annotations.Param;

public interface ContentNameService extends IService<ContentName> {
    public int updateIsOnLineByContentId(@Param("isOnLine") Integer isOnLine, @Param("contentId") Integer contentId);
}
