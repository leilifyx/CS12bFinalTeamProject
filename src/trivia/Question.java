package trivia;
import java.util.ArrayList;
import java.util.Random;


public class Question {
	ArrayList<String> question;

	/*this class' constructor creates a ArrayList question
	 * which takes in an array of Strings as a parameter
	 *
	 * for question, question[0] will always be the actual question
	 * question[1] will always be the right answer
	 * the following will be wrong answers
	 */
	public Question(String[] values) {
		/*
		 * constructor, takes in array of Strings, adds them to arraylist
		 */
		this.question = new ArrayList<String>();
		for (int i=0; i<values.length; i++) {
			question.add(values[i]);
		}

	}

	public Question(ArrayList<String> question){
		/*
		 * another constructor, this time takes in arraylist
		 */
		this.question=question;
	}

	public String toString(){
		/*
		 * prints out questions in format of {question, ..., question}
		 */
		String toPrint="{";
		for (int i=0; i<this.question.size(); i++) {
			if(i==0){
				toPrint+=this.question.get(i);
			}
			else{
				toPrint+=", "+this.question.get(i);
			}
			}
		toPrint+="}";
		return toPrint;
	}


	public int getSize(){
		//returns size of arraylist
		return this.question.size();
	}

	public String getQuesAtLoc(int loc){
		//returns String in array list at given location
		return question.get(loc);
	}
	
	public void removeAtLoc(int loc){
		//given location, removes from arraylist
		question.remove(loc);
	}
	
	public Question shuffleAnswers(Question question2){
		/*
		 * shuffles answers so right and wrong answers are randomly mixed
		 * Question stays as fist line
		 */
		String[] getQues=new String[1];
		getQues[0]=question2.getQuesAtLoc(0);
		Question tempQuestion2= new Question(getQues);
		Random rand= new Random();
		int numAnswers=question.size()-1;
		while(numAnswers>0){
			int random= rand.nextInt(numAnswers)+1;
			String temp= question2.getQuesAtLoc(random);
			question2.removeAtLoc(random);
			tempQuestion2.addToEnd(temp);
			numAnswers--;
		}
		
		return tempQuestion2;
	}

	
	public void addToEnd(String string){
		//adds String to end of arraylist
		question.add(string);
	}

	public boolean equals(Question question2){
		//tests if two Questions are equal
		boolean isEqual=true;
		if(question2.getSize()!=this.question.size()){
			isEqual=false;
		}
		for(int j=0; j<this.question.size(); j++){
			if(this.question.get(j)!=question2.getQuesAtLoc(j)){
				isEqual=false;
			}
		}
		return isEqual;
	}

}
