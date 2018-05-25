package cn.com.yangzhenyu.brick.service;

import cn.com.yangzhenyu.brick.example.CommonExample;
import cn.com.yangzhenyu.brick.exception.MySQLException;
import cn.com.yangzhenyu.brick.pojo.Column;
import cn.com.yangzhenyu.brick.pojo.FindData;
import cn.com.yangzhenyu.brick.pojo.Table;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/4/19
 * Time: 10:05
 */
public interface TableService {

    /**
     * 字段表相关
     */
    void addColumn(Column column) throws MySQLException;

    void modifyColumn(Column column) throws MySQLException;

    void deleteColumn(Column column) throws MySQLException;

    void createTable(Table table) throws MySQLException;

    void deleteTable(Table table) throws MySQLException;

    int addData(Map<String, Object> dataMap) throws MySQLException;

    int editData(Map<String, Object> dataMap) throws MySQLException;

    int deleteData(Map<String, Object> dataMap) throws MySQLException;

    List<LinkedHashMap<String,Object>> findListData(FindData data) throws MySQLException;

    Long findListCount(FindData data) throws MySQLException;

    LinkedHashMap<String,Object> findOneData(FindData data) throws MySQLException;

    List<LinkedHashMap<String,Object>> findListData(CommonExample example) throws MySQLException;

    Long findListCount(CommonExample example) throws MySQLException;

    LinkedHashMap<String,Object> findOneData(CommonExample example) throws MySQLException;

}
