package part2;

import java.util.ArrayList;

import common.Config;

import part1.CategoryClassifier;

public class Wrapper {
	static CategoryClassifier classifier = new CategoryClassifier(1.0);
	static ArrayList<String> movieNameList = SQLiteRunner.getMovieNameList();
	static ArrayList<String> movieWorkerNameList = SQLiteRunner.getMovieWorkerNameList(); 
	public static String questionToResult(String question){
		 String cat = classifier.classify(question);
		 String dbName = "";
		 if(cat.startsWith("G"))
			 dbName = Config.getGeographyDB();
		 else if(cat.startsWith("M"))
			 dbName = Config.getMovieDB();
		 else if(cat.startsWith("S"))
			 dbName = Config.getSportsDB();
		 else{
			 return "Category classifier fails, unable to determine which db to use!!! :("; 
		 }
		 
		 // append the question mark if not exist
		 if(question.endsWith("?")){
			question = question.substring(0, question.length()-1) + " ?"; 
		 }else{
			question +=" ?"; 
		 }
		 
		
		 // annotate proper noun phrase
		 question = annotateNNP(cat, question);
		 System.out.println("\n¡¾Annotated question¡¿ :" + question);
		 String sql = SemanticAttachment.questionToSQL(cat, question);
		 System.out.println("\n¡¾Annotated question SQL¡¿:" + sql);	 
		 // de-annotate proper noun phrase
		 sql = sql.replaceAll("\\^", " "); 
		 sql = sql.replaceAll("\\@", "''");
		 
		 sql = sql.replaceAll("MOVIE-", "");
		 sql = sql.replaceAll("MOVIEWORKER-", "");
		 System.out.println("\n¡¾De-Annotated question SQL¡¿:" + sql);

		 return SQLiteRunner.getSQLResult(dbName, sql);
	}
	/*
	 *  According to the catogry, aggregate the proper noun phrase
	 *  e.g Did he win the figure skating ? = > Did he win the FIGURE_SKATING ?
	 *  
	 *  map  space  to %
	 *       '      to @
	 */
	public static String annotateNNP(String cat, String question){
		 if(cat.equals("G")){
			 
		 }else if(cat.equals("M")){
			 for (String movieName : movieNameList) {
				 int index = question.toLowerCase().indexOf(movieName.toLowerCase());
				 if(index>=0){
					 movieName = movieName.replaceAll(" ", "^");
					 movieName = movieName.replaceAll("'", "@");
					 movieName = movieName;
					 question = question.substring(0,index) + "MOVIE-"+ movieName.toUpperCase()
							    + question.substring(index + movieName.length());
				 }
			 }
			 for (String movieWorkerName : movieWorkerNameList) {
				 int index = question.toLowerCase().indexOf(movieWorkerName.toLowerCase());
				 if(index>=0){
					 movieWorkerName = movieWorkerName.replaceAll(" ", "^");
					 movieWorkerName = movieWorkerName.replaceAll("'", "@");
					 movieWorkerName = movieWorkerName;
					 question = question.substring(0,index) + "MOVIEWORKER-"+ movieWorkerName.toUpperCase()
							    + question.substring(index + movieWorkerName.length());
				 }
			}
		 }else if(cat.equals("S")){
			 
		 }
		 return question;
	}
	public static void main(String[] args) {
		String question = "Did Swank win the oscar in 2000?";
		System.out.println("Question :" + question);
		System.out.println(Wrapper.questionToResult(question));
	}
	
}
