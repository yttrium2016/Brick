package cn.com.yangzhenyu.brick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/4/23
 * Time: 13:10
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public ModelAndView index(){
        return new ModelAndView("index");
    }
}
