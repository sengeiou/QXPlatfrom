package love.qx.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import love.qx.platform.dao.IPermission;
import love.qx.platform.entity.Permission;
import love.qx.platform.service.PermissionService;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends ServiceImpl<IPermission, Permission> implements PermissionService {
}
