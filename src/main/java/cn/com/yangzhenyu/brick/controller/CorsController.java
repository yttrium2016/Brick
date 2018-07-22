package cn.com.yangzhenyu.brick.controller;

import cn.com.yangzhenyu.brick.example.CommonExample;
import cn.com.yangzhenyu.brick.exception.MySQLException;
import cn.com.yangzhenyu.brick.pojo.ApiResult;
import cn.com.yangzhenyu.brick.pojo.BaseData;
import cn.com.yangzhenyu.brick.pojo.HttpRequestData;
import cn.com.yangzhenyu.brick.pojo.HttpResult;
import cn.com.yangzhenyu.brick.service.HttpService;
import cn.com.yangzhenyu.brick.service.TableService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 允许的跨域访问
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/6/28
 * Time: 23:03
 */
@Controller
@RequestMapping("/cors")
public class CorsController {

    @Resource
    private TableService tableService;

    @Resource
    private HttpService httpService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() throws MySQLException {
        ModelAndView mv = new ModelAndView("cors");
        CommonExample example = new CommonExample("system_cors");
        List<LinkedHashMap<String, Object>> list = tableService.findListData(example);
        mv.addObject("corsList", list);
        return mv;
    }

    @RequestMapping(value = "{action}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable(name = "action") String action, HttpServletRequest request) throws MySQLException {
        try {
            CommonExample example = new CommonExample("system_cors");
            CommonExample.Criteria criteria = example.createCriteria();
            criteria.andColumnEqualTo("attr_name", "/"+action);
            CommonExample.Criteria or = example.or();
            or.andColumnEqualTo("attr_name", action);
            List<LinkedHashMap<String, Object>> list = tableService.findListData(example);
            if (list == null || list.size() < 1) {
                return ApiResult.error(-1, "没有对应的cors配置");
            }
            HttpRequestData httpRequestData = new HttpRequestData();
            httpRequestData.setUrl(list.get(0).get("url_name").toString());
            httpRequestData.setMethod("GET");
            httpRequestData.setHeaders(null);

            Map<String, Object> params = new HashMap<>();
            Enumeration<String> names = request.getParameterNames();
            while (names.hasMoreElements()) {
                String paraName = names.nextElement();
                params.put(paraName, request.getParameter(paraName));
            }
            httpRequestData.setParams(params);
            HttpResult httpResult = httpService.go(httpRequestData);
            if (null != httpResult) {
                int status = httpResult.getStatus();
                if (200 == status || 302 == status) {
                    return httpResult.getBody();
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

    @RequestMapping(value = "{action}", method = RequestMethod.POST)
    @ResponseBody
    public Object post(@PathVariable(name = "action") String action, HttpServletRequest request) throws MySQLException {
        try {
            CommonExample example = new CommonExample("system_cors");
            CommonExample.Criteria criteria = example.createCriteria();
            criteria.andColumnEqualTo("attr_name", "/"+action);
            CommonExample.Criteria or = example.or();
            or.andColumnEqualTo("attr_name", action);
            List<LinkedHashMap<String, Object>> list = tableService.findListData(example);
            if (list == null || list.size() < 1) {
                return ApiResult.error(-1, "没有对应的cors配置");
            }
            HttpRequestData httpRequestData = new HttpRequestData();
            httpRequestData.setUrl(list.get(0).get("url_name").toString());
            httpRequestData.setMethod("POST");
            httpRequestData.setHeaders(null);

            Map<String, Object> params = new HashMap<>();
            Enumeration<String> names = request.getParameterNames();
            while (names.hasMoreElements()) {
                String paraName = names.nextElement();
                params.put(paraName, request.getParameter(paraName));
            }
            httpRequestData.setParams(params);
            HttpResult httpResult = httpService.go(httpRequestData);
            if (null != httpResult) {
                int status = httpResult.getStatus();
                if (200 == status || 302 == status) {
                    return httpResult.getBody();
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


    @RequestMapping("/create")
    @ResponseBody
    public ApiResult create(String attrName, String urlName) throws MySQLException {
        if (StrUtil.hasBlank(attrName, urlName)) {
            return ApiResult.error("请求参数不能为空..");
        }
        try {
            BaseData data = BaseData.createBaseData("system_cors");
            data.putData("attr_name", attrName);
            data.putData("url_name", urlName);
            return ApiResult.success("添加成功").data(tableService.addData(data));
        } catch (MySQLException e) {
            return ApiResult.error(e.getErrorCode(), e.getErrorMessage());
        } catch (Exception ex) {
            return ApiResult.error(500, "服务器发生错误[" + ex.getMessage() + "]");
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ApiResult delete(String attrName) throws MySQLException {
        if (StrUtil.hasBlank(attrName)) {
            return ApiResult.error("请求参数不能为空..");
        }
        try {
            //{"where":{"name":"杨振宇"},"tableName":"test"}
            BaseData data = BaseData.createBaseData("system_cors");
            data.putWhere("attr_name", attrName);
            return ApiResult.success("删除成功").data(tableService.deleteData(data));
        } catch (MySQLException e) {
            return ApiResult.error(e.getErrorCode(), e.getErrorMessage());
        } catch (Exception ex) {
            return ApiResult.error(500, "服务器发生错误[" + ex.getMessage() + "]");
        }
    }

}
