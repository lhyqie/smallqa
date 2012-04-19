
package part2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IdentityHashMap;

import org.junit.Test;

import part2b.SportsVectorBuilder;

public class SportsVectorBuilderTest {

	
	
	@Test
	public void testSetSemantic() {
	   //String question = "Did Cameron direct Titanic?";
	   //String question = "Did Dicaprio star Titanic?";
	   //String question = "Did Dicaprio star in Titanic?";
	   //String question = "Do Dicaprio act in Titanic?";
	   //String question = "Did Neeson star in Schindler's List ?";
		 String question = "Did Razzoli win the men slalom in 2010?";
		SportsVectorBuilder sb = new SportsVectorBuilder();
		sb.generateQuestionVector(question);
		
		int[] array = sb.get_qvector();
		for(int x: array)
		{
			System.out.print(x+"\t");
		}
		System.out.println();
		System.out.println(Arrays.asList(sb.get_stringvalue()));
	}

}
