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
		text = this.text;
	}
	public Question(String id, String text){
		id = this.id;
		text = this.text;
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
	private String id = "no-id";
	private String text;
	private char category;  
}
