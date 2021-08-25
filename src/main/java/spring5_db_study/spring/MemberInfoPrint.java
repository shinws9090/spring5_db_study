package spring5_db_study.spring;

import org.springframework.beans.factory.annotation.Autowired;

import spring5_db_study.spring.exception.MemberNotFoundException;

public class MemberInfoPrint {
	
	@Autowired //setter() 대용 자동주입기능 @bean 설정되어있는것만
	private MemberDao memberDao;
	@Autowired
	private MemberPrint print;
	
	public void printMemberInfo(String email) {
		Member member =memberDao.selectByEmail(email);
		if(member == null) {
			throw new MemberNotFoundException("없다");
		}
		print.print(member);
		System.out.println();
	}
	
}
