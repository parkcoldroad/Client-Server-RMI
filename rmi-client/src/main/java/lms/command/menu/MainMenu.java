package lms.command.menu;


import lms.command.CourseCmd;
import lms.command.EnrollmentCmd;
import lms.command.UserCmd;
import lms.rmi.Client;
import lms.utils.Log;
import lms.utils.Session;

public enum MainMenu implements Menu {//인터페이스 상속 후, 모든 메뉴 다 enum으로 처리
	User(UserCmd::initialize,"1"),
	COURSE(CourseCmd::initialize,"2"),
	ENROLLMENT(EnrollmentCmd::initialize,"3"),
	LOGOUT(()->Session.getSession().exit(),"4"),
	LOG(Log::readLog,"5"),
	QUIT(Client::quit,"6");
	
	private final Runnable runnable;
	private final String keyword;


	MainMenu(Runnable runnable,String keyword) {
		this.runnable = runnable;
		this.keyword = keyword;
	}
	@Override
	public void execute() {
		this.runnable.run();
	}
	@Override
	public String getChoice() {
		return this.keyword;
	}

	public static void printMenu(){
			System.out.println("\n-----------------Main Menu--------------------");
			System.out.println("1.User");
			System.out.println("2.Course");
			System.out.println("3.Enrollment");
			System.out.println("4.Logout");
			System.out.println("5.Log");
			System.out.println("6.Quit");
	}
}
