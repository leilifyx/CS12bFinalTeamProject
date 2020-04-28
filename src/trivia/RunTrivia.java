package trivia;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;

public class RunTrivia {
	static Scanner scan=new Scanner(System.in);
	static Player[] players;
	static int numRound=0;
	int numQuestions=0;
	static Question[] allQuestions;
	static Scoreboard scoreboard;

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

	public static void tick() {
		
		System.out.println("\nRound " + (numRound+1)); //prints what Round is on
		Question toAsk=askQuestion();
		//System.out.println(readyToAsk(toAsk));
		String asking=readyToAsk(toAsk);
		int[] answers= getEachPersonsAnswer(asking);
		checkAnswers(toAsk, answers);
		scoreboard=new Scoreboard(players);
		System.out.println(scoreboard);
		numRound++;
	}
	
	
	
	public static Question askQuestion(){
		String[] tempQuess= new String[allQuestions[numRound].getSize()];
		for(int i=0; i<tempQuess.length; i++){
			tempQuess[i]=allQuestions[numRound].getQuesAtLoc(i);
		}
		Question theQuestion= new Question(tempQuess);
		return theQuestion.shuffleAnswers(theQuestion);	
	}
	
	public static String readyToAsk(Question questioning){
		String toAsk="\n"+questioning.getQuesAtLoc(0)+"\n";
		for(int i=1; i<(questioning.getSize()); i++){
			toAsk+=((i)+ " - " + questioning.getQuesAtLoc(i)+ "\n");
		}
		return toAsk;
	}
	
	public static int[] getEachPersonsAnswer(String asking){
		int[] theirAnswers= new int[players.length];
		for(int i=0; i<players.length; i++){
			System.out.println(asking);
			System.out.println(players[i].getName()+", what is your answer?");
			theirAnswers[i]=scan.nextInt();
			noPeeking();
		}
		return theirAnswers;
		
	}
	
	public static void checkAnswers(Question asked, int[] answers){
		System.out.println("\nThe correct answer is:\n\t"+(allQuestions[numRound].getQuesAtLoc(1)));
		for(int i=0; i<answers.length; i++){
			int tempAnswer=answers[i];
			if(tempAnswer<=asked.getSize() && asked.getQuesAtLoc(tempAnswer).equals(allQuestions[numRound].getQuesAtLoc(1))){
				players[i].increasePoints();
			}
		}
	}
	
	

	/*public static void updatePoints(String answer, Question question, Player player){
		if(answer.equals(question.get(1))){
			player.points++;
		}
	}*/
	
	
	

	public static void main(String[] args) throws FileNotFoundException{
		getPlayers();
		/*for(int i=0; i<players.length; i++){
			System.out.print(players[i]);
		}
		*/
		getQuestions();
		/*for(int i=0; i<allQuestions.length; i++){
			System.out.print("\n"+allQuestions[i]);
		}*/
		noPeeking();
		while(numRound<allQuestions.length){
			tick();
		}
		Scoreboard finalScores= new FinalScoreboard(players);
		System.out.println(finalScores.toString());
		System.out.println("Congrats, you have finished the game");
	}
}
