package love.qx.platform.vo;

import lombok.Data;

@Data
public class Result<T> {
    private String code;
    private String msg;
    private T data;
    public Result(){}
    //成功消息，不带数据
    public static Result success(ResultCode resultCode) {
        return new Result<>(resultCode.getCode(), resultCode.getMsg());
    }
    //自定义成功消息
    public static Result success(String code,String msg){
        return new Result(code,msg);
    }
    //成功消息，带数据
    public static <T> Result<T> success(ResultCode resultCode, T data) {
        return new Result<>(resultCode.getCode(), resultCode.getMsg(), data);
    }
    //失败消息，不带数据
    public static Result failed(ResultCode resultCode) {
        return new Result<>(resultCode.getCode(), resultCode.getMsg(),null);
    }
    //自定义失败消息内容
    public static Result failed(String code,String msg) {
        return new Result<>(code, msg);
    }
    //失败消息,带数据的
    public static <T> Result<T> failed(ResultCode resultCode,T data) {
        return new Result<>(resultCode.getCode(), resultCode.getMsg(),data);
    }
//    public static Result<Object> build(Integer code, String msg) {
//        return new Result<>(code, msg);
//    }
//    public static <T> Result<Object> build(Integer code, String msg, T data) {
//        return new Result<>(code, msg, data);
//    }
    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
