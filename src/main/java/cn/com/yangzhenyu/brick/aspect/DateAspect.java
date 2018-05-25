package cn.com.yangzhenyu.brick.aspect;

import cn.hutool.core.date.DateUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 将返回的时间格式数据格式化
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/5/22
 * Time: 10:18
 */
@Aspect
@Component
public class DateAspect {

    @Value("${spring.mvc.date-format}")
    private String format = null;

    @Around(value = "execution(* cn.com.yangzhenyu.brick.service.TableService.*(..))  ")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        Object result = null;
        try {
            //前
            result = point.proceed();
            if (result instanceof List<?>) {
                List<LinkedHashMap<String, Object>> list = (List<LinkedHashMap<String, Object>>) result;
                Long s = System.currentTimeMillis();
                for (LinkedHashMap<String, Object> map : list) {
                    formatData(map);
                }
                Long e = System.currentTimeMillis();
                System.out.println("时间类型转换的时间为:[" + (e - s) + "]");
                return list;
            }
            if (result instanceof LinkedHashMap<?, ?>) {
                LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>) result;
                Long s = System.currentTimeMillis();
                formatData(data);
                Long e = System.currentTimeMillis();
                System.out.println("时间类型转换的时间为:[" + (e - s) + "]");
                return data;
            }
            //后
        } catch (Exception e) {
            //异常
        }
        return result;
    }

    private void formatData(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Date) {
                map.put(key, DateUtil.format((Date) value, format == null ? "yyyy-MM-dd HH:mm:ss" : format));
            }
        }
    }

}
