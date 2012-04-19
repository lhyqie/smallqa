package part2;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class SQLiteRunnerTest {

	//@Test
	public void testGetSQLResult() {
		System.out.println(SQLiteRunner.getSQLResult("imdb-oscar-movie.sqlite", 
				"SELECT count(*)  FROM Person " +
				"P INNER JOIN Director D ON P.id = D.director_id " +
				"INNER JOIN Movie M ON D.movie_id = M.id " +
				"WHERE P.Name LIKE '%Cameron'  and  M.name LIKE '%Titanic%' "));
	}
	//@Test
	public void testGetMovieNameList(){
		ArrayList<String> movieNameList = SQLiteRunner.getMovieNameList();
		System.out.println(movieNameList);
		System.out.println(movieNameList.size());
	}
	@Test
	public void testGetMovieWorkerNameList(){
		ArrayList<String> movieWorkerNameList = SQLiteRunner.getMovieWorkerNameList();
		System.out.println(movieWorkerNameList);
		System.out.println(movieWorkerNameList.size());
	}
}
