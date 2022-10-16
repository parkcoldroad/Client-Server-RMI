package global;


import java.io.IOException;
import pRMI.Client;

public enum MENU implements MenuInterface {//인터페이스 상속 후, 모든 메뉴 다 enum으로 처리
	CREATE(() -> {try {Client.getInstance().create();} catch (IOException e) {throw new RuntimeException(e);}},"1"),
	READ(() -> {try {Client.getInstance().read();} catch (IOException e) {throw new RuntimeException(e);}},"2"),
	SEARCH(() -> {try {Client.getInstance().search();} catch (IOException e) {throw new RuntimeException(e);}},"3"),
	UPDATE(() -> Client.getInstance().update(),"4"),
	DELETE(() -> Client.getInstance().delete(),"5"),
	QUIT(() -> {try {Client.getInstance().quit();} catch (IOException e) {throw new RuntimeException(e);}},"6");
	
	private final Runnable runnable;
	private final String keyword;


	MENU(Runnable runnable,String keyword) {
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
			System.out.println("1.Create");
			System.out.println("2.Read");
			System.out.println("3.Search");
			System.out.println("4.Update");
			System.out.println("5.Delete");
			System.out.println("6.Make Reservation"); //수강신청 (courseId,studentId를 붙여놔라, 조건:student Id, courseId, 선수과목 체크 (서버에서 처리)
			System.out.println("7.Quit");
	}
}
