package pRMI;

public class DataMain {

	public static void main(String[] args) {
		DataImpl dataImpl = DataImpl.getInstance();
		dataImpl.start();
	}
	
}
