import java.util.Scanner;

/*
 *	Creates a map of the United States with highly populated cities
 * 	having a larger presence in the map
 * 
 *	@author Aarav Prakash
 *	@since September 4, 2024
 */
 
public class USMap
{
	/**
	  *		Main method used to get out of a static method
	  */
	public static void main (String[]args)
	{
		USMap usm = new USMap();
		usm.runner();
	}
	public void runner()
	{
		FileUtils inFile = new FileUtils();
		Scanner cities = inFile.openToRead("cities.txt");
		Scanner bigCities = inFile.openToRead("bigCities.txt");
		String[] cityInfo = new String[1];
		String[] bigCityInfo = new String[1];
		
		while(cities.hasNextLine())
		{
			String[] placeHolder = cityInfo;
			cityInfo = new String[cityInfo.length + 1];
			
			for(int i = 0; i < cityInfo.length; i++)
			{
				if(i == cityInfo.length - 1)
				{
					cityInfo[i] = cities.nextLine();
				}
				else
				{
					cityInfo[i] = placeHolder[i];
				}
			}
		}
		
		String prevInfoStr = (cityInfo[1]).substring(((cityInfo[1]).indexOf(' ') + 1));
		prevInfoStr = prevInfoStr.substring((prevInfoStr.indexOf(' ') + 1));
		String prevCityName = (prevInfoStr.substring(0,prevInfoStr.indexOf(','))).trim();
		prevInfoStr = prevInfoStr.substring((prevInfoStr.indexOf(',') + 1));
		String prevStateName = prevInfoStr;
		prevStateName = (prevStateName.replace(",","")).trim();
		
		
		for(int i = 2; i < cityInfo.length; i++)
		{
			String infoStr = cityInfo[i].substring((cityInfo[i].indexOf(' ') + 1));
			infoStr = infoStr.substring((infoStr.indexOf(' ') + 1));
			String cityName = (infoStr.substring(0,infoStr.indexOf(','))).trim();
			infoStr = infoStr.substring((infoStr.indexOf(',') + 1));
			String stateName = infoStr;
			stateName = (stateName.replace(",","")).trim();
			
			if(cityName.equals(prevCityName) && stateName.equals(prevStateName))
				cityInfo[i] = "";
				
			prevCityName = cityName;
			prevStateName = stateName;
		}

		while(bigCities.hasNextLine())
		{
			String[] placeHolder = bigCityInfo;
			bigCityInfo = new String[bigCityInfo.length + 1];
			for(int i = 0; i < bigCityInfo.length; i++)
			{
				if(i == bigCityInfo.length - 1)
				{
					bigCityInfo[i] = bigCities.nextLine();
				}
				else
				{
					bigCityInfo[i] = placeHolder[i];
				}
			}
		}

		setUpCanvas();

		int[]allPopulations = new int[bigCityInfo.length - 1];
		for(int i = 0; i < bigCityInfo.length-1; i++)
		{
			allPopulations[i] = Integer.parseInt(((bigCityInfo[i+1]).substring(bigCityInfo[i+1].lastIndexOf(" "))).trim());
		}
		String topTen = settingTopTen(allPopulations, "");

		for(int i = 1; i < cityInfo.length; i++)
		{
			if(! cityInfo[i].equals(""))
				drawCities(cityInfo[i], bigCityInfo, topTen);
		}
	}
	public void drawCities(String infoStr, String[]bigCities, String topTenStr)
	{
		double firstCoords = Double.parseDouble(infoStr.substring(0,infoStr.indexOf(' ')));
		infoStr = infoStr.substring((infoStr.indexOf(' ') + 1));
		
		double secondCoords = Double.parseDouble(infoStr.substring(0,infoStr.indexOf(' ')));
		infoStr = infoStr.substring((infoStr.indexOf(' ') + 1));
		
		String cityName = (infoStr.substring(0,infoStr.indexOf(','))).trim();
		infoStr = infoStr.substring((infoStr.indexOf(',') + 1));
		
		String stateName = infoStr;
		stateName = (stateName.replace(",","")).trim();

		int population = 0;

		for(int i = 1; i < bigCities.length; i++)
		{
			population = bigCityChecker(cityName, stateName, bigCities[i], population);
		}
		
		if(population == 0)
		{
			StdDraw.setPenRadius(0.006);
			StdDraw.setPenColor(StdDraw.GRAY);
		}
		else
		{
			StdDraw.setPenRadius(0.6 * (Math.sqrt(population)/18500));
			if(topTenChecker(population, topTenStr))
			{
				System.out.println("hello");
				StdDraw.setPenColor(StdDraw.RED);
			}
			else
				StdDraw.setPenColor(StdDraw.BLUE);
		}
		StdDraw.point(secondCoords, firstCoords);
	}

	public boolean topTenChecker(int popInput, String topTenStr)
	{
		int[] topTenArray = new int[10];

		for(int i = 0; i < 10; i++)
		{
			topTenArray[i] = Integer.parseInt((topTenStr.substring(topTenStr.lastIndexOf(" "))).trim());
			topTenStr = topTenStr.substring(0,topTenStr.lastIndexOf(" "));
		}

		boolean isTopTen = false;
		for(int i = 0; i < 10; i++)
		{
			if(topTenArray[i] == popInput)
				isTopTen = true;
		}
		return isTopTen;
	}

	public String settingTopTen(int[] popSize, String topTenStr)
	{
		int maxNum = 0;
		for(int i = 0; i < popSize.length; i++)
		{
			maxNum = Math.max(maxNum,popSize[i]);
		}
		topTenStr += " " + maxNum;
		int spacesCount = 0;
		for(int i = 0; i < topTenStr.length(); i++)
		{
			if(topTenStr.charAt(i) == ' ')
				spacesCount++;
		}
		if(spacesCount == 10)
			return topTenStr;
		else
		{
			for(int i = 0; i < popSize.length; i++)
			{
				if(popSize[i] == maxNum)
					popSize[i] = 0;
			}
			return settingTopTen(popSize,topTenStr);
		}


	}

	public int bigCityChecker(String city, String state, String bigInfo, int popSize)
	{
		if(popSize == 0)
		{
			bigInfo = (bigInfo.substring(bigInfo.indexOf(" "))).trim();

			String bigCityName = (bigInfo.substring(0, bigInfo.indexOf(","))).trim();
			bigInfo = ((bigInfo.substring(bigInfo.indexOf(","))).replace(",","")).trim();

			String bigStateName = bigInfo.substring(0, bigInfo.indexOf(" "));
			
			int populationSize = Integer.parseInt(((bigInfo.replace(bigStateName, "")).trim()));

			if(city.equals(bigCityName) && state.equals(bigStateName))
			{
				return populationSize;
			}
			else
				return 0;
		}
		else
		{
			return popSize;
		}
	}

	public void setUpCanvas()
	{
		StdDraw.setTitle("USMap");
		StdDraw.setCanvasSize(900, 512);
		StdDraw.setXscale(128.0, 65.0);
		StdDraw.setYscale(22.0, 52.0);
	}
}
