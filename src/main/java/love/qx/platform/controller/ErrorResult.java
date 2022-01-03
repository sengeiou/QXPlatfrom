package love.qx.platform.controller;

import love.qx.platform.vo.Result;
import love.qx.platform.vo.ResultCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Error")
public class ErrorResult {
    @GetMapping("/Forbidden")
    public Result<?> forbidden() {
        return Result.failed(ResultCode.NOACCESSPERMISSION);
    }

    @GetMapping("/Unauthorized")
    public Result<?> unauthenticated() {
        return Result.failed(ResultCode.NOLOGIN);
    }
}
