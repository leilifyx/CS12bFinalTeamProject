package trivia;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;

public class RunTrivia {
	static Scanner scan=new Scanner(System.in);
	static Player[] players;
	int numRound=0;
	int numQuestions=0;
	static Question[] allQuestions; 
	Scoreboard scoreboard;

	public RunTrivia() {
		// TODO Auto-generated constructor stub
	}
	
	public static Player[] getPlayers(){
		
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
	
	public static void getQuestions() throws FileNotFoundException{
		System.out.println("\n\nWould you like to:\n1 - use our questions\n2 - enter your own questions");
		int answer=scan.nextInt();
		if(answer==1){
			getSetQuestions();
		}
		else if (answer==2){
			getQuestionsFromInput();
		}
		else{
			System.out.println("invalid answer");
			getQuestions();
		}
	
	}
	
	public static void getQuestionsFromInput(){
	    Scanner in=new Scanner(System.in);
	    System.out.println("How many questions do you want?");
	    int numOfQuestions=in.nextInt();
	    //Question[] questions=new Question[numOfQuestions];
	    System.out.println("How many answer choices do you want per question?");
	    int numOfChoices=in.nextInt();
	    allQuestions=new Question[numOfQuestions];
	    int tracker=0;
	    String blank=in.nextLine();//was scanning extra line
	    while(tracker<numOfQuestions){
	    	ArrayList<String> tempQuesArrayList= new ArrayList<String>();
	    	Question tempQuestion=new Question(tempQuesArrayList);
	    	System.out.println("What is question number" + (tracker+1)+"?");
	    	tempQuestion.addToEnd(in.nextLine());
	    	System.out.println("What is the correct answer?");
	    	tempQuestion.addToEnd(in.nextLine());
	    	for(int i=0; i<numOfChoices-1;i++){
	    		System.out.println("What is wrong answer number" +(i+1)+"?");
		    	tempQuestion.addToEnd(in.nextLine());
	    	}
	    	allQuestions[tracker]=tempQuestion;
	    	tracker++;
	    }
	}
	
	public static void getSetQuestions() throws FileNotFoundException{
		String blank=scan.nextLine();
		System.out.println("enter file's name");
		String fileName=scan.nextLine();
		File file= new File(fileName);
		readFile(file);
	}
	
	public static void readFile(File file) throws FileNotFoundException{
		
		Scanner scanFile=new Scanner(file);
		int numQuestions=0;
		while(scanFile.hasNextLine()){
			scanFile.nextLine();
			numQuestions++;
		}
		numQuestions/=5; //every 5 lines is a new question
		//System.out.println(numQuestions);
		readInQuestions(file, numQuestions);
		
	}
	
	public static void readInQuestions(File file, int numQuestions) throws FileNotFoundException{
		Scanner scanFile=new Scanner(file);
		allQuestions= new Question[numQuestions];
		int tempLineOn=0;
		int tempQuestionOn=0;
		String[] temp= new String[5];
		while(scanFile.hasNextLine()){
			//System.out.println("tempQuestionOn= " + tempQuestionOn);
			//System.out.println("tempLineOn= " + tempLineOn);
			temp[tempLineOn]=scanFile.nextLine();
			tempLineOn++;
			if(tempLineOn==5){
				allQuestions[tempQuestionOn]=new Question(temp);
				tempQuestionOn++;
				tempLineOn=0;
			}
			if(tempQuestionOn==numQuestions){
				break;
			}
			
		}
		/*for(int i=0; i<numQuestions; i++){
			System.out.print(allQuestions[i]);
		}*/
	}
	
	public static void noPeeking(){
		String stars="* * * * * * * * * * * * * * * * * * * * * * * * * ";
		for(int i=0; i<50; i++){
			System.out.println(stars+stars);
		}
	}
	
	public void tick() {
		numRound++;
		System.out.println("\nRound " + numRound); //prints what Round is on
		
	}
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws FileNotFoundException{
		//getPlayers();
		/*for(int i=0; i<players.length; i++){
			System.out.print(players[i]);
		}
		*/
		getQuestions();
		//scoreboard=new Scoreboard(players);
		//noPeeking();
		
		for(int i=0; i<allQuestions.length; i++){
			System.out.print("\n"+allQuestions[i]);
	}

}
}
