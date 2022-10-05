package pRMI;


enum CRUD {
	CREATE(() -> Client.getInstance().create()),
	READ(() -> Client.getInstance().read()),
	UPDATE(() -> Client.getInstance().update()),
	DELETE(() -> Client.getInstance().delete()),
	QUIT(() -> Client.getInstance().quit());
	
	private Runnable runnable;


	CRUD(Runnable runnable) {
		this.runnable = runnable;
	}
	
	public void execute() {
		this.runnable.run();
	}
}
