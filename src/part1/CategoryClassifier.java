package part1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;
import common.Config;
import edu.stanford.nlp.ling.TaggedWord;

public class CategoryClassifier {
	Connection connection = null;  
    Statement statement = null;  
    
	private TreeSet<String> moviePN = new TreeSet<String>(); 
	private TreeSet<String> sportPN = new TreeSet<String>(); 
	private TreeSet<String> geographyPN = new TreeSet<String>(); 
	
	public CategoryClassifier(){
		loadKeyWordSetsForMovie();
		loadKeyWordSetsForSports();
		loadKeyWordSetsForGeography();
	}
	
	public String classify(String text){
		ArrayList<TaggedWord> tagWords = PrintParseTree.getTaggedText(text);
		System.out.println(tagWords);
		//System.out.println(tagWords.get(1).tag());
		int votes[] = new int[3]; 
		String classLabel[] = {"M","S","G"};
		for (int i = 0; i < tagWords.size(); i++) {
			if(tagWords.get(i).tag().equals("NNP")){
				String word = tagWords.get(i).word().toLowerCase();
				for (String str : moviePN) {
					if( str.toLowerCase().indexOf(word) >= 0 ) {
						++ votes[0];
						break;
					}
				}
				for (String str : sportPN) {
					if( str.toLowerCase().indexOf(word) >= 0 ) {
						++ votes[1];
						break;
					}
				}
				for (String str : geographyPN) {
					if( str.toLowerCase().indexOf(word) >= 0 ) {
						++ votes[2];
						break;
					}
				}
				
			}
		}
		int maxVote = -1;
		int maxIndex = -1; 
		int maxCnt = 0;
		for (int i = 0; i < votes.length; i++) {
			if(maxVote < votes[i]){
				maxIndex = i;
				maxVote = votes[i];
			}
		}
		for (int i = 0; i < votes.length; i++) {
			if(maxVote == votes[i]) ++maxCnt;
		}
		System.out.print(Arrays.toString(votes));
		if(maxCnt == 1){
			return classLabel[maxIndex];
		}
		else 
			return "";
	}
	private void connectToDB(String dbName){
		try {  
	      Class.forName("org.sqlite.JDBC");  
	      connection = DriverManager.getConnection("jdbc:sqlite:data/db/"+dbName);
	      statement = connection.createStatement();  
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private void closeDB(){
        try {
			statement.close();  
		    connection.close();  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	/* add MovieDB.Person.name and MovieDB.Movie.name
	 */
	private void loadKeyWordSetsForMovie(){
		connectToDB(Config.getMovieDB());
		try {
			ResultSet rs = statement.executeQuery("select * from Person");
			while(rs.next()){
				moviePN.add(rs.getString("name"));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		try {
			ResultSet rs = statement.executeQuery("SELECT * FROM Movie");
			while(rs.next()){
				moviePN.add(rs.getString("name"));
				
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		closeDB();
	}
	/* add  SportsDB.athletes.name and  SportsDB.athletes.nationality
	        SportsDB.competitions.name and SportsDB.results.winner
	        and SportsDB.results.medal
	*/
	private void loadKeyWordSetsForSports(){
		connectToDB(Config.getSportsDB());
		try {
			ResultSet rs = statement.executeQuery("SELECT * FROM athletes");
			while(rs.next()){
				sportPN.add(rs.getString("name"));
				sportPN.add(rs.getString("nationality"));
			}
			rs.close();
			
			rs = statement.executeQuery("SELECT * FROM competitions");
			while(rs.next()){
				sportPN.add(rs.getString("name"));
			}
			rs.close();
			
			rs = statement.executeQuery("SELECT * FROM results");
			while(rs.next()){
				sportPN.add(rs.getString("winner"));
				sportPN.add(rs.getString("medal"));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		closeDB();	
	}
	/*
	 * add GeographyDB.Cities.Name and GeographyDB.Continents.Continent and
	 *     GeographyDB.Countries.Name and GeographyDB.Mountains.Name and
	 *     GeographyDB.Seas.Ocean
	 *    
	 */
	private void loadKeyWordSetsForGeography(){
		connectToDB(Config.getGeographyDB());
		try {
			ResultSet rs = statement.executeQuery("SELECT * FROM Cities");
			while(rs.next()){
				geographyPN.add(rs.getString("Name"));
			}
			rs.close();
			
			rs = statement.executeQuery("SELECT * FROM Continents");
			while(rs.next()){
				geographyPN.add(rs.getString("Continent"));
			}
			rs.close();
			
			rs = statement.executeQuery("SELECT * FROM Countries");
			while(rs.next()){
				geographyPN.add(rs.getString("Name"));
			}
			rs.close();
			
			rs = statement.executeQuery("SELECT * FROM Mountains");
			while(rs.next()){
				geographyPN.add(rs.getString("Name"));
			}
			rs.close();
			
			rs = statement.executeQuery("SELECT * FROM Seas");
			while(rs.next()){
				geographyPN.add(rs.getString("Ocean"));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		closeDB();
	}
//	/*
//	 * if a term/proper noun is only in Movies
//	 */
//	public boolean termOnlyInMoviePN(String term){
//		if(moviePN.contains(term)){
//			if(sportsPN.contains(term) || geographyPN.contains(term)){
//				return false;
//			}else return true;
//		}else return false;
//	}
//	/*
//	 * if a term/proper noun is only in Movies
//	 */
//	public boolean termOnlyInSportPN(String term){
//		if(sportsPN.contains(term)){
//			if(moviePN.contains(term) || geographyPN.contains(term)){
//				return false;
//			}else return true;
//		}else return false;
//	}
//	/*
//	 * if a term/proper noun is only in Movies
//	 */
//	public boolean termOnlyInGeographyPN(String term){
//		if(geographyPN.contains(term)){
//			if(moviePN.contains(term) || sportsPN.contains(term)){
//				return false;
//			}else return true;
//		}else return false;
//	}
//	
	public TreeSet<String> getMoviePN() {
		return moviePN;
	}
	public TreeSet<String> getSportsPN() {
		return sportPN;
	}
	public TreeSet<String> getGeographyPN() {
		return geographyPN;
	}
}
