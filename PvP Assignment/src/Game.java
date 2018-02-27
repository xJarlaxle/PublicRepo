import java.util.Random;

public class Game{

	
	//Random Number generator
	public static int randNumber() {

	    // http://stackoverflow.com/questions/20389890/generating-a-random-number-between-1-and-10-java
	    Random rand = new Random();
	    int max = 20;
	    int min = 1;


	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}//end randNumber
	
	
}
