package xyz.frt.basesdk2.common;

import xyz.frt.basesdk2.entity.BaseEntity;

import java.util.List;

public class Result {

    private Integer status;
    private String msg;
    private Object data;

    protected Result() {

    }

    protected Result(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    protected  Result(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    protected Result(Integer status) {
        this.status = status;
    }

    public Object singleValue() {
        if (data instanceof List<?>) {
            if (((List) data).size() > 0) {
                return ((List) data).get(0);
            } else {
                return null;
            }
        } else {
            return data;
        }
    }

    public List<?> listValue() {
        if (data instanceof List<?>) {
            return (List<?>) data;
        } else {
            return null;
        }
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result response(Integer status, String msg, Object data) {
        return new Result(status, msg, data);
    }

    public static Result response(Integer status, String msg) {
        return new Result(status, msg);
    }

    public static Result response(Integer status) {
        return new Result(status);
    }
}
