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
		drawCities(cityInfo[1]);
		/*for(int i = 1; i < cityInfo.length; i++)
		{
			drawCities(cityInfo[i]);
		}*/
		setUpCanvas();
	}
	public void drawCities(String infoStr)
	{
		String firstCoords = infoStr.substring(0,infoStr.indexOf(' '));
		infoStr = infoStr.substring((infoStr.indexOf(' ') + 1));
		
		String secondCoords = infoStr.substring(0,infoStr.indexOf(' '));
		infoStr = infoStr.substring((infoStr.indexOf(' ') + 1));
		
		String cityName = infoStr.substring(0,infoStr.indexOf(' '));
		infoStr = infoStr.substring((infoStr.indexOf(' ') + 1));
		
		String stateName = 
		infoStr = infoStr.substring((infoStr.indexOf(' ') + 1));;
		
		System.out.println(firstCoords + "|" + secondCoords + "|" + cityName + "|" + infoStr);
		
		StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.point(0.5, 0.5);
	}
	public void setUpCanvas()
	{
		StdDraw.setTitle("USMap");
		StdDraw.setCanvasSize(900, 512);
		StdDraw.setXscale(128.0, 65.0);
		StdDraw.setYscale(22.0, 52.0);
	}
}
