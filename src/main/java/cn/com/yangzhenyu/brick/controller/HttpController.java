package cn.com.yangzhenyu.brick.controller;

import cn.com.yangzhenyu.brick.pojo.ApiResult;
import cn.com.yangzhenyu.brick.pojo.HttpRequestData;
import cn.com.yangzhenyu.brick.pojo.HttpResult;
import cn.com.yangzhenyu.brick.service.HttpService;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/4/16
 * Time: 16:09
 */
@RestController
public class HttpController {

    @Resource
    private HttpService httpService;

    @RequestMapping("/go")
    public ApiResult go(String data) {
        if (StrUtil.hasBlank(data)) {
            return ApiResult.error("请求参数不能为空..");
        }
        try {
            HttpRequestData httpRequestData = JSONUtil.toBean(data, HttpRequestData.class);
            HttpResult httpResult = httpService.go(httpRequestData);

            if (null != httpResult) {
                int status = httpResult.getStatus();
                if (200 == status || 302 == status) {
                    return ApiResult.success("请求成功").data(httpResult.getBody());
                } else {
                    return ApiResult.error(status, httpResult.getMsg());
                }
            } else {
                return ApiResult.error(-999, "返回结果为空");
            }
        } catch (Exception e) {
            return ApiResult.error(500, "服务器发生错误[" + e.getMessage() + "]");
        }
    }


}
