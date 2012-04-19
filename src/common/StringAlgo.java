package common;

public class StringAlgo
{
	public static boolean contains(String question_low, String name)
	{
		int index = question_low.indexOf(name);
		if(index>=0)
		{
			if(index==0 || !Character.isLetterOrDigit(question_low.charAt(index-1)))
				if(index+name.length()==question_low.length() || !Character.isLetterOrDigit(question_low.charAt(index+name.length())))
					return true;
		}
		return false;
	}
}
