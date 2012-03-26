

import static org.junit.Assert.*;
import java.util.LinkedList;
import org.junit.Test;
import part1.PrintParseTree;
import part1.Question;

public class PrintParseTreeTest {

	@Test
	public void testReadQuestionsFromFile() {
		PrintParseTree tester = new PrintParseTree();
		LinkedList<Question> qList = tester.readQuestionsFromFile("data/part1_questions.txt");
		for (Question question : qList) {
			System.out.println(question);
		}
	}

}
