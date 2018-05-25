package cn.com.yangzhenyu.brick.pojo;

import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/4/24
 * Time: 16:16
 */
public class HttpRequestData {
    private String url;
    private String method;
    private Map<String,String> headers;
    private Map<String,Object> params;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
