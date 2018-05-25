package cn.com.yangzhenyu.brick.pojo;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/4/23
 * Time: 13:56
 */
public class HttpResult {
    private int status;
    private String encoding;
    private String cookieStr;
    private String body;
    private String msg;


    public HttpResult(){
    }

    public HttpResult(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getCookieStr() {
        return cookieStr;
    }

    public void setCookieStr(String cookieStr) {
        this.cookieStr = cookieStr;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
