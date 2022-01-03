package love.qx.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import love.qx.platform.dao.IInform;
import love.qx.platform.entity.Inform;
import love.qx.platform.service.InformService;
import org.springframework.stereotype.Service;

@Service
public class InformServiceImpl extends ServiceImpl<IInform, Inform> implements InformService {
}
