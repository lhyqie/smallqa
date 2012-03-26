package part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import edu.stanford.nlp.parser.lexparser.LexicalizedParser;

public class PrintParseTree {
	LexicalizedParser lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
	/*
	 * Input: relative path of file storing the questions
	 * Output: a collection of Questions
	 * Author: Huayi
	 * Note:  # is the line comment
	 */
	public LinkedList<Question> readQuestionsFromFile(String filePath){
		LinkedList<Question> qList = new LinkedList<Question>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(scanner.hasNext()){
			String line = scanner.nextLine();
			if(line.length()==0) continue; // skip empty line
			String pieces[] = line.split("[()]");
			
		}
		
		scanner.close();
		return qList;
		
	}
}
