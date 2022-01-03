package love.qx.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import love.qx.platform.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IPermission extends BaseMapper<Permission> {
}
