package part2b;

import static org.junit.Assert.*;

import org.junit.Test;

public class MovieVectorBuilderTest {

	@Test
	public void testGenerateQuestionVector() {
		MovieVectorBuilder builder = new MovieVectorBuilder();
		String questions[]= {	 
//								 "Which is the scary movie by Kubrik with Nicholson?",
//								 "Did a French actor win the oscar in 2012?",
//								 "Did Cameron direct Titanic?",
//								 "Did Dicaprio star Titanic?",
//								 "Did Dicaprio act Titanic?",
//								 "Do Cameron direct in Titanic?",
//								 "Did Neeson star in Schindler's List?",
//								 "Did Swank win the oscar in 2000?",
//								 "Did a French actor win the oscar in 2012?",
//								 "Who directed Hugo II?",
//								 "Who won the oscar for best actor in 2005?",
//								 "Who directed the best movie in 2010?",
//								 "Is the Shining by Kubrik?",
//								 "Who won the oscar for the best actor in 2005?",
//								 "Which actress won the oscar in 2012?",
//								 "Who directed the best movie in 2010?",
//								 "Who is the director of Avatar?",
								 "In which year was Titanic showed?",
							};
		//case 1;
		for (String question : questions) {
			System.out.println(question);
			builder.generateQuestionVector(question);
			for (int i = 0; i < builder.SIZE; i++) {
				System.out.print((i+1)+"\t");
			}
			System.out.println();
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
				System.out.print(builder.qvector[i]+"\t");
			}
			System.out.println();
			
			for (int i = 0; i < builder.sems.length; i++) {
				System.out.print(builder.sems[i]+"\t");
			}
			System.out.println();
			System.out.println();
		} 
	}

	//@Test
	public void testGenerateSQL() {
		fail("Not yet implemented");
	}

}
