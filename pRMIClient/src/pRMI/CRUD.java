package pRMI;


enum CRUD {
	CREATE(() -> Client.getInstance().create(),"1"),
	READ(() -> Client.getInstance().readMenu(),"2"),
	UPDATE(() -> Client.getInstance().update(),"3"),
	DELETE(() -> Client.getInstance().delete(),"4"),
	QUIT(() -> Client.getInstance().quit(),"5");
	
	private Runnable runnable;
	private String keyword;


	CRUD(Runnable runnable,String keyword) {
		this.runnable = runnable;
		this.keyword = keyword;
	}
	
	public void execute() {
		this.runnable.run();
	}
	
	public String getKeyword() {
		return this.keyword;
	}
}
