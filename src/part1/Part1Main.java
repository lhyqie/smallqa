package part1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
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
				categoryField.setText("TBA");
				parseTreeField.setText(PrintParseTree.getParseTree(new Question(text)));
			}
		});
	}
	public static void main(String[] args) {
		//printParseTree();
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	       //UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");            
	       Part1Main mainFrame = new Part1Main();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
		testingPhrase();
	}
	public static void printParseTree(){
		PrintParseTree app = new PrintParseTree();
		LinkedList<Question> qList = app.readQuestionsFromFile("data/part1_questions.txt");
		app.writeParseTreeToFile("output/parseTrees.txt", qList);
	}
	public static void trainingPhrase(){
		
	}
	public static void testingPhrase(){
		PrintParseTree parseTree = new PrintParseTree();
		CategoryClassifier classifier = new CategoryClassifier();
		LinkedList<Question> qList = parseTree.readQuestionsFromFile("data/questions.txt");
		for (Question question : qList) {
			System.out.print(question.getId() + " " +question.getText());
			System.out.println(classifier.classify(question.getText()));
		}
		System.out.println(classifier.features[0].length);
	}
	
}
