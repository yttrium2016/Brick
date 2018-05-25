package cn.com.yangzhenyu.brick.pojo;

import cn.com.yangzhenyu.brick.util.StringTypeUtils;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/5/17
 * Time: 15:07
 */
public class FindData {

    private String tableName = null;

    private List<String> columnNames = null;

    private List<WhereItem> andWhere = null;

    private List<WhereItem> orWhere = null;

    private String order = null;

    private Integer pageIndex = null;

    private Integer pageSize = null;


    public FindData() {
    }

    public FindData(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public List<WhereItem> getAndWhere() {
        return andWhere;
    }

    public void setAndWhere(List<WhereItem> andWhere) {
        this.andWhere = andWhere;
    }

    public List<WhereItem> getOrWhere() {
        return orWhere;
    }

    public void setOrWhere(List<WhereItem> orWhere) {
        this.orWhere = orWhere;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public static class WhereItem {

        private String key;
        private String type;
        private Object value;

        public WhereItem() {

        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type.toUpperCase();
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = StringTypeUtils.convert((String) value);
        }
    }
}
