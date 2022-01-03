package love.qx.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import love.qx.platform.entity.WebsiteType;
import love.qx.platform.service.WebsiteTypeService;
import love.qx.platform.vo.Result;
import love.qx.platform.vo.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLNonTransientException;
import java.util.List;

/*
网站类型表
 */
@RestController
@RequestMapping("/websiteType")
public class WebsiteTypeController {

    @Autowired
    WebsiteTypeService websiteTypeService;

    //添加一条类型信息
    @PostMapping("/addType")
    public Result addType(@RequestBody WebsiteType websiteType){
        if (websiteType.getTypeName()==null||websiteType.getTypeName()=="")return Result.failed("001","typeName为空/null");
        if (websiteType.getAddTime()==null||websiteType.getAddTime()=="")return Result.failed("001","addTime为空/null");
        if (websiteType.getTypeIcon()==null||websiteType.getTypeIcon()=="")return Result.failed("001","typeIcon为空/null");

        websiteType.setIsOnLine(2);
        boolean save = websiteTypeService.save(websiteType);
        if (save)return Result.success(ResultCode.ADDDATASUCCESS);
        return Result.failed(ResultCode.ADDDATAFAILURE);
    }


    //查询所有类型
    @GetMapping("/findAll")
    public Result<List<WebsiteType>> findAll(){
        List<WebsiteType> list = websiteTypeService.list();
        if (list!=null)return Result.success(ResultCode.GETDATASUCCESS,list);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }

    //查询所有已上线的信息
    @GetMapping("/findAllByOnLine")
    public Result findAllByOnLine(){
        QueryWrapper<WebsiteType> wrapper=new QueryWrapper<>();
        List<WebsiteType> is_on_line = websiteTypeService.list(wrapper.eq("is_on_line", 2));
        if (is_on_line!=null)return Result.success(ResultCode.GETDATASUCCESS,is_on_line);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }

    //根据id查询类型
    @GetMapping("/findById")
    public Result<WebsiteType> findById(@RequestParam("typeId") Integer typeId){
        WebsiteType byId = websiteTypeService.getById(typeId);
        if(byId!=null)return Result.success(ResultCode.GETDATASUCCESS,byId);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //根据类型模糊查询一组数据
    @GetMapping("/findByType")
    public Result<List<WebsiteType>> findByType(@RequestParam("type") String type){
        QueryWrapper<WebsiteType> wrapper=new QueryWrapper<>();
        List<WebsiteType> type_name = websiteTypeService.list(wrapper.like("type_name", type));
        if (type_name!=null)return Result.success(ResultCode.GETDATASUCCESS,type_name);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //根据是否上线查询一组数据
    @GetMapping("/findByIsOnLine")
    public Result<List<WebsiteType>> findByIsOnLine(@RequestParam("isOnLine") Integer isOnLine){
        QueryWrapper<WebsiteType> wrapper=new QueryWrapper<>();
        List<WebsiteType> is_on_line = websiteTypeService.list(wrapper.eq("is_on_line", isOnLine));
        if (is_on_line!=null)return Result.success(ResultCode.GETDATASUCCESS,is_on_line);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //根据日期查询一组数据
    @GetMapping("/findByDate")
    public Result<List<WebsiteType>> findByDate(@RequestParam("date") String date){
        if (date==null||date=="")return Result.failed(ResultCode.PARAMETERMISTAKE);
        QueryWrapper<WebsiteType> wrapper=new QueryWrapper<>();
        List<WebsiteType> add_time = websiteTypeService.list(wrapper.like("add_time", date));
        return Result.success(ResultCode.GETDATASUCCESS,add_time);
    }
    //根据类型id修改是否上线该类型
    @PostMapping("/updateIsOnLineByTypeId")
    public Result updateIsOnLineByTypeId(@RequestBody WebsiteType websiteType){
        if (websiteType.getTypeId()==0||websiteType.getIsOnLine()==0)
            return Result.failed(ResultCode.PARAMETERMISTAKE);
        int type_id = websiteTypeService.updateIsOnLineByTypeId(websiteType.getIsOnLine(),websiteType.getTypeId());
        if (type_id!=0)return Result.success(ResultCode.UPDATEDATASUCCESS);
        return Result.failed(ResultCode.UPDATEDATAFAILURE);
    }
    //根据类型id修改类型名称
    @PostMapping("/updateType")
    public Result updateTypeNameByTypeId(@RequestBody WebsiteType websiteType){
        if (websiteType.getTypeIcon()==""||websiteType.getTypeIcon()==null||
        websiteType.getTypeName()==""||websiteType.getTypeName()==null||
        websiteType.getAddTime()==""||websiteType.getAddTime()==null||
        websiteType.getIsOnLine()==0||websiteType.getTypeId()==0)
            return Result.failed(ResultCode.UPDATEDATAFAILURE);
        boolean i = websiteTypeService.updateById(websiteType);
        if(i)return Result.success(ResultCode.UPDATEDATASUCCESS);
        return Result.failed(ResultCode.UPDATEDATAFAILURE);
    }
    //根据id删除一条类型信息
    @PostMapping("/delByTypeId")
    public Result delByTypeId(@RequestBody WebsiteType websiteType){
        if (websiteType.getTypeId()==0)return Result.failed(ResultCode.PARAMETERMISTAKE);
        try {
            boolean b = websiteTypeService.removeById(websiteType.getTypeId());
            if (b)return Result.success(ResultCode.DELETEDATASUCCESS);
            return Result.failed(ResultCode.DELETEDATAFAILURE);
        }catch (Exception e){
            return Result.failed(ResultCode.HAVECHILDDATA);
        }

    }
    //根据id数组删除一组类型信息
    @PostMapping("/delByTypeIdList")
    public Result delByTypeIdList(@RequestBody List<Integer> typeId){
        boolean b = websiteTypeService.removeByIds(typeId);
        if (b)return Result.success(ResultCode.DELETEDATASUCCESS);
        return Result.failed(ResultCode.DELETEDATAFAILURE);
    }
}
