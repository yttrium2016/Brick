package cn.com.yangzhenyu.brick.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/5/17
 * Time: 14:20
 */
public class BaseData extends HashMap<String, Object> {

    protected int id = -1;

    protected String tableName;

    protected Map<String, Object> data = new HashMap<>();

    protected Map<String, Object> where = new HashMap<>();

    protected void addData(String key,Object value){
        this.data.put(key, value);
    }

    protected void addWhere(String key,Object value){
        this.where.put(key, value);
    }

    public BaseData(){

    }

    public BaseData(String tableName) {
        this.tableName = tableName;
        this.put("tableName", this.tableName);
    }

    public static BaseData createBaseData(String tableName) {
        return new BaseData(tableName);
    }

    public BaseData tableName(String tableName) {
        this.tableName = tableName;
        this.put("tableName", this.tableName);
        return this;
    }

    public BaseData id(int id){
        this.id = id;
        this.put("id", this.id);
        return this;
    }

    public BaseData date(Map<String, Object> data) {
        if (data != null) this.data = data;
        this.put("data", this.data);
        return this;
    }

    public BaseData where(Map<String, Object> where) {
        if (where != null) this.where = where;
        this.put("where", this.where);
        return this;
    }

    public BaseData putData(String key, Object value) {
        addData(key, value);
        this.put("data", this.data);
        return this;
    }

    public BaseData putData(String key1, Object value1, String key2, Object value2) {
        addData(key1, value1);
        addData(key2, value2);
        this.put("data", this.data);
        return this;
    }

    public BaseData putData(String key1, Object value1, String key2, Object value2, String key3, Object value3) {
        addData(key1, value1);
        addData(key2, value2);
        addData(key3, value3);
        this.put("data", this.data);
        return this;
    }

    public BaseData putWhere(String key, Object value) {
        addWhere(key, value);
        this.put("where", where);
        return this;
    }

    public BaseData putWhere(String key1, Object value1, String key2, Object value2) {
        addWhere(key1, value1);
        addWhere(key2, value2);
        this.put("where", where);
        return this;
    }

    public BaseData putWhere(String key1, Object value1, String key2, Object value2, String key3, Object value3) {
        addWhere(key1, value1);
        addWhere(key2, value2);
        addWhere(key3, value3);
        this.put("where", where);
        return this;
    }
}
