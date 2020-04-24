package trivia;
import java.util.Scanner;

public class QuestionInput.java{

  public QuestionInput(){};

  public static main void(String[] args){
    Scanner in=new Scanner(System.in);
    System.out.println("How many questions do you want?");
    int numOfQuestions=in.nextInt();
    Question[] questions=new Question[numOfQuestions];
    System.out.println("How many answer choices do you want per question?");
    int numOfChoices=in.nextInt();
    int tracker=0;
    while(tracker<numOfQuestions){
      Question question=new Question();
      System.out.println("Enter the question: ");
      String q=in.nextLine();
      question.add(q);
      System.out.println("Enter the correct answer: ");
      String correctAnswer=in.nextLine();
      question.add(correctAnswer);
      for(int i=0; i<numOfChoices; i++){
        System.out.println("Enter an incorrect answer: ");
        String wrongAnswer=in.nextLine();
        question.add(wrongAnswer);
      }
      for(int k=0; k<numOfQuestions; k++){
        questions[k]=question;
      }
    }
  }
}
