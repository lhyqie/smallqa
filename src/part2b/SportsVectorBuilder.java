package part2b;

public class SportsVectorBuilder extends VectorBuilder
{
	String[] gender_keywords = {"male", "female", "gender", "woman", "man", "sex"};
	String[] competition_name_keywords = {"biathlon", "skijumping", "ski jumping", "ski-jumping", 
			"speedskating", "speed skating", "speed-skating", "shorttrack", "short track", "short-track", 
			"figureskating", "figure skating", "figure-skating", "giantslalom", "giant slalom", "giant-slalom",
			"crosscountry", "cross country", "cross-country", "slalom", "superg", "super g", "super-g"};
	String[] competition_type_keywords = {"competition type", "type", "individual", "nh", 
			"1000", "one thousand", "one-thousand", "500", "five hundreds", "five-hundred", 
			"1500", "one thousand and five hundreds", "fifteen hundreds", "fifteen-hundred", "lh", "sprint"};
	String[] athletes_name_keywords = {"who","aamodt","ammann","andrea","angerer","arakawa","asada",
			"bauer","baverel-robert","bjorndalen","buttle","bystol","cheek","chenal","cleski",
			"cohen","davis","dorfmeister","dorin","dorofeyev","efremova","fak","greis","hanevold","hautamaki",
			"hedda","hedrick","herbst","ho-suk","hoffmann","hosp","huttary","hyun-soo","jay","jiajun",
			"kang-seok","kim","kofler","kostelic","kramer","kuzmina","lambiel","lee","ljokelsoy","lysacek",
			"maier","malysz","mancuso","mcivor","meissnitzer","miller","mo","morgenstern","myhrer","neuner","ohno",
			"olofsson","ottosson","paerson","parson","plushenko","poutiainen","raich","razzoli","riesch","rochette",
			"schild","schlierenzauer","schonfelder","skobrev","slutskaya","svendsen","takahashi","veerpalu","wennemars",
			"zurbriggen"};
	String[] nationality_keywords = {"austria","canada","canadian","china","chinese",
			"croatia","czech republic","czech","estonia","estonian","finland","france","french",
			"germany","german","italy","italian","japan","japanese","south korea","south korean",
			"korea","korean","netherlands","dutch","norway","poland","polish","russia","russian",
			"slovakia","sweden","swedish","switzerland","swiss","ukraine","usa","american"};
	String[] medal_keywords = {"gold medal", "gold", "silver medal", "silver", "bronze medal", "bronze", "first place", "second place", "third place"};
	String[] win_keywords = {"get","gets","got", "win","wins", "won"};
	final int ALTHLETE_NAME = 0;
	final int COMPETITION_NAME = 1;
	final int NATIONALITY = 2;
	final int GENDER = 3;
	final int COMPETITION_TYPE = 4;
	final int MEDAL = 5;
	final int MEDAL_YEAR = 6;
	final int WIN = 7;
	public void generateQuestionVector(String question)
	{
		
	
	}
	public String generateSQL(){
		return "";
	}
}
