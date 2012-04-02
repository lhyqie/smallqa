package part1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Part1Main extends JFrame {
	JTextField questionField = new JTextField(30);
	JButton commitBtn = new JButton("Find category and print parse Tree"); 
	JTextArea categoryField = new JTextArea(2,50);
	JTextArea parseTreeField = new JTextArea(10,50);
	JPanel panel = new JPanel(); 
	private int width = 600;
	private int height = 400;
	CategoryClassifier classifier;
	public Part1Main(){
		this.setSize(width,height);
		questionField.setBounds(30, 30, 400, 30);
		commitBtn.setBounds(450, 30, 50, 30);
		categoryField.setBounds(30,100,50,30);
		parseTreeField.setBounds(30,200,50,30);
		panel.add(questionField);
		panel.add(commitBtn);
		panel.add(categoryField);
		panel.add(parseTreeField);
		categoryField.setEditable(false);
		this.add(panel);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Part1 of NLP project by Huayi & Weixiang");
		//add classifier and use it.
		classifier = new CategoryClassifier(1.0);
		
		commitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = questionField.getText();
				if(text.length() == 0){
					JOptionPane.showMessageDialog(Part1Main.this,
						    "No question as input",
						    "Missing information",
						    JOptionPane.WARNING_MESSAGE);
					return ;
				}
				
				categoryField.setText(classifier.classify(text));
				parseTreeField.setText(PrintParseTree.getParseTree(new Question(text)));
			}
		});
	}
	public static void main(String[] args) {
		//printParseTree();
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			Part1Main mainFrame = new Part1Main();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
		testingPhrase(1);
	}
	public static void printParseTree(){
		PrintParseTree app = new PrintParseTree();
		LinkedList<Question> qList = app.readQuestionsFromFile("data/part1_questions.txt");
		app.writeParseTreeToFile("output/parseTrees.txt", qList);
	}
	public static void trainingPhrase(){
		
	}
	public static void testingPhrase(double train_percent){
		PrintParseTree parseTree = new PrintParseTree();
		CategoryClassifier classifier = new CategoryClassifier(train_percent);
		LinkedList<Question> qList = classifier.loadTrainingData();		
		int correct_count = 0;
		
//		for (int i=0;i<classifier.test_list.length;i++) {
//			Question question = qList.get(classifier.test_list[i]);
//			System.out.println(question.getCategory()+"!");
//			System.out.print(question.getId() + " " +question.getText());
//			String res = classifier.classify(question.getText());
//			System.out.println(res);
//			if(res.equalsIgnoreCase(question.getCategory()+""))
//				correct_count++;
//		}
		for (Question question:qList) {
			System.out.println(question.getCategory()+"!");
			System.out.print(question.getId() + " " +question.getText());
			String res = classifier.classify(question.getText());
			System.out.println(res);
			if(res.equalsIgnoreCase(question.getCategory()+""))
				correct_count++;
		}
//		System.out.println("Which is the scary movie by Kubrik with Nicholson?");
//		System.out.println(classifier.classify("Which is the scary movie by Kubrik with Nicholson?"));
//		System.out.println("Did a French actor win the oscar in 2012?");
//		System.out.println(classifier.classify("Did a French actor win the oscar in 2012?"));
//		System.out.println("Which actress won the oscar in 2012?");
//		System.out.println(classifier.classify("Which actress won the oscar in 2012?"));
//		
		System.out.println("the accuracy is "+ 1.0*correct_count/qList.size());
	}
}
