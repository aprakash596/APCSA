public class IntArrayWorker
{
  /** two dimensional matrix */
  private int[][] matrix = null;
  
  /** set the matrix to the passed one
    * @param theMatrix the one to use
    */
  public void setMatrix(int[][] theMatrix)
  {
    matrix = theMatrix;
  }
  
  public int getCount(int val)
  {
	  int count = 0;
	  for(int i = 0; i < matrix.length; i++)
	  {
		  for(int j = 0; j < matrix[i].length; j++)
		  {
			  if(matrix[i][j] == val)
				count++;
		  }
	  }
	  return count;
  } 
  
  public int getLargest()
  {
	  int largest = 0; 
	  for(int i = 0; i < matrix.length; i++)
	  {
		  for(int j = 0; j < matrix[i].length; j++)
		  {
			  largest = Math.max(largest, matrix[i][j]);
		  }
	  }
	  return largest;
  } 
  
  public int getColTotal(int val)
  {
	  int total = 0;
	  for(int i = 0; i < matrix[val].length; i++)
	  {
		  total += matrix[val][i];
	  }
	  return total;
  }
  
  public void reverseRows()
  {
	  System.out.println("Array before reverseRows:");
	  printer(matrix);
	  
	  int[][] temp = new int[matrix.length][matrix[0].length];
	  for(int i = 0; i < temp.length; i++)
	  {
		  for(int j = 1; j <= temp[0].length; j++)
		  {
				temp[i][j] = matrix[matrix.length-i][matrix[i].length-j];
		  }
	  }
	  System.out.println("Array after reverseRows:");
	  printer(temp);
  }
  
  public void printer(int[][]nums)
  {
	  for(int i = 0; i < nums.length; i++)
	  {
		  for(int j = 0; j < nums[i].length; j++)
		  {
			  System.out.print(nums[i][j] + " ");
		  }
		  System.out.println("");
	  }
	  System.out.println("");
  }
  
  /**
   * Method to return the total 
   * @return the total of the values in the array
   */
  public int getTotal()
  {
    int total = 0;
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < matrix[0].length; col++)
      {
        total = total + matrix[row][col];
      }
    }
    return total;
  }
  
  /**
   * Method to return the total using a nested for-each loop
   * @return the total of the values in the array
   */
  public int getTotalNested()
  {
    int total = 0;
    for (int[] rowArray : matrix)
    {
      for (int item : rowArray)
      {
        total = total + item;
      }
    }
    return total;
  }
  
  /**
   * Method to fill with an increasing count
   */
  public void fillCount()
  {
    int numCols = matrix[0].length;
    int count = 1;
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < numCols; col++)
      {
        matrix[row][col] = count;
        count++;
      }
    }
  }
  
  /**
   * print the values in the array in rows and columns
   */
  public void print()
  {
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < matrix[0].length; col++)
      {
        System.out.print( matrix[row][col] + " " );
      }
      System.out.println();
    }
    System.out.println();
  }
  
  
  /** 
   * fill the array with a pattern
   */
  public void fillPattern1()
  {
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < matrix[0].length; 
           col++)
      {
        if (row < col)
          matrix[row][col] = 1;
        else if (row == col)
          matrix[row][col] = 2;
        else
          matrix[row][col] = 3;
      }
    }
  }
 
}
