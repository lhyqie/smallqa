package competition;

import static org.junit.Assert.*;

import org.junit.Test;

public class MiniWatsonTest {
	String questions[]= {
//---------------------------------------- Movie ---------------------------------------------			 
//			 "Which movie wins the Best picture in 2011?",
//			 "Which movie won the best picture in 2009?",
//			 "What is the genre of The Hangover?",
//			 "What is the earnings rank of New Moon?",
//		     "What's the runtime of Spider-Man 2?",
//			 "What is the rating of The Shore?",
//			 "What is the rating of Shrek?",
//			 "What's the genre, rating and runtime for Star Wars 3?",
//			 "When was Avatar released?",
//			 "Was Jurassic Park shown in 1993?",
//			 "Was Jurassic Park released in 1993?",
//			 "In which year was Titanic showed?",
//			 "Did Swank win the oscar in 2000?",
//			 "Who directed the best movie in 2010?",
//			 "Who wins the best director in 2010?",
//			 "Who won the best actor in 2010?",
//			 "Who won the best supporting actress in 2010?",
//			 "When did Will Smith won the best actor?",
//			 "Who won the best actor of oscar in 2011?",
//			 "Which actress won the oscar in 2012?",
//			 "Who won the oscar for the best actor in 2005?",
//			 "Where was John Wayne born?",
//			 "Where was Gary Cooper born?",
//			 "When was John Belushi born?",
//			 "When was Bette Davis born?",
//			 "What is the birth date of James Dean?",
//			 "What is the date of birth of James Dean?",
//			 "What is the birthday of James Dean?", 
//			 "What is the place of birth of Gary Cooper?",
//			 "What is the birth place of Gary Cooper?",	 
//			 "Did Cameron direct Titanic?",
//			 "Does Cameron direct in Titanic?",
//			 "Who directed Hugo II?",
//			 "Who is the director of Avatar?",
//			 "Who directs Avatar?",
//			 "Who is the director of Avatar?",
//			 "Did Dicaprio star Titanic?",
//			 "Did Dicaprio act Titanic?",
//			 "Did Neeson star in Schindler's List?",
//			 "Which actor acted in Rocky?",
//			 "In which movie did Jeff Bridges won the best actor in 2010?",
//			 "In which movie did Dicaprio get the Best supporting actress in 2010?",
//			 "Who was the best actor in 1940?",	
//			 "Who are the actors of Avatar?",
//			 "Who are the actresses of Avatar?",
////----------------------------------------- Geography ------------------------------------
//			 "Is France in Europe?",
//			 "Is Rome the capital of Italy?",
//			 "What is the capital of Bahrain?",
//			 "Which is the highest mountain in the world?",
//			 "In which continent does Canada lie?",
//			 "What is the capital of Bahrain?",
//			 "what mountain is higher than lhotse?",
//			 "is K2 higher than lhotse? ",
//			 "Which is the highest mountain in the world?",
//			 "which continent is larger than Africa?",
//			 "Is Asia higher than Africa?",
//			 "Does Africa have larger population than Asia?",
//			 "Is the Pacific deeper than than the Atlantic?",
//			 "What ocean is deeper than than the Atlantic?",
//			 "Which city is the capital of Luxembourg?",
//			 "Is Paris the capital of France?",
//			 "Which country is Cape Town in?",
//			 "Which countinent is Benin in?",
//			 "Is South Africa in Australia?",
//			 "What is the area of Asia?",
//			 "Which continent is the highest?",
//			 "What is the population of Europe?",
//			 "Which ocean is the deepest?",
//			 "What is the height of Mount Everest?",
//			 "Is Makalu the highest mountain?",
//			 "Which continent is France in?",
//			 "What's the area of Africa?",
//			 "What's the population of Asia?",
//			 "Which continent has the highest sea level?",
//			 "Which continent has the lowest altitude?",
//			 "What's the capital of Italy?",
//			 "Which mountain is the highest?",
//			 "What's the height of Mount Everest?",
//			 "Which ocean is the deepest?",
//			 "What is the most depth of Arctic Ocean?",
//-----------------------------Sports --------------------------------------
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
	@Test
	public void testAnswerQuestion() {
		MiniWatson watson = new MiniWatson();
		for (String question : questions) {
			System.out.println(question);
			System.out.println(watson.answerQuestion(question));
		}	
	}

	//@Test
	public void testClassifyCategory() {
		MiniWatson watson = new MiniWatson();
		for (String question : questions) {
			System.out.println(watson.classifyCategory(question));
		}	
	}

}
