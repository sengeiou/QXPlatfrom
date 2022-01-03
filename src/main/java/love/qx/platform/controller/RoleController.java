package love.qx.platform.controller;

import com.baomidou.mybatisplus.extension.api.R;
import love.qx.platform.entity.Permission;
import love.qx.platform.entity.Role;
import love.qx.platform.service.RoleService;
import love.qx.platform.vo.Result;
import love.qx.platform.vo.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
角色表
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    //更新
    //
    //根据角色id修改信息
    @GetMapping("/updateById")
    public Result updateById(@RequestBody Role role){
        if (role.getRoleId()==0||role.getRole()==null||role.getDescription()==null)
            return Result.failed(ResultCode.PARAMETERMISTAKE);
        boolean b = roleService.updateById(role);
        if (b)return Result.success(ResultCode.UPDATEDATASUCCESS);
        return Result.failed(ResultCode.UPDATEDATAFAILURE);
    }
    //查询
    //
    //查询所有角色信息
    @GetMapping("/findAll")
    public Result findAll(){
        List<Role> list = roleService.list();
        if (list!=null)return Result.success(ResultCode.GETDATASUCCESS,list);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //根据角色id查询一条数据
    @GetMapping("/findById")
    public Result findById(@RequestParam("roleId") Integer roleId){
        if (roleId==0)return Result.failed(ResultCode.PARAMETERMISTAKE);
        Role byId = roleService.getById(roleId);
        if (byId!=null)return Result.success(ResultCode.GETDATASUCCESS,byId);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }

    //删除
    //
    //根据角色id删除一条数据
    @PostMapping("/delById")
    public Result delById(@RequestParam("roleId") Integer roleId){
        if (roleId==0)return Result.failed(ResultCode.PARAMETERMISTAKE);
        boolean b = roleService.removeById(roleId);
        if (b)return Result.success(ResultCode.DELETEDATASUCCESS);
        return Result.failed(ResultCode.DELETEDATAFAILURE);
    }
    //添加
    //
    //添加一个角色
    @PostMapping("/addRole")
    public Result addPermission(@RequestBody Role role){
        if (role.getRoleId()!=0||role.getRole()==null||role.getDescription()==null)
            return Result.failed(ResultCode.PARAMETERMISTAKE);
        boolean b = roleService.save(role);
        if (b)return Result.success(ResultCode.ADDDATASUCCESS);
        return Result.failed(ResultCode.ADDDATAFAILURE);
    }
}
