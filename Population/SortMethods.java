/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author Aarav Prakash
 *	@since	November 26, 2024
 */
public class SortMethods {
	
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		array of Integer objects to sort
	 */
	public void bubbleSort(Integer [] arr) 
	{
		for(int outer = arr.length - 1; outer > 0; outer--)
			for(int inner = 0; inner < outer; inner++)
				if(arr[inner].compareTo(arr[inner+1]) > 0)
					swap(arr,inner,inner+1);
	}
	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(Integer[] arr, int x, int y) 
	{
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void selectionSort(Integer [] arr) 
	{
		for(int n = arr.length; n > 1; n--)
		{
			int iMax = 0;
			for(int i = 1; i < n; i++)
			{
				if(arr[i] > arr[iMax])
					iMax = i;
			}
			swap(arr, iMax, n-1);
		}
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void insertionSort(Integer [] arr) 
	{
		for(int n = 1; n < arr.length; n++)
		{
			int arrTemp = arr[n];
			int i = n;
			while(i > 0 && arrTemp < arr[i-1])
			{
				arr[i] = arr[i-1];
				i--;
			}
			arr[i] = arrTemp;
		}
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void mergeSort(Integer [] arr) 
	{
		int n = arr.length;
		int[]temp = new int[n];
		recursiveSort(arr,0,n-1,temp);
	}
	
	private void recursiveSort(Integer[]arr, int from, int to, int[]temp)
	{
		if(to - from < 2)
		{
			if(arr[to] < arr[from])
				swap(arr, from, to);
		}
		else
		{
			int middle = (from + to)/2;
			recursiveSort(arr,from,middle,temp);
			recursiveSort(arr,middle+1,to,temp);
			merge(arr,from,middle,to,temp);
		}
	}
	
	private void merge(Integer[]arr, int from, int middle, int to, int[]temp)
	{
		int index = middle + 1;
		
		
		for(int i = from; i <= middle; i++)
		{
			for(int j = index; j <= to; j++)
			{
				if(arr[i] < arr[j])
				{
					temp[]
					index++;
				}
			}
		}
		
		
		
		
		/*int i = from, j = middle + 1, k = to;
		
		while(i < middle && j < to)
		{
			if(arr[i] < arr[j])
			{
				temp[k] = arr[i];
				i++;
			}
			else
			{
				temp[k] = arr[j];
				j++;
			}
			k++;
		}
		
		while(j <= to)
		{
			temp[k] = arr[j];
			j++;
			k++;
		}
		
		for(k = from; k <= to; k++)
		{
			arr[k] = temp[k];
		}*/
	}
	
	/*****************************************************************/
	/************************* For Testing ***************************/
	/*****************************************************************/
	
	/**
	 *	Print an array of Integers to the screen
	 *	@param arr		the array of Integers
	 */
	public void printArray(Integer[] arr) {
		if (arr.length == 0) System.out.print("(");
		else System.out.printf("( %4d", arr[0]);
		for (int a = 1; a < arr.length; a++) {
			if (a % 10 == 0) System.out.printf(",\n  %4d", arr[a]);
			else System.out.printf(", %4d", arr[a]);
		}
		System.out.println(" )");
	}

	public static void main(String[] args) {
		SortMethods se = new SortMethods();
		se.run();
	}
	
	public void run() {
		Integer[] arr = new Integer[10];
		// Fill arr with random numbers
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nBubble Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		bubbleSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
	
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nSelection Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		selectionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();


		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nInsertion Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		insertionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
			
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		mergeSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
	}
}
