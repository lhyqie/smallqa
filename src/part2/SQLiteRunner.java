package part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import part1.CategoryClassifier;

public class SQLiteRunner {
	SQLiteRunner(){}
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
