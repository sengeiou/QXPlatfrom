package love.qx.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import love.qx.platform.dao.IFeedback;
import love.qx.platform.entity.Feedback;
import love.qx.platform.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl extends ServiceImpl<IFeedback, Feedback> implements FeedbackService {
    @Autowired
    IFeedback feedback;

    @Override
    public int updateResultByFeedbackId(Integer feedbackId, String result) {
        return feedback.updateResultByFeedbackId(feedbackId,result);
    }
}
