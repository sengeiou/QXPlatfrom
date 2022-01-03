package love.qx.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import love.qx.platform.entity.Inform;
import love.qx.platform.service.InformService;
import love.qx.platform.vo.Result;
import love.qx.platform.vo.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
系统通知表
 */
@RestController
@RequestMapping("/inform")
public class InformController {

    @Autowired
    InformService informService;

    //添加
    //
    //添加一条通知
    @PostMapping("/addInform")
    public Result addInform(@RequestBody Inform inform){
        if (inform.getInformation()==null || inform.getRole()==null||inform.getTime()==null)
            return Result.failed(ResultCode.PARAMETERMISTAKE);
        boolean save = informService.save(inform);
        if (save)return Result.success(ResultCode.ADDDATASUCCESS);
        return Result.failed(ResultCode.ADDDATAFAILURE);
    }
    //删除
    //
    //根据通知id删除一条数据
    @PostMapping("/delById")
    public Result delById(@RequestParam("informId") Integer id){
        if (id==0)return Result.failed(ResultCode.PARAMETERMISTAKE);
        boolean b = informService.removeById(id);
        if (b)return Result.success(ResultCode.DELETEDATASUCCESS);
        return Result.failed(ResultCode.DELETEDATAFAILURE);
    }
    //根据发布时间删除一组数据
    @PostMapping("/delByTime")
    public Result delByTime(@RequestParam("time") String time){
        if (time==null)return Result.failed(ResultCode.PARAMETERMISTAKE);
        QueryWrapper<Inform> wrapper=new QueryWrapper<>();
        boolean b = informService.remove(wrapper.like("time",time));
        if (b)return Result.success(ResultCode.DELETEDATASUCCESS);
        return Result.failed(ResultCode.DELETEDATAFAILURE);
    }
    //根据发布者删除一组数据
    @PostMapping("/delByRole")
    public Result delByPromulgator(@RequestParam("role") String role){
        if (role==null)return Result.failed(ResultCode.PARAMETERMISTAKE);
        QueryWrapper<Inform> wrapper=new QueryWrapper<>();
        boolean b = informService.remove(wrapper.eq("role",role));
        if (b)return Result.success(ResultCode.DELETEDATASUCCESS);
        return Result.failed(ResultCode.DELETEDATAFAILURE);
    }
    //查询
    //
    //查询所有通知
    @GetMapping("findAll")
    public Result findAll(){
        List<Inform> list = informService.list();
        if (list!=null)return Result.success(ResultCode.GETDATASUCCESS,list);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //根据通知id查询一条数据
    @GetMapping("/findByInformId")
    public Result findById(@RequestParam("informId") int informId){
        if (informId==0)return Result.failed(ResultCode.PARAMETERMISTAKE);
        Inform byId = informService.getById(informId);
        if (byId!=null)return Result.success(ResultCode.GETDATASUCCESS,byId);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //根据发布时间查询一组数据
    @GetMapping("/findByTime")
    public Result findByTime(@RequestParam("time") String time){
        if (time==null)return Result.failed(ResultCode.PARAMETERMISTAKE);
        QueryWrapper<Inform> wrapper=new QueryWrapper<>();
        List<Inform> time1 = informService.list(wrapper.like("time", time));
        if (time1!=null)return Result.success(ResultCode.GETDATASUCCESS,time1);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //根据发布者查询一组数据
    @GetMapping("/findByRole")
    public Result findByRole(@RequestParam("role") String role){
        if (role==null)return Result.failed(ResultCode.PARAMETERMISTAKE);
        QueryWrapper<Inform> wrapper=new QueryWrapper<>();
        List<Inform> role1 = informService.list(wrapper.eq("role", role));
        if (role1!=null)return Result.success(ResultCode.GETDATASUCCESS,role1);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //根据发布的内容查询数据
    @GetMapping("/findByInformation")
    public Result findByInformation(@RequestParam("information") String information){
        if (information==null)return Result.failed(ResultCode.PARAMETERMISTAKE);
        QueryWrapper<Inform> wrapper=new QueryWrapper<>();
        List<Inform> information1 = informService.list(wrapper.like("information", information));
        if (information1!=null)return Result.success(ResultCode.GETDATASUCCESS,information1);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
}
