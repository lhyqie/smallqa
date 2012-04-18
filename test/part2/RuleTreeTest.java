package part2;

import static org.junit.Assert.*;

import org.junit.Test;

public class RuleTreeTest {

	//@Test
	public void testPrint() {
		 RuleTree rTree = new RuleTree(null);
		 SemanticAttachment.getRulesAndRelation("Did Allen direct Romeo?", rTree);
		 rTree.print();
	}

	//@Test
	public void testGetParentNode() {
		 RuleTree rTree = new RuleTree(null);
		 SemanticAttachment.getRulesAndRelation("Did Allen direct Romeo?", rTree);
		 rTree.print();
		 
		 RuleTree node = rTree.children.get(0).children.get(1);
		 System.out.println(node);
		 System.out.println(node.getParentNode(rTree,node));
	}

}
