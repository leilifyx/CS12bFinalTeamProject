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
		String printed=("Player name: "+this.name);
		printed+=("Points scored: "+this.points);
		return printed;
	}

}
