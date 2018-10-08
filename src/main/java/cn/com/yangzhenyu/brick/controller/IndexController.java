package cn.com.yangzhenyu.brick.controller;

import cn.com.yangzhenyu.brick.example.CommonExample;
import cn.com.yangzhenyu.brick.exception.MySQLException;
import cn.com.yangzhenyu.brick.pojo.ApiResult;
import cn.com.yangzhenyu.brick.service.TableService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 页面controller
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/4/23
 * Time: 13:10
 */
@Controller
public class IndexController {

    @Resource
    private TableService tableService;

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping("/table/{tableName}")
    public ModelAndView table(@PathVariable(name = "tableName") String tableName) throws MySQLException {
        ModelAndView mv = new ModelAndView("table");
        CommonExample example = new CommonExample("system_table");
        List<LinkedHashMap<String, Object>> list = tableService.findListData(example);
        mv.addObject("tableList", list);
        if (!StrUtil.hasBlank(tableName)) {
            CommonExample.Criteria criteria = example.createCriteria();
            criteria.andColumnEqualTo("table_name", tableName);
            LinkedHashMap<String, Object> table = tableService.findOneData(example);
            mv.addObject("table", table);
            if (table != null) {
                CommonExample columnExample = new CommonExample("system_column");
                columnExample.createCriteria().andColumnEqualTo("table_name", (String) table.get("table_name"));
                mv.addObject("columnList",tableService.findListData(columnExample));
            }
        }
        return mv;
    }

    @RequestMapping("/table")
    public ModelAndView table() throws MySQLException {
        return table(null);
    }

    @RequestMapping("/addTable")
    public ModelAndView addTable() throws MySQLException {
        return new ModelAndView("add_table");
    }

    @RequestMapping("/noPermissions ")
    @ResponseBody
    public ApiResult noPermissions() {
        return ApiResult.error(-10,"没有权限");
    }
}
