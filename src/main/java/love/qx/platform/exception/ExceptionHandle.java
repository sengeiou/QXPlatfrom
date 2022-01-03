package love.qx.platform.exception;

import love.qx.platform.vo.Result;
import love.qx.platform.vo.ResultCode;
import org.apache.kafka.common.errors.ApiException;
import javax.validation.UnexpectedTypeException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.UnexpectedTypeException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandle {

    //调用接口未传递参数或参数错误时异常
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public Result apiException(MissingServletRequestParameterException e){
        return Result.failed(ResultCode.PARAMETERTYPEANOMALY);
    }

    //参数传递类型异常处理
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public Result typeException(MethodArgumentTypeMismatchException e){
        return Result.failed(ResultCode.PARAMETERTYPEANOMALY,e.getMessage());
    }


    //参数校验异常处理
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result parameterVerify(MethodArgumentNotValidException e){
        List<String> list=new ArrayList<>();        // 从异常对象中拿到ObjectError对象
        if (!e.getBindingResult().getAllErrors().isEmpty()){
            for(ObjectError error:e.getBindingResult().getAllErrors()){
                list.add(error.getDefaultMessage().toString());
            }
        }
         return Result.failed(ResultCode.PARAMETERCHECKOUTANOMALY,list);
    }

    //请求方式异常处理
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public Result methodException(HttpRequestMethodNotSupportedException e){
        return Result.failed(ResultCode.REQUESTWAYERROR);
    }
}
