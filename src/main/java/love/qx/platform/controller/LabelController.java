package love.qx.platform.controller;

import love.qx.platform.entity.Label;
import love.qx.platform.service.LabelService;
import love.qx.platform.vo.Result;
import love.qx.platform.vo.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/label")
public class LabelController {
    @Autowired
    LabelService labelService;

    @GetMapping("/findAll")
    public Result<List<Label>> findAll(){
        List<Label> list = labelService.list();
        return Result.success(ResultCode.GETDATASUCCESS,list);
    }
}
