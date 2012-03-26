package part1;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class PrintParseTreeTest {
	PrintParseTree tester = new PrintParseTree();

	//@Test
	public void testReadQuestionsFromFile() {
		LinkedList<Question> qList = tester.readQuestionsFromFile("data/part1_questions.txt");
		for (Question question : qList) {
			System.out.println(question);
		}
	}
	//@Test
	public void testGetParseTree() {
		String res = tester.getParseTree(new Question("qid","This is a question"));
		System.out.println(res);
	}
	@Test
	public void testWriteParseTreeToFile(){
		LinkedList<Question> qList = tester.readQuestionsFromFile("data/part1_questions.txt");
		tester.writeParseTreeToFile("output/parseTrees.txt", qList);
	}
}
