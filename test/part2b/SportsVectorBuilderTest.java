
package part2b;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IdentityHashMap;

import org.junit.Test;

import common.Config;

import part2.SQLiteRunner;
import part2b.SportsVectorBuilder;

public class SportsVectorBuilderTest {

	
	
	@Test
	public void testSetSemantic() {
	   //String question = "Did Cameron direct Titanic?";
	   //String question = "Did Dicaprio star Titanic?";
		String question = "what event does ?";
//		String question = "Who won women figure skating in 2010?";
//		String question = "Did a Korean woman win the gold medal in figure skating?";
//		String question = "Did Razzoli win the men slalom in 2010?";
		SportsVectorBuilder sb = new SportsVectorBuilder();
		sb.generateQuestionVector(question);
		
		int[] array = sb.get_qvector();
		for(int x: array)
		{
			System.out.print(x+"\t");
		}
		System.out.println();
		System.out.println(Arrays.asList(sb.get_stringvalue()));
		System.out.println("----------------------------");
		String sql = sb.generateSQL();
		System.out.println(sql);
		
		System.out.println(SQLiteRunner.getSQLResult(Config.getSportsDB(), sql));
	}

}
