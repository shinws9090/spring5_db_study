package spring5_db_study.spring;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberListPrint {
	
	@Autowired
	private MemberDao memberDao; //연관관계 ,메소드에 매계변수로만 있으면 의존관계
	@Autowired
	private MemberPrint print;
	


	
	public void selectAll() {
		Collection<Member> members = memberDao.selectAll();
		members.forEach(m->print.print(m));
	}
	
}
