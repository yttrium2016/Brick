package cn.com.yangzhenyu.brick.dao;

import cn.com.yangzhenyu.brick.example.CommonExample;
import cn.com.yangzhenyu.brick.pojo.Column;
import cn.com.yangzhenyu.brick.pojo.Table;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/4/17
 * Time: 14:40
 */
@Mapper
@Repository
public interface TableDao {

    /**
     * 初始化系统表
     */
    void initSystemTables() throws BadSqlGrammarException;

    int insertSystemTable(Table table) throws BadSqlGrammarException;

    void createTable(Table table) throws BadSqlGrammarException;

    void initSystemColumnByTable(Table table);

    int deleteSystemTableByTableName(Table table) throws BadSqlGrammarException;

    int deleteTable(Table table) throws BadSqlGrammarException;

    void addColumn(Column column) throws BadSqlGrammarException;

    int insertSystemColumn(Column column) throws BadSqlGrammarException;

    void modifyColumn(Column column) throws BadSqlGrammarException;

    int updateSystemColumnById(Column column) throws BadSqlGrammarException;

    int updateSystemColumnByColumnName(Column column) throws BadSqlGrammarException;

    int deleteSystemColumnById(Column column) throws BadSqlGrammarException;

    int deleteSystemColumnByColumnName(Column column) throws BadSqlGrammarException;

    int deleteSystemColumnByTableName(String tableName) throws BadSqlGrammarException;

    void deleteColumn(Column column) throws BadSqlGrammarException;

    List<LinkedHashMap<String, Object>> selectListByExample(CommonExample example);

    LinkedHashMap<String, Object> selectOneByExample(CommonExample example);

    Long selectListCountByExample(CommonExample example);

    /**
     * 添加数据 返回ID
     *
     * @param params
     * @return
     */
    Integer insertData(Map<String, Object> params);

    /**
     * 修改数据 返回条数
     *
     * @param params
     * @return
     */
    Integer updateData(Map<String, Object> params);


    Integer deleteData(Map<String, Object> params);


}
