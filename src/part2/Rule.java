package part2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Rule implements Comparable<Rule> {
	 final static int SEMANTIC_DEFAULT = 0;
	 final static int SEMANTIC_ATOM = 1;
	 final static int SEMANTIC_CONCAT = 2;
	 final static int SEMANTIC_REDUCTION = 3;
	 
	 public Rule(String left, String [] rightElements){
	 	 this.left = left; 
	 	 this.right = new ArrayList<String>();
	 	 right = Arrays.asList(rightElements);
	 }
	 public Rule(String left, ArrayList<String> right){
	 	 this.left = left; 
	 	 this.right = right;
	 }
	 String left;
	 List<String> right;
	 int sem_type = SEMANTIC_DEFAULT;
	 String sem;
	 public String toString(){
		 return ""+left+"=>"+right + "\n"+ sem;
	 }
	 
	@Override
	public int compareTo(Rule other) {
		// TODO Auto-generated method stub
		if(left.compareTo(other.left) != 0 )
			return left.compareTo(other.left);
		else {
			for (int i = 0; i < Math.min(right.size(), other.right.size()); i++) {
				int res = right.get(i).compareTo(other.right.get(i));
				if(res!=0) return res;
			}
			return right.size()-other.right.size();
		}
	}
}
