package love.qx.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import love.qx.platform.entity.UserP;
import org.apache.ibatis.annotations.Param;

public interface UserPService extends IService<UserP> {
    public int UpdatePwd(@Param("pwd") String pwd, @Param("userEmail") String userEmail);
}
