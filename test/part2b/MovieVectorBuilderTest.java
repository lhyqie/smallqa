package part2b;

import static org.junit.Assert.*;
import org.junit.Test;
import common.Config;
import common.StringAlgo;
import part2.SQLiteRunner;
public class MovieVectorBuilderTest {
	
	String questions[]= {
//			 "Who won the most academy awards in oscar?",
//			 "Who are the actors of Avatar?",
//			 "Who are the actresses of Avatar?",
//			 "Who are the actors and actresses in Titanic?",
//			 "What types of awards does oscar have?"
//			 "Does Men in Black have Will Smith?", 			
//			 "Is Will Smith in the movie Hitch?"
//			 "How many movies was Michael J. Fox in?",
//			 "How many movies did Ron Howard star in?",
//			 "Who acted in Bonnie and Clyde?",
//			 "Which is the scary movie by Kubrik with Nicholson?",
//			 "Did a French actor win the oscar in 2012?",
//			 "Which actress won the oscar in 2012?",
//			 "Is The King's Speech in 2010?",
//        -------------------------------------------------------------

//--------------------solved ---------------------
			 "Which movie wins the Best picture in 2011?",
			 "Which movie won the best picture in 2009?",
			 "What is the genre of The Hangover?",
			 "What is the earnings rank of New Moon?",
		     "What's the runtime of Spider-Man 2?",
			 "What is the rating of The Shore?",
			 "What is the rating of Shrek?",
			 "What's the genre, rating and runtime for Star Wars 3?",
			 "When was Avatar released?",
			 "Was Jurassic Park shown in 1993?",
			 "Was Jurassic Park released in 1993?",
			 "In which year was Titanic showed?",
			 "Did Swank win the oscar in 2000?",
			 "Who directed the best movie in 2010?",
			 "Who wins the best director in 2010?",
			 "Who was the best actor in 1940?",
			 "Who won the best actor in 2010?",
			 "Who won the best supporting actress in 2010?",
			 "Who won the best actor of oscar in 2011?",
			 "When did Will Smith won the best actor?",
			 "Which actress won the oscar in 2012?",
			 "Who won the oscar for the best actor in 2005?",
			 "Where was John Wayne born?",
			 "Where was Gary Cooper born?",
			 "When was John Belushi born?",
			 "When was Bette Davis born?",
			 "What is the birth date of James Dean?",
			 "What is the date of birth of James Dean?",
			 "What is the birthday of James Dean?", 
			 "What is the place of birth of Gary Cooper?",
			 "What is the birth place of Gary Cooper?",	 
			 "Did Cameron direct Titanic?",
			 "Does Cameron direct in Titanic?",
			 "Who directed Hugo II?",
			 "Who is the director of Avatar?",
			 "Who directs Avatar?",
			 "Who is the director of Avatar?",
			 "Did Dicaprio star Titanic?",
			 "Did Dicaprio act Titanic?",
			 "Did Neeson star in Schindler's List?",
			 "Which actor acted in Rocky?",
			 "In which movie did Jeff Bridges won the best actor in 2010?",
			 "In which movie did Dicaprio get the Best supporting actress in 2010?",
			 "In which movie did Kathryn Bigelow won the best director in 2010?",
			 "Did Cameron win the best director?",
			 "Did Dicaprio win the best actor?",
			 "Did Smith win the oscar?",
			 "Is Joan Fontaine older than Tyrone Power?",
			 "Is Joan Fontaine younger than Tyrone Power?",
			 "Is Joan Fontaine as young as Tyrone Power?",
			 "Does Platoon have more runtime than Monster?",
			 "Does Platoon have less runtime than Monster?",
			 "Is the earnings rank of  Platoon higher than Monster?",
			 "Is the earnings rank of  Platoon lower than Monster?",
			 "Is the earnings rank of  Platoon the same as Monster?",
	};
	
	//@Test
	public void testGenerateQuestionVector() {
		MovieVectorBuilder builder = new MovieVectorBuilder();
			
		for (String question : questions) {
			System.out.print(question+",");
			builder.generateQuestionVector(question);
//			for (int i = 0; i < builder.SIZE; i++) {
//				System.out.print((i+1)+"\t");
//			}
//			System.out.println();
			
//			System.out.print("MW"+"\t\t");
//			System.out.print("MO"+"\t\t");
//			System.out.print("MW2"+"\t\t");
//			System.out.print("MO2"+"\t\t");
//			System.out.print("DOB"+"\t\t");
//			System.out.print("POB"+"\t\t");
//			System.out.print("MYEAR"+"\t\t");
//			System.out.print("RATING"+"\t\t");
//			System.out.print("RUNTIME"+"\t\t");
//			System.out.print("GENRE"+"\t\t");
//			System.out.print("ERANK"+"\t\t");
//			System.out.print("ATYPE"+"\t\t");
//			System.out.print("AYEAR"+"\t\t");
//			System.out.print("ACT"+"\t\t");
//			System.out.print("DIRECT"+"\t\t");
//			System.out.print("WIN"+"\t\t");
//			System.out.println();
			for (int i = 0; i < builder.qvector.length; i++) {
				System.out.print(builder.qvector[i]+",");
			}
			System.out.println();
			
//			for (int i = 0; i < builder.sems.length; i++) {
//				System.out.print(builder.sems[i]+"\t");
//			}

		} 
	}

	@Test
	public void testGenerateSQL() {
		MovieVectorBuilder builder = new MovieVectorBuilder();
		
		for (String question : questions) {
			System.out.println(question);
			builder.generateQuestionVector(question);
			for (int i = 0; i < builder.SIZE; i++) {
				System.out.print((i+1)+"\t");
			}
			System.out.println();
			for (int i = 0; i < builder.qvector.length; i++) {
				System.out.print(builder.qvector[i]+"\t");
			}
			System.out.println();
			
			for (int i = 0; i < builder.sems.length; i++) {
				System.out.print(builder.sems[i]+"\t");
			}
			System.out.println();
			System.out.println();
			
			String sql = builder.generateSQL();
			System.out.println(sql);
			System.out.println(SQLiteRunner.getSQLResult(Config.getMovieDB(), sql));
			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		}
	}
    //@Test
	public void testQuestionType(){
		MovieVectorBuilder mBuilder = new MovieVectorBuilder();
		for (String question : questions) {
			System.out.println(question);
			System.out.println(mBuilder.questionType(question));
		}
	}
}
