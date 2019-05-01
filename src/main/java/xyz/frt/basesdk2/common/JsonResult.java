package xyz.frt.basesdk2.common;

/**
 * @author phw
 * @date 04-08-2018
 * @description 所有请求返回的响应类
 */
public class JsonResult extends Result {


    private JsonResult(Integer status, String msg, Object data) {
        super(status, msg, data);
    }

    private JsonResult(Integer status, String msg) {
        super(status, msg);
    }

    private JsonResult(Integer status) {
        super(status);
    }

    /**
     * 通过Result创建响应结果
     * @param result res
     * @return 结果
     */
    public static JsonResult response(Result result) {
        return new JsonResult(result.getStatus(), result.getMsg(), result.getData());
    }

    /**
     * 成功提示
     * @param msg 提示消息
     * @return json result
     */
    public static JsonResult success(String msg) {
        return new JsonResult(AppConst.STATUS_SUCCESS, msg);
    }

    /**
     * 附带数据的成功提示
     * @param msg 提示消息
     * @param data 返回的数据
     * @return json result
     */
    public static JsonResult success(String msg, Object data) {
        return new JsonResult(AppConst.STATUS_SUCCESS, msg, data);
    }

    /**
     * 成功提示
     * @return json result
     */
    public static JsonResult success() {
        return new JsonResult(AppConst.STATUS_SUCCESS, "request ok");
    }

    /**
     * 成功提示
     * @param data 变量
     * @return json result
     */
    public static JsonResult success(Object data) {
        return new JsonResult(AppConst.STATUS_SUCCESS, "request ok", data);
    }

    /**
     * 错误提示
     * @param msg 提示消息
     * @return json result
     */
    public static JsonResult error(String msg) {
        return new JsonResult(AppConst.STATUS_ERROR, msg);
    }

    /**
     * 错误提示
     * @param status 错误码
     * @param msg 提示消息
     * @return json result
     */
    public static JsonResult error(Integer status, String msg) {
        return new JsonResult(status, msg);
    }

    /**
     * 错误提示
     * @return 错误码
     */
    public static JsonResult error() {
        return new JsonResult(AppConst.STATUS_ERROR, "request fail");
    }

    /**
     * 警告信息
     * @param msg 提示消息
     * @return json result
     */
    public static JsonResult warn(String msg) {
        return new JsonResult(AppConst.STATUS_WARN, msg);
    }

}
