package love.qx.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import love.qx.platform.dao.IContentName;
import love.qx.platform.entity.ContentName;
import love.qx.platform.service.ContentNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentNameServiceImpl extends ServiceImpl<IContentName, ContentName> implements ContentNameService {
    @Autowired
    IContentName contentName;

    @Override
    public int updateIsOnLineByContentId(Integer isOnLine, Integer contentId) {
        return contentName.updateIsOnLineByContentId(isOnLine,contentId);
    }
}
