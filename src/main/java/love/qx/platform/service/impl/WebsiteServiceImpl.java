package love.qx.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import love.qx.platform.dao.IWebsite;
import love.qx.platform.entity.Website;
import love.qx.platform.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebsiteServiceImpl extends ServiceImpl<IWebsite, Website> implements WebsiteService {
    @Autowired
    IWebsite website;

    @Override
    public int updateIsOnLineById(Integer websiteId, Integer isOnLine) {
        return website.updateIsOnLineById(websiteId,isOnLine);
    }

    @Override
    public List<Website> findOnLineByTypeId(Integer typeId) {
        return website.findOnLineByTypeId(typeId);
    }
}
