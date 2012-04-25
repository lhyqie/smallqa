package draft;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.TreeSet;

import common.Config;

public class GetKeywords
{
	Connection connection = null;  
    Statement statement = null;  
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
	public void get_keywords()
	{
		connectToDB(Config.getGeographyDB());
		TreeSet<String> sports_personName = new TreeSet<String>();
		try {
			ResultSet rs = statement.executeQuery("SELECT * FROM Continents");
			while(rs.next()){
//				sportPN.add(rs.getString("name").toLowerCase());
				sports_personName.add(rs.getString("continent").toLowerCase());
//				sportPN.add(rs.getString("nationality").toLowerCase());
			}
			rs.close();
			
//			rs = statement.executeQuery("SELECT * FROM competitions");
//			while(rs.next()){
//				sportPN.add(rs.getString("name").toLowerCase());
//			}
//			rs.close();
//			
//			rs = statement.executeQuery("SELECT * FROM results");
//			while(rs.next()){
//				sportPN.add(rs.getString("winner").toLowerCase());
//				sportPN.add(rs.getString("medal").toLowerCase());
//			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		closeDB();
		Iterator<String> it = sports_personName.iterator();
		while(it.hasNext())
		{
			String str = it.next();
			System.out.println(str);
		}
	}
	public static void main(String[] args)
	{
//		GetKeywords g= new GetKeywords();
//		g.get_keywords();
		TreeSet<String> ts = new TreeSet<String>();
		ts.add("hello");
		ts.add("world");
		System.out.println(ts);
	}
}
