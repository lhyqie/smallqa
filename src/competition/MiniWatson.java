package competition;
import common.Config;

import part1.CategoryClassifier;
import part2.SQLiteRunner;
import part2b.MovieVectorBuilder;
import edu.uic.cs.cs421.miniwatson.*;
public class MiniWatson implements IWatson {
	CategoryClassifier classifier = new CategoryClassifier(1.0);
	MovieVectorBuilder mBuilder = new MovieVectorBuilder();
	public MiniWatson(){
		
	}
	@Override
	public String answerQuestion(String question) {
		// TODO Auto-generated method stub
		String results = "";
		
		String label = classifier.classify(question);
		if(label.equals("M")){
			mBuilder.generateQuestionVector(question);
			String sql = mBuilder.generateSQL();
			results = SQLiteRunner.getSQLResult(Config.getMovieDB(), sql);
		}else if(label.equals("S")){
			
		}else if(label.equals("G")){
			
		}
		return results;
	}

	@Override
	public QuestionCategory classifyCategory(String question) {
		// TODO Auto-generated method stub
		String label = classifier.classify(question);
		if(label.equals("M")){
			return QuestionCategory.MOVIES;
		}else if(label.equals("S")){
			return QuestionCategory.OLYMPICS;
		}else if(label.equals("G")){
			return QuestionCategory.GEOGRAPHY;
		}
		return null;
	}

}
