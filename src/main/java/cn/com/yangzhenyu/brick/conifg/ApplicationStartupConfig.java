package cn.com.yangzhenyu.brick.conifg;

import cn.com.yangzhenyu.brick.dao.TableDao;
import cn.com.yangzhenyu.brick.interceptor.SqlInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/4/17
 * Time: 15:34
 */
@Component
public class ApplicationStartupConfig implements CommandLineRunner {

    @Resource
    private SqlInterceptor sqlInterceptor;

    @Resource
    private TableDao tableDao;

    @Override
    public void run(String... args) throws Exception {
        tableDao.initSystemTables();
    }

    /**
     * MyBatis 拦截器
     *      第一个是用来拦截Sql语句记录的。
     * @return
     */
    @Bean
    public Interceptor[] getInterceptor(){
        return new Interceptor[]{sqlInterceptor};
    }
}
