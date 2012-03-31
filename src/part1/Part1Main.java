package part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Part1Main {
	public static void main(String[] args) {
		//printParseTree();
		
	}
	public static void printParseTree(){
		PrintParseTree app = new PrintParseTree();
		LinkedList<Question> qList = app.readQuestionsFromFile("data/part1_questions.txt");
		app.writeParseTreeToFile("output/parseTrees.txt", qList);
	}
	public static void trainingPhrase(){
		LinkedList<Question> qList = loadTrainingData();
		
	}
	public static void testingPhrase(){
//		PrintParseTree parseTree = new PrintParseTree();
//		CategoryClassifier classifier = new CategoryClassifier();
//		LinkedList<Question> qList = parseTree.readQuestionsFromFile("data/questions.txt");
//		for (Question question : qList) {
//			System.out.print(question.getId() + " " +question.getText());
//			System.out.println(classifier.classify(question.getText()));
//		}
	}
	private static LinkedList<Question> loadTrainingData(){
		LinkedList<Question> qList = new LinkedList<Question>();
		Scanner scan = null;
		try {
			scan = new Scanner(new File("data/training_data.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(scan.hasNextLine()){
			String line = scan.nextLine();
			if(line.startsWith("#") || line.length() == 0) continue;
			if(scan.hasNextLine()){
				String classLabel = scan.nextLine();
				qList.add(new Question("no-id",line,classLabel.charAt(0)));
			}
		}
		return qList;
	}
}
