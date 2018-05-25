package cn.com.yangzhenyu.brick.util;

import cn.hutool.core.date.DateException;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.util.StringUtils;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/5/17
 * Time: 16:05
 */
public class StringTypeUtils {
    public static final String INT_PATTERN = "^-?[1-9]\\d*$";
    public static final String DOUBLE_PATTERN = "^[-]?[0-9]\\d*\\.\\d*|-0\\.\\d*[1-9]\\d*$";
    public static final String ZERO_DOUBLE_PATTERN = "^0.\\d*|-0\\.\\d*[1-9]\\d*$";

    public static Object convert(String item) {
        // 忽略所有空字符串或全是空格的字符串
        if (!StringUtils.hasText(item)) {
            return null;
        }
        item = item.trim();
        //不需要
//        if ("true".equalsIgnoreCase(item) || "false".equalsIgnoreCase(item)) {
//            return Boolean.valueOf(item);
//        }
        if (item.matches(INT_PATTERN) || "0".equals(item)) {
            return Integer.valueOf(item);
        }
        if (item.matches(DOUBLE_PATTERN) || item.matches(ZERO_DOUBLE_PATTERN)) {
            return Double.valueOf(item);
        }
        try {
            return DateUtil.parse(item);
        } catch (DateException ignored) {
        }
        if (JSONUtil.isJsonArray(item)) {
            return JSONUtil.toList(JSONUtil.parseArray(item), String.class);
        }

        return item;
    }


    public static boolean isNumberInt(String obj) {
        if (obj.matches(INT_PATTERN) || "0".equals(obj)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        convert("1234-13-12 12:12:1529999999999999");
        System.out.println("1");
    }
}
