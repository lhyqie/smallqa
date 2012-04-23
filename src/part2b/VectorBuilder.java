package part2b;

import java.io.StringReader;
import java.util.List;

import common.LoadStanfordParserModel;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.objectbank.TokenizerFactory;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.trees.Tree;

public abstract class VectorBuilder {
	   static LexicalizedParser lp = LoadStanfordParserModel.getModel();
       int [] qvector = null;
       String [] sems = null;
       abstract public void generateQuestionVector(String question);
       abstract public String generateSQL();
       public static String questionType(String question){
    	 // append the question mark if not exist
  		 if(question.endsWith("?")){
  			question = question.substring(0, question.length()-1) + " ?"; 
  		 }else{
  			question +=" ?"; 
  		 }
  		 
  		 //get parser tree
  		 String text = question;
	     TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
	     List<CoreLabel> rawWords = tokenizerFactory.getTokenizer(new StringReader(text)).tokenize();
	     Tree root = lp.apply(rawWords);	
	     if(root!=null && root.label().value().equals("ROOT")){
    	      Tree child = root.getChild(0);
	    	  if(child!=null && child.label().value().equals("SQ")){
    	    	  return "YES-NO";
    	      }else if(child!=null && child.label().value().equals("SBARQ")){
    	    	  return "WH";
    	      }else {
    	    	  return "";
    	      }
         }else 
        	 return "";
       }
}
