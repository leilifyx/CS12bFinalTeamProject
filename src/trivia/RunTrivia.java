package trivia;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class RunTrivia {
	static Player[] players;

	public RunTrivia() {
		// TODO Auto-generated constructor stub
	}
	
	public static Player[] getPlayers(){
		Scanner scan=new Scanner(System.in);
		System.out.println("How many people are playing");
		int numPlaying=scan.nextInt();
		String blank=scan.nextLine(); //was scanning in extra line
		players= new Player[numPlaying];
		for(int i=0; i<numPlaying; i++){
			System.out.println("Enter name of player"+(i+1)+":");
			String tempNamePlayer=scan.nextLine();
			players[i]=new Player(tempNamePlayer);
		}
		return players;
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		getPlayers();
		for(int i=0; i<players.length; i++){
			System.out.print(players[i]);
		}
		//Scoreboard scoreboard=new Scoreboard(players);
		
	}

}
