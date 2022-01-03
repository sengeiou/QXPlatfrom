package love.qx.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import love.qx.platform.entity.UserP;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface IUserp extends BaseMapper<UserP> {

    @Update("update userp set pwd=#{pwd} where user_email=#{userEmail}")
    public int UpdatePwd(@Param("pwd") String pwd,@Param("userEmail") String userEmail);
}
