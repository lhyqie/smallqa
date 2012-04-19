package part2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.IdentityHashMap;

import org.junit.Test;

public class SemanticAttachmentTest {

	//@Test
	public void testGetRulesAndRelation() {
		 RuleTree rTree = new RuleTree(null);
		 SemanticAttachment.getRulesAndRelation("Did Allen direct Romeo?", rTree);
		 rTree.print();
	}

	//@Test
	public void testSetSemanticForNode() {
		 RuleTree rTree = new RuleTree(null);
		 String question = "Did Allen direct Romeo?";
		 SemanticAttachment.getRulesAndRelation(question, rTree);
		 rTree.print();
		 System.out.println();
		 
		 String cat = "M";
		 
		 RuleTree node = null;
		 // test (1) VB => direct
		 System.out.println("\ntest 1 VB=>direct"); 
		 node = rTree.children.get(0).children.get(2).children.get(0);
		 System.out.println(node);
		 SemanticAttachment.setSemanticForNode(cat, rTree, node, question);
		 System.out.println(node.rule.sem);
		 
		 // test (2) NNP => Romeo  
		 System.out.println("\ntest 2 NNP=>Romeo"); 
		 node = rTree.children.get(0).children.get(2).children.get(1).children.get(0);
		 System.out.println(node);
		 SemanticAttachment.setSemanticForNode(cat, rTree, node, question);
		 System.out.println(node.rule.sem);
		 
		 // test (3) NP => NNP
		 System.out.println("\ntest 3 NP=>NNP"); 
		 node = rTree.children.get(0).children.get(2).children.get(1);
		 System.out.println(node);
		 SemanticAttachment.setSemanticForNode(cat, rTree, node, question);
		 System.out.println(node.rule.sem);		 
		 
		 //test (4) NP => NNP 
		 System.out.println("\ntest 4 NP=>NNP"); 
		 node = rTree.children.get(0).children.get(2);
		 System.out.println(node);
		 SemanticAttachment.setSemanticForNode(cat, rTree, node, question);
		 System.out.println(node.rule.sem);	
		 
		 //test (5) NNP=>Allen
		 System.out.println("\ntest 5 NNP=>Allen"); 
		 node = rTree.children.get(0).children.get(1).children.get(0);
		 System.out.println(node);
		 SemanticAttachment.setSemanticForNode(cat, rTree, node, question);
		 System.out.println(node.rule.sem);	
		 
		 //test (3) NP=>NNP
		 System.out.println("\ntest 3 NP=>NNP=>Allen"); 
		 node = rTree.children.get(0).children.get(1);
		 System.out.println(node);
		 SemanticAttachment.setSemanticForNode(cat, rTree, node, question);
		 System.out.println(node.rule.sem);	
		 
		 //test (7) SQ => VBD NP VP .
		 System.out.println("\ntest 7 SQ => VBD NP VP ."); 
		 node = rTree.children.get(0);
		 System.out.println(node);
		 SemanticAttachment.setSemanticForNode(cat, rTree, node, question);
		 System.out.println(node.rule.sem);	
	}
	
	@Test
	public void testSetSemantic() {
	   //String question = "Did Cameron direct Titanic?";
	   //String question = "Did Dicaprio star Titanic?";
	   //String question = "Did Dicaprio star in Titanic?";
	   //String question = "Do Dicaprio act in Titanic?";
	   //String question = "Did Neeson star in Schindler's List ?";
		 String question = "Did Swank win the oscar in 2000 ?";
		
		 question = Wrapper.annotateNNP("M", question);
		 RuleTree rTree = new RuleTree(null);
		 SemanticAttachment.getRulesAndRelation(question, rTree);
		 rTree.print();
		 System.out.println();
		 
		 SemanticAttachment.setSemantic("M",rTree, question);
		 
		 rTree.print();
		 System.out.println();
	}
	
	//@Test
	public void testLamdaReduction() {
		String sqlSem = "[SELECT] += SELECT count(*),(lambda :y :x){[FROM] += FROM Person P INNER JOIN Director D ON P.id = D.director_id INNER JOIN Movie M ON D.movie_id = M.id ,[WHERE] += P.Name LIKE '%:x' ,[WHERE] += M.name LIKE '%:y%' ,}<Romeo><Allen>";
		SemanticAttachment.lamdaReduction(sqlSem);
	}
	//@Test
	public void testTranslateToSQL(){
		String sqlSem = "[SELECT] += SELECT count(*),(lambda :y :x){[FROM] += FROM Person P INNER JOIN Director D ON P.id = D.director_id INNER JOIN Movie M ON D.movie_id = M.id ,[WHERE] += P.Name LIKE '%:x' ,[WHERE] += M.name LIKE '%:y%' ,}<Romeo><Allen>";
		sqlSem = SemanticAttachment.lamdaReduction(sqlSem);
		String sql = SemanticAttachment.translateToSQL(sqlSem);
		System.out.println("sql = \n "+sql);
	}
	//@Test
	public void testQuestionToSQL(){
		String question = "Did Cameron direct Titanic?";
		String cat ="M";
		String sql = SemanticAttachment.questionToSQL(cat, question);
		System.out.println("sql = \n" + sql);
	}
}
