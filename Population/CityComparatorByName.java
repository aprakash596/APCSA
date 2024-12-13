import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

/**
 * Compares cities by their names
 * 
 *  @author Aarav Prakash
 *  @since  December 2, 2024
 */

public class CityComparatorByName implements Comparator<City>
{
    public int compare(City city, City other)
    {
        if(city.getName().compareTo(other.getName()) != 0)
            return city.getName().compareTo(other.getName());
        else
            return city.getPopulation() - other.getPopulation();
    }

    /**
	 *	Swaps two Integer objects in array arr
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

    public void insertionSort(List<City> arr) 
	{
		for(int n = 1; n < arr.size(); n++)
		{
			City arrTemp = arr.get(n);
			int i = n;
			while(i > 0 && compare(arrTemp, arr.get(i-1)) < 0)
			{
				arr.set(i, arr.get(i-1));
				i--;
			}
			arr.set(i, arrTemp);
		}
	}

    /**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void mergeSort(List<City> arr) 
	{
		int n = arr.size();
		List<City>temp = new ArrayList<>();
		recursiveSort(arr,0,n-1,temp);
	}
	
	private void recursiveSort(List<City> arr, int from, int to, List<City> temp)
	{
		if(to - from < 1)
		{
			if(compare(arr.get(to), arr.get(from)) > 0)
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
			if(compare(temp.get(i), temp.get(j)) > 0)
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
