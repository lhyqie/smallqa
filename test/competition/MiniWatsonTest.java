package competition;

import static org.junit.Assert.*;

import org.junit.Test;

public class MiniWatsonTest {
	String questions[]= {
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
			 "Who won the best actor in 2010?",
			 "Who won the best supporting actress in 2010?",
			 "When did Will Smith won the best actor?",
			 "Who won the best actor of oscar in 2011?",
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
			 "Who was the best actor in 1940?",	 				 
	};
	//@Test
	public void testAnswerQuestion() {
		MiniWatson watson = new MiniWatson();
		for (String question : questions) {
			System.out.println(watson.answerQuestion(question));
		}	
	}

	@Test
	public void testClassifyCategory() {
		MiniWatson watson = new MiniWatson();
		for (String question : questions) {
			System.out.println(watson.classifyCategory(question));
		}	
	}

}
