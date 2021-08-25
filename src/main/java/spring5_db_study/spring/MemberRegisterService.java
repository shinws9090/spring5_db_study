package spring5_db_study.spring;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import spring5_db_study.spring.exception.DuplicateMemberException;

public class MemberRegisterService {
	
	@Autowired
	private MemberDao memberDao; //연관관계 ,메소드에 매계변수로만 있으면 의존관계

	
	
	public Long regist(RegisterRequst req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		if(member != null) {
			throw new DuplicateMemberException("dup email"+req.getEmail());
		}
		Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now());
		memberDao.insert(newMember);
		return newMember.getId();
	}
	
	
}
