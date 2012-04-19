package part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import common.Config;



public class SQLiteRunner {
	SQLiteRunner(){}
	public static ArrayList<String> getMovieNameList(){
		  ArrayList<String> movieNameList = new ArrayList<String>();
		  Connection connection = null;  
		  Statement statement = null; 
	      ResultSet rs  = null;  
	      try {  
	          Class.forName("org.sqlite.JDBC");  
	          connection = DriverManager  
	                  .getConnection("jdbc:sqlite:data/db/"+Config.getMovieDB());  
	          statement = connection.createStatement();  
	          rs = statement.executeQuery("select name from Movie");  
	          while (rs.next()) {  
	        	  movieNameList.add(rs.getString("name"));
	          }  
	      } catch (Exception e) {  
	          e.printStackTrace();  
	      } finally {  
	          try {  
	              rs.close();  
	              statement.close();  
	              connection.close();  
	          } catch (Exception e) {  
	              e.printStackTrace();  
	          }  
	      }  
	      return movieNameList;
	}
	public static ArrayList<String> getMovieWorkerNameList(){
		  ArrayList<String> movieWorkerNameList = new ArrayList<String>();
		  Connection connection = null;  
		  Statement statement = null; 
	      ResultSet rs  = null;  
	      try {  
	          Class.forName("org.sqlite.JDBC");  
	          connection = DriverManager  
	                  .getConnection("jdbc:sqlite:data/db/"+Config.getMovieDB());  
	          statement = connection.createStatement();  
	          rs = statement.executeQuery("select name from Person");  
	          while (rs.next()) {  
	        	  String name = rs.getString("name");
	        	  String pieces[] = name.split(" ");
	        	  if(pieces.length==2 ) movieWorkerNameList.add(pieces[1]);
	          }  
	      } catch (Exception e) {  
	          e.printStackTrace();  
	      } finally {  
	          try {  
	              rs.close();  
	              statement.close();  
	              connection.close();  
	          } catch (Exception e) {  
	              e.printStackTrace();  
	          }  
	      }  
	      return movieWorkerNameList;
	}
	public static ArrayList<String> getMovieWorkerPOBList(){
		  ArrayList<String> movieWorkerPOBs = new ArrayList<String>();
		  Connection connection = null;  
		  Statement statement = null; 
	      ResultSet rs  = null;  
	      try {  
	          Class.forName("org.sqlite.JDBC");  
	          connection = DriverManager  
	                  .getConnection("jdbc:sqlite:data/db/"+Config.getMovieDB());  
	          statement = connection.createStatement();  
	          rs = statement.executeQuery("select pob from Person");  
	          while (rs.next()) {  
	        	  movieWorkerPOBs.add(rs.getString("pob"));
	          }  
	      } catch (Exception e) {  
	          e.printStackTrace();  
	      } finally {  
	          try {  
	              rs.close();  
	              statement.close();  
	              connection.close();  
	          } catch (Exception e) {  
	              e.printStackTrace();  
	          }  
	      }  
	      return movieWorkerPOBs;
	}
	public static String getSQLResult(String dbName, String sql){
		  String ret = "";
		  Connection connection = null;  
	      ResultSet resultSet  = null;  
	      Statement statement = null;  
	      ResultSetMetaData rsMetaData = null;
	      try {  
	          Class.forName("org.sqlite.JDBC");  
	          connection = DriverManager  
	                  .getConnection("jdbc:sqlite:data/db/"+dbName);  
	          statement = connection.createStatement();  
	          resultSet = statement  
	                  .executeQuery(sql);  
	          rsMetaData = resultSet.getMetaData();
	          int numberOfColumns = rsMetaData.getColumnCount();
	          while (resultSet.next()) {  
	        	  for (int i = 1; i <= numberOfColumns; i++) {
	        		   int type = rsMetaData.getColumnType(i); 
					   switch(type){
					   		case java.sql.Types.INTEGER: ret += resultSet.getInt(i); break;
					   		case java.sql.Types.VARCHAR: ret += resultSet.getString(i); break;
					   }
				  }
	        	  ret+="\n";
	          }  
	      } catch (Exception e) {  
	          e.printStackTrace();  
	      } finally {  
	          try {  
	              resultSet.close();  
	              statement.close();  
	              connection.close();  
	          } catch (Exception e) {  
	              e.printStackTrace();  
	          }  
	      }  
	      return ret;
	}
}
