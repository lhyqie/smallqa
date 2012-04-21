package part2b;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import common.StringAlgo;

public class GeoVectorBuilder extends VectorBuilder
{
	ArrayList<String> city_keywords;
	ArrayList<String> country_keywords;
	ArrayList<String> mountain_keywords;
	ArrayList<String> sea_keywords;
	ArrayList<String> continent_keywords;
	
	
	TreeMap<String, String> country_keywords_map;
	TreeMap<String, String> mountain_keywords_map;
	TreeMap<String,String> sea_keywords_map;
	TreeMap<String, String> continent_keywords_map;
	
	final int LENGTH = 13;
	final int CITY = 0;
	final int COUNTRY = 1;
	final int MOUNTAIN = 2;
	final int SEA = 3;
	final int CONTINENT = 4;
	final int MOUNTAIN_HIGH = 5;
	final int SEA_DEEP = 6;
	final int CONTINENT_AREA = 7;
	final int CONTINENT_POPULATION = 8;
	final int CONTINENT_HIGH = 9;
	final int CONTINENT_LOW = 10;
	final int CAPITAL = 11;
	
	public GeoVectorBuilder()
	{
		city_keywords = new ArrayList<String>();
		country_keywords = new ArrayList<String>();
		mountain_keywords = new ArrayList<String>();
		sea_keywords = new ArrayList<String>();
		continent_keywords = new ArrayList<String>();
		
		country_keywords_map = new TreeMap<String, String>();
		mountain_keywords_map = new TreeMap<String, String>();
		sea_keywords_map = new TreeMap<String, String>();
		continent_keywords_map = new TreeMap<String, String>();
		this.qvector = new int[LENGTH];
		this.sems = new String[LENGTH];
		for(int i=0;i<LENGTH;i++)
			this.sems[i] = null;
		
		load_keywords("data/"+"city_keywords.txt", city_keywords, null);
		load_keywords("data/"+"country_keywords.txt", country_keywords, country_keywords_map);
		load_keywords("data/"+"mountain_keywords.txt", mountain_keywords, mountain_keywords_map);
		load_keywords("data/"+"sea_keywords.txt", sea_keywords, sea_keywords_map);
		load_keywords("data/"+"continent_keywords.txt", continent_keywords, continent_keywords_map);
	}
	private void load_keywords(String file, ArrayList<String> list, TreeMap<String, String> map)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(new File(file)));
			String line = br.readLine();
			while(line!=null)
			{
				if(map!=null)
				{
					String[] words = line.split("\t");
					map.put(words[0], words[1]);
					list.add(words[0]);
				}
				else
				{
					list.add(line);
				}
				line = br.readLine();
			}
			br.close();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void generateQuestionVector(String question)
	{
		clear();
		String question_low = question.toLowerCase();
		//City name
		boolean tag = false;
		for(String name: city_keywords)
		{
			if(StringAlgo.contains(question_low,name))
			{
				tag = true;
				this.qvector[CITY] = 1;
				this.sems[CITY] = name;
				break;
			}
		}
		if(!tag
			&&(question_low.startsWith("which")||question_low.startsWith("what"))
			&&(StringAlgo.contains(question_low, "city")||StringAlgo.contains(question_low, "cities"))
			)
		{
			this.qvector[CITY] = 1;
			this.sems[CITY] = "?";
		}
		
		//Country
		tag = false;
		for(String name: country_keywords)
		{
			if(StringAlgo.contains(question_low,name))
			{
				tag = true;
				this.qvector[COUNTRY] = 1;
				this.sems[COUNTRY] = country_keywords_map.get(name);
				break;
			}
		}
		if(!tag
			&&(question_low.startsWith("which")||question_low.startsWith("what"))
			&&(StringAlgo.contains(question_low, "country")||StringAlgo.contains(question_low, "nation")||StringAlgo.contains(question_low, "countries")||StringAlgo.contains(question_low, "nations"))
			)
		{
			this.qvector[COUNTRY] = 1;
			this.sems[COUNTRY] = "?";
		}
		//mountain
		tag = false;
		for(String name: mountain_keywords)
		{
			if(StringAlgo.contains(question_low,name))
			{
				tag = true;
				this.qvector[MOUNTAIN] = 1;
				this.sems[MOUNTAIN] = mountain_keywords_map.get(name);
				break;
			}
		}
		if(!tag
			&&(question_low.startsWith("which")||question_low.startsWith("what"))
			&&(StringAlgo.contains(question_low, "mountain")||StringAlgo.contains(question_low, "mountains"))
			)
		{
			this.qvector[MOUNTAIN] = 1;
			this.sems[MOUNTAIN] = "?";
		}
		//Sea
		tag = false;
		for(String name: sea_keywords)
		{
			if(StringAlgo.contains(question_low,name))
			{
				tag = true;
				this.qvector[SEA] = 1;
				this.sems[SEA] = sea_keywords_map.get(name);
				break;
			}
		}
		if(!tag
			&&(question_low.startsWith("which")||question_low.startsWith("what"))
			&&(StringAlgo.contains(question_low, "ocean")||StringAlgo.contains(question_low, "oceans")||StringAlgo.contains(question_low, "sea")||StringAlgo.contains(question_low, "seas"))
			)
		{
			this.qvector[SEA] = 1;
			this.sems[SEA] = "?";
		}
		
		//continent
		tag = false;
		for(String name: continent_keywords)
		{
			if(StringAlgo.contains(question_low,name))
			{
				tag = true;
				this.qvector[CONTINENT] = 1;
				this.sems[CONTINENT] = continent_keywords_map.get(name);
				break;
			}
		}
		if(!tag
			&&(question_low.startsWith("which")||question_low.startsWith("what"))
			&&(StringAlgo.contains(question_low, "continent")||StringAlgo.contains(question_low, "continents"))
			)
		{
			this.qvector[CONTINENT] = 1;
			this.sems[CONTINENT] = "?";
		}
	}
	

	@Override
	public String generateSQL()
	{
		// TODO Auto-generated method stub
		return "TEST";
	}
	public int[] get_qvector()
	{
		return this.qvector;
	}
	public String[] get_stringvalue()
	{
		return this.sems;
	}
	private void clear()
	{
		for(int i=0;i<LENGTH;i++)
		{
			this.qvector[i] = 0;
			this.sems[i] = null;
		}
	}
}
