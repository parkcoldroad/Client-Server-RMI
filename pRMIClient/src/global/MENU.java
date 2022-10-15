package global;


import pRMI.Client;

public enum MENU implements MenuInterface {//인터페이스 상속 후, 모든 메뉴 다 enum으로 처리
	CREATE(() -> Client.getInstance().create(),"1"),
	READ(() -> Client.getInstance().read(),"2"),
	SEARCH(() -> Client.getInstance().search(),"3"),
	UPDATE(() -> Client.getInstance().update(),"4"),
	DELETE(() -> Client.getInstance().delete(),"5"),
	QUIT(() -> Client.getInstance().quit(),"6");
	
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
	public String getKeyword() {
		return this.keyword;
	}

	public static void printMenu(){
			System.out.println("\n-----------------MENU--------------------");
			System.out.println("1.Create");
			System.out.println("2.Read");
			System.out.println("3.Search");
			System.out.println("4.Update");
			System.out.println("5.Delete");
			System.out.println("6.Quit");
	}
}
