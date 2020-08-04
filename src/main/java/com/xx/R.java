package com.xx;


/**
 * @author yifanl
 * @Date 2020/4/15 17:38
 */

public class R<T> {

    public static final int ERROR_CODE_ILLEGAL_ARGUMENT = 101;

    private Boolean success;
    private Integer code;
    private String message;
    private T data;

    private R(){};

    public static R ok(){
        R r = new R<>();
        r.setCode(200);
        r.setSuccess(true);
        return r;
    }

    public static R error(int errorCode, String errorMessage){
        R r = new R<>();
        r.setCode(errorCode);
        r.setSuccess(false);
        r.setMessage(errorMessage);
        return r;
    }

    public R data(T data) {
        this.setData(data);
        return this;
    }


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
