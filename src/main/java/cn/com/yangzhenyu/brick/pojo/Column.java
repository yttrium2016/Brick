package cn.com.yangzhenyu.brick.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 字段的实体类(用于字段添加)
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/4/18
 * Time: 9:43
 */
public class Column {
    /**
     * ALTER TABLE `bbb` ADD COLUMN `hello` VARCHAR (255) CHARACTER
     * SET utf8 COLLATE utf8_general_ci NULL DEFAULT '哈哈'
     * <p>
     * ALTER TABLE `bbb`
     * ADD COLUMN `tttt2`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '哈哈' ;
     * <p>
     * ALTER TABLE `bbb`
     * MODIFY COLUMN `gs`  int(1000) NULL DEFAULT 1 ;
     * <p>
     * ALTER TABLE `bbb`
     * ADD COLUMN `ddddd`  datetime NULL ON UPDATE CURRENT_TIMESTAMP ;
     * <p>
     * ALTER TABLE `bbb`
     * MODIFY COLUMN `kkkkk`  double(255,30) ZEROFILL NULL DEFAULT 456.00000000000000000000000000000 ;
     */

    //序号
    private Integer id;
    //表名
    private String tableName;
    //列名
    private String columnName;
    //列中文
    private String columnShowName;
    //Java类型
    private String javaType;
    //类型 varchar int text datetime double
    private DataType dataType;
    //数据长度
    private Integer dataTypeLength;
    //默认
    private String defaultData = null;
    //是否有默认
    private boolean isDefault = false;
    //是否允许为空
    private boolean isNull = true;
    //是否默认修改当前时间更新
    private boolean isDateUpdateNow = false;

    private boolean isDateCreateNow = false;

    private Date createDate;

    private boolean deleteFlag;

    public Column() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnShowName() {
        return columnShowName;
    }

    public void setColumnShowName(String columnShowName) {
        this.columnShowName = columnShowName;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getSqlDataType() {
        if (dataType == null) return null;
        if ("varchar".equals(dataType.toString().toLowerCase()) && dataTypeLength == null) dataTypeLength = 255;
        if (dataTypeLength == null) {
            switch (dataType.toString().toLowerCase()) {
                case "varchar":
                case "text":
                    return dataType.toString() + " CHARACTER SET utf8 COLLATE utf8_general_ci ";
                default:
                    return dataType.toString();
            }
        } else {
            switch (dataType.toString().toLowerCase()) {
                case "varchar":
                case "text":
                    return dataType.toString() + "(" + dataTypeLength + ") CHARACTER SET utf8 COLLATE utf8_general_ci ";
                default:
                    return dataType.toString() + "(" + dataTypeLength + ") ";
            }
        }
    }

    public String getSqlColumnName() {
        return "`" + columnName + "`";
    }

    public String getSqlTableName() {
        return "`" + tableName + "`";
    }

    public String getDataType() {
        return dataType == null ? null : dataType.toString().toUpperCase();
    }

    public String getOriginalDataType() {
        return dataType == null ? null : dataType.toString().toLowerCase();
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
        if (null != dataType){
            setJavaTypeByDataType(dataType.toString());
        }
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : DataType.valueOf(dataType.toUpperCase());
        setJavaTypeByDataType(dataType);
    }

    public Integer getDataTypeLength() {
        return dataTypeLength;
    }

    public void setDataTypeLength(Integer dataTypeLength) {
        this.dataTypeLength = dataTypeLength;
    }

    public String getDefaultData() {
        return defaultData;
    }

    public String getSqlDefaultData() {
        if (dataType != null) {
            switch (dataType.toString().toLowerCase()) {
                case "text":
                case "varchar":
                    return "'" + defaultData + "'";
                case "datetime":
                    return isDateUpdateNow || isDateCreateNow ? "CURRENT_TIMESTAMP" : "'" + defaultData + "'";
                default:
                    return defaultData;
            }
        }
        return null;
    }

    public void setDefaultData(String StringData) {
        this.defaultData = StringData;
        if (null != dataType && !"text".equalsIgnoreCase(dataType.toString())) {
            this.isDefault = true;
        }
    }

    public void setDefaultData(int intData) {
        this.defaultData = String.valueOf(intData);
        if (null != dataType && !"text".equalsIgnoreCase(dataType.toString())) {
            this.isDefault = true;
        }
    }

    public void setDefaultData(double doubleData) {
        this.defaultData = String.valueOf(doubleData);
        if (null != dataType && !"text".equalsIgnoreCase(dataType.toString())) {
            this.isDefault = true;
        }
    }

    public void setDefaultData(Date dateData) {
        this.defaultData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateData);
        if (null != dataType && !"text".equalsIgnoreCase(dataType.toString())) {
            this.isDefault = true;
        }
    }

    public boolean isNull() {
        return isNull;
    }

    public void setIsNull(boolean aNull) {
        isNull = aNull;
    }

    public boolean isDateUpdateNow() {
        return isDateUpdateNow;
    }

    public void setIsDateUpdateNow(boolean dateUpdateNow) {
        isDateUpdateNow = dateUpdateNow;
        isDefault = dateUpdateNow;
    }

    public boolean isDateCreateNow() {
        return isDateCreateNow;
    }

    public void setIsDateCreateNow(boolean dateCreateNow) {
        isDateCreateNow = dateCreateNow;
        isDefault = dateCreateNow;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public void setJavaTypeByDataType(String dataType){
        if (null != dataType){
            switch (dataType.toUpperCase()){
                // VARCHAR, INT, TEXT, DATETIME, DOUBLE;
                case "VARCHAR":
                case "TEXT":
                    javaType = "STRING";
                    break;
                case "INT":
                    javaType = "INT";
                    break;
                case "DATETIME":
                    javaType = "DATE";
                    break;
                case "DOUBLE":
                    javaType = "DOUBLE";
                    break;
                default:
                    javaType = null;
                    break;
            }
        }
    }

    public enum DataType {
        //类型 varchar int text datetime double
        VARCHAR, INT, TEXT, DATETIME, DOUBLE;
    }
}
