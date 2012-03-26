package part1;
/*
 * class Question represent the question entity
 * it contains the question id e.g. (2e)
 *             the question text body e.g. who directed Hugo?
 *             the category to which the question belongs
 *              A: world geography B: movies C: latest winter Olympics
 * Author: Huayi
 * Date: 2012-3-25 18:54:47
 */
public class Question {
	
	public Question(String text){
		this.text = text;
	}
	public Question(String id, String text){
		this.id = id;
		this.text = text;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public char getCategory() {
		return category;
	}
	public void setCategory(char category) {
		this.category = category;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Question [id=" + id + ", text=" + text + ", category="
				+ category + "]";
	}
	private String id = "no-id";
	
	private String text;
	private char category;  
}
