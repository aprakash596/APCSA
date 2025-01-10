import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;		// for testing purposes

/**
 *	SortMethods - Sorts an ArrayList of Strings in ascending order.
 *
 *	Requires FileUtils class to compile.
 *	Requires file randomWords.txt to execute a test run.
 *
 *	@author	Aarav Prakash
 *	@since	January 10, 2025
 */
public class SortMethods 
{
	/**
	 *	Swaps two String objects in array arr
	 *	@param arr		array of String objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(List<String> arr, int x, int y) 
	{
		String temp = arr.get(x);
		arr.set(x, arr.get(y));
		arr.set(y, temp);
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order
	 *	@param arr		List of String objects to sort
	 */
	public void mergeSort(List<String> arr) {
		List<String>temp = new ArrayList<>();
		mergeSortRecurse(arr, 0, arr.size() - 1, temp);
	}
	
	/**
	 *	Recursive mergeSort method.
	 *	@param arr		List of String objects to sort
	 *	@param from		first index of arr to sort
	 *	@param to		last index of arr to sort
	 * 	@param temp		a temporary list of Strings to store arr
	 */
	public void mergeSortRecurse(List<String> arr, int from, int to, List<String> temp) 
	{
		if(to - from < 1)
		{
			if(arr.get(to).compareTo(arr.get(from)) < 0)
				swap(arr, from, to);
		}
		else
		{
			int middle = from + (to - from)/2;
			mergeSortRecurse(arr,from,middle,temp);
			mergeSortRecurse(arr,middle+1,to,temp);
			merge(arr,from,middle,to,temp);
		}
	}
	
	/**
	 *	Merge two lists that are consecutive elements in array.
	 *	@param arr		List of String objects to merge
	 *	@param from		first index of first list
	 *	@param mid		the last index of the first list;
	 *					mid + 1 is first index of second list
	 *	@param to		last index of second list
	 * 	@param temp		a temporary list of Strings to hold arr
	 */
	public void merge(List<String> arr, int from, int middle, int to,  List<String> temp) 
	{
		for(int i = from; i <= to; i++)
		{
			if(i != temp.size())
				temp.set(i, arr.get(i));
			else
				temp.add(arr.get(i));
		}

		int i = from, j = middle + 1, k = from;
		
		while(i <= middle && j <= to)
		{
			if(temp.get(i).compareTo(temp.get(j)) < 0)
			{
				arr.set(k, temp.get(i));
				i++;
			}
			else
			{
				arr.set(k, temp.get(j));
				j++;
			}
			k++;
		}
		
		while (i <= middle) 
		{
			arr.set(k, temp.get(i));
			i++;
			k++;
   		}
		
		while(j <= to)
		{
			arr.set(k, temp.get(j));
			j++;
			k++;
		}
	}

	
	/**
	 *	Print an List of Strings to the screen
	 *	@param arr		the List of Strings
	 */
	public void printArray(List<String> arr) {
		if (arr.size() == 0) System.out.print("(");
		else System.out.printf("( %-15s", arr.get(0));
		for (int a = 1; a < arr.size(); a++) {
			if (a % 5 == 0) System.out.printf(",\n  %-15s", arr.get(a));
			else System.out.printf(", %-15s", arr.get(a));
		}
		System.out.println(" )");
	}
	
	/*************************************************************/
	/********************** Test program *************************/
	/*************************************************************/
	private final String FILE_NAME = "randomWords.txt";
	
	public static void main(String[] args) {
		SortMethods se = new SortMethods();
		se.run();
	}
	
	public void run() {
		List<String> arr = new ArrayList<String>();
		// Fill List with random words from file		
		fillArray(arr);
		
		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		mergeSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
	}
	
	// Fill String array with words
	public void fillArray(List<String> arr) {
		Scanner inFile = FileUtils.openToRead(FILE_NAME);
		while (inFile.hasNext())
			arr.add(inFile.next());
		inFile.close();
	}
}
