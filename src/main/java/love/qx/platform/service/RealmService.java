package love.qx.platform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import love.qx.platform.dao.IUser;
import love.qx.platform.dao.IUserp;
import love.qx.platform.dao.RealmMapper;
import love.qx.platform.entity.Permission;
import love.qx.platform.entity.Role;
import love.qx.platform.entity.UserP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RealmService {
    @Autowired
    RealmMapper realmMapper;


    @Cacheable(value = "roleNames",key = "#email",condition = "#email!=null")
    public Set<String> getRoleNamesByUserId(String email)
    {
        List<Role> roles = realmMapper.getRolesByUserId(email);
        Set<String> roleNames=new HashSet<>();
        for(Role role : roles)
            roleNames.add(role.getRole());
        return roleNames;
    }
    @Cacheable(value = "permissions",key = "#email",condition = "#email!=null")
    public Set<String> getPermissionsByUserId(String email)
    {
        List<Permission> ps = realmMapper.getPermissionsByUserId(email);
        Set<String> permissions=new HashSet<>();
        for(Permission p : ps)
            permissions.add(p.getPermission());
        return permissions;
    }
}
