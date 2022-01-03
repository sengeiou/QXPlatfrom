package love.qx.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import love.qx.platform.dao.IRole;
import love.qx.platform.entity.Role;
import love.qx.platform.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<IRole, Role> implements RoleService {
}
