package part2;

import part1.CategoryClassifier;

public class Wrapper {
	static CategoryClassifier classifier = new CategoryClassifier(1.0);
	public static String questionToResult(String question){
		 String label = classifier.classify(question);
		 String dbName = "";
		 if(label.startsWith("G"))
			 dbName = "WorldGeography.sqlite";
		 else if(label.startsWith("M"))
			 dbName = "imdb-oscar-movie.sqlite";
		 else if(label.startsWith("S"))
			 dbName = "Winter-Olympics-2006-2010.sqlite";
		 else{
			 return "Category classifier fails, unable to determine which db to use!!! :("; 
		 }
		 
		 String sql = SemanticAttachment.questionToSQL(question);
		 
		 return SQLiteRunner.getSQLResult(dbName, sql);
	}
	
}
