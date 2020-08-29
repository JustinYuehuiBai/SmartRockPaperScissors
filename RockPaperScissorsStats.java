
public class RockPaperScissorsStats 
{
	private int computerInputStat;
	private int userInputStat;
	private boolean computerWin;
	private boolean userWin;
	private int gameNumber;
	
	public RockPaperScissorsStats(int computerInputStat, int userInputStat, boolean userWin, boolean computerWin, int gameNumber)
	{
		this.computerInputStat = computerInputStat;
		this.userInputStat = userInputStat;
		this.userWin = userWin;
		this.computerWin = computerWin;
		this.gameNumber = gameNumber;
	}
	
	public int getComputerInput()
	{
		return computerInputStat;
	}
	
	public int getUserInput()
	{
		return userInputStat;
	}
	
	public boolean getUserWin()
	{
		return userWin;
	}
	
	public boolean getComputerWin()
	{
		return computerWin;
	}
	
	public int getGameNumber()
	{
		return gameNumber;
	}
}
