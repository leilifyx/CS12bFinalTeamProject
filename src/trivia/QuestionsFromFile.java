package trivia;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;

public class QuestionsFromFile {

	public QuestionsFromFile() {
		// TODO Auto-generated constructor stub
		
	}
	
	public static void readFile(File file) throws FileNotFoundException{
		Scanner scan=new Scanner(file);
		int numQuestions=0;
		while(scan.hasNextLine()){
			System.out.println(scan.nextLine());
			numQuestions++;
		}
		numQuestions/=5; //every 5 lines is a new question
		Question[] allQuestions= new Question[numQuestions];
		int tempLineOn=1;
		int tempQuestionOn=1;
		String[] temp= new String[5];
		while(scan.hasNextLine()){
			System.out.println("in while");
			temp[tempLineOn]=scan.nextLine();
			tempLineOn++;
			if(tempLineOn==5){
				allQuestions[tempQuestionOn]=new Question(temp);
				tempQuestionOn++;
				if(tempQuestionOn==numQuestions){
					break;
				}
				tempLineOn=1;
				temp= new String[5];
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
