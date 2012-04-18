package common;

import edu.stanford.nlp.parser.lexparser.LexicalizedParser;

public class LoadStanfordParserModel {
	static LexicalizedParser lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
	public static LexicalizedParser getModel(){
		return lp;
	}
}
