package love.qx.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import love.qx.platform.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUser extends BaseMapper<User> {
}
