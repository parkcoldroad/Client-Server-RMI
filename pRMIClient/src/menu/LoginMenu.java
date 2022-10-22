package menu;


import service.LoginService;

public enum LoginMenu implements Menu {//인터페이스 상속 후, 모든 메뉴 다 enum으로 처리
	LOGIN(() -> LoginService.getInstance().initialize(),"1"),
	REGISTER(() -> LoginService.getInstance().initialize(),"2");

	private final Runnable runnable;
	private final String keyword;


	LoginMenu(Runnable runnable,String keyword) {
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
			System.out.println("\n-----------------Login Menu--------------------");
			System.out.println("1.Login");
			System.out.println("2.Register");
	}
}
