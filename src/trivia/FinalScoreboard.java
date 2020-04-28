package trivia;
import java.util.HashSet;


public class FinalScoreboard extends Scoreboard {

	public FinalScoreboard(Player[] players) {
		super(players);
	}
	
	public static HashSet<String> findWinner(){
		//chose to use HashSet so can have multiple winners listed
		HashSet<String> winnersSet= new HashSet<String>();
		String Winner="";
		int temp1=0;
		int temp2=0;
		//finding largest score anyone has
		for(int k=0; k<playerpoints.length; k++){
			 temp2=playerpoints[k];
			 if(temp2>temp1){
				 temp1=temp2;
			 }
		}
		for(int i=0; i<playerpoints.length; i++){
			if(playerpoints[i]==temp1){
				winnersSet.add(playername[i]);
			}
		}
		/*
		for(int i=0; i<playerpoints.length-1; i++){
			for (int j = i + 1; j < playerpoints.length; j++){
					if ( playerpoints[i] > playerpoints[j])
					{
							int temp = playerpoints[i];
							 playerpoints[i] = playerpoints[j];
							 playerpoints[j] = temp;
					}
			}
			Winner = (playername[i-1]);
		}
		return Winner;
		*/
		return winnersSet;
	}
	
	public String toString(){
		String toPrint="\nWinner(s) is/are: "+ (findWinner()).toString();
		return toPrint;
		
	}
	

}
