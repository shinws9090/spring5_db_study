package spring5_db_study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring5_db_study.spring.MemberDao;
import spring5_db_study.spring.MemberPrint;

@Configuration
public class AppCtx1 {
	
//	@Bean
//	public MemberDao getMemberDao() {
//		return new MemberDao();
//	}

	@Bean
	public MemberPrint getMemberPrint() {
		return new MemberPrint();
	}

}
