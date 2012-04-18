package part2;

import part1.CategoryClassifier;

public class Wrapper {
	static CategoryClassifier classifier = new CategoryClassifier(1.0);
	public static String questionToResult(String question){
		 String cat = classifier.classify(question);
		 String dbName = "";
		 if(cat.startsWith("G"))
			 dbName = "WorldGeography.sqlite";
		 else if(cat.startsWith("M"))
			 dbName = "imdb-oscar-movie.sqlite";
		 else if(cat.startsWith("S"))
			 dbName = "Winter-Olympics-2006-2010.sqlite";
		 else{
			 return "Category classifier fails, unable to determine which db to use!!! :("; 
		 }
		 
		 // append the question mark if not exist
		 if(question.endsWith("?")){
			question = question.substring(0, question.length()-1) + " ?"; 
		 }else{
			question +=" ?"; 
		 }
		 
		 String sql = SemanticAttachment.questionToSQL(question);
		 
		 return SQLiteRunner.getSQLResult(dbName, sql);
	}
	/*
	 *  According to the catogry, aggregate the proper noun phrase
	 *  e.g Did he win the figure skating ? = > Did he win the FIGURE_SKATING ?
	 */
	public static String annotateNNP(String cat, String question){
		 if(cat.equals("G")){
			 
		 }else if(cat.equals("M")){
			 
		 }else if(cat.equals("S")){
			 
		 }
		 return question;
	}
	
}
