package cn.com.yangzhenyu.brick.controller;

import cn.com.yangzhenyu.brick.example.CommonExample;
import cn.com.yangzhenyu.brick.exception.MySQLException;
import cn.com.yangzhenyu.brick.pojo.*;
import cn.com.yangzhenyu.brick.service.HttpService;
import cn.com.yangzhenyu.brick.service.TableService;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信小程序
 * 微信 各种与微信API的接口
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/8/19
 * Time: 15:53
 */
@Controller
@RequestMapping("/wx")
public class WeiXinController {

    @Value("${wx.appid}")
    private String appID = null;
    @Value("${wx.appsecret}")
    private String appSecret = null;
    @Value("${wx.grant_type}")
    private String grantType = null;

    @Resource
    private HttpService httpService;

    @Resource
    private TableService tableService;


    @RequestMapping(value = "login")
    @ResponseBody
    public ApiResult get(String code) {
        if (StrUtil.hasBlank(code)) {
            return ApiResult.error(-1, "code不能为空");
        }
        HttpRequestData httpRequestData = new HttpRequestData();
        httpRequestData.setUrl("https://api.weixin.qq.com/sns/jscode2session");
        httpRequestData.setHeaders(MapUtil.of("Content-Type", "application/json"));
        httpRequestData.setMethod("GET");
        Map<String, Object> params = new HashMap<>();
        params.put("appid", appID);
        params.put("secret", appSecret);
        params.put("js_code", code);
        params.put("grant_type", "authorization_code");
        httpRequestData.setParams(params);
        HttpResult httpResult = httpService.go(httpRequestData);

        if (null != httpResult) {
            int status = httpResult.getStatus();
            if (200 == status || 302 == status) {
                try {

                    WxLoginInfo wxLoginInfo = JSONUtil.toBean(httpResult.getBody(), WxLoginInfo.class);
                    //加到数据库 有则改 无则加
                    CommonExample wxUserExample = new CommonExample("wx_user");
                    CommonExample.Criteria wxUserCriteria = wxUserExample.createCriteria();
                    wxUserCriteria.andColumnEqualTo("openid", wxLoginInfo.getOpenid());
                    Long size = tableService.findListCount(wxUserExample);
                    boolean isFirst = false;
                    if (size > 0) {
                        BaseData data = BaseData.createBaseData("wx_user");
                        data.putWhere("openid", wxLoginInfo.getOpenid());
                        data.putData("session_key", wxLoginInfo.getSession_key());
                        data.putData("expires_in", wxLoginInfo.getExpires_in());
                        tableService.editData(data);
                    } else {
                        isFirst = true;
                        BaseData data = BaseData.createBaseData("wx_user");
                        data.putData("openid", wxLoginInfo.getOpenid());
                        data.putData("session_key", wxLoginInfo.getSession_key());
                        data.putData("expires_in", wxLoginInfo.getExpires_in());
                        tableService.addData(data);
                    }

                    //查找本地的user信息传回去 去掉密码
                    CommonExample example = new CommonExample("user");
                    CommonExample.Criteria criteria = example.createCriteria();
                    criteria.andColumnEqualTo("openid", wxLoginInfo.getOpenid());
                    List<LinkedHashMap<String, Object>> list = tableService.findListData(example);
                    if (list == null || list.size() < 1) {
                        wxLoginInfo.setExpires_in("");
                        wxLoginInfo.setSession_key("");
                        wxLoginInfo.setIs_first(isFirst);
                        return ApiResult.success("微信登录成功").data(wxLoginInfo);
                    } else {
                        LinkedHashMap<String, Object> map = list.get(0);
                        map.put("password", null);
                        map.put("isFirst", isFirst);
                        return ApiResult.success("微信登录成功").data(map);
                    }
                } catch (MySQLException e) {
                    return ApiResult.error(e.getErrorCode(), e.getErrorMessage());
                }
            } else {
                return ApiResult.error(status, httpResult.getMsg());
            }
        } else {
            return ApiResult.error(-999, "返回结果为空");
        }
    }

    @RequestMapping(value = "addInfo")
    @ResponseBody
    public ApiResult get(String openid, Map<String, String> info) {
        if (StrUtil.hasBlank(openid)) {
            return ApiResult.error(-1, "openid不能为空");
        }
        try {

            BaseData data = BaseData.createBaseData("wx_user");
            data.putWhere("openid", openid);
            data.putData("avatarUrl", info.get("avatarUrl"));
            data.putData("city", info.get("city"));
            data.putData("country", info.get("country"));
            data.putData("gender", info.get("gender"));
            data.putData("language", info.get("language"));
            data.putData("nickName", info.get("nickName"));
            data.putData("province", info.get("province"));

            tableService.editData(data);
            return ApiResult.success("添加成功");
        } catch (MySQLException e) {
            return ApiResult.error(e.getErrorCode(), e.getErrorMessage());
        }
    }
}
