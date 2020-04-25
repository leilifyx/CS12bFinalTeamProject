/*package trivia;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

//questions.txt taken from https://www.funtrivia.com/playquiz/quiz37732454be8.html

public class QuestionsFromFile {
	static Question[] allQuestions; 

	public QuestionsFromFile(File file) throws FileNotFoundException{
		// TODO Auto-generated constructor stub
		readFile(file);
	}
	
	public static void readFile(File file) throws FileNotFoundException{
		Scanner scan=new Scanner(file);
		int numQuestions=0;
		while(scan.hasNextLine()){
			scan.nextLine();
			numQuestions++;
		}
		numQuestions/=5; //every 5 lines is a new question
		readInQuestions(file, numQuestions);
		
	}
	
	public static void readInQuestions(File file, int numQuestions) throws FileNotFoundException{
		Scanner scan=new Scanner(file);
		allQuestions= new Question[numQuestions];
		int tempLineOn=0;
		int tempQuestionOn=0;
		String[] temp= new String[5];
		while(scan.hasNextLine()){
			//System.out.println("tempQuestionOn= " + tempQuestionOn);
			//System.out.println("tempLineOn= " + tempLineOn);
			temp[tempLineOn]=scan.nextLine();
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
		for(int i=0; i<numQuestions; i++){
			System.out.print(allQuestions[i]);
		}
	}
	
	
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner scanning=new Scanner(System.in);
		System.out.println("enter file's name");
		String fileName=scanning.nextLine();
		File file= new File(fileName);
		readFile(file);
		
	}

}
*/
