package love.qx.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import love.qx.platform.entity.Feedback;
import love.qx.platform.entity.Website;
import love.qx.platform.service.FeedbackService;
import love.qx.platform.vo.Result;
import love.qx.platform.vo.ResultCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
网站反馈信息表
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;
    //添加
    //
    //添加一条反馈信息
    @PostMapping("/addFeedback")
    public Result addFeedback(@RequestBody Feedback feedback){
        if (feedback.getFeedbackId()!=0||feedback.getWebsiteId()==0||
                feedback.getResult()==null||feedback.getTime()==null||
                feedback.getUserEmail()==null)
            return Result.failed(ResultCode.PARAMETERMISTAKE);
        boolean save = feedbackService.save(feedback);
        if (save)return Result.success(ResultCode.ADDDATASUCCESS);
        return Result.failed(ResultCode.ADDDATAFAILURE);
    }
    //删除
    //
    //根据反馈id删除一条数据
    @PostMapping("/delByFeedbackId")
    public Result delByFeedbackId(@RequestParam("feedbackId") Integer feedbackId){
        if (feedbackId==0)return Result.failed(ResultCode.PARAMETERMISTAKE);
        boolean b = feedbackService.removeById(feedbackId);
        if (b)return Result.success(ResultCode.DELETEDATASUCCESS);
        return Result.failed(ResultCode.DELETEDATAFAILURE);
    }
    //修改
    //
    //根据反馈id修改处理结果
    @PostMapping("/updateResultByFeedbackId")
    public Result updateResultByFeedbackId(@RequestParam("result") String result,@RequestParam("feedbackId") Integer feedbackId){
        if (result==null||feedbackId==0)return Result.failed(ResultCode.PARAMETERMISTAKE);
        int i = feedbackService.updateResultByFeedbackId(feedbackId, result);
        if (i!=0)return Result.success(ResultCode.DELETEDATASUCCESS);
        return Result.failed(ResultCode.DELETEDATAFAILURE);
    }
    //查询
    //
    //查询所有反馈信息
    @GetMapping("/findAll")
    public Result findAll(){
        List<Feedback> list = feedbackService.list();
        if (list!=null)return Result.success(ResultCode.GETDATASUCCESS,list);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //根据反馈id查询一条数据
    @GetMapping("/findByFeedbackId")
    public Result<Feedback> findByFeedbackId(@RequestParam("feedbackId") Integer feedbackId){
        if (feedbackId==0)return Result.failed(ResultCode.PARAMETERMISTAKE);
        Feedback feedback = feedbackService.getById(feedbackId);
        if (feedback!=null)return Result.success(ResultCode.GETDATASUCCESS,feedback);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //根据网站id/用户邮箱模糊查询数据
    @GetMapping("/findByWebsiteIdOrEmail")
    public Result<List<Feedback>> findByWebsiteIdOrEmail(@RequestParam("info") String info){
        if (info==null)return Result.failed(ResultCode.PARAMETERMISTAKE);
        QueryWrapper<Feedback> wrapper=new QueryWrapper<>();
        List<Feedback> feedback = feedbackService.list(wrapper.like("website_id", info).or(w->w.like("user_email",info)));
        if (feedback!=null)return Result.success(ResultCode.GETDATASUCCESS,feedback);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //根据反馈时间查询数据
    @GetMapping("/findByTime")
    public Result<List<Feedback>> findByTime(@RequestParam("time") String time){
        if (time==null)return Result.failed(ResultCode.PARAMETERMISTAKE);
        QueryWrapper<Feedback> wrapper=new QueryWrapper<>();
        List<Feedback> feedback = feedbackService.list(wrapper.eq("time", time));
        if (feedback!=null)return Result.success(ResultCode.GETDATASUCCESS,feedback);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //根据处理结果查询一组数据
    @GetMapping("/findByResult")
    public Result findByResult(@RequestParam("result") String result){
        if (result==null)return Result.failed(ResultCode.PARAMETERMISTAKE);
        QueryWrapper<Feedback> wrapper=new QueryWrapper<>();
        List<Feedback> result1 = feedbackService.list(wrapper.like("result", result));
        if (result1!=null)return Result.success(ResultCode.GETDATASUCCESS,result1);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
}
