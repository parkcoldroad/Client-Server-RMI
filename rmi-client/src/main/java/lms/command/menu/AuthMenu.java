package lms.command.menu;


import lms.command.AuthCmd;
import lms.rmi.Client;

public enum AuthMenu implements Menu {
	SignIn(AuthCmd::signIn,"1"),
	SignUp(AuthCmd::signUp,"2"),
	Quit(Client::quit,"3");

	private final Runnable runnable;
	private final String keyword;


	AuthMenu(Runnable runnable,String keyword) {
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
			System.out.println("\n-----------------Auth Menu--------------------");
			System.out.println("1.Sign In");
			System.out.println("2.Sign Up");
			System.out.println("3.Quit");
	}
}
