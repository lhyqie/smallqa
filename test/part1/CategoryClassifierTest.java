package part1;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class CategoryClassifierTest {

	//@Test
	public void testCategoryClassifier() {
		CategoryClassifier classifier = new CategoryClassifier();
		System.out.println(classifier.getMoviePN().size());
		System.out.println(classifier.getMoviePN());
		System.out.println(classifier.getSportsPN().size());
		System.out.println(classifier.getSportsPN());
		System.out.println(classifier.getGeographyPN().size());
		System.out.println(classifier.getGeographyPN());
	}
	
	@Test
	public void testClassify(){
		CategoryClassifier classifier = new CategoryClassifier();
		PrintParseTree parseTree = new PrintParseTree();
		LinkedList<Question> qList = classifier.loadTrainingData();
		for (Question question : qList) {
			System.out.println(question.getCategory()+"!");
			System.out.println(classifier.classify(question.getText()));
			System.out.println();
		}
			
	}

}
