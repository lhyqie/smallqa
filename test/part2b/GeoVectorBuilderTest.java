
package part2b;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IdentityHashMap;

import org.junit.Test;

import common.Config;

import part2.SQLiteRunner;
import part2b.SportsVectorBuilder;

public class GeoVectorBuilderTest {

	
	
	@Test
	public void testSetSemantic() {
	   //String question = "Did Cameron direct Titanic?";
	   //String question = "Did Dicaprio star Titanic?";
		String[] questions = {"Is France in Europe?",
				"Is Rome the capital of Italy?",
				"What is the capital of Bahrain?",
				"Which is the highest mountain in the world?",
				"In which continent does Canada lie?",
				"What is the capital of Bahrain?",
//				"With which countries does Italy have a border?",
				"Which is the highest mountain in the world?",
//				"Is the Pacific deeper than than the Atlantic?",
				"Which city is the capital of Luxembourg?",
				"Is Paris the capital of France?",
				"Which country is Cape Town in?",
				"Which countinent is Benin in?",
				"Is South Africa in Australia?",
				"What is the area of Asia?",
				"Which continent is the highest?",
				"What is the population of Europe?",
//				"Which country is on the border of Italy?",
				"Which ocean is the deepest?",
				"What is the height of Mount Everest?",
				"Is Makalu the highest mountain?",
//				"Is Slovenia on the border of Canada?",
				"Which continent is France in?",
				"What's the area of Africa?",
				"What's the population of Asia?",
				"Which continent has the highest sea level?",
				"Which continent has the lowest altitude?",
				"What's the capital of Italy?",
//				"Which country has more than one capital?",
//				"With which countries does Canada have a border?",
				"Which mountain is the highest?",
				"What's the height of Mount Everest?",
				"Which ocean is the deepest?",
				"What is the most depth of Arctic Ocean?"
				};
//		String question = "Who won women figure skating in 2010?";
//		String question = "Did a Korean woman win the gold medal in figure skating?";
//		String question = "Did Razzoli win the men slalom in 2010?";
		GeoVectorBuilder sb = new GeoVectorBuilder();
		for (String question : questions)
		{
			sb.generateQuestionVector(question);
			int[] array = sb.get_qvector();
			System.out.println(question);
			for(int x: array)
			{
				System.out.print(x+"\t");
			}
			System.out.println();
			System.out.println(Arrays.asList(sb.get_stringvalue()));
			System.out.println("----------------------------");
			String sql = sb.generateSQL();
			System.out.println(sql);
			
			System.out.println(SQLiteRunner.getSQLResult(Config.getGeographyDB(), sql));
		}
	}

}
