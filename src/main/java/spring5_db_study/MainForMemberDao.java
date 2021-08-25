package spring5_db_study;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring5_db_study.config.AppConfImport;
import spring5_db_study.spring.Member;
import spring5_db_study.spring.MemberDao;

public class MainForMemberDao {

	private static MemberDao memberDao;
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");

	public static void main(String[] args) throws IOException {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfImport.class);) {
			DataSource ds = ctx.getBean(DataSource.class);
			System.out.println(ds);
			memberDao = ctx.getBean(MemberDao.class);
//			memberDao.insert(new Member("test@test.co.kr", "1234", "test", LocalDateTime.now()));
			selectAll();
			insertMember();
			selectAll();
			updateMember();
			selectAll();
			deleteMember();
			selectAll();

		}
	}

	private static void insertMember() {
		System.out.println("----- insertMember");
		String prefix = formatter.format(LocalDateTime.now());
		Member member = new Member(prefix + "test.co.kr", prefix, prefix, LocalDateTime.now());
		memberDao.insert(member);
		System.out.println(member.getId() + "데이터 추가");
	}

	private static void updateMember() {
		System.out.println("----- updateMember");
		Member member = memberDao.selectByEmail("test@test.co.kr");
		String oldPw = member.getPassword();
		String newPw = Double.toHexString(Math.random());
		member.changePassword(oldPw, newPw);

		memberDao.update(member);
		System.out.println("암호변경 : " + oldPw + ">" + newPw);
		System.out.println(member.getId() + "데이터수정");
	}

	private static void deleteMember() {
		System.out.println("----- deleteMember");
		Member member = memberDao.selectByEmail("0517121049test.co.kr");
		memberDao.delete(member);
		System.out.println(member.getId() + "데이터삭제");

	}

	private static void selectAll() {
		System.out.println("------------selectAll");
		int total = memberDao.count();
		System.out.println("전체 데이터  :  " + total);
		List<Member> members = memberDao.selectAll();
		for (Member member : members) {
			System.out.printf("%d : %s : %s%n", member.getId(), member.getEmail(), member.getName());
		}
		System.out.println();

	}

}
