package spring5_db_study.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ AppCtx1.class, AppCtx2.class, }) // 설정파일 임포트 시켜 이 클래스로 한꺼번에 사용하기
@ComponentScan(basePackages = { "spring5_db_study.spring" })
public class AppConfImport {
	
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost/spring5?useSSL=false");
		ds.setUsername("user_spring5");
		ds.setPassword("rootroot");
		ds.setInitialSize(1000);						// 초기 커넥션 개수 기본값은 10
		ds.setMaxIdle(10);								// 커넥션을 유지할수있는 최대 커넥션 개수
		ds.setMaxActive(10);							// 커넥션풀에서 가져올수있는 최대 커낵션 개수 기본값은 100
		
		//일정시간이 지나면 커넥션 연결이 끊기거나 유지시키는 설정
		ds.setTestWhileIdle(true);  					// 유휴 커넥션 검사
		ds.setMinEvictableIdleTimeMillis(100 * 60 * 3); // 최소 유휴시간 3분
		ds.setTimeBetweenEvictionRunsMillis(1000 * 10); // 10초 주기
		
		return ds;
	}
}
