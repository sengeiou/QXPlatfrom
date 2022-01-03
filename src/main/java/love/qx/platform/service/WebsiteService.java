package love.qx.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import love.qx.platform.entity.Website;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WebsiteService extends IService<Website> {
    public int updateIsOnLineById(@Param("websiteId") Integer websiteId, @Param("isOnLine") Integer isOnLine);
    public List<Website> findOnLineByTypeId(@Param("typeId") Integer typeId);
}
