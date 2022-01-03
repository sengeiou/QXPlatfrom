package love.qx.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import love.qx.platform.dao.ILabel;
import love.qx.platform.entity.Label;
import love.qx.platform.service.LabelService;
import org.springframework.stereotype.Service;

@Service
public class LabelServiceImpl extends ServiceImpl<ILabel, Label> implements LabelService {
}
