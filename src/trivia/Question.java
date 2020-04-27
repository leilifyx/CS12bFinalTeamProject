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
		this.question = new ArrayList<String>();
		for (int i=0; i<values.length; i++) {
			question.add(values[i]);
		}

	}

	public Question(ArrayList<String> question){
		this.question=question;
	}

	public String toString(){
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
		return this.question.size();
	}

	public String getQuesAtLoc(int loc){
		return question.get(loc);
	}

	public void removeAtLoc(int loc){
		question.remove(loc);
	}

	public Question shuffleAnswers(Question question2){
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
		question.add(string);
	}

	public boolean equals(Question question2){
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
