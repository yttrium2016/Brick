package cn.com.yangzhenyu.brick.pojo;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/1/2
 * Time: 13:31
 */
public class ApiResult extends HashMap<String, Object> {

    /**
     * 成功
     */
    private static int SUCCESS = 0;
    /**
     * 失败
     */
    private static int ERROR = -1;


    public ApiResult() {
        put("code", SUCCESS);
    }

    public static ApiResult error() {
        return error(ERROR, "发生错误,请打110报警");
    }

    public static ApiResult error(String msg) {
        return error(ERROR, msg);
    }

    public static ApiResult error(int code, String msg) {
        ApiResult r = new ApiResult();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static ApiResult success(String msg) {
        ApiResult r = new ApiResult();
        r.put("msg", msg);
        return r;
    }

    public static ApiResult success() {
        return new ApiResult();
    }

    public ApiResult data(Object value) {
        super.put("data", value);
        return this;
    }

    public ApiResult msg(String value) {
        super.put("msg", value);
        return this;
    }

    public ApiResult code(int code) {
        super.put("code", code);
        return this;
    }

}
