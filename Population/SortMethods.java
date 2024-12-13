import java.util.List;
import java.util.ArrayList;

/**
 *	SortMethods for Population.java 
 		- Sorts an array of City based on population
 *
 *	@author Aarav Prakash
 *	@since	December 2, 2024
 */
public class SortMethods {
	
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		array of City objects to sort
	 */
	public void bubbleSort(List<City> arr) 
	{
		for(int outer = arr.size() - 1; outer > 0; outer--)
			for(int inner = 0; inner < outer; inner++)
				if(arr.get(inner).compareTo(arr.get(inner+1)) > 0)
					swap(arr,inner,inner+1);
	}
	
	/**
	 *	Swaps two City objects in array arr
	 *	@param arr		array of City objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(List<City> arr, int x, int y) 
	{
		City temp = arr.get(x);
		arr.set(x, arr.get(y));
		arr.set(y, temp);
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order
	 *	@param arr		array of City objects to sort
	 */
	public void selectionSort(List<City> arr) 
	{
		for(int n = arr.size(); n > 1; n--)
		{
			int iMax = 0;
			for(int i = 1; i < n; i++)
			{
				if(arr.get(i).compareTo(arr.get(iMax)) > 0)
					iMax = i;
			}
			swap(arr, iMax, n-1);
		}
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order
	 *	@param arr		array of City objects to sort
	 */
	public void insertionSort(List<City> arr) 
	{
		for(int n = 1; n < arr.size(); n++)
		{
			City arrTemp = arr.get(n);
			int i = n;

			while(i > 0 && arrTemp.compareTo(arr.get(i-1)) < 0)
			{
				arr.set(i, arr.get(i-1));
				i--;
			}
			arr.set(i, arrTemp);
		}
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order
	 *	@param arr		array of City objects to sort
	 */
	public void mergeSort(List<City> arr) 
	{
		int n = arr.size();
		List<City>temp = new ArrayList<>();
		recursiveSort(arr,0,n-1,temp);
	}
	
	/**
	 * Does the splits and merges for merge sort
	 * 
	 * 	@param arr	an ArrayList of City created from the US City Data
	 * 	@param from	the starting index for each subset in merge sort
	 * 	@param to	the ending index for each subset in merge sort
	 * 	@param temp	a temporary ArrayList of the City class
	 */
	private void recursiveSort(List<City> arr, int from, int to, List<City> temp)
	{
		if(to - from < 1)
		{
			if(arr.get(to).compareTo(arr.get(from)) < 0)
				swap(arr, from, to);
		}
		else
		{

			int middle = from + (to - from)/2;
			recursiveSort(arr,from,middle,temp);
			recursiveSort(arr,middle+1,to,temp);
			merge(arr,from,middle,to,temp);
		}
	}
	
	/**
	 *	Merges the splits in merge sort
	 * 
	 * 	@param arr	an ArrayList of City created from the US City Data
	 * 	@param from	the starting index for each subset in merge sort
	 * 	@param to	the ending index for each subset in merge sort
	 * 	@param temp	a temporary ArrayList of the City class
	 */
	private void merge(List<City> arr, int from, int middle, int to, List<City> temp)
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
}