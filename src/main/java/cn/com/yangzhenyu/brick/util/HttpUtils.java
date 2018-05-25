package cn.com.yangzhenyu.brick.util;

import cn.com.yangzhenyu.brick.pojo.HttpResult;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/4/24
 * Time: 10:52
 */
public class HttpUtils {

    public static HttpResult go(@NotNull Method method, String url, Map<String, String> headers, Map<String, Object> params) {
        if (StrUtil.hasBlank(url)) {
            return new HttpResult(-1, "URL不能为空");
        }
        HttpRequest httpRequest = HttpUtil.createRequest(method, url);
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpRequest = httpRequest.header(entry.getKey(), entry.getValue());
            }
        }
        if (params != null && params.size() > 0) {
            httpRequest = httpRequest.form(params);
        }
        try {
            if (StrUtil.containsAnyIgnoreCase(url, "https")) {
                SslUtils.ignoreSsl();
            }
            HttpResult result = new HttpResult();
            HttpResponse httpResponse = httpRequest.execute();
            result.setStatus(httpResponse.getStatus());
            result.setEncoding(httpResponse.contentEncoding());
            result.setCookieStr(httpResponse.getCookieStr());
            result.setBody(httpResponse.body());
            result.setMsg("网络请求成功");
            //打印一下 记得删除
            System.out.println("请求成功[" + httpResponse.body() + "]");

            return result;
        } catch (Exception e) {
            return new HttpResult(1000, "网络请求出错:[" + e.getMessage() + "]");
        }
    }

    public static HttpResult go(String methodName, String url, Map<String, String> headers, Map<String, Object> params) {
        if (!StrUtil.hasBlank(methodName)) {
            try {
                Method method = Method.valueOf(methodName.toUpperCase());
                return go(method, url, headers, params);
            } catch (Exception e) {
                return new HttpResult(-100, "没有对应的Method[" + e.getMessage() + "]");
            }
        } else {
            return new HttpResult(-200, "Method不能为空");
        }
    }

    public static HttpResult get(String url, Map<String, String> headers, Map<String, Object> params) {
        return go(Method.GET, url, headers, params);
    }

    public static HttpResult get(String url, Map<String, Object> params) {
        return get(url, null, params);
    }

    public static HttpResult post(String url, Map<String, String> headers, Map<String, Object> params) {
        return go(Method.POST, url, headers, params);
    }

    public static HttpResult post(String url, Map<String, Object> params) {
        return post(url, null, params);
    }

    public static HttpResult put(String url, Map<String, String> headers, Map<String, Object> params) {
        return go(Method.PUT, url, headers, params);
    }

    public static HttpResult put(String url, Map<String, Object> params) {
        return put(url, null, params);
    }

    public static HttpResult delete(String url, Map<String, String> headers, Map<String, Object> params) {
        return go(Method.DELETE, url, headers, params);
    }

    public static HttpResult delete(String url, Map<String, Object> params) {
        return delete(url, null, params);
    }

    public static HttpResult head(String url, Map<String, String> headers, Map<String, Object> params) {
        return go(Method.HEAD, url, headers, params);
    }

    public static HttpResult head(String url, Map<String, Object> params) {
        return head(url, null, params);
    }
}
