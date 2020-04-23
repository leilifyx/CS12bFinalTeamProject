package trivia;

public class Player {
	String name;
	int points;

	public Player() {
		// TODO Auto-generated constructor stub
	}

	public Player(String name, int points){
		this.name=name;
		this.points=points;
	}

	public String toString(){
		System.out.println("Player name: "+this.name);
		System.out.println("Points scored: "+this.points);
	}

}
