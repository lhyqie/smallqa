package part1;

import java.util.ArrayList;
import java.util.Arrays;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class NaiveBayesClassifier {
	public static void buildDataOnTheFly(){
		Attribute num1 = new Attribute("num1");
		Attribute num2 = new Attribute("num2");
		ArrayList<String> labels = new ArrayList<String>();
		labels.add("no");
		labels.add("yes");
		Attribute cls = new Attribute("class", labels);
		ArrayList<Attribute> attributes = new ArrayList<Attribute>();
		attributes.add(num1);
		attributes.add(num2);
		attributes.add(cls);
		Instances dataset = new Instances("Training-dataset", attributes, 0);
		dataset.setClassIndex(dataset.numAttributes() - 1);
		Instances testDataSet = new Instances(dataset);
		
		double values[] = new double[dataset.numAttributes()];
		for (int i = 0; i < 10; i++) {
			values[0] = 1.23;
			values[1] = 2.4;
			values[2] = dataset.attribute(2).indexOfValue("yes");
			Instance inst = new DenseInstance(1.0, values);
			dataset.add(inst);
		}
		values[0] = 0.7;
		values[1] = 1.1;
		values[2] = dataset.attribute(2).indexOfValue("no");
		Instance inst = new DenseInstance(1.0, values);
		dataset.add(inst);
//		System.out.println(dataset);

		values[0] = 0.7;
		values[1] = 1.1;
//		values[2] = dataset.attribute(2).indexOfValue("yes");
		inst = new DenseInstance(1.0, values);
		testDataSet.add(inst);
		
		NaiveBayes nb = new NaiveBayes(); // new instance of tree
		try {
			nb.buildClassifier(dataset);
			double dist[] = nb.distributionForInstance(testDataSet.get(0));
			for (double d : dist) {
				System.out.println(d);
			}
			System.out.println(nb.classifyInstance(testDataSet.get(0)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // build classifier

	}
	public static void buildDataFromArffFile(){
		try {
			//String classLabels [] = {"yes","no"};
			String classLabels [] = {"M","S","G"};
			Instances trainData = DataSource.read("data/naive_bayes_data.arff");
			trainData.setClassIndex(trainData.numAttributes() - 1);
			Instances testData = new Instances(trainData);
			System.out.println(trainData);
			//System.out.println(testData);
			NaiveBayes nb = new NaiveBayes(); // new instance of tree
			nb.buildClassifier(trainData);
			
//			double values[] = new double[trainData.numAttributes()];
//			values[0] = trainData.attribute(0).indexOfValue("n");
//			values[1] = trainData.attribute(0).indexOfValue("t");
//			Instance inst = new DenseInstance(1.0, values);
//			
//			testData.add(inst);
			for (int i =0 ; i<testData.size(); i++) {
				System.out.println(classLabels[(int)testData.get(i).classValue()]+"!");
				double dist[] = nb.distributionForInstance(testData.get(i));
				System.out.println(Arrays.toString(dist));
				System.out.println(classLabels[(int)nb.classifyInstance(testData.get(i))]);
				System.out.println();
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		//buildDataOnTheFly();
		buildDataFromArffFile();
	}
}
