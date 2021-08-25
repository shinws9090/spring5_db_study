package spring5_db_study;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring5_db_study.config.AppConfImport;
import spring5_db_study.spring.ChangePasswordService;
import spring5_db_study.spring.MemberInfoPrint;
import spring5_db_study.spring.MemberRegisterService;
import spring5_db_study.spring.RegisterRequst;
import spring5_db_study.spring.MemberListPrint;
import spring5_db_study.spring.VersionPrint;
import spring5_db_study.spring.exception.DuplicateMemberException;
import spring5_db_study.spring.exception.MemberNotFoundException;
import spring5_db_study.spring.exception.WrongIdPasswordException;

public class MainForSpring {
	
	public static void main(String[] args) {
		Scanner scr = new Scanner(System.in);
		while(true) {
			System.out.println("명령어를 입력하세요");
			String command = scr.nextLine();
			if(command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
			}
			if(command.startsWith("new ")) {
				processNewCommand(command.split(" "));
				continue;
			}
			if(command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
				continue;
			}
			if(command.startsWith("list")) {
				processListCommand();
				continue;
			}
			if(command.startsWith("info ")) {
				processInfoCommand(command.split(" "));
				continue;
			}
			if(command.startsWith("version")) {
				processVersionCommand();
				continue;
			}
			printHelp();
		}
				
	}
	







	private static ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfImport.class);
	
	private static void processNewCommand(String[] arg) {
		if(arg.length !=5) {
			printHelp();
			return;
		}
		MemberRegisterService regSvc = ctx.getBean("getMemberRegisterService",MemberRegisterService.class);
		RegisterRequst req = new RegisterRequst();
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);
		
		if(!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호와 확인이 일치하지 않습니다.\n");
			return;
		}
		try {
			regSvc.regist(req);
			System.out.println("등록했습니다.");
		}catch(DuplicateMemberException e){
			System.out.println("이미존제하는 이메일 입니다.\n");
		}
	}

	private static void processChangeCommand(String[] arg) {
		if(arg.length !=4) {
			printHelp();
			return;
		}
		ChangePasswordService changePwdSvc = ctx.getBean("getChangePasswordService",ChangePasswordService.class);
		try {
			changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
			System.out.println("암호를 변경했습니다.\n");
		}catch(MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일 입니다.\n");
		}catch(WrongIdPasswordException e){
			System.out.println("이메일과 암호가 일치하지 않습니다.\n");
		}
		
	}
	
	private static void processListCommand() {
		MemberListPrint selSvc =ctx.getBean("getSelectService",MemberListPrint.class);
		selSvc.selectAll();
//		Map<String, Member> map = selSvc.selectAll();
//		for(Entry<String, Member> m : map.entrySet()) {
//			System.out.println(m.getKey()+">>>>>>"+m.getValue());
//		}
	}
	private static void processInfoCommand(String[] arg) {
		if(arg.length !=2) {
			printHelp();
			return;
		}
		MemberInfoPrint inSvc =ctx.getBean("getMemberInfoPrint",MemberInfoPrint.class);
		inSvc.printMemberInfo(arg[1]);
	}

	private static void processVersionCommand() {
		VersionPrint print = ctx.getBean("versionPrint",VersionPrint.class);
		print.print();
		
	}
	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
		System.out.println("명령어사용법");
		System.out.println("new 이메일 이름 암호 암호확인");
		System.out.println("change 이메일 현재비번 변경비번");
		System.out.println();
	}

}
