package part2;

import static org.junit.Assert.*;

import org.junit.Test;

public class WrapperTest {

	@Test
	public void testQuestionToResult() {
		String question = "Did Cameron direct Titanic?";
		System.out.println(Wrapper.questionToResult(question));
	}

}
