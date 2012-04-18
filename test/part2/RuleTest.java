package part2;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import org.junit.Test;

public class RuleTest {

	//@Test
	public void testRule() {
		Rule r = new Rule("S",new String[]{"NP","VP"});
		System.out.println(r);
	}
	
	//@Test
	public void testRule2() {
		ArrayList<String> right = new ArrayList<String>();
		right.add("NP");
		right.add("VP");
		Rule r = new Rule("S",right);
		System.out.println(r);
	}
	//@Test
	public void testRule3(){
		TreeSet<Rule> rules = new TreeSet<Rule>();
		Rule r1 = new Rule("CD",new String[]{"2010"});
		Rule r2 = new Rule("CD",new String[]{"2012"});
		rules.add(r1); 
		rules.add(r2);
		System.out.println(rules);
	}
	@Test
	public void loadRules(){
		TreeSet<Rule> rules = new TreeSet<Rule>();
		try {
			Scanner scan = new Scanner(new File("data/rules_trainning.txt"));
			while(scan.hasNextLine()){
				String line = scan.nextLine();
				if(line.contains("=>")){
					String pieces[] = line.split("=>");
					String left = pieces[0].trim();
					String right[] = pieces[1].split(" ");
					ArrayList<String> rightElements = new ArrayList<String>();
					for (String string : right) {
						if(string.trim().length() == 0) continue;
						if(string.equals("|")){
							rules.add(new Rule(left,rightElements));
							rightElements = new ArrayList<String>();
						}else{
							rightElements.add(string);
						}
					}
					//System.out.println(new Rule(left,rightElements));
					rules.add(new Rule(left,rightElements));
				}	
			}
			for (Object r : rules) {
				System.out.println(r);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
