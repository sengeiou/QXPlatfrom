package love.qx.platform.vo;

//
public enum ResultCode {
    GETDATASUCCESS("200","获取数据成功"),
    UPDATEDATASUCCESS("300","修改数据成功"),
    DELETEDATASUCCESS("600","删除数据成功"),
    ADDDATASUCCESS("100","添加数据成功"),
    NOACCESSPERMISSION("403","没有访问权限"),
    NOLOGIN("20","未登录"),
    LOGINSUCCESS("21","登录成功"),
    USERNOEXST("30","用户不存在"),
    GETDATAFAILURE("201","获取数据失败"),
    UPDATEDATAFAILURE("301","修改数据失败"),
    DELETEDATAFAILURE("601","删除数据失败"),
    ADDDATAFAILURE("101","添加数据失败"),
    USERPWDNOCORRECT("31","用户密码不正确"),
    USERLOCK("32","用户被锁定"),
    PARAMETERMISTAKE("001","参数错误"),
    PARAMETERCHECKOUTANOMALY("999","参数校验异常"),
    REQUESTWAYERROR("405","请求方式错误"),
    EMAILSENDSUCCESS("334","邮件发送成功"),
    EMAILSENDFAILURE("333","邮件发送失败"),
    SYSTEMERROR("444","系统错误，请联系管理员"),
    HAVECHILDDATA("777","该类型还有子约束，不能删除"),
    PARAMETERTYPEANOMALY("400","参数传递类型错误");


    private final String code;
    private final String msg;

    private ResultCode(String code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public String getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }

}
