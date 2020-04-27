package trivia;

public class FinalScoreboard extends Scoreboard {

	public FinalScoreboard(Player[] players) {
		super(players);
		System.out.println("Final score");
		playerpoints=new int[players.length];
		playername=new String[players.length];
		for(int i=0; i<playerpoints.length; i++){
			for (int j = i + 1; j < playerpoints.length; j++){
					if ( playerpoints[i] > playerpoints[j])
					{
							int temp = playerpoints[i];
							 playerpoints[i] = playerpoints[j];
							 playerpoints[j] = temp;
					}
			}
		String toPrintString = (playerName[i-1] + "Wins!");
		return ;

		}

	}

}
