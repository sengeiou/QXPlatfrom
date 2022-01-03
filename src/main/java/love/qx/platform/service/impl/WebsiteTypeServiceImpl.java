package love.qx.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import love.qx.platform.dao.IWebsiteType;
import love.qx.platform.entity.WebsiteType;
import love.qx.platform.service.WebsiteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebsiteTypeServiceImpl extends ServiceImpl<IWebsiteType, WebsiteType> implements WebsiteTypeService {

    @Autowired
    IWebsiteType websiteType;

    @Override
    public int updateIsOnLineByTypeId(Integer isOnLine, Integer typeId) {
        return websiteType.updateIsOnLineByTypeId(isOnLine,typeId);
    }

    @Override
    public int updateTypeNameByTypeId(String typeName, Integer typeId) {
        return websiteType.updateTypeNameByTypeId(typeName,typeId);
    }
}
