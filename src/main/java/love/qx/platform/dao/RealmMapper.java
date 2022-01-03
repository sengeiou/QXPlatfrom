package love.qx.platform.dao;

import love.qx.platform.entity.Permission;
import love.qx.platform.entity.Role;
import love.qx.platform.entity.UserP;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RealmMapper {

//    @Select("select * from userp where user_email=#{user_email}")
//    public UserP getUserPwdByEmail(String user_email);

    @Select("SELECT role.* FROM " +
            "role INNER JOIN user_role ON role.role_id = user_role.role_id " +
            "WHERE user_role.user_email=#{user_email}")
    public List<Role> getRolesByUserId(String user_email);

    @Select("SELECT permission.* FROM role_permission " +
            "INNER JOIN permission ON role_permission.permission_id = permission.permission_id " +
            "INNER JOIN user_role ON user_role.role_id = role_permission.role_id " +
            "WHERE user_role.user_email=#{user_email}")
    public List<Permission> getPermissionsByUserId(String user_email);
}
