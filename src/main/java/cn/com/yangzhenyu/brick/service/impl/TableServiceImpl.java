package cn.com.yangzhenyu.brick.service.impl;

import cn.com.yangzhenyu.brick.dao.TableDao;
import cn.com.yangzhenyu.brick.example.CommonExample;
import cn.com.yangzhenyu.brick.example.CommonExampleFactory;
import cn.com.yangzhenyu.brick.exception.MySQLException;
import cn.com.yangzhenyu.brick.pojo.Column;
import cn.com.yangzhenyu.brick.pojo.FindData;
import cn.com.yangzhenyu.brick.pojo.Table;
import cn.com.yangzhenyu.brick.service.TableService;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/4/19
 * Time: 10:06
 */
@Service
public class TableServiceImpl implements TableService {

    @Resource
    private TableDao tableDao;

    @Transactional
    @Override
    public void createTable(Table table) throws MySQLException {
        try {
            tableDao.createTable(table);
            tableDao.insertSystemTable(table);
            tableDao.initSystemColumnByTable(table);
        } catch (BadSqlGrammarException e) {
            throw new MySQLException(e.getSQLException().getErrorCode());
        } catch (Exception e) {
            throw new MySQLException(500, "服务器出错[" + e.getMessage() + "]");
        }
    }

    @Transactional
    @Override
    public void deleteTable(Table table) throws MySQLException {
        try {
            tableDao.deleteTable(table);
            tableDao.deleteSystemTableByTableName(table);
            tableDao.deleteSystemColumnByTableName(table.getTableName());
        } catch (BadSqlGrammarException e) {
            throw new MySQLException(e.getSQLException().getErrorCode());
        } catch (Exception e) {
            throw new MySQLException(500, "服务器出错[" + e.getMessage() + "]");
        }
    }

    @Transactional
    @Override
    public void addColumn(Column column) throws MySQLException {
        try {
            tableDao.addColumn(column);
            tableDao.insertSystemColumn(column);
        } catch (BadSqlGrammarException e) {
            throw new MySQLException(e.getSQLException().getErrorCode());
        } catch (Exception e) {
            throw new MySQLException(500, "服务器出错[" + e.getMessage() + "]");
        }
    }

    @Transactional
    @Override
    public void modifyColumn(Column column) throws MySQLException {
        try {
            tableDao.modifyColumn(column);
            tableDao.updateSystemColumnByColumnName(column);
        } catch (BadSqlGrammarException e) {
            throw new MySQLException(e.getSQLException().getErrorCode());
        } catch (Exception e) {
            throw new MySQLException(500, "服务器出错[" + e.getMessage() + "]");
        }
    }

    @Transactional
    @Override
    public void deleteColumn(Column column) throws MySQLException {
        try {
            tableDao.deleteColumn(column);
            tableDao.deleteSystemColumnByColumnName(column);
        } catch (BadSqlGrammarException e) {
            throw new MySQLException(e.getSQLException().getErrorCode());
        } catch (Exception e) {
            throw new MySQLException(500, "服务器出错[" + e.getMessage() + "]");
        }
    }

    @Override
    public int addData(Map<String, Object> dataMap) throws MySQLException {
        try {
            Integer res = tableDao.insertData(dataMap);
            return res != null && res > 0 && dataMap.containsKey("id") ? (int) dataMap.get("id") : -1;
        } catch (BadSqlGrammarException e) {
            throw new MySQLException(e.getSQLException().getErrorCode());
        } catch (Exception e) {
            throw new MySQLException(500, "服务器出错[" + e.getMessage() + "]");
        }
    }

    @Override
    public int editData(Map<String, Object> dataMap) throws MySQLException {
        try {
            return tableDao.updateData(dataMap);
        } catch (BadSqlGrammarException e) {
            throw new MySQLException(e.getSQLException().getErrorCode());
        } catch (Exception e) {
            throw new MySQLException(500, "服务器出错[" + e.getMessage() + "]");
        }
    }

    @Override
    public int deleteData(Map<String, Object> dataMap) throws MySQLException {
        try {
            return tableDao.deleteData(dataMap);
        } catch (BadSqlGrammarException e) {
            throw new MySQLException(e.getSQLException().getErrorCode());
        } catch (Exception e) {
            throw new MySQLException(500, "服务器出错[" + e.getMessage() + "]");
        }
    }

    @Override
    public List<LinkedHashMap<String, Object>> findListData(FindData data) throws MySQLException {
        try {
            return tableDao.selectListByExample(CommonExampleFactory.createCommonExample(data));
        } catch (BadSqlGrammarException e) {
            throw new MySQLException(e.getSQLException().getErrorCode());
        } catch (Exception e) {
            throw new MySQLException(500, "服务器出错[" + e.getMessage() + "]");
        }
    }

    @Override
    public Long findListCount(FindData data) throws MySQLException {
        try {
            return tableDao.selectListCountByExample(CommonExampleFactory.createCommonExample(data));
        } catch (BadSqlGrammarException e) {
            throw new MySQLException(e.getSQLException().getErrorCode());
        } catch (Exception e) {
            throw new MySQLException(500, "服务器出错[" + e.getMessage() + "]");
        }
    }

    @Override
    public LinkedHashMap<String, Object> findOneData(FindData data) throws MySQLException {
        try {
            return tableDao.selectOneByExample(CommonExampleFactory.createCommonExample(data));
        } catch (BadSqlGrammarException e) {
            throw new MySQLException(e.getSQLException().getErrorCode());
        } catch (Exception e) {
            throw new MySQLException(500, "服务器出错[" + e.getMessage() + "]");
        }
    }

    @Override
    public List<LinkedHashMap<String, Object>> findListData(CommonExample example) throws MySQLException {
        try {
            return tableDao.selectListByExample(example);
        } catch (BadSqlGrammarException e) {
            throw new MySQLException(e.getSQLException().getErrorCode());
        } catch (Exception e) {
            throw new MySQLException(500, "服务器出错[" + e.getMessage() + "]");
        }
    }

    @Override
    public Long findListCount(CommonExample example) throws MySQLException {
        try {
            return tableDao.selectListCountByExample(example);
        } catch (BadSqlGrammarException e) {
            throw new MySQLException(e.getSQLException().getErrorCode());
        } catch (Exception e) {
            throw new MySQLException(500, "服务器出错[" + e.getMessage() + "]");
        }
    }

    @Override
    public LinkedHashMap<String, Object> findOneData(CommonExample example) throws MySQLException {
        try {
            return tableDao.selectOneByExample(example);
        } catch (BadSqlGrammarException e) {
            throw new MySQLException(e.getSQLException().getErrorCode());
        } catch (Exception e) {
            throw new MySQLException(500, "服务器出错[" + e.getMessage() + "]");
        }
    }

    @Override
    public List<LinkedHashMap<String, Object>> executeSql(String sql) throws MySQLException {
        // 需要sql的检验 不过先不做了 自己开发的 后期后台重新开发
        try {
            return tableDao.executeSql(sql);
        } catch (BadSqlGrammarException e) {
            throw new MySQLException(e.getSQLException().getErrorCode());
        } catch (Exception e) {
            throw new MySQLException(500, "服务器出错[" + e.getMessage() + "]");
        }
    }


}
