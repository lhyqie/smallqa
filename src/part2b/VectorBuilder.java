package part2b;

public abstract class VectorBuilder {
       int [] qvector = null;
       String [] sems = null;
       abstract public void generateQuestionVector(String question);
       abstract public String generateSQL();
}