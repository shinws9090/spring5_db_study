package spring5_db_study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring5_db_study.spring.ChangePasswordService;
import spring5_db_study.spring.MemberInfoPrint;
import spring5_db_study.spring.MemberRegisterService;
import spring5_db_study.spring.MemberListPrint;
import spring5_db_study.spring.VersionPrint;

@Configuration
public class AppCtx2 {
		
	@Bean
	public MemberRegisterService getMemberRegisterService() {
		return new MemberRegisterService();
	}
	@Bean
	public ChangePasswordService getChangePasswordService() {
		return new ChangePasswordService();
	}
	
	@Bean
	public MemberListPrint getSelectService() {
		return new MemberListPrint();
	}
	@Bean
	public MemberInfoPrint getMemberInfoPrint() {
		MemberInfoPrint infoPrint = new MemberInfoPrint();
		return infoPrint;
	}
	@Bean
	public VersionPrint versionPrint() {
		VersionPrint versionPrint = new VersionPrint();
		versionPrint.setMaiorVersion(5);
		versionPrint.setMinorVersion(0);
		return versionPrint;
	}
}
