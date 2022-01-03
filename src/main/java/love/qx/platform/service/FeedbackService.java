package love.qx.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import love.qx.platform.entity.Feedback;
import org.apache.ibatis.annotations.Param;

public interface FeedbackService extends IService<Feedback> {
    public int updateResultByFeedbackId(@Param("feedbackId") Integer feedbackId, @Param("result") String result);
}
