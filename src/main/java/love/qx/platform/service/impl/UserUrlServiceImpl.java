package love.qx.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import love.qx.platform.dao.IContentName;
import love.qx.platform.dao.IUserUrl;
import love.qx.platform.entity.ContentName;
import love.qx.platform.entity.UserUrl;
import love.qx.platform.service.UserUrlService;
import org.springframework.stereotype.Service;

@Service
public class UserUrlServiceImpl extends ServiceImpl<IUserUrl, UserUrl> implements UserUrlService {
}
