package cn.com.yangzhenyu.brick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
public class BrickApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrickApplication.class, args);
	}
}
