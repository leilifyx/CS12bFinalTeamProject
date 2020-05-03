package trivia;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;

/*
 * RunTrivia is the class that actually runs the game itself
 */
public class RunTrivia {
	static Scanner scan=new Scanner(System.in);
	static Player[] players;
	static int numRound=0;
	int numQuestions=0;
	static Question[] allQuestions;
	static Scoreboard scoreboard;

	public RunTrivia() {
		// TODO Auto-generated constructor stub
		//default constructor
	}

	public static Player[] getPlayers(){
		/*
		 * getPlayers() is a method that asks for user input of how many people are playing
		 * based on that number it fills the array players with type Player
		 */

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

	public static void getQuestions() throws FileNotFoundException, InputMismatchException{
		/*
		 * getQuestions() gives users the choice to input their own questions or use pre existing ones
		 * 
		 */
		int answer=0;
		System.out.println("\n\nWould you like to:\n1 - use our questions\n2 - enter your own questions");
		String tempAns= scan.nextLine();
		try{
			 answer=Integer.parseInt(tempAns);
		}catch(NumberFormatException e){
			System.out.println("You need to input an integer value");
			getQuestions();
		}
		if(answer==1){
			getSetQuestions();
		}
		else if (answer==2){
			getQuestionsFromInput();
		}
		else{
			System.out.println("invalid answer");
			getQuestions();
			//by recalling getQuestions() for invalid answers, the prompt just repeats until user gives valid input
		}
		

	}

	public static void getQuestionsFromInput(){
		/*
		 * this method allows users to input their own questions, choosing how many questions they want and how many answer choices they'd like to give
		 */
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
	    	System.out.println("\nWhat is question number " + (tracker+1)+"?");
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
		/*
		 * If the user chooses to use the set questions, they choose the name of the file to be scanned
		 */
		
		File folder= new File("questions");
		String[] files= folder.list();
		System.out.println("enter file's name\n\twe currently have:");
		for(String file : files){
			if(!file.equals(".DS_Store")){
				System.out.println("\t  "+file);
			}
		}
		String fileName=scan.nextLine();
		try{
			File file= new File(fileName);
			readFile(file);
		} catch (FileNotFoundException e){
			System.out.println("could not find this file");
			getQuestions();
		}
		
	}

	public static void readFile(File file) throws FileNotFoundException{
		/*
		 * readFile(File file) is a short method that creates a scanner for the file and counts 
		 * how many questions there are
		 */
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
		/*
		 * this method reads in individual questions from the file
		 */
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
		
	}

	public static void noPeeking(){
		/*
		 * the purpose of this method is so if multiple people are playing on one computer
		 * then if they pass the computer back and forth they are not seeing the previous person's answers
		 */
		String stars="* * * * * * * * * * * * * * * * * * * * * * * * * ";
		for(int i=0; i<50; i++){
			System.out.println(stars+stars);
		}
	}

	public static void tick() {
		/*
		 * tick() counts round number and calls needed methods for each round
		 */
		
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
		/*
		 * askQuestion() get the question to be asked 
		 */
		String[] tempQuess= new String[allQuestions[numRound].getSize()];
		for(int i=0; i<tempQuess.length; i++){
			tempQuess[i]=allQuestions[numRound].getQuesAtLoc(i);
		}
		Question theQuestion= new Question(tempQuess);
		return theQuestion.shuffleAnswers(theQuestion);	
	}
	
	public static String readyToAsk(Question questioning){
		/*
		 * this method numbers the answer, so users know corresponding numbers for the answers
		 */
		String toAsk="\n"+questioning.getQuesAtLoc(0)+"\n";
		for(int i=1; i<(questioning.getSize()); i++){
			toAsk+=((i)+ " - " + questioning.getQuesAtLoc(i)+ "\n");
		}
		return toAsk;
	}
	
	public static int[] getEachPersonsAnswer(String asking){
		/*
		 * takes in user's responses, by scanning ints, for each question
		 */
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
		/*
		 * goes through each user's answer and the correct and and increases points if correct
		 */
		System.out.println("\nThe correct answer is:\n\t"+(allQuestions[numRound].getQuesAtLoc(1)));
		for(int i=0; i<answers.length; i++){
			int tempAnswer=answers[i];
			if(tempAnswer<=asked.getSize() && asked.getQuesAtLoc(tempAnswer).equals(allQuestions[numRound].getQuesAtLoc(1))){
				players[i].increasePoints();
			}
		}
	}
	
	
	
	
	

	public static void main(String[] args) throws FileNotFoundException{
		//main method to run trivia game
		getPlayers();
		/*
		 * uncomment to view players
		 * for(int i=0; i<players.length; i++){
			System.out.print(players[i]);
		}
		*/
		getQuestions();
		/*
		 * uncomment to view questions
		 * for(int i=0; i<allQuestions.length; i++){
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
