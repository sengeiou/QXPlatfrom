package love.qx.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import love.qx.platform.entity.Feedback;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface IFeedback extends BaseMapper<Feedback> {
    //根据反馈id修改处理结果
    @Update("update feedback set result=#{result} where feedback_id=#{feedbackId}")
    public int updateResultByFeedbackId(@Param("feedbackId") Integer feedbackId,@Param("result") String result);
}
