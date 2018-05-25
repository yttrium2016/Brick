package cn.com.yangzhenyu.brick.conifg;

import cn.com.yangzhenyu.brick.pojo.ApiResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller 异常处理类
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/5/22
 * Time: 10:00
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 系统异常处理，比如：404,500
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResult defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        ApiResult result = ApiResult.success(e.getMessage());
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            result.code(404);
        } else {
            result.code(500);
        }
        result.data(null);
        return result;
    }
}
