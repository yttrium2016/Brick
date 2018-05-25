package cn.com.yangzhenyu.brick.dao;

import cn.com.yangzhenyu.brick.example.CommonExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2017/7/12
 * Time: 9:46
 */
@Mapper
@Repository
public interface CommonDao {


    int init();

    int initData();

    /**
     * 新建实体
     *
     * @param map key：value
     *            entityName：实体名字
     * @return
     */
    int createEntity(Map map);

    /**
     * 删除实体
     *
     * @param map key：value
     *            entityName：实体名字
     * @return
     */
    int deleteEntity(Map map);

    /**
     * 实体添加字段
     *
     * @param map key：value
     *            entityName：实体名字
     *            columns:添加的字段 List<String>
     *            例：“ name varchar(100) ”
     */
    void addEntityColumns(Map map);

    /**
     * 实体删除字段
     *
     * @param map key：value
     *            entityName：实体名字
     *            columns:添加的字段 List<String>
     *            例：“ name ”
     */
    void deleteEntityColumns(Map map);

    /**
     * 添加数据
     *
     * @param map key：value
     *            entityName：实体名字
     *            //keys：添加的字段 List<String>
     *            //例：“ name ”
     *            //已经不用了 新的写法
     *            params：对应字段的值 Map<String,Object>
     *            例：“ 'name':'yzy' ”
     *            例：“ 'age': 18 ”
     *            error result = -1
     */
    int insertData(Map map);

    /**
     * 获取实体列表包含分页
     *
     * @param map key：value
     *            entityName：实体名字
     *            params:筛选条件 List<String>
     *            例：“ name like '%yzy%' ” 不用 And
     *            pageIndex：从第几条开始
     *            pageSize：查找几条
     * @return
     */
    List<HashMap<String, Object>> getEntityList(Map map);

    /**
     * 获取单条实体
     *
     * @param map key：value
     *            entityName：实体名字
     *            id：id序号
     * @return
     */
    HashMap<String, Object> getEntityOne(Map map);

    /**
     * 获取实体的总条目
     *
     * @param map key：value
     *            entityName：实体名字
     *            params:筛选条件 List<String>
     * @return
     */
    long getEntityCount(Map map);

    List<LinkedHashMap<String, Object>> getEntityAllList(Map map);

    List<LinkedHashMap<String, Object>> selectEntityListByExample(CommonExample example);

    /**
     * 通过查询一条数据
     *
     * @param example 例子参数
     * @return 对应实体一条数据
     */
    LinkedHashMap<String, Object> selectEntityOneByExample(CommonExample example);


    long selectEntityCountByExample(CommonExample example);

    List<LinkedHashMap<String, Object>> selectEntityList(@Param("entityInfo") Map entityInfo, @Param("example") CommonExample example);

}
