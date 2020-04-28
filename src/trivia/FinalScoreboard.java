package trivia;
import java.util.HashSet;

//extends parent class Scoreboard
public class FinalScoreboard extends Scoreboard {

	//takes from parent class arrays playerpoints and playername
	public FinalScoreboard(Player[] players) {
		super(players);
	}
	
	
	public static HashSet<String> findWinner(){
		/*
		 * findWinner() looks through the players and see which has the highest
		 * points, it returns a hashset containing these players
		 */
		//chose to use HashSet so can have multiple winners listed
		HashSet<String> winnersSet= new HashSet<String>();
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
		
		return winnersSet;
	}
	
	
	public String toString(){
		//the toString method displays the final winner/winners
		String toPrint="\nWinner(s) is/are: "+ (findWinner()).toString();
		return toPrint;
		
	}
	

}
