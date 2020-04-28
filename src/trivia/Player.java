package trivia;


public class Player {
	String name;
	int points;

	public Player() {
		// TODO Auto-generated constructor stub
		//default constructor, null name, 0 points to start
		name=null;
		points=0;
	}

	public Player(String name, int points){
		//constructor, given name and points, start with those values
		this.name=name;
		this.points=points;
	}
	
	
	public Player(String name){
		//constructor, given name, sets name to name, points start at 0
		this.name=name;
		points=0;
	}
	
	public int getPoints(){
		//return points
		return points;
	}
	
	public String getName(){
		//return name
		return name;
	}
	
	public void setPoints(int newAmount){
		//given new amount of points, set that value for new value of points
		points=newAmount;
	}
	
	public void increasePoints(){
		//increases points by one
		points+=1;
	}

	public String toString(){
		//to String, gives player's name and points
		String printed=("\nPlayer name: "+this.name);
		printed+=("\n\tPoints scored: "+this.points);
		return printed;
	}

}
