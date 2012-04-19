package part2b;

import java.util.ArrayList;

import common.LoadStanfordParserModel;

import edu.stanford.nlp.parser.lexparser.LexicalizedParser;

import part2.SQLiteRunner;

public class MovieVectorBuilder extends VectorBuilder {
	
	//-------------Entities--------------
	final int MOVIEWORKERNAME = 0;
	final int MOVIENAME = 1;
	final int MOVIEWORKERNAME2 = 2;
	final int MOVIENAME2 = 3;
	//-------------Entitiy Aspects---------------
	final int DOB = 4;
	final int POB = 5;
	final int MOVIEYEAR = 6;
	final int RATING = 7;
	final int RUNTIME = 8;
	final int GENRE = 9;
	final int EARNINGS_RANK = 10;
	//-------------Relation Aspects------------
	final int AWARDTYPE = 11;
	final int AWARDYEAR = 12;
	//-------------Events-----------------------
	final int ACT = 13; 	 		//including act play star
	final int DIRECT = 14;      	//direct
	final int WIN = 15;
	final int SIZE = 16;

	ArrayList<String> movieWorkerLastNames = SQLiteRunner.getMovieWorkerNameList();
	ArrayList<String> movieNames = SQLiteRunner.getMovieNameList();
	ArrayList<String> movieWorkerPOBs = SQLiteRunner.getMovieWorkerPOBList();
	@Override
	public void generateQuestionVector(String question) {
		// TODO Auto-generated method stub
		qvector = new int[SIZE];
		sems = new String[SIZE];
		generateEntities(question);		
		generateEvent(question);
		generateEntityAspects(question);
		generateRelationAspects(question);
	}
	
	@Override
	public String generateSQL() {
		// TODO Auto-generated method stub
		return null;
	}
	private void generateEntityAspects(String question){
		if(question.contains("when") && (question.contains("born") || question.contains("birth") || question.contains("birthday"))) {
			qvector[DOB] = 1;
			sems[DOB] = "?";
		}else if(question.contains("where") && question.contains("born")){
			qvector[POB] = 1;
			sems[POB] = "?";
		}
//      a French actor
//		else{
//			for (String movieWorkerPOB : movieWorkerPOBs) {
//				String pieces[] = movieWorkerPOB.split(",");
//				String countryName = 
//			}
//		}
	}
	private void generateRelationAspects(String question){
		//--------------AWARDTYPE-----------------------------
		if(question.contains("best picture") || question.contains("best movie") ){
			qvector[AWARDTYPE] = 1;
			sems[AWARDTYPE] = "BEST-PICTURE";
		}
		else if(question.contains("best director")){
			qvector[AWARDTYPE] = 1;
			sems[AWARDTYPE] = "BEST-DIRECTOR";
		}
		else if(question.contains("best actor") || question.contains("best leading actor")){
			qvector[AWARDTYPE] = 1;
			sems[AWARDTYPE] = "BEST-ACTOR";
		}
		else if(question.contains("best supporting actor")){
			qvector[AWARDTYPE] = 1;
			sems[AWARDTYPE] = "BEST-SUPPORTING-ACTOR";
		}
		else if(question.contains("best actress") || question.contains("best leading actress")){
			qvector[AWARDTYPE] = 1;
			sems[AWARDTYPE] = "BEST-ACTRESS";
		}
		else if(question.contains("best supporting actress")){
			qvector[AWARDTYPE] = 1;
			sems[AWARDTYPE] = "BEST-SUPPORTING-ACTRESS";
		}
		else if(question.contains("oscar")){
			qvector[AWARDTYPE] = 1;
			sems[AWARDTYPE] = "*";
		}
		if(qvector[AWARDTYPE] == 1) {
		//--------------AWARDYEAR-----------------------------
			String tokens[] = question.split("[ ?]");
			for (String token : tokens) {
				if(isNumber(token)){
					qvector[AWARDYEAR] = 1;
					sems[AWARDYEAR] = token;
					if(token.equals("2012")){
						qvector[MOVIENAME] = 0;
						sems[MOVIENAME] = null;
					}
					break;
				}
			}
		}
		
	}
	private boolean isNumber(String token){
		for (int i = 0; i < token.length(); i++) {
			char c = token.charAt(i);
			if(!Character.isDigit(c)) return false;
		}
		return true;
	}
	private void generateEvent(String question){
		question = question.toLowerCase();
		if(question.contains("direct") || question.contains("director") 
				|| question.contains("directed")){
			qvector[DIRECT] = 1;
			sems[DIRECT] = "DIRECT";
		}
		if(
			question.contains("act") || question.contains("acts") || question.contains("acted") 
			|| question.contains("actor") || question.contains("actors")
			|| question.contains("play") || question.contains("plays") || question.contains("played")
			|| question.contains("star") || question.contains("stars") || question.contains("stared")
		        ) 
		{
			qvector[ACT] = 1;
			sems[ACT] = "ACT";
		}
		if(question.contains("win") || question.contains("wins") || question.contains("won")){
			qvector[WIN] = 1;
			sems[WIN] = "WIN";	
		}
	}
	
	private void generateEntities(String question){
		question = question.toLowerCase();
		if(question.contains("who") || question.contains("which actor") || question.contains("which actress") 
		   || question.contains("which director") ){
			qvector[MOVIEWORKERNAME] = 1;
			sems[MOVIEWORKERNAME] = "?";
		}else if(question.contains("which movie")){
			qvector[MOVIENAME] = 1;
			sems[MOVIENAME] = "?";
		}
		int index_of_movieWorker1 = -1;
		for (String movieWorker : movieWorkerLastNames) {
			if(question.contains(movieWorker.toLowerCase())){
				qvector[MOVIEWORKERNAME] = 1;
				sems[MOVIEWORKERNAME] = movieWorker;
				index_of_movieWorker1 = question.indexOf(movieWorker);
				break;
			}
		}
		if(index_of_movieWorker1 >=0 ){
			String subQuestion = question.substring(index_of_movieWorker1 + sems[MOVIEWORKERNAME].length());
			for (String movieWorker : movieWorkerLastNames) {
				if(subQuestion.contains(movieWorker.toLowerCase())){
					qvector[MOVIEWORKERNAME2] = 1;
					sems[MOVIEWORKERNAME2] = movieWorker;
					break;
				}
			}
		}
		int index_of_movie1 = -1;
		for (String movie : movieNames) {
			if(question.contains(movie.toLowerCase())){
				qvector[MOVIENAME] = 1;
				sems[MOVIENAME] = movie;
				index_of_movie1 = question.indexOf(movie);
				break;
			}
		}
		if(index_of_movie1 >=0){
			String subQuestion = question.substring(index_of_movie1 + sems[MOVIENAME].length());
			for (String movie : movieNames) {
				if(subQuestion.contains(movie.toLowerCase())){
					qvector[MOVIENAME2] = 1;
					sems[MOVIENAME2] = movie;
					break;
				}
			}
		}
	}
}
