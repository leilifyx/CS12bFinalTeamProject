package trivia;
import java.util.Scanner;

public class Scoreboard {
	static int[] playerpoints;
	static String[] playername; 

	public Scoreboard(Player[] players) {
		 playerpoints=new int[players.length];
		 playername=new String[players.length];
		 for(int i=0; i<players.length; i++){
			 playerpoints[i]=players[i].getPoints();
			 String tempPlayerName=players[i].getName();
			 if(tempPlayerName.length()>5){
				 tempPlayerName=tempPlayerName.substring(0,5)+".";
			 }
			 playername[i]=tempPlayerName;
		 }

	}
	
	public String toString(){
		String toPrintString="\nScore Board: \n";
		toPrintString+="\n\tName\tPoints\n";
		for(int i=0; i<playername.length;i++){
			for(int j=0; j<2; j++){
				if(j==0){
					toPrintString+="\n\t"+playername[i]+"\t";
				}
				else{
					toPrintString+=(playerpoints[i]+"\n");
				}
			}
		}
		return toPrintString;
	}

}
