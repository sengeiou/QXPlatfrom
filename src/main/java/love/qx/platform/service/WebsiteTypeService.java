package love.qx.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import love.qx.platform.entity.WebsiteType;
import org.apache.ibatis.annotations.Param;

public interface WebsiteTypeService extends IService<WebsiteType> {
    public int updateIsOnLineByTypeId(@Param("isOnLine") Integer isOnLine, @Param("typeId") Integer typeId);
    public int updateTypeNameByTypeId(@Param("typeName") String typeName,@Param("typeId") Integer typeId);
}
