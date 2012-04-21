package part2b;

import java.util.ArrayList;

import common.LoadStanfordParserModel;
import common.StringAlgo;

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
	
	final String ActorWinOscarInMovie    = "1100000000010100";
	final String DirectorWinOscarInMovie = "1100000000010010";
	final String PersonWinOscarInMovie   = "1100000000010000";
	
	final String ActorWinOscar	     	 = "1000000000010100";
	final String DirectorWinOscar	     = "1000000000010010";
	final String PersonWinOscar			 = "1000000000010000";
	final String MovieWinOscar			 = "0100000000010000";
	
	final String ActorMovie              = "1100000000000100";
	final String DirectorMovie           = "1100000000000010";
	
	final String DOBofPerson             = "1000100000000000";
	final String POBofPerson             = "1000010000000000";
	
	final String MYearofMovie            = "0100001000000000";
	final String RatingofMovie			 = "0100000100000000";
	final String RuntimeOfMovie          = "0100000010000000";
	final String GenreOfMovie            = "0100000001000000";
	final String EarningsrankOfMovie     = "0100000000100000";
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
		String SELECT = "SELECT distinct";
		String FROM = "";
		String WHERE = "";
		String pattern = "";
		for (int bit : qvector) {
			pattern += bit;
		}
		//System.out.println(pattern);
		if(StringAlgo.bitCover(pattern, ActorWinOscarInMovie)){
			FROM += "FROM Person join Actor on Person.id = Actor.actor_id join Oscar on Actor.movie_id = Oscar.movie_id " +
					"join Movie on Movie.id = Oscar.movie_id";
		}else if(StringAlgo.bitCover(pattern, DirectorWinOscarInMovie)){
			FROM += "FROM Movie join Oscar on Movie.id = Oscar.movie_id join Director on Director.movie_id = Movie.id " +
					"join Person on Person.id = Director.director_id";
		}
		else if(StringAlgo.bitCover(pattern, PersonWinOscarInMovie)){
			FROM += "FROM Movie join Oscar on Movie.id = Oscar.movie_id join Person on Person.id = Oscar.person_id";
		}
		else if(StringAlgo.bitCover(pattern, ActorWinOscar) ||
			    StringAlgo.bitCover(pattern, DirectorWinOscar) || 
			    StringAlgo.bitCover(pattern, PersonWinOscar) 
				)
		{
			FROM += "FROM Person join Oscar on Person.id = Oscar.person_id";
		}else if(StringAlgo.bitCover(pattern, MovieWinOscar)){
			FROM += "FROM Movie join Oscar on Movie.id = Oscar.movie_id";
		}else if(StringAlgo.bitCover(pattern, ActorMovie)){
			FROM += "FROM Person join Actor on Person.id = Actor.actor_id join Movie on Actor.movie_id = Movie.id";
		}else if(StringAlgo.bitCover(pattern, DirectorMovie)){
			FROM += "FROM Person join Director on Person.id = Director.director_id join Movie on Director.movie_id = Movie.id";
		}else if(StringAlgo.bitCover(pattern, DOBofPerson) ||
				 StringAlgo.bitCover(pattern, POBofPerson) 
				){
			FROM += "FROM Person";
		}else if(StringAlgo.bitCover(pattern, MYearofMovie) ||
				 StringAlgo.bitCover(pattern, RatingofMovie) ||
				 StringAlgo.bitCover(pattern, RuntimeOfMovie) ||
				 StringAlgo.bitCover(pattern, GenreOfMovie) ||
				 StringAlgo.bitCover(pattern, EarningsrankOfMovie) 
				){
			FROM += "FROM MOVIE";
		}
		//-----------------begin pattern matching ------------------------------
		if(sems[MOVIEWORKERNAME] != null){
			if(sems[MOVIEWORKERNAME].equals("?")) SELECT+=" Person.name,";
			else {
				WHERE += " Person.name like '%"+sems[MOVIEWORKERNAME]+"' and";
			}
		}
		if(sems[MOVIENAME] != null){
			if(sems[MOVIENAME].equals("?")) SELECT+=" Movie.name,";
			else {
				WHERE += " Movie.name like '%"+StringAlgo.SQLiteStringGetValid(sems[MOVIENAME])+"%' and";
			}
		}
		if(sems[DOB] != null){
			if(sems[DOB].equals("?")) SELECT+=" Person.dob,";
			else {
				WHERE += " Person.dob='"+sems[DOB]+"' and";
			}
		}
		if(sems[POB] != null){
			if(sems[POB].equals("?")) SELECT+=" Person.pob,";
			else {
				WHERE += " Person.pob='"+sems[POB]+"' and";
			}
		}
		if(sems[MOVIEYEAR] != null){
			if(sems[MOVIEYEAR].equals("?")) SELECT+=" Movie.year,";
			else {
				WHERE += " Movie.year="+sems[MOVIEYEAR]+" and";
			}
		}
		if(sems[RATING] != null){
			if(sems[RATING].equals("?")) SELECT+=" Movie.rating,";
			else {
				WHERE += " Movie.rating='"+sems[RATING]+"' and";
			}
		}
		if(sems[RUNTIME] != null){
			if(sems[RUNTIME].equals("?")) SELECT+=" Movie.runtime,";
			else {
				WHERE += " Movie.runtime="+sems[RUNTIME]+" and";
			}
		}
		if(sems[GENRE] != null){
			if(sems[GENRE].equals("?")) SELECT+=" Movie.genre,";
			else {
				WHERE += " Movie.genre='"+sems[GENRE]+"' and";
			}
		}
		if(sems[EARNINGS_RANK] != null){
			if(sems[EARNINGS_RANK].equals("?")) SELECT+=" Movie.earnings_rank,";
			else {
				WHERE += " Movie.earnings_rank="+sems[EARNINGS_RANK]+" and";
			}
		}
		if(sems[AWARDTYPE]!=null){
			if(sems[AWARDTYPE].equals("?")) SELECT+=" Oscar.type,";
			else if(!sems[AWARDTYPE].equals("*")){
				WHERE += " Oscar.type='"+sems[AWARDTYPE]+"' and";
			}
		}
		if(sems[AWARDYEAR]!=null){
			if(sems[AWARDYEAR].equals("?")) SELECT+=" Oscar.year,";
			else {
				WHERE += " Oscar.year='"+sems[AWARDYEAR]+"' and";
			}
		}
		/*
		 *  the following approach has been deprecated
		 */
//		//----------------------------BEGIN  FROM -----------------------------------
//		if(  // Movie + Oscar      //¡Ì
//				pattern.equals("0100000000011001")
//		  )
//		{
//			FROM += "FROM Movie join Oscar on Movie.id = Oscar.movie_id";
//		}else if( // Movie
//					pattern.equals("0100000000100000") ||
//					pattern.equals("0100000001000000") || 
//					pattern.equals("0100000010000000") ||
//					pattern.equals("0100000100000000") ||
//					pattern.equals("0100000111000100") ||
//					pattern.equals("0100001000000000") 
//				 )
//		{
//			FROM += "FROM Movie";  
//		}else if( // Movie + Oscar + Person             //¡Ì
//					pattern.equals("1000000000011001")
//				)
//		{
//			FROM += "FROM Movie join Oscar on Movie.id = Oscar.movie_id join Person on Person.id = Oscar.person_id";		
//		}else if( // Movie + Oscar + Director + Person  //¡Ì
//					pattern.equals("1000000000011010") ||
//					pattern.equals("1100000000011011")
//				)
//		{        
//			FROM += "FROM Movie join Oscar on Movie.id = Oscar.movie_id join Director on Director.movie_id = Movie.id join Person on Person.id = Director.director_id";
//		}else if( // Person + Oscar                     //¡Ì
//					pattern.equals("1000000000011011") ||
//					pattern.equals("1000000000011101") ||
//					pattern.equals("1000000000011100") 
//				)
//		{
//			FROM += "FROM Person join Oscar on Person.id = Oscar.person_id";
//		}else if( // Person   //¡Ì
//				pattern.equals("1000100000000000") ||
//				pattern.equals("1000010000000000") 
//				)
//		{
//			FROM += "FROM Person";
//		}else if( // Person + Director + Movie    //¡Ì
//				pattern.equals("1100000000000010")
//				)
//		{
//			FROM += "FROM Person join Director on Person.id = Director.director_id join Movie on Director.movie_id = Movie.id";	
//		}else if( // Person + Actor + Movie    //¡Ì
//				pattern.equals("1100000000000100")
//				)
//		{
//			FROM += "FROM Person join Actor on Person.id = Actor.actor_id join Movie on Actor.movie_id = Movie.id";
//		}else if( //Moive + Oscar + Actor + Person //¡Ì
//				pattern.equals("1100000000011101") ||
//				pattern.equals("1100000000011100") 
//				)
//		{
//			FROM += "FROM Person join Actor on Person.id = Actor.actor_id join Oscar on Actor.movie_id = Oscar.movie_id join Movie on Movie.id = Oscar.movie_id";
//		}
//		//-------------------------------END FROM -------------------------------------	
		
		if(SELECT.equals("SELECT distinct")) SELECT = "SELECT COUNT(*) ";
		if(SELECT.endsWith(",")) SELECT = SELECT.substring(0,SELECT.length()-1);
		if(WHERE.length()>0) WHERE = " WHERE "+ WHERE.substring(0, WHERE.length()-"and".length());
		return SELECT + " " + FROM + " " + WHERE;
	}
	
	
	//-------------------------------------------------------------------------------------------------------
	private void generateEntityAspects(String question){
		question = question.toLowerCase();
		if( (StringAlgo.contains(question,"when") && (StringAlgo.contains(question,"born") ) || 
			(StringAlgo.contains(question,"what") && (StringAlgo.contains(question,"birth")) || StringAlgo.contains(question,"birthday")))) {
			qvector[DOB] = 1;
			sems[DOB] = "?";
		}else if(StringAlgo.contains(question,"where") && StringAlgo.contains(question,"born")){
			qvector[POB] = 1;
			sems[POB] = "?";
		}
		
		if(
			StringAlgo.contains(question,"show") || StringAlgo.contains(question,"showed") || StringAlgo.contains(question,"shown")
			|| StringAlgo.contains(question,"release") || StringAlgo.contains(question,"released")
		  ) 
		{
		    if( StringAlgo.contains(question,"which year") || StringAlgo.contains(question,"what year") || StringAlgo.contains(question,"when")){
		    	qvector[MOVIEYEAR] = 1;
		    	sems[MOVIEYEAR] = "?";
		    }else{ 
	    		String tokens[] = question.split("[ ?]");
	    			for (String token : tokens) {
	    				if(StringAlgo.isNumber(token)){
	    					qvector[MOVIEYEAR] = 1;
	    					sems[MOVIEYEAR] = token;
	    					if(token.equals("2012")){
	    						qvector[MOVIENAME] = 0;
	    						sems[MOVIENAME] = null;
	    					}
	    					break;
	    			}
	    	    }
		    }
		}
		if(
			(StringAlgo.contains(question,"what's") 
					|| StringAlgo.contains(question,"what is") )
			&& (StringAlgo.contains(question,"runtime") || StringAlgo.contains(question,"runtime") )
		  )
		{
			qvector[RUNTIME] = 1;
			sems[RUNTIME] = "?";
		}
		if(StringAlgo.contains(question,"earnings") || StringAlgo.contains(question,"earningsrank") || StringAlgo.contains(question,"earnings-rank")
			|| StringAlgo.contains(question,"earning") || StringAlgo.contains(question,"earningrank") || StringAlgo.contains(question,"earning-rank")	
		  ){
			if( StringAlgo.contains(question,"what's") || StringAlgo.contains(question,"what") ){
				qvector[EARNINGS_RANK] = 1;
				sems[EARNINGS_RANK] = "?";	
			}
		}
		if(StringAlgo.contains(question,"rating") || StringAlgo.contains(question,"ratings")){
			if( StringAlgo.contains(question,"what's") || StringAlgo.contains(question,"what") ){
				qvector[RATING] = 1;
				sems[RATING] = "?";	
			}
		}
		if(StringAlgo.contains(question,"genre") || StringAlgo.contains(question,"genres")){
			if( StringAlgo.contains(question,"what's") || StringAlgo.contains(question,"what") ){
				qvector[GENRE] = 1;
				sems[GENRE] = "?";	
			}
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
		question = question.toLowerCase();
		//--------------AWARDTYPE-----------------------------
		if(StringAlgo.contains(question,"best picture") || StringAlgo.contains(question,"best movie") ){
			qvector[AWARDTYPE] = 1;
			sems[AWARDTYPE] = "BEST-PICTURE";
		}
		else if(StringAlgo.contains(question,"best director")){
			qvector[AWARDTYPE] = 1;
			sems[AWARDTYPE] = "BEST-DIRECTOR";
		}
		else if(StringAlgo.contains(question,"best actor") || StringAlgo.contains(question,"best leading actor")
				|| (StringAlgo.contains(question,"actor") && StringAlgo.contains(question,"oscar"))){
			qvector[AWARDTYPE] = 1;
			sems[AWARDTYPE] = "BEST-ACTOR";
		}
		else if(StringAlgo.contains(question,"best supporting actor")){
			qvector[AWARDTYPE] = 1;
			sems[AWARDTYPE] = "BEST-SUPPORTING-ACTOR";
		}
		else if(StringAlgo.contains(question,"best actress") || StringAlgo.contains(question,"best leading actress")
				|| (StringAlgo.contains(question,"actress") && StringAlgo.contains(question,"oscar")) ){
			qvector[AWARDTYPE] = 1;
			sems[AWARDTYPE] = "BEST-ACTRESS";
		}
		else if(StringAlgo.contains(question,"best supporting actress")){
			qvector[AWARDTYPE] = 1;
			sems[AWARDTYPE] = "BEST-SUPPORTING-ACTRESS";
		}
		else if(StringAlgo.contains(question,"oscar")){
			qvector[AWARDTYPE] = 1;
			sems[AWARDTYPE] = "*";
		}
		if(qvector[AWARDTYPE] == 1) {
		//--------------AWARDYEAR-----------------------------
			if(StringAlgo.contains(question, "when") || StringAlgo.contains(question, "which year") || StringAlgo.contains(question, "what year")){
				qvector[AWARDYEAR] = 1;
				sems[AWARDYEAR] = "?";
			}else{
				String tokens[] = question.split("[ ?]");
				for (String token : tokens) {
					if(StringAlgo.isNumber(token)){
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
		
	}

	private void generateEvent(String question){
		question = question.toLowerCase();
		if(StringAlgo.contains(question,"direct") || StringAlgo.contains(question,"director") 
				|| StringAlgo.contains(question,"directed") || StringAlgo.contains(question,"directs")){
			qvector[DIRECT] = 1;
			sems[DIRECT] = "DIRECT";
		}
		if(
			StringAlgo.contains(question,"act") || StringAlgo.contains(question,"acts") || StringAlgo.contains(question,"acted") 
			|| StringAlgo.contains(question,"actor") || StringAlgo.contains(question,"actors")
			|| StringAlgo.contains(question,"actress") || StringAlgo.contains(question,"actresses")
			|| StringAlgo.contains(question,"play") || StringAlgo.contains(question,"plays") || StringAlgo.contains(question,"played")
			|| StringAlgo.contains(question,"star") || StringAlgo.contains(question,"stars") || StringAlgo.contains(question,"stared")
		        ) 
		{
			qvector[ACT] = 1;
			sems[ACT] = "ACT";
		}
		if(StringAlgo.contains(question,"win") || StringAlgo.contains(question,"wins") || StringAlgo.contains(question,"won")){
			qvector[WIN] = 1;
			sems[WIN] = "WIN";	
		}
	}
	
	private void generateEntities(String question){
		question = question.toLowerCase();
		if(StringAlgo.contains(question,"who") || StringAlgo.contains(question,"which actor") || StringAlgo.contains(question,"which actress") 
		   || StringAlgo.contains(question,"which director") ){
			qvector[MOVIEWORKERNAME] = 1;
			sems[MOVIEWORKERNAME] = "?";
		}else if(StringAlgo.contains(question,"which movie")){
			qvector[MOVIENAME] = 1;
			sems[MOVIENAME] = "?";
		}
		int index_of_movieWorker1 = -1;
		for (String movieWorker : movieWorkerLastNames) {
			if(StringAlgo.contains(question,movieWorker.toLowerCase())){
				qvector[MOVIEWORKERNAME] = 1;
				sems[MOVIEWORKERNAME] = movieWorker;
				index_of_movieWorker1 = question.indexOf(movieWorker);
				break;
			}
		}
		if(index_of_movieWorker1 >=0 ){
			String subQuestion = question.substring(index_of_movieWorker1 + sems[MOVIEWORKERNAME].length());
			for (String movieWorker : movieWorkerLastNames) {
				if(StringAlgo.contains(subQuestion,movieWorker.toLowerCase())){
					qvector[MOVIEWORKERNAME2] = 1;
					sems[MOVIEWORKERNAME2] = movieWorker;
					break;
				}
			}
		}
		int index_of_movie1 = -1;
		for (String movie : movieNames) {
			if(StringAlgo.contains(question,movie.toLowerCase())){
				qvector[MOVIENAME] = 1;
				sems[MOVIENAME] = movie;
				index_of_movie1 = question.indexOf(movie);
				break;
			}
		}
		if(index_of_movie1 >=0){
			String subQuestion = question.substring(index_of_movie1 + sems[MOVIENAME].length());
			for (String movie : movieNames) {
				if(StringAlgo.contains(subQuestion,movie.toLowerCase())){
					qvector[MOVIENAME2] = 1;
					sems[MOVIENAME2] = movie;
					break;
				}
			}
		}
	}
}
