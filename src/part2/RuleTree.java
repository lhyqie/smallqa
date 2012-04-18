package part2;

import java.util.ArrayList;

public class RuleTree {
	public RuleTree(){}
	public RuleTree(Rule rule){
		this.rule = rule;
	}
	Rule rule = null;
	ArrayList<RuleTree> children = new ArrayList<RuleTree>();
	public String toString(){
		return rule.toString();
	}
	public void print(){
		printNode(this);
	}
	private void printNode(RuleTree root){
		if(root!=null){
			System.out.println(root.rule);
			for (int i = 0; i < root.children.size(); i++) {
				printNode(root.children.get(i));
			}
		}
	}
	public RuleTree getParentNode(RuleTree root, RuleTree target){
		if(root!=null){
			for (RuleTree child : root.children) {
				if( child == target) return root;
				RuleTree res = getParentNode(child, target); 
				if(res!=null) return res;
			}
		}
		return null;
	}
}
