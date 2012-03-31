package part1;

import java.util.LinkedList;

public class TestingPhrase {
	public static void main(String[] args) {
		PrintParseTree parseTree = new PrintParseTree();
		CategoryClassifier classifier = new CategoryClassifier();
		LinkedList<Question> qList = parseTree.readQuestionsFromFile("data/questions.txt");
		for (Question question : qList) {
			System.out.print(question.getId() + " " +question.getText());
			System.out.println(classifier.classify(question.getText()));
		}
	}
}
