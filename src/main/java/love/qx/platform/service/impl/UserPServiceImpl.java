package love.qx.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import love.qx.platform.dao.IUserp;
import love.qx.platform.entity.UserP;
import love.qx.platform.service.UserPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPServiceImpl extends ServiceImpl<IUserp, UserP> implements UserPService {

    @Autowired
    IUserp userp;
    @Override
    public int UpdatePwd(String pwd, String userEmail) {
        return userp.UpdatePwd(pwd,userEmail);
    }
}
