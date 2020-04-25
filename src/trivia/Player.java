package trivia;

public class Player {
	String name;
	int points;

	public Player() {
		// TODO Auto-generated constructor stub
		name=null;
		points=0;
	}

	public Player(String name, int points){
		this.name=name;
		this.points=points;
	}
	
	public Player(String name){
		this.name=name;
		points=0;
	}

	public String toString(){
		String printed=("\nPlayer name: "+this.name);
		printed+=("\n\tPoints scored: "+this.points);
		return printed;
	}

}
