import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *	Population - lists and sorts the populations of various cities in the 
 	United States in 2017
 *
 *	@author	Aarav Prakash
 *	@since	December 2, 2024
 */
public class Population 
{
	// List of cities
	private List<City> cities = new ArrayList<>();
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";

	// instances for SortMethods and CityComparatorByName
	SortMethods sort = new SortMethods();
	CityComparatorByName nameCompare = new CityComparatorByName();

	public static void main(String[]args)
	{
		Population po = new Population();
		po.run();
	}

	/**
	 * 	Runs the methods that are used in Population and asks the user for 
	 * 	information
	 */
	public void run()
	{
		printIntroduction();

		Scanner cityData = FileUtils.openToRead(DATA_FILE);

		String[]data = new String[4];
		int dataIndex = 0;
		cityData.useDelimiter("[\t\n]");

		while(cityData.hasNext())
		{
			data[dataIndex] = cityData.next();
			dataIndex++;

			if(dataIndex == 4)
			{
				dataIndex = 0;
				cities.add(new City(data[1],data[0],data[2],Integer.parseInt(data[3])));
			}
		}
		cityData.close();

		System.out.println("" + cities.size() + " cities in database\n");

		int selection = 0;
		while(selection != 9)
		{
			printMenu();
			selection = Prompt.getInt("Enter selection");
			int index = 0;
			if(selection == 1)
			{
				System.out.println("\nFifty least populous states");
				long startMillisec = System.currentTimeMillis(); 
				sort.selectionSort(cities);
				long endMillisec = System.currentTimeMillis();

				System.out.printf("    %-22s %-22s %-12s %12s\n","State","City","Type","Population");
				for(int i = 0; i < 50; i++)
				{
					index++;
					printer(index, cities.get(i));
				}
				System.out.println("\nElapsed Time " + (endMillisec-startMillisec) + " milliseconds");
			}
			if(selection == 2)
			{
				System.out.println("\nFifty most populous states");
				long startMillisec = System.currentTimeMillis(); 
				sort.mergeSort(cities);
				long endMillisec = System.currentTimeMillis();

				System.out.printf("    %-22s %-22s %-12s %12s\n","State","City","Type","Population");
				for(int i = cities.size(); i > cities.size() - 50; i--)
				{
					index++;
					printer(index, cities.get(i-1));
				}

				System.out.println("\nElapsed Time " + (endMillisec-startMillisec) + " milliseconds");
			}
			if(selection == 3)
			{
				System.out.println("\nFifty cities sorted by name");
				long startMillisec = System.currentTimeMillis(); 
				nameCompare.insertionSort(cities);
				long endMillisec = System.currentTimeMillis();

				System.out.printf("    %-22s %-22s %-12s %12s\n","State","City","Type","Population");
				for(int i = 0; i < 50; i++)
				{
					index++;
					printer(index, cities.get(i));
				}

				System.out.println("\nElapsed Time " + (endMillisec-startMillisec) + " milliseconds");
			}
			if(selection == 4)
			{
				System.out.println("\nFifty cities sorted by name descending");
				long startMillisec = System.currentTimeMillis(); 
				nameCompare.mergeSort(cities);
				long endMillisec = System.currentTimeMillis();

				System.out.printf("    %-22s %-22s %-12s %12s\n","State","City","Type","Population");
				for(int i = 0; i < 50; i++)
				{
					index++;
					printer(index, cities.get(i));
				}

				System.out.println("\nElapsed Time " + (endMillisec-startMillisec) + " milliseconds");
			}
			if(selection == 5)
			{
				List<City> cityInState = new ArrayList<>();
				String stateName = "";
				while(cityInState.size() == 0)
				{
					stateName = Prompt.getString("Enter state name (ie. Alabama)");
					for(int i = 0; i < cities.size(); i++)
					{
						if(cities.get(i).getState().equalsIgnoreCase(stateName))
							cityInState.add(cities.get(i));
					}
				}

				System.out.println("\nFifty most populous cities in " + cityInState.get(0).getState());

				sort.mergeSort(cityInState);

				System.out.printf("    %-23s%-23s%-15s%10s\n","State","City","Type","Population");
				for(int i = cityInState.size(); i > cityInState.size() - 50; i--)
				{
					index++;
					printer(index, cityInState.get(i-1));
				}
			}
			if(selection == 6)
			{
				List<City> sameName = new ArrayList<>();
				String name = "";
				while(sameName.size() == 0)
				{
					name = Prompt.getString("Enter city name");
					for(int i = 0; i < cities.size(); i++)
					{
						if(cities.get(i).getName().equalsIgnoreCase(name))
							sameName.add(cities.get(i));
					}
				}
				System.out.println("City " + sameName.get(0).getName() + " by population");

				sort.mergeSort(sameName);
				System.out.printf("    %-23s%-23s%-15s%10s\n","State","City","Type","Population");
				for(int i = sameName.size()-1; i >= 0; i--)
				{
					index++;
					printer(index, sameName.get(i));
				}
			}
			if(selection == 9)
				System.out.print("\nThanks for using Population!\n");
			
			System.out.println("");
		}
	}

	/**
	 * Prints the city's information based on the sorted cities array list
	 * 
	 * @param index		the current city number to be printed
	 * @param sorted	the City class from which the information is printed
	 */
	public void printer(int index, City sorted)
	{
		System.out.printf("%2d: %s", index, sorted.toString());
		System.out.println("");
	}
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
}