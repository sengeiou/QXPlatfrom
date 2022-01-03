package love.qx.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import love.qx.platform.entity.Permission;
import love.qx.platform.entity.Role;
import love.qx.platform.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IRole extends BaseMapper<Role> {

    //向用户和角色关联表中添加数据
    @Insert("insert into user_role(user_email,role_id) values(#{user.user_email},#{role.role_id})")
    public void insertUserRole(@Param("user") User user,@Param("role") Role role);

    //向角色和权限表中添加数据
    @Insert("insert into role_permission(role_id,permission_id) values(#{role.role_id},#{permission.permission_id})")
    public void insertRolePermission(@Param("role") Role role,@Param("permission") Permission permission);
}
