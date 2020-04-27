package trivia;

public class FinalScoreboard extends Scoreboard {

	public FinalScoreboard(Player[] players) {
		super(players);
		System.out.println("Final score");
		playerpoints=new int[players.length];
		playername=new String[players.length];
		for(int i=0; i<playerpoints; i++){
			for (int j = i + 1; j < playerpoints; j++){
					if ( playerpoints[i] >  playerpoints[j])
					{
							temp = playerpoints[i];
							 playerpoints[i] = playerpoints[j];
							 playerpoints[j] = temp;
					}
			}
		toPrintString = (playerName[i-1] + "Wins!");
		return ;

	}


		}


	}


