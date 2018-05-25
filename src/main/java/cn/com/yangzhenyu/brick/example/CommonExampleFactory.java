package cn.com.yangzhenyu.brick.example;

import cn.com.yangzhenyu.brick.pojo.FindData;
import cn.hutool.core.util.StrUtil;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/5/17
 * Time: 16:57
 */
public class CommonExampleFactory {

    public static CommonExample createCommonExample(FindData data) {
        if (StrUtil.hasBlank(data.getTableName())) return null;
        CommonExample commonExample = new CommonExample(data.getTableName());
        if (data.getColumnNames() != null && data.getColumnNames().size() > 0) {
            commonExample.setColumnNames(data.getColumnNames());
        }
        if (data.getPageIndex() != null && data.getPageSize() != null && data.getPageIndex() >= 0 && data.getPageSize() > 0) {
            commonExample.setPagingParam(data.getPageIndex(), data.getPageSize());
        }
        if (!StrUtil.hasBlank(data.getOrder())) {
            commonExample.setOrderByClause(data.getOrder());
        }
        if (data.getAndWhere() != null && data.getAndWhere().size() > 0) {
            CommonExample.Criteria c = commonExample.createCriteria();
            for (FindData.WhereItem w : data.getAndWhere()) {
                addCriteria(c, w);
            }
        }
        if (data.getOrWhere() != null && data.getOrWhere().size() > 0) {
            for (FindData.WhereItem w : data.getOrWhere()) {
                CommonExample.Criteria c = commonExample.or();
                addCriteria(c, w);
            }
        }
        return commonExample;
    }

    private static void addCriteria(CommonExample.Criteria c, FindData.WhereItem w) {
        switch (w.getType()) {
            case "NULL":
                c.andColumnIsNull(w.getKey());
                break;
            case "NOTNULL":
                c.andColumnIsNotNull(w.getKey());
                break;
            case "LIKE":
                c.andColumnLike(w.getKey(), "%" + w.getValue() + "%");
                break;
            case "NOTLIKE":
                c.andColumnNotLike(w.getKey(), "%" + w.getValue() + "%");
                break;
            case "EQ":
                if (w.getValue() instanceof String) {
                    c.andColumnEqualTo(w.getKey(), (String) w.getValue());
                }
                if (w.getValue() instanceof Integer) {
                    c.andColumnEqualTo(w.getKey(), (Integer) w.getValue());
                }
                if (w.getValue() instanceof Date) {
                    c.andColumnEqualTo(w.getKey(), (Date) w.getValue());
                }
                if (w.getValue() instanceof Double) {
                    c.andColumnEqualTo(w.getKey(), (Double) w.getValue());
                }
                break;
            case "NOTEQ":
                if (w.getValue() instanceof String) {
                    c.andColumnNotEqualTo(w.getKey(), (String) w.getValue());
                }
                if (w.getValue() instanceof Integer) {
                    c.andColumnNotEqualTo(w.getKey(), (Integer) w.getValue());
                }
                if (w.getValue() instanceof Date) {
                    c.andColumnNotEqualTo(w.getKey(), (Date) w.getValue());
                }
                if (w.getValue() instanceof Double) {
                    c.andColumnNotEqualTo(w.getKey(), (Double) w.getValue());
                }
                break;
            case "LESS":
                if (w.getValue() instanceof String) {
                    c.andColumnLessThan(w.getKey(), (String) w.getValue());
                }
                if (w.getValue() instanceof Integer) {
                    c.andColumnLessThan(w.getKey(), (Integer) w.getValue());
                }
                if (w.getValue() instanceof Date) {
                    c.andColumnLessThan(w.getKey(), (Date) w.getValue());
                }
                if (w.getValue() instanceof Double) {
                    c.andColumnLessThan(w.getKey(), (Double) w.getValue());
                }
                break;
            case "LESSEQ":
                if (w.getValue() instanceof String) {
                    c.andColumnLessThanOrEqualTo(w.getKey(), (String) w.getValue());
                }
                if (w.getValue() instanceof Integer) {
                    c.andColumnLessThanOrEqualTo(w.getKey(), (Integer) w.getValue());
                }
                if (w.getValue() instanceof Date) {
                    c.andColumnLessThanOrEqualTo(w.getKey(), (Date) w.getValue());
                }
                if (w.getValue() instanceof Double) {
                    c.andColumnLessThanOrEqualTo(w.getKey(), (Double) w.getValue());
                }
                break;
            case "GREATER":
                if (w.getValue() instanceof String) {
                    c.andColumnGreaterThan(w.getKey(), (String) w.getValue());
                }
                if (w.getValue() instanceof Integer) {
                    c.andColumnGreaterThan(w.getKey(), (Integer) w.getValue());
                }
                if (w.getValue() instanceof Date) {
                    c.andColumnGreaterThan(w.getKey(), (Date) w.getValue());
                }
                if (w.getValue() instanceof Double) {
                    c.andColumnGreaterThan(w.getKey(), (Double) w.getValue());
                }
                break;
            case "GREATEREQ":
                if (w.getValue() instanceof String) {
                    c.andColumnGreaterThanOrEqualTo(w.getKey(), (String) w.getValue());
                }
                if (w.getValue() instanceof Integer) {
                    c.andColumnGreaterThanOrEqualTo(w.getKey(), (Integer) w.getValue());
                }
                if (w.getValue() instanceof Date) {
                    c.andColumnGreaterThanOrEqualTo(w.getKey(), (Date) w.getValue());
                }
                if (w.getValue() instanceof Double) {
                    c.andColumnGreaterThanOrEqualTo(w.getKey(), (Double) w.getValue());
                }
                break;
            case "IN":
                if (w.getValue() instanceof List) {
                    c.andColumnIn(w.getKey(), (List<?>) w.getValue());
                }
                break;
            case "NOTIN":
                if (w.getValue() instanceof List) {
                    c.andColumnNotIn(w.getKey(), (List<?>) w.getValue());
                }
                break;
        }
    }
}
