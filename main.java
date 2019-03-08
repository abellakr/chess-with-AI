import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class main {
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		//simulation();
		// EasyAI frick = new EasyAI(yes);
		// hardAI frack = new hardAI(yes);

		displayGame yes = new displayGame();
		
			GameLogic no = new GameLogic(yes);
			no.simulation();
	}//main
	
	
}//class

