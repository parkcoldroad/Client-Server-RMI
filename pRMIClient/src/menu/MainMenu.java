package menu;


import pRMI.Client;
import service.CourseService;
import service.StudentService;

public enum MainMenu implements Menu {//인터페이스 상속 후, 모든 메뉴 다 enum으로 처리
	STUDENT(() -> StudentService.getInstance().initialize(),"1"),
	COURSE(() -> CourseService.getInstance().initialize(),"2"),
	QUIT(() -> Client.getInstance().quit(),"3");
	
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
			System.out.println("\n-----------------MENU--------------------");
			System.out.println("1.Student");
			System.out.println("2.Course");
			System.out.println("3.Quit");
	}
}
