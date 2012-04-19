package part2;

import static org.junit.Assert.*;

import org.junit.Test;

public class WrapperTest {
	
	@Test
	public void testQuestionToResult() {
		//String question = "Did Cameron direct Titanic?";
		//String question = "Did Dicaprio star Titanic?";
		//String question = "Did Dicaprio star in Titanic?";
		//String question = "Did Dicaprio act Titanic?";
		//String question = "Do Cameron direct in Titanic?";
		//String question = "Did Neeson star in Schindler's List?";
		String question = "Did Swank win the oscar in 2000?";
		
		System.out.println("Question :" + question);
		System.out.println(Wrapper.questionToResult(question));
	}
	
	//@Test
	public void testAnnotateNNP(){
		String aString = Wrapper.annotateNNP("M", "Did Neeson star in Schindler's List?");
		System.out.println(aString);
	}
}
