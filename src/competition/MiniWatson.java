package competition;
import common.Config;
import common.StringAlgo;

import part1.CategoryClassifier;
import part2.SQLiteRunner;
import part2b.GeoVectorBuilder;
import part2b.MovieVectorBuilder;
import part2b.SportsVectorBuilder;
import part2b.VectorBuilder;
import edu.uic.cs.cs421.miniwatson.*;
public class MiniWatson implements IWatson {
	CategoryClassifier classifier = new CategoryClassifier(1.0);
	MovieVectorBuilder mBuilder = new MovieVectorBuilder();
	GeoVectorBuilder  gBuilder = new GeoVectorBuilder();
	SportsVectorBuilder sBuilder = new SportsVectorBuilder();
	public MiniWatson(){
		
	}
	@Override
	public String answerQuestion(String question) {
		// TODO Auto-generated method stub
		String results = "";
		try{
			String label = classifier.classify(question);
			if(label.equals("M")){
				mBuilder.generateQuestionVector(question);
				String sql = mBuilder.generateSQL();
				results = SQLiteRunner.getSQLResult(Config.getMovieDB(), sql);
			}else if(label.equals("S")){
				sBuilder.generateQuestionVector(question);
				String sql = sBuilder.generateSQL();
				results = SQLiteRunner.getSQLResult(Config.getMovieDB(), sql);
			}else if(label.equals("G")){
				gBuilder.generateQuestionVector(question);
				String sql = gBuilder.generateSQL();
				results = SQLiteRunner.getSQLResult(Config.getMovieDB(), sql);
			}
			if(VectorBuilder.questionType(question).equals("YES-NO")){
				if(StringAlgo.isNumber(results)){
					Integer res = Integer.parseInt(results);
					if(res == 0) results= "false";
					else results = "true";
				}
			}
		}catch(Exception e){
			return "";
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
