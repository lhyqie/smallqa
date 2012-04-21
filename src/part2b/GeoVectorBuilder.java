package part2b;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import weka.core.ContingencyTables;

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
	
	final int LENGTH = 18;
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
	
	final int MOUNTAIN_HIGHEST = 12;
	final int DEEPEST = 13;
	final int CONTINENT_LOWEST = 14;
	final int CONTINENT_HIGHEST = 15;
	final int AREA_LARGEST = 16;
	final int POPULATION_LARGEST = 17;
	
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
			&&(StringAlgo.contains(question_low, "which")||StringAlgo.contains(question_low, "what")||StringAlgo.contains(question_low, "what's"))
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
			&&(StringAlgo.contains(question_low, "which")||StringAlgo.contains(question_low, "what")||StringAlgo.contains(question_low, "what's"))
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
			&&(StringAlgo.contains(question_low, "which")||StringAlgo.contains(question_low, "what")||StringAlgo.contains(question_low, "what's"))
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
			&&(StringAlgo.contains(question_low, "which")||StringAlgo.contains(question_low, "what")||StringAlgo.contains(question_low, "what's"))
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
			&&(StringAlgo.contains(question_low, "which")||StringAlgo.contains(question_low, "what")||StringAlgo.contains(question_low, "what's"))
			&&(StringAlgo.contains(question_low, "continent")||StringAlgo.contains(question_low, "continents"))
			)
		{
			this.qvector[CONTINENT] = 1;
			this.sems[CONTINENT] = "?";
		}
		
		//MOUNTAIN_HIGH
		if(this.sems[MOUNTAIN]!=null)
		{
			if(StringAlgo.contains(question_low, "height") || StringAlgo.contains(question_low, "high"))
			{
				String[] tokens = question_low.split(" ");
				for (String token : tokens)
				{
					if(StringAlgo.isNumber(token))
					{
						this.qvector[MOUNTAIN_HIGH] = 1;
						this.sems[MOUNTAIN_HIGH] = token;
						break;
					}
				}
				if(this.sems[MOUNTAIN_HIGH]==null && (StringAlgo.contains(question_low, "what")||StringAlgo.contains(question_low, "what's")|| StringAlgo.contains(question_low, "how")))
				{
					this.qvector[MOUNTAIN_HIGH] = 1;
					this.sems[MOUNTAIN_HIGH] = "?";
				}
			}
		}
		//SEA_DEEP
		if(this.sems[SEA]!=null)
		{
			if(StringAlgo.contains(question_low, "deep") || StringAlgo.contains(question_low, "depth"))
			{
				String[] tokens = question_low.split(" ");
				for (String token : tokens)
				{
					if(StringAlgo.isNumber(token))
					{
						this.sems[SEA_DEEP] = token;
						break;
					}
				}
				if(this.sems[SEA_DEEP]==null && (StringAlgo.contains(question_low, "what") ||StringAlgo.contains(question_low, "what's") || StringAlgo.contains(question_low, "how")))
				{
					this.sems[SEA_DEEP] = "?";
				}
			}
		}
		
		//CONTINENT_AREA
		if(this.sems[CONTINENT]!=null)
		{
			if(StringAlgo.contains(question_low, "area") || StringAlgo.contains(question_low, "large") ||StringAlgo.contains(question_low, "big"))
			{
				String[] tokens = question_low.split(" ");
				for (String token : tokens)
				{
					if(StringAlgo.isNumber(token))
					{
						this.sems[CONTINENT_AREA] = token;
						break;
					}
				}
				if(this.sems[CONTINENT_AREA]==null && (StringAlgo.contains(question_low, "what") ||StringAlgo.contains(question_low, "what's") ||  StringAlgo.contains(question_low, "how")))
				{
					this.sems[CONTINENT_AREA] = "?";
				}
			}
		}
		
		//CONTINENT_POPULATION
		if(this.sems[CONTINENT]!=null)
		{
			if(StringAlgo.contains(question_low, "population") || StringAlgo.contains(question_low, "people"))
			{
				String[] tokens = question_low.split(" ");
				for (String token : tokens)
				{
					if(StringAlgo.isNumber(token))
					{
						this.sems[CONTINENT_POPULATION] = token;
						break;
					}
				}
				if(this.sems[CONTINENT_POPULATION]==null && (StringAlgo.contains(question_low, "what") ||StringAlgo.contains(question_low, "what's") || StringAlgo.contains(question_low, "how many")))
				{
					this.sems[CONTINENT_POPULATION] = "?";
				}
			}
		}
		
		//CONTINENT_HIGH
		if(this.sems[CONTINENT]!=null)
		{
			if(StringAlgo.contains(question_low, "hight") || StringAlgo.contains(question_low, "high"))
			{
				String[] tokens = question_low.split(" ");
				for (String token : tokens)
				{
					if(StringAlgo.isNumber(token))
					{
						this.sems[CONTINENT_HIGH] = token;
						break;
					}
				}
				if(this.sems[CONTINENT_HIGH]==null && (StringAlgo.contains(question_low, "what")||StringAlgo.contains(question_low, "what's")|| StringAlgo.contains(question_low, "how")))
				{
					this.sems[CONTINENT_HIGH] = "?";
				}
			}
		}
		
		//CONTINENT_LOW
		if(this.sems[CONTINENT]!=null)
		{
			if(StringAlgo.contains(question_low, "low") || StringAlgo.contains(question_low, "lowest"))
			{
				String[] tokens = question_low.split(" ");
				for (String token : tokens)
				{
					if(StringAlgo.isNumber(token))
					{
						this.sems[CONTINENT_LOW] = token;
						break;
					}
				}
				if(this.sems[CONTINENT_LOW]==null && (StringAlgo.contains(question_low, "what")||StringAlgo.contains(question_low, "what's")|| StringAlgo.contains(question_low, "how")))
				{
					this.sems[CONTINENT_LOW] = "?";
				}
			}
		}
//		final int CAPITAL = 11;
		if(StringAlgo.contains(question_low, "capital"))
		{
			this.qvector[CAPITAL] = 1;
			this.sems[CAPITAL] = "#";
			if((StringAlgo.contains(question_low, "what's")||StringAlgo.contains(question_low, "what")||StringAlgo.contains(question_low, "which")) 
					&& this.sems[CITY]==null)
			{
				this.qvector[CITY] = 1;
				this.sems[CITY] = "?";
			}
				
		}
//		final int MOUNTAIN_HIGHEST = 12;
		if(this.sems[MOUNTAIN]!=null && StringAlgo.contains(question_low, "highest"))
		{
			this.qvector[MOUNTAIN_HIGHEST] = 1;
			this.sems[MOUNTAIN_HIGHEST] = "#";
		}
//		final int DEEPEST = 13;
		if(this.sems[SEA]!=null && (StringAlgo.contains(question_low, "deepest")))
		{
			this.qvector[DEEPEST] = 1;
			this.sems[DEEPEST] = "#";
		}
//		final int LOWEST = 14;
		if((this.sems[COUNTRY]!=null || this.sems[CITY]!=null || this.sems[CONTINENT]!=null) 
				&& StringAlgo.contains(question_low, "lowest"))
		{
			this.qvector[CONTINENT_LOWEST] = 1;
			this.sems[CONTINENT_LOWEST] = "#";
		}
		
//		final int CONTINENT_HIGHEST = 15;
		if((this.sems[COUNTRY]!=null || this.sems[CITY]!=null || this.sems[CONTINENT]!=null) 
				&& StringAlgo.contains(question_low, "highest"))
		{
			this.qvector[CONTINENT_HIGHEST] = 1;
			this.sems[CONTINENT_HIGHEST] = "#";
		}
//		final int AREA_LARGEST = 16;
		if((this.sems[COUNTRY]!=null || this.sems[CITY]!=null || this.sems[CONTINENT]!=null) 
				&& (StringAlgo.contains(question_low, "largest area") || StringAlgo.contains(question_low, "largest")))
		{
			this.qvector[AREA_LARGEST] = 1;
			this.sems[AREA_LARGEST] = "#";
		}
//		final int POPULATION_LARGEST = 17;
		if((this.sems[COUNTRY]!=null || this.sems[CITY]!=null || this.sems[CONTINENT]!=null) 
				&& (StringAlgo.contains(question_low, "largest population") || StringAlgo.contains(question_low, "most population")))
		{
			this.qvector[POPULATION_LARGEST] = 1;
			this.sems[POPULATION_LARGEST] = "#";
		}
		
		
	}
	private String generateSQL_other()
	{
		this.qvector[MOUNTAIN] = 0;
		this.qvector[SEA]=0;
		this.qvector[MOUNTAIN_HIGH] = 0;
		this.qvector[SEA_DEEP] = 0;
		this.sems[MOUNTAIN] = null;
		this.sems[SEA]=null;
		this.sems[MOUNTAIN_HIGH] = null;
		this.sems[SEA_DEEP] = null;
		
		final int MOUNTAIN_HIGHEST = 12;
		final int DEEPEST = 13;
		ArrayList<Integer> question_mark = new ArrayList<Integer>();
		ArrayList<Integer> conditions = new ArrayList<Integer>();
		String sql = "select ";
		String table = "continents outer left join (select country_id, country_name, city_id, city_name, continentid as continent_id from (select id as country_id, name as country_name, city_id, city_name from countries outer left join (select id as city_id, name as city_name, countryid as country_id from cities outer left join capitals on cities.id = capitals.cityid)  as t on countries.id = t.country_id ) as t2 outer left join countrycontinents on t2.country_id = countrycontinents.countryid) as t3 on continents.id = t3.continent_id ";
		for(int i=0;i<LENGTH;i++)
		{
			if(this.sems[i]!=null)
			{
				if(this.sems[i].indexOf('?')>=0)
					question_mark.add(i);
				else
					conditions.add(i);
			}	
		}
		if(question_mark.size()==0)
		{
			sql += "count(*) ";
		}
		else
		{
			sql += "distinct ";
			for(int i=0;i<question_mark.size();i++)
			{
				switch (question_mark.get(i))
				{
				case CITY:
					sql += "city_name ";
					break;
				case COUNTRY:
					sql += "country_name ";
					break;
				case CONTINENT:
					sql += "continent ";
					break;
				case CONTINENT_AREA:
					sql += "area_km2 ";
					break;
				case CONTINENT_POPULATION:
					sql += "population ";
					break;
				case CONTINENT_HIGH:
					sql +="highest ";
					break;
				case CONTINENT_LOW:
					sql +="lowest ";
					break;
				default:
					break;
				}
				if(i!=question_mark.size()-1)
					sql +=", ";
			}
		}
		
		sql += "from "+ table;
		if(conditions.contains(CAPITAL))
		{
			conditions.remove(conditions.indexOf(CAPITAL));
		}
		if(conditions.size()!=0)
			sql += "where ";
		for(int i=0;i<conditions.size();i++)
		{
			switch (conditions.get(i))
			{
			case CITY:
				sql += "city_name like '"+ this.sems[CITY]+"' ";
				break;
			case COUNTRY:
				sql += "country_name like '"+ this.sems[COUNTRY]+"' ";
				break;
			case CONTINENT:
				sql += "continent like '"+ this.sems[CONTINENT]+"' ";
				break;
			case CONTINENT_LOWEST:
				sql += "lowest = (select max(lowest) from ( "+table+" )) ";
				break;
			case CONTINENT_HIGHEST:
				sql += "highest = (select max(highest) from ( "+table+" )) ";
				break;
			case POPULATION_LARGEST:
				sql += "population = (select max(population) from ( "+table+" )) ";
				break;
			case AREA_LARGEST:
				sql += "area_km2 = (select max(area_km2) from ( "+table+" )) ";
				break;
			case CAPITAL:
				break;
			default:
				break;
			}
			if(i!=conditions.size()-1)
				sql +="and ";
		}
		return sql;
	}
	private String generateSQL_Sea()
	{
		
		ArrayList<Integer> question_mark = new ArrayList<Integer>();
		ArrayList<Integer> conditions = new ArrayList<Integer>();
		String sql = "select ";
		String table = "Seas ";
		for(int i=0;i<LENGTH;i++)
		{
			if(this.sems[i]!=null)
			{
				if(this.sems[i].indexOf('?')>=0)
					question_mark.add(i);
				else
					conditions.add(i);
			}	
		}
		if(question_mark.size()==0)
		{
			sql += "count(*) ";
		}
		else
		{
			sql += "distinct ";
			for(int i=0;i<question_mark.size();i++)
			{
				switch (question_mark.get(i))
				{
				case SEA:
					sql += "seas.ocean ";
					break;
				case SEA_DEEP:
					sql += "seas.deepest ";
					break;
				default:
					break;
				}
				if(i!=question_mark.size()-1)
					sql +=", ";
			}
		}
		sql += "from "+ table;
		if(conditions.size()!=0)
			sql += "where ";
		for(int i=0;i<conditions.size();i++)
		{
			switch (conditions.get(i))
			{
			case SEA:
				sql += "seas.ocean like '"+ this.sems[SEA]+"' ";
				break;
			case SEA_DEEP:
				sql += "seas.deepest like '"+ this.sems[SEA_DEEP]+"' ";
				break;
			case DEEPEST:
				sql += "seas.deepest = (select max(seas.deepest) from seas) ";
				break;
			default:
				break;
			}
			if(i!=conditions.size()-1)
				sql +="and ";
		}
		return sql;
	}
	private String generateSQL_Mountain()
	{
		ArrayList<Integer> question_mark = new ArrayList<Integer>();
		ArrayList<Integer> conditions = new ArrayList<Integer>();
		String sql = "select ";
		String table = "Mountains ";
		for(int i=0;i<LENGTH;i++)
		{
			if(this.sems[i]!=null)
			{
				if(this.sems[i].indexOf('?')>=0)
					question_mark.add(i);
				else //if(this.sems[i].indexOf('#')<0)
					conditions.add(i);
			}	
		}
		if(question_mark.size()==0)
		{
			sql += "count(*) ";
		}
		else
		{
			sql += "distinct ";
			for(int i=0;i<question_mark.size();i++)
			{
				switch (question_mark.get(i))
				{
				case MOUNTAIN:
					sql += "mountains.name ";
					break;
				case MOUNTAIN_HIGH:
					sql += "mountains.height ";
					break;
				default:
					break;
				}
				if(i!=question_mark.size()-1)
					sql +=", ";
			}
		}
		sql += "from "+ table;
		if(conditions.size()!=0)
			sql += "where ";
		for(int i=0;i<conditions.size();i++)
		{
			switch (conditions.get(i))
			{
			case MOUNTAIN:
				sql += "mountains.name like '"+ this.sems[MOUNTAIN]+"' ";
				break;
			case MOUNTAIN_HIGH:
				sql += "cr.name like '"+ this.sems[MOUNTAIN_HIGH]+"' ";
				break;
			case MOUNTAIN_HIGHEST:
				sql += "mountains.height = (select max(mountains.height) from mountains) ";
				break;
			default:
				break;
			}
			if(i!=conditions.size()-1)
				sql +="and ";
		}
		return sql;
	}
	@Override
	public String generateSQL()
	{
		if(this.sems[CONTINENT]!=null)
		{
			return generateSQL_other();
		}
		if(this.sems[MOUNTAIN]!=null)
			return generateSQL_Mountain();
		if(this.sems[SEA] != null)
			return generateSQL_Sea();
		else return generateSQL_other();
//		return "TEST";
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
