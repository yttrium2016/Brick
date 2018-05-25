package cn.com.yangzhenyu.brick.service.impl;

import cn.com.yangzhenyu.brick.pojo.HttpRequestData;
import cn.com.yangzhenyu.brick.pojo.HttpResult;
import cn.com.yangzhenyu.brick.service.HttpService;
import cn.com.yangzhenyu.brick.util.HttpUtils;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/4/16
 * Time: 16:10
 */
@Service
public class HttpServiceImpl implements HttpService {

    @Override
    public HttpResult go(HttpRequestData httpRequestData) {
        return HttpUtils.go(httpRequestData.getMethod(), httpRequestData.getUrl(), httpRequestData.getHeaders(), httpRequestData.getParams());
    }
}
