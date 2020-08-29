import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class RPSInterface2 
{
	public static void main(String[] args) throws InterruptedException
	{
		System.out.println("Would you like to play?");
		System.out.println("Press 1 to continue, 2 to quit");
		
		int userInput;
		int computerInput;
		Scanner s = new Scanner(System.in);
		
		userInput = s.nextInt();
		
		int gameNum = 0;
		
		ArrayList <RockPaperScissorsStats> gameArray = new ArrayList <RockPaperScissorsStats>();
		
		double rockP = 33;
		double paperP = 33;
		double scissorP = 34;
		
		while (userInput < 2)                          //first 10 games the computer randomly generates answers
		{
			gameNum++;
			
			System.out.println("Enter 1 for rock, 2 for paper, 3 for scissors");       //Explanation
			
			rpsPreMatchSaying();                                                       //Prematch stuff
			
			userInput = s.nextInt();                                                   //assigns value to user and computer input
			computerInput = oneTwoThree(rockP, paperP, scissorP);
			
			if(userInput < 1 || userInput > 3)                                         // illegal argument 
				throw new IllegalArgumentException("Invalid Input.");
			
			showHand(computerInput, userInput);                                        //shows users the plays
			
			int result = rockPaperScissors(computerInput, userInput);                  //calculates result
			
			System.out.println(winDrawLose(result));                                   //reveals if user won or lost
			
			boolean didUserWin = convertUserResultBoolean(result);                        //did user win?
			boolean didComputerWin = convertComputerResultBoolean(result);                //did computer win?
			
			RockPaperScissorsStats game = new RockPaperScissorsStats(computerInput, userInput, didUserWin, didComputerWin, gameNum);
			gameArray.add(game);
			
			TimeUnit.SECONDS.sleep(1);
			
			double[] tempArray = rpsAnalysis(gameArray, userInput, result);
			
			rockP = tempArray[2];
			paperP = tempArray[0];
			scissorP = tempArray[1];
			
			System.out.println("Press 1 to continue, 2 to quit");
			
			userInput = s.nextInt();
		}
		
		System.out.println("Thank You For Playing!");
	}
	
	public static int rockPaperScissors(int computer, int user)    //1 is user lose, 2 is draw, 3 is user win
	{
		if (user == 1)
		{
			if (computer == 1)
				return 2;
			else if (computer == 2)
				return 1;
			else
				return 3;
		}
		else if (user == 2)
		{
			if (computer == 1)
				return 3;
			else if (computer == 2)
				return 2;
			else 
				return 1;
		}
		else
		{
			if (computer == 1)
				return 1;
			else if (computer == 2)
				return 3;
			else
				return 2;
		}
	}
	
	public static int oneTwoThree(double rockPercent, double paperPercent, double scissorPercent)               //generates either a one, two, or three (temporarily)
	{
		double oneTwoOrThree = (Math.random() * (100-1) + 1);
		
		if (oneTwoOrThree <= rockPercent)
			return 1;
		else if (oneTwoOrThree<= rockPercent + paperPercent)
			return 2;
		else
			return 3;
	}
	
	public static String winDrawLose(int theResult)   //Prints if you won, lost, or draw
	{
		if (theResult == 1)
			return "You Lost!";
		else if (theResult == 2)
			return "It's A Draw!";
		else
			return "You Won!";
	}
	
	public static void rpsPreMatchSaying() throws InterruptedException     //Says the rock paper scissors shoot stuff
	{
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Rock");
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Paper");
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Scissors");
		TimeUnit.SECONDS.sleep(1);
		System.out.println("SHOOT!");
	}
	
	public static void showHand(int computer, int user)        //shows what the computer and user drew
	{
		String compHand;
		String userHand;
		
		if(computer == 1)
			compHand = "Rock     o";
		else if (computer == 2)
			compHand = "Paper    -";
		else
			compHand = "Scissor  >";
		
		if(user == 1)
			userHand = "Rock     o";
		else if (user == 2)
			userHand = "Paper    -";
		else 
			userHand = "Scissor  >";
		
		System.out.println("Computer: " + compHand);
		System.out.println("You:      " + userHand);
	}
	
	public static boolean convertUserResultBoolean(int outcome)
	{
		if(outcome == 1 || outcome == 2)
			return false;
		else
			return true;
	}
	
	public static boolean convertComputerResultBoolean(int outcome)
	{
		if(outcome == 3 || outcome == 2)
			return false;
		else
			return true;
	}
	
	public static double[] rpsAnalysis(ArrayList<RockPaperScissorsStats> gamesArray, int userPlay, int gameResult)
	{
		double[] futurePlay = new double[3];
		int size = gamesArray.size();
		
		int rockAmount = 0;
		int paperAmount = 0;
		int scissorAmount = 0;
		int total;
		
		for (int a = 0; a < size - 1; a++)
		{
			int userOutcome = gamesArray.get(a).getUserInput();
			boolean didUserWin = gamesArray.get(a).getUserWin();
			boolean didCompWin = gamesArray.get(a).getComputerWin();
			
			if (userOutcome == userPlay)
			{
				if (gameResult == 3 && didUserWin && !didCompWin)
				{
					switch (gamesArray.get(a+1).getUserInput())
					{
						case 1: rockAmount++;
							break;
						case 2: paperAmount++;
							break;
						case 3: scissorAmount++;
							break;
						default: System.out.println("rip");
							break;
					}
				}
				else if (gameResult == 2 && !didUserWin && !didCompWin)
				{
					switch (gamesArray.get(a+1).getUserInput())
					{
						case 1: rockAmount++;
							break;
						case 2: paperAmount++;
							break;
						case 3: scissorAmount++;
							break;
						default: System.out.println("rip");
							break;
					}
				}
				else if (gameResult == 3 && !didUserWin && didCompWin)
				{
					switch (gamesArray.get(a+1).getUserInput())
					{
						case 1: rockAmount++;
							break;
						case 2: paperAmount++;
							break;
						case 3: scissorAmount++;
							break;
						default: System.out.println("rip");
							break;
					}
				}
			}
		}
		
		total = rockAmount + paperAmount + scissorAmount;
		
		if (total == 0)
		{
			futurePlay[0] = 33;
			futurePlay[1] = 33;
			futurePlay[2] = 34;
		}
		else
		{
			futurePlay[0] = (double) (rockAmount / total);
			futurePlay[1] = (double) (paperAmount / total);
			futurePlay[2] = (double) (scissorAmount / total);
		}
		
		return futurePlay;
	}
	
	
}
