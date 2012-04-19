package part2b;

import common.LoadStanfordParserModel;

import edu.stanford.nlp.parser.lexparser.LexicalizedParser;

public abstract class VectorBuilder {

       int [] qvector = null;
       String [] sems = null;
       abstract public void generateQuestionVector(String question);
       abstract public String generateSQL();
}
