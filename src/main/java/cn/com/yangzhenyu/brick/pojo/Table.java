package cn.com.yangzhenyu.brick.pojo;

import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/5/4
 * Time: 13:48
 */
public class Table {
    private Integer id;
    private String tableName;
    private String tableShowName;
    private Date createDate;
    private Integer tableType = 0;
    private Integer deleteFlag = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public String getSqlTableName() {
        return "`" + tableName + "`";
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableShowName() {
        return tableShowName == null ? tableName : tableShowName;
    }

    public void setTableShowName(String tableShowName) {
        this.tableShowName = tableShowName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getTableType() {
        return tableType;
    }

    public void setTableType(int tableType) {
        this.tableType = tableType;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
