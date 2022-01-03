package love.qx.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import love.qx.platform.entity.ContentName;
import love.qx.platform.service.ContentNameService;
import love.qx.platform.vo.Result;
import love.qx.platform.vo.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
类型下的网站总名称表
 */
@RestController
@RequestMapping(value = "/contentName")
public class ContentNameController {

    @Autowired
    ContentNameService contentNameService;
    //添加一条数据
    @PostMapping("/addContentName")
    public Result addContentName(@RequestBody ContentName contentName){
        if (contentName.getTypeId()==0)return Result.failed("001","typeId不能为空");
        if(contentName.getContentName()==null||contentName.getContentName()=="")return Result.failed("001","contentName不能为空/null");
        if(contentName.getAddTime()==null||contentName.getAddTime()=="")return Result.failed("001","addTime不能为空/null");
        contentName.setIsOnLine(2);
        boolean save = contentNameService.save(contentName);
        if (save)return Result.success(ResultCode.ADDDATASUCCESS);
        return Result.failed(ResultCode.ADDDATAFAILURE);
    }
    //根据总名称id修改内容信息
    @PostMapping("/updateInfoByContentId")
    public Result updateInfoByContentId(@RequestBody ContentName contentName){
        if (contentName.getTypeId()==0)return Result.failed("001","typeId不能为空");
        if(contentName.getContentName()==null||contentName.getContentName()=="")return Result.failed("001","typeName不能为空/null");
        if(contentName.getAddTime()==null||contentName.getAddTime()=="")return Result.failed("001","addTime不能为空/null");
        QueryWrapper<ContentName> wrapper=new QueryWrapper<>();
        boolean content_id = contentNameService.update(contentName, wrapper.eq("content_id", contentName.getContentId()));
        if (content_id)return Result.success(ResultCode.UPDATEDATASUCCESS);
        return Result.failed(ResultCode.UPDATEDATAFAILURE);
    }
    //根据总名称id修改是否上线
    @PostMapping("/updateIsOnLineByContentId")
    public Result updateIsOnLineByContentId(@RequestBody ContentName contentName){
        if (contentName.getContentId()==0||contentName.getIsOnLine()==0)
            return Result.failed(ResultCode.PARAMETERMISTAKE);
        int i = contentNameService.updateIsOnLineByContentId(contentName.getIsOnLine(),contentName.getContentId());
        if(i!=0)return Result.success(ResultCode.UPDATEDATASUCCESS);
        return Result.failed(ResultCode.UPDATEDATAFAILURE);
    }
    //查询所有网站总名称信息
    @GetMapping("/findAll")
    public Result<List<ContentName>> findAll(){
        List<ContentName> list = contentNameService.list();
        if (list!=null)return Result.success(ResultCode.GETDATASUCCESS,list);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //根据内容名称id查询一条数据
    @GetMapping("/findOneByContentId")
    public Result<List<ContentName>> findOneByContentId(@RequestParam("contentId") Integer contentId){
        QueryWrapper<ContentName> wrapper=new QueryWrapper<>();
        List<ContentName> content_id = contentNameService.list(wrapper.eq("content_id", contentId));
        if(content_id.size()==1)return Result.success(ResultCode.GETDATASUCCESS,content_id);
        return Result.failed(ResultCode.GETDATAFAILURE);

    }
    //根据类型id查询一组数据
    @GetMapping("/findByTypeId")
    public Result<List<ContentName>> findByTypeId(@RequestParam("typeId") Integer typeId){
        if (typeId==0)return Result.failed(ResultCode.PARAMETERMISTAKE);
        QueryWrapper<ContentName> wrapper=new QueryWrapper<>();
        List<ContentName> type_id = contentNameService.list(wrapper.eq("type_id", typeId));
        if (type_id!=null)return Result.success(ResultCode.GETDATASUCCESS,type_id);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //根据类型id查询一组上线的数据
    @GetMapping("/findByTypeIdAndOnLine")
    public Result findByTypeIdAndOnLine(@RequestParam("typeId") Integer typeId){
        if (typeId==0)return Result.failed(ResultCode.PARAMETERMISTAKE);
        QueryWrapper<ContentName> wrapper=new QueryWrapper<>();
        List<ContentName> type_id = contentNameService.list(wrapper.eq("type_id", typeId).and(w->w.eq("is_on_line",2)));
        if (type_id!=null)return Result.success(ResultCode.GETDATASUCCESS,type_id);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //根据内容名称模糊查询一组数据
    @GetMapping("/findByContent")
    public Result<List<ContentName>> findByContent(@RequestParam("content") String content){
        QueryWrapper<ContentName> wrapper=new QueryWrapper<>();
        List<ContentName> content_name = contentNameService.list(wrapper.like("content_name", content));
        if(content_name!=null)return Result.success(ResultCode.GETDATASUCCESS,content_name);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //根据日期查询一组数据
    @GetMapping("/findByDate")
    public Result<List<ContentName>> findByDate(@RequestParam("date") String date){
        if (date==""||date==null)return Result.failed(ResultCode.PARAMETERMISTAKE);
        QueryWrapper<ContentName> wrapper=new QueryWrapper<>();
        List<ContentName> add_time = contentNameService.list(wrapper.like("add_time", date));
        return Result.success(ResultCode.GETDATASUCCESS,add_time);
    }
    //根据是否上线查询一组数据
    @GetMapping("/findByIsOnLine")
    public Result<List<ContentName>> findByIsOnLine(@RequestParam("isOnLine") Integer isOnLine){
        QueryWrapper<ContentName> wrapper=new QueryWrapper<>();
        List<ContentName> is_on_line = contentNameService.list(wrapper.eq("is_on_line", isOnLine));
        if (is_on_line!=null)return Result.success(ResultCode.GETDATASUCCESS,is_on_line);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //根据类型id删除一组数据
    @PostMapping("/delByTypeId")
    public Result delByTypeId(@RequestBody ContentName contentName){
        if (contentName.getTypeId()==0)return Result.failed(ResultCode.PARAMETERMISTAKE);
        QueryWrapper<ContentName> wrapper=new QueryWrapper<>();
        try {
            boolean b = contentNameService.remove(wrapper.eq("type_id",contentName.getTypeId()));
            if (b)return Result.success(ResultCode.DELETEDATASUCCESS);
            return Result.failed(ResultCode.DELETEDATAFAILURE);
        }catch (Exception e){
            return Result.failed(ResultCode.HAVECHILDDATA);
        }

    }
    //根据内容id删除一条数据
    @PostMapping("/delByContentId")
    public Result delByContentId(@RequestBody ContentName contentName){
        if (contentName.getContentId()==0)return Result.failed(ResultCode.PARAMETERMISTAKE);
        try {
        boolean b = contentNameService.removeById(contentName.getContentId());
        if (b)return Result.success(ResultCode.DELETEDATASUCCESS);
        return Result.failed(ResultCode.DELETEDATAFAILURE);
        }catch (Exception e){
            return Result.failed(ResultCode.HAVECHILDDATA);
        }
    }

}
