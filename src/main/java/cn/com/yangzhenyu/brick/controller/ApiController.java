package cn.com.yangzhenyu.brick.controller;

import cn.com.yangzhenyu.brick.example.CommonExample;
import cn.com.yangzhenyu.brick.exception.MySQLException;
import cn.com.yangzhenyu.brick.pojo.*;
import cn.com.yangzhenyu.brick.service.TableService;
import cn.com.yangzhenyu.brick.util.StringTypeUtils;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/4/19
 * Time: 10:04
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Resource
    private TableService tableService;


    @RequestMapping("/table/{action}")
    public ApiResult Table(@PathVariable(name = "action") String action, @PathVariable(required = false) Table table) {
        if (!StrUtil.hasBlank(action)) {
            try {
                if ("create".equalsIgnoreCase(action)) {
                    tableService.createTable(table);
                    return ApiResult.success("创建成功");
                }
                if ("delete".equalsIgnoreCase(action)) {
                    tableService.deleteTable(table);
                    return ApiResult.success("删除成功");
                }
                if (StringTypeUtils.isNumberInt(action)) {
                    CommonExample example = new CommonExample("system_table");
                    example.createCriteria().andColumnEqualTo("id", Integer.valueOf(action));
                    return ApiResult.success("查找成功").data(tableService.findOneData(example));
                }
                CommonExample example = new CommonExample("system_table");
                example.createCriteria().andColumnEqualTo("table_name", action);
                LinkedHashMap<String, Object> data = tableService.findOneData(example);
                if (data != null) {
                    return ApiResult.success("查找成功").data(data);
                }

            } catch (MySQLException e) {
                return ApiResult.error(e.getErrorCode(), e.getErrorMessage());
            }
        }
        return ApiResult.error("没有对应操作");
    }

    @RequestMapping("/column/{action}")
    public ApiResult Column(@PathVariable(name = "action") String action, Column column) {
        if (!StrUtil.hasBlank(action)) {
            try {
                if ("create".equalsIgnoreCase(action)) {
                    tableService.addColumn(column);
                }
                if ("update".equalsIgnoreCase(action)) {
                    tableService.modifyColumn(column);
                }
                if ("delete".equalsIgnoreCase(action)) {
                    tableService.deleteColumn(column);
                }
                return ApiResult.success();
            } catch (MySQLException e) {
                return ApiResult.error(e.getErrorCode(), e.getErrorMessage());
            }
        }
        return ApiResult.error("没有对应操作");
    }

    @RequestMapping("/data/{action}")
    public ApiResult Data(@PathVariable(name = "action") String action, String data) {
        if (!StrUtil.hasBlank(action) && !StrUtil.hasBlank(data) && JSONUtil.isJsonObj(data)) {
            try {

                if ("add".equalsIgnoreCase(action)) {
                    Map<String, Object> dataMap = JSONUtil.toBean(data, BaseData.class);
                    return ApiResult.success().data(tableService.addData(dataMap));
                }
                if ("edit".equalsIgnoreCase(action)) {
                    Map<String, Object> dataMap = JSONUtil.toBean(data, BaseData.class);
                    return ApiResult.success().data(tableService.editData(dataMap));
                }
                if ("delete".equalsIgnoreCase(action)) {
                    Map<String, Object> dataMap = JSONUtil.toBean(data, BaseData.class);
                    return ApiResult.success().data(tableService.deleteData(dataMap));
                }
                if ("find".equalsIgnoreCase(action)) {
                    FindData findData = JSONUtil.toBean(data, FindData.class);
                    return ApiResult.success().data(tableService.findListData(findData));
                }
                if ("one".equalsIgnoreCase(action)) {
                    FindData findData = JSONUtil.toBean(data, FindData.class);
                    return ApiResult.success().data(tableService.findOneData(findData));
                }
                if ("size".equalsIgnoreCase(action)) {
                    FindData findData = JSONUtil.toBean(data, FindData.class);
                    return ApiResult.success().data(tableService.findListCount(findData));
                }
            } catch (MySQLException e) {
                return ApiResult.error(e.getErrorCode(), e.getErrorMessage());
            }
        }
        return ApiResult.error("没有对应操作");
    }

}
