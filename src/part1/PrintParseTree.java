package part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.objectbank.TokenizerFactory;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreePrint;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.TypedDependency;

public class PrintParseTree {
	static LexicalizedParser lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
	
	/* 
	 * Input: relative path of file storing the questions
	 * Output: a collection of Questions
	 * Author: Huayi
	 * Note:  # is the line comment
	 */ 
	public LinkedList<Question> readQuestionsFromFile(String inputFilePath){
		LinkedList<Question> qList = new LinkedList<Question>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(inputFilePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(scanner.hasNext()){
			String line = scanner.nextLine();
			if(line.length()==0) continue; // skip empty line
			String pieces[] = line.split("[()]");
			String qid = pieces[1];
			String text = pieces[2];
			Question q = new Question(qid,text); 
			System.out.println(q);
			qList.add(q);
			
		}
		scanner.close();
		return qList;
	}
	
	/* 
	 * Input: an question
	 * Output: a parse tree
	 * Author: Huayi
	 */ 
	public String getParseTree(Question question){
		String text = question.getText();
	    TokenizerFactory<CoreLabel> tokenizerFactory = 
	      PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
	    List<CoreLabel> rawWords = 
	      tokenizerFactory.getTokenizer(new StringReader(text)).tokenize();
	    Tree parse = lp.apply(rawWords);
	    TreebankLanguagePack tlp = new PennTreebankLanguagePack();
	    GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
	    GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
	    List<TypedDependency> tdl = gs.typedDependenciesCCprocessed();
	    //System.out.println(tdl);
	    //System.out.println();
	    TreePrint tp = new TreePrint("penn,typedDependenciesCollapsed");
	    StringWriter sw = new StringWriter(); // get the parser tree
		PrintWriter pw = new PrintWriter(sw);
		tp.printTree(parse, pw);
		//System.out.println(sw.getBuffer().toString());
		String lineFeed = System.getProperty("line.separator");
	    return "Question ("+ question.getId()+"):" + text +lineFeed +sw.getBuffer().toString()+ lineFeed + lineFeed;
	}
	
	/*
	 *  input : output file path, a collection of questions
	 *  output: write the parse tree to the file
	 */
	public void writeParseTreeToFile(String outputFilePath, LinkedList<Question> qList){
		FileWriter fw = null;
		try {
			fw = new FileWriter(outputFilePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Question question : qList) {
			String parseTree = getParseTree(question);
			System.out.println(parseTree);
			try {
				fw.write(parseTree);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 *   input: text
	 *   output: text with POS tags
	 */
	public static ArrayList<TaggedWord> getTaggedText(String text){
		TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
		List<CoreLabel> rawWords = tokenizerFactory.getTokenizer(new StringReader(text)).tokenize();
		Tree parse = lp.apply(rawWords);
		return parse.taggedYield();
	}
	
	public static void main(String[] args) {
		PrintParseTree app = new PrintParseTree();
		LinkedList<Question> qList = app.readQuestionsFromFile("data/part1_questions.txt");
		app.writeParseTreeToFile("output/parseTrees.txt", qList);
	}
}
