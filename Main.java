import java.io.File;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CheckChangeInDirectory("D:/temp2");
		
		for(;;){
			try {
				Thread.currentThread().sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Main thread is still alive");
		}
		
	}
	
	

}
