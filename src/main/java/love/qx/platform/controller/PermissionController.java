package love.qx.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import love.qx.platform.entity.Permission;
import love.qx.platform.service.PermissionService;
import love.qx.platform.vo.Result;
import love.qx.platform.vo.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
权限表
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    PermissionService permissionService;
    //查询
    //
    //查询所有权限信息
    @GetMapping("/findAll")
    public Result findAll(){
        List<Permission> list = permissionService.list();
        if (list!=null)return Result.success(ResultCode.GETDATASUCCESS,list);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //根据权限id查询权限信息
    @GetMapping("/findById")
    public Result findById(@RequestParam("permissionId") Integer permissionId){
        if (permissionId==0)return Result.failed(ResultCode.PARAMETERMISTAKE);
        Permission byId = permissionService.getById(permissionId);
        if (byId!=null)return Result.success(ResultCode.GETDATASUCCESS,byId);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //根据权限名称或权限简介查询权限信息
    @GetMapping("/findByNameOrDescription")
    public Result findByNameOrDescription(@RequestParam("info") String info){
        if (info==null)return Result.failed(ResultCode.PARAMETERMISTAKE);
        QueryWrapper<Permission> wrapper=new QueryWrapper<>();
        List<Permission> list = permissionService.list(wrapper.like("permission", info).or(w -> w.like("description", info)));
        if (list!=null)return Result.success(ResultCode.GETDATASUCCESS,list);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //修改
    //
    //根据权限id修改权限信息
    @PostMapping("/updateById")
    public Result updateById(@RequestBody Permission permission){
        if (permission.getPermissionId()==0||permission.getPermission()==null||permission.getDescription()==null)
            return Result.failed(ResultCode.PARAMETERMISTAKE);
        boolean b = permissionService.updateById(permission);
        if (b)return Result.success(ResultCode.UPDATEDATASUCCESS);
        return Result.failed(ResultCode.UPDATEDATAFAILURE);
    }
    //删除
    //
    //根据权限id删除权限信息
    @PostMapping("/delById")
    public Result delById(@RequestParam("permissionId") Integer permissionId){
        if (permissionId==0)return Result.failed(ResultCode.PARAMETERMISTAKE);
        boolean b = permissionService.removeById(permissionId);
        if (b)return Result.success(ResultCode.DELETEDATASUCCESS);
        return Result.failed(ResultCode.DELETEDATAFAILURE);
    }
    //添加
    //
    //添加一条权限信息
    @PostMapping("/addPermission")
    public Result addPermission(@RequestBody Permission permission){
        if (permission.getPermissionId()!=0||permission.getPermission()==null||permission.getDescription()==null)
            return Result.failed(ResultCode.PARAMETERMISTAKE);
        boolean b = permissionService.save(permission);
        if (b)return Result.success(ResultCode.ADDDATASUCCESS);
        return Result.failed(ResultCode.ADDDATAFAILURE);
    }
}
