package cn.com.yangzhenyu.brick.service;

import cn.com.yangzhenyu.brick.pojo.HttpRequestData;
import cn.com.yangzhenyu.brick.pojo.HttpResult;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/4/16
 * Time: 16:10
 */
public interface HttpService {

    HttpResult go(HttpRequestData httpRequestData);
}
