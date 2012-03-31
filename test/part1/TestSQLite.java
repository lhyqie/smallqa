package part1;
  

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.Statement;  


public class TestSQLite {
	public static void main(String[] args) {  
		  
      Connection connection = null;  
      ResultSet resultSet  = null;  
      Statement statement = null;  

      try {  
          Class.forName("org.sqlite.JDBC");  
          connection = DriverManager  
                  .getConnection("jdbc:sqlite:data/db/imdb-oscar-movie.sqlite");  
          statement = connection.createStatement();  
          resultSet = statement  
                  .executeQuery("SELECT * FROM Person");  
          while (resultSet.next()) {  
              System.out.println("id:"  
                      + resultSet.getString("id"));  
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
  }  
}

