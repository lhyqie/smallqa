
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
		String[] questions = {
				"Who won women figure skating in 2010?",
				"Did a Korean woman win the gold medal in figure skating?",
				"Did Razzoli win the men slalom in 2010?",
				"Did a woman from Korea win the gold medal in figure skating?",
				"Who won women figure skating in Vancouver?",
				"Which country won gold in women hockey?",
				"Which event did Lysacek win?",
				"In which event did Ohno win silver?",
				"In which sport did Canada win two gold medals?",
				"Is shorttrack in the winter Olympics?",
				"Who got the gold medal for biathlon in 2010?",
				"In which game did razzoli win a gold medal in 2010?",
				"Who won the second place in slalom in 2010?",
				"In which game did kostelic win a silver medal in 2006?",
				"Who won the bronze medal in figureskating 2006?",
				"Who is the first place in skijumping 2010?",
				"Did Ammann win the silver medal of skijumping in 2010?",
				"who won the silver medal of 1000 speedskating in 2010?",
				"Who won the crosscountry gold medal in 2006?",
				"What's the nationality of Svendsen?",
				"Which country does Lee come from?",
				"Is Miller male or female?",
				"How many competitions are there in the Winter Olympics?",
				"Who won the ski jumping in Winter Olympics 2010?",
				"Which athlete won the most medals in Winter Olympics 2010?",
				};
//		String question = ;
//		String question = "Did a Korean woman win the gold medal in figure skating?";
//		String question = "Did Razzoli win the men slalom in 2010?";
		SportsVectorBuilder sb = new SportsVectorBuilder();
		for(String question:questions)
		{
			sb.generateQuestionVector(question);
			System.out.println(question);
			int[] array = sb.get_qvector();
			for(int x: array)
			{
				System.out.print(x+"\t");
			}
			System.out.println();
			System.out.println(Arrays.asList(sb.get_stringvalue()));
			String sql = sb.generateSQL();
			System.out.println(sql);
			System.out.println(SQLiteRunner.getSQLResult(Config.getSportsDB(), sql));
			System.out.println("-------------------------------------------------");
		}
		
	}

}
