package cn.com.yangzhenyu.brick.pojo;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/8/19
 * Time: 18:28
 */
public class WxLoginInfo {
    private String openid;
    private String session_key;
    private String expires_in;
    private Boolean is_first = false;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public Boolean getIs_first() {
        return is_first;
    }

    public void setIs_first(Boolean is_first) {
        this.is_first = is_first;
    }
}
