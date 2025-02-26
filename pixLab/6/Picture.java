import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List;

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu and Aarav Prakash
 * @since  February 3, 2025
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
	/* not needed but use it to show students the implicit call to super()
	 * child constructors always call a parent constructor 
	 */
	super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
	// let the parent class handle this fileName
	super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
	// let the parent class handle this width and height
	super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
	// let the parent class do the copy
	super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
	super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
	String output = "Picture, filename " + getFileName() + 
	  " height " + getHeight() 
	  + " width " + getWidth();
	return output;
	
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
	Pixel[][] pixels = this.getPixels2D();
	for (Pixel[] rowArray : pixels)
	{
	  for (Pixel pixelObj : rowArray)
	  {
		pixelObj.setBlue(0);
	  }
	}
  }
  
  /** Method to keep only the blue */
  public void keepOnlyBlue()
  {
	Pixel[][] pixels = this.getPixels2D();
	for (Pixel[] rowArray : pixels)
	{
	  for (Pixel pixelObj : rowArray)
	  {
		pixelObj.setRed(0);
		pixelObj.setGreen(0);
	  }
	}
  }
  
  /** creates the inverse of the image */
  public void negate()
  {
	Pixel[][] pixels = this.getPixels2D();
	for (Pixel[] rowArray : pixels)
	{
	  for (Pixel pixelObj : rowArray)
	  {
		pixelObj.setRed(255-pixelObj.getRed());
		pixelObj.setBlue(255-pixelObj.getBlue());
		pixelObj.setGreen(255-pixelObj.getGreen());
	  }
	}
  }
  
  /** grayscales the image */
  public void grayscale()
  {
	Pixel[][] pixels = this.getPixels2D();
	for (Pixel[] rowArray : pixels)
	{
	  for (Pixel pixelObj : rowArray)
	  {
		int colorAverage = (pixelObj.getRed()+pixelObj.getBlue()+pixelObj.getGreen())/3;
		pixelObj.setRed(colorAverage);
		pixelObj.setBlue(colorAverage);
		pixelObj.setGreen(colorAverage);
	  }
	}
  }
  
  /**
   * Method that makes pictures more pixelated
   * @param size  the size of the pixelation
   */
  public void pixelate(int size)
  {
	  // should run very fast, but blur should pause for a bit
	  Pixel[][]pixel = this.getPixels2D();
	  int redAverage = 0;
	  int blueAverage = 0; 
	  int greenAverage = 0;
	  int amtOfPixels = 0;
	  for(int i = 0; i < pixel.length; i+=size)
	  {
		  for(int j = 0; j < pixel[0].length; j+=size)
		  {	
			  for(int row = i; row < i+size; row++)
			  {
				  for(int col = j; col < j+size; col++)
				  {
					  if(row < pixel.length && col < pixel[0].length)
					  {
						  redAverage += pixel[row][col].getRed();
						  blueAverage += pixel[row][col].getBlue();
						  greenAverage += pixel[row][col].getGreen();
						  amtOfPixels++;
					  }
				  }
			  }
			  redAverage /= amtOfPixels;
			  blueAverage /= amtOfPixels;
			  greenAverage /= amtOfPixels;
			  for(int row = i; row < i+size; row++)
			  {
				  for(int col = j; col < j+size; col++)
				  {
					  if(row < pixel.length && col < pixel[0].length)
					  {
						  pixel[row][col].setRed(redAverage);
						  pixel[row][col].setGreen(greenAverage);
						  pixel[row][col].setBlue(blueAverage);
					  }
				  }
			  }
			  
			  redAverage = blueAverage = greenAverage = amtOfPixels = 0;
			  
		  }
	  }
  }
  
	/** Method that blurs the picture
	 * 	@param size Blur size, greater is more blur
	 * 	@return Blurred picture
	 */
	public Picture blur(int size)
	{
		//only even blur areas
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();	
		
		for(int i = 0; i < pixels.length; i++)
		{
			for(int j = 0; j < pixels[0].length; j++)
			{
				int redAverage = 0;
				int greenAverage = 0;
				int blueAverage = 0;
				int amtOfPixels = 0;
				
				for(int k = i-(size/2); k <= i + (size/2); k++)
				{
					for(int l = j-(size/2); l <= j + (size/2); l++)
					{
						if(k > -1 && l > -1 && k < pixels.length && l < pixels[0].length)
						{
							redAverage += pixels[k][l].getRed();
							greenAverage += pixels[k][l].getGreen();
							blueAverage += pixels[k][l].getBlue();
							amtOfPixels++;
						}
					}
				}
				resultPixels[i][j].setRed(redAverage/amtOfPixels);
				resultPixels[i][j].setGreen(greenAverage/amtOfPixels);
				resultPixels[i][j].setBlue(blueAverage/amtOfPixels);
			}
		}
		return result;
	}
	
	/** Method that enhances a picture by getting average Color around
	* a pixel then applies the following formula:
	*
	* pixelColor <- 2 * currentValue - averageValue
	*
	* size is the area to sample for blur.
	*
	* @param size Larger means more area to average around pixel
	* and longer compute time.
	* @return enhanced picture
	*/
	public Picture enhance(int size)
	{
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();
		
		for(int i = 0; i < pixels.length; i++)
		{
			for(int j = 0; j < pixels[0].length; j++)
			{
				int redAverage = 0;
				int greenAverage = 0;
				int blueAverage = 0;
				int amtOfPixels = 0;
				
				for(int k = i-(size/2); k <= i + (size/2); k++)
				{
					for(int l = j-(size/2); l <= j + (size/2); l++)
					{
						if(k > -1 && l > -1 && k < pixels.length && l < pixels[0].length)
						{
							redAverage += pixels[k][l].getRed();
							greenAverage += pixels[k][l].getGreen();
							blueAverage += pixels[k][l].getBlue();
							amtOfPixels++;
						}
					}
				}
				redAverage /= amtOfPixels;
				greenAverage /= amtOfPixels;
				blueAverage /= amtOfPixels;
				//int average = (redAverage + greenAverage + blueAverage)/3;
				resultPixels[i][j].setRed(2*pixels[i][j].getRed()-redAverage);
				resultPixels[i][j].setGreen(2*pixels[i][j].getGreen()-greenAverage);
				resultPixels[i][j].setBlue(2*pixels[i][j].getBlue()-blueAverage);
			}
		}
		return result;
	}

  /**
   * Method that swaps the left and right side of the image
   * @return  the edited picture with the left and right swapped
   */
  public Picture swapLeftRight()
	{
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();
		
		for(int i = 0; i < pixels.length; i++)
		{
			for(int j = 0; j < pixels[0].length; j++)
			{
				int newColumn = (j + pixels[0].length/2) % pixels[0].length;
				resultPixels[i][newColumn].setRed(pixels[i][j].getRed());
				resultPixels[i][newColumn].setGreen(pixels[i][j].getGreen());
				resultPixels[i][newColumn].setBlue(pixels[i][j].getBlue());
			}
		}
		return result;
	}

	/**
	 * Method that shifts the picture to the right
	 * with a stair step approach
	 * @param shiftCount  the amount a step should be shifted
	 * @param steps the amount of steps the picture should have
	 * @return  the edited picture with the stair steps
	 */
	public Picture stairStep(int shiftCount, int steps)
	{
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();

		int rowsPerStep = pixels.length / steps;  // Number of rows per step
		int remainingRows = pixels.length % steps;  // Remaining rows that don't fit evenly

		int currentStep = 0;  // Tracks the step
		int rowOffset = 0;    // Tracks the starting row of the current step

		for (int step = 0; step < steps; step++) 
		{
			int rowsInCurrentStep;

			if (step < remainingRows)
				rowsInCurrentStep = rowsPerStep + 1;
			else
				rowsInCurrentStep = rowsPerStep;

			for (int i = rowOffset; i < rowOffset + rowsInCurrentStep; i++) 
			{
				int newColumn = (i * shiftCount) % pixels[0].length;

				if (newColumn < 0)
					newColumn += pixels[0].length;

				for (int j = 0; j < pixels[0].length; j++) 
				{
					int shiftedColumn = (j + newColumn) % pixels[0].length;

					resultPixels[i][shiftedColumn].setRed(pixels[i][j].getRed());
					resultPixels[i][shiftedColumn].setGreen(pixels[i][j].getGreen());
					resultPixels[i][shiftedColumn].setBlue(pixels[i][j].getBlue());
				}
			}
			rowOffset += rowsInCurrentStep;
		}
		return result;
	}


	/**
	 * Method that creates a distortion along the center
	 * horizontal of the picture
	 * @param maxHeight the amount distortion
	 * @return  the picture with the liquify effect
	 */
	public Picture liquify(int maxHeight)
	{
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();

		int height = pixels.length;
		int width = pixels[0].length;
		int midY = height / 2;
		double sigma = height / 6.0; // standard deviation
		double A = maxHeight;

		for (int i = 0; i < height; i++) 
		{
			for (int j = 0; j < width; j++) 
				resultPixels[i][j].setColor(pixels[i][j].getColor());
		}

		for (int i = 0; i < pixels.length; i++) 
		{
			int shift = (int) (A * Math.exp(-1 * Math.pow(i - midY, 2) / (2 * sigma * sigma)));
			
			for (int j = width - 1; j >= 0; j--) 
			{
				int newColumn = (j + shift) % width;
				resultPixels[i][newColumn].setColor(pixels[i][j].getColor());
			}
		}
		
		return result;
	}

	/**
	 * Method that creates oscillating distortions in 
	 * a picture
	 * @param amplitude the amount distortion in one oscillation
	 * @return  the picture with the wave effect
	 */
	public Picture wave(int amplitude) 
	{
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();
		
		double frequency = 5.0;
		int height = pixels.length;
		int width = pixels[0].length;

		for (int i = 0; i < height; i++) {
			int shift = (int) (amplitude * Math.sin(2 * Math.PI * frequency * i / height)); 
			
			for (int j = 0; j < width; j++) 
			{
				int newColumn = (j + shift) % width;
				if (newColumn < 0)
					newColumn += width;
				
				resultPixels[i][newColumn].setColor(pixels[i][j].getColor());
			}
		}
		
		return result;
	}


  /** Method that mirrors the picture around a 
	* vertical mirror in the center of the picture
	* from left to right */
  public void mirrorVertical()
  {
	Pixel[][] pixels = this.getPixels2D();
	Pixel leftPixel = null;
	Pixel rightPixel = null;
	int width = pixels[0].length;
	for (int row = 0; row < pixels.length; row++)
	{
	  for (int col = 0; col < width / 2; col++)
	  {
		leftPixel = pixels[row][col];
		rightPixel = pixels[row][width - 1 - col];
		rightPixel.setColor(leftPixel.getColor());
	  }
	} 
  }

  
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
	int mirrorPoint = 276;
	Pixel leftPixel = null;
	Pixel rightPixel = null;
	int count = 0;
	Pixel[][] pixels = this.getPixels2D();
	
	// loop through the rows
	for (int row = 27; row < 97; row++)
	{
	  // loop from 13 to just before the mirror point
	  for (int col = 13; col < mirrorPoint; col++)
	  {
		
		leftPixel = pixels[row][col];      
		rightPixel = pixels[row]                       
						 [mirrorPoint - col + mirrorPoint];
		rightPixel.setColor(leftPixel.getColor());
	  }
	}
  }
  
  /** copy from the passed fromPic to the
	* specified startRow and startCol in the
	* current picture
	* @param fromPic the picture to copy from
	* @param startRow the start row to copy to
	* @param startCol the start col to copy to
	*/
  public void copy(Picture fromPic, 
				 int startRow, int startCol)
  {
	Pixel fromPixel = null;
	Pixel toPixel = null;
	Pixel[][] toPixels = this.getPixels2D();
	Pixel[][] fromPixels = fromPic.getPixels2D();
	for (int fromRow = 0, toRow = startRow; 
		 fromRow < fromPixels.length &&
		 toRow < toPixels.length; 
		 fromRow++, toRow++)
	{
	  for (int fromCol = 0, toCol = startCol; 
		   fromCol < fromPixels[0].length &&
		   toCol < toPixels[0].length;  
		   fromCol++, toCol++)
	  {
		fromPixel = fromPixels[fromRow][fromCol];
		toPixel = toPixels[toRow][toCol];
		toPixel.setColor(fromPixel.getColor());
	  }
	}   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
	Picture flower1 = new Picture("flower1.jpg");
	Picture flower2 = new Picture("flower2.jpg");
	this.copy(flower1,0,0);
	this.copy(flower2,100,0);
	this.copy(flower1,200,0);
	Picture flowerNoBlue = new Picture(flower2);
	flowerNoBlue.zeroBlue();
	this.copy(flowerNoBlue,300,0);
	this.copy(flower1,400,0);
	this.copy(flower2,500,0);
	this.mirrorVertical();
	this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
	* @param edgeDist the distance for finding edges
	*/
  public void edgeDetection(int edgeDist)
  {
	Pixel leftPixel = null;
	Pixel rightPixel = null;
	Pixel[][] pixels = this.getPixels2D();
	Color rightColor = null;
	for (int row = 0; row < pixels.length; row++)
	{
	  for (int col = 0; 
		   col < pixels[0].length-1; col++)
	  {
		leftPixel = pixels[row][col];
		rightPixel = pixels[row][col+1];
		rightColor = rightPixel.getColor();
		if (leftPixel.colorDistance(rightColor) > 
			edgeDist)
		  leftPixel.setColor(Color.BLACK);
		else
		  leftPixel.setColor(Color.WHITE);
	  }
	}
  }
  
	/** 
	 * Method that creates a green screen picture
	 * @return the green screen picture
	 */
	public Picture greenScreen()
	{
		// Get background picture
		Picture bkgnd = new Picture("greenScreenImages/IndoorHouseLibraryBackground.jpg");
		Pixel[][] bkgndPixels = bkgnd.getPixels2D();
		
		// Get cat picture
		Picture cat = new Picture("greenScreenImages/kitten1GreenScreen.jpg");

		// Get mouse picture
		Picture mouse = new Picture("greenScreenImages/mouse1GreenScreen.jpg");

		Picture smallCat = scaleImage(cat, (int) (cat.getWidth() / 1.5), (int) (cat.getHeight() / 1.5));
		Picture smallMouse = scaleImage(mouse, mouse.getWidth() / 3, mouse.getHeight());

		Pixel[][] catPixels = smallCat.getPixels2D();
		Pixel[][] mousePixels = smallMouse.getPixels2D();

		int catStartRow = 350, catStartCol = 520;
		int mouseStartRow = 345, mouseStartCol = 290;

		overlayImage(bkgndPixels, catPixels, catStartRow, catStartCol);
		overlayImage(bkgndPixels, mousePixels, mouseStartRow, mouseStartCol);

		return bkgnd;
	}
	
	/**
	* Helper method that overlays an image onto the background
	* @param bkgndPixels 	the background pixel array
	* @param overlayPixels 	the pixels of the image to overlay
	* @param startRow 		the starting row for the image
	* @param startCol		the starting column for the image
	*/
	private void overlayImage(Pixel[][] bkgndPixels, Pixel[][] overlayPixels, int startRow, int startCol) 
	{
	  for (int row = 0; row < overlayPixels.length; row++) 
	  {
		  for (int col = 0; col < overlayPixels[0].length; col++) 
		  {
			  if ((row + startRow) < bkgndPixels.length && (col + startCol) < bkgndPixels[0].length) 
			  {
				  Pixel overlayPixel = overlayPixels[row][col];
				  Pixel bgPixel = bkgndPixels[row + startRow][col + startCol];

				  int red = overlayPixel.getRed();
				  int green = overlayPixel.getGreen();
				  int blue = overlayPixel.getBlue();

				  if (!(green > 150 && red < 100 && blue < 100)) {
					  bgPixel.setColor(overlayPixel.getColor());
				  }
			  }
		  }
	  }
	}

	/**
	* Scales an image to a new width and height
	* @param original 	the original image
	* @param newWidth 	the desired width
	* @param newHeight 	the desired height
	* @return 			a scaled Picture
	*/
	public static Picture scaleImage(Picture original, int newWidth, int newHeight) 
	{
	  Picture scaled = new Picture(newWidth, newHeight);
	  Pixel[][] origPixels = original.getPixels2D();
	  Pixel[][] scaledPixels = scaled.getPixels2D();

	  int origHeight = origPixels.length;    
	  int origWidth = origPixels[0].length; 

	  int scaledHeight = scaledPixels.length;   
	  int scaledWidth = scaledPixels[0].length;    

	  for (int row = 0; row < scaledHeight; row++) 
	  {
		  for (int col = 0; col < scaledWidth; col++) 
		  {
			  int oldRow = (int) ((double) row / scaledHeight * origHeight);
			  int oldCol = (int) ((double) col / scaledWidth * origWidth);

			  oldRow = Math.min(oldRow, origHeight - 1);
			  oldCol = Math.min(oldCol, origWidth - 1);

			  if (oldRow >= 0 && oldRow < origHeight && oldCol >= 0 && oldCol < origWidth) {
				  scaledPixels[row][col].setColor(origPixels[oldRow][oldCol].getColor());
			  }
		  }
	  }
	  return scaled;
	}


	/**
	* Rotate image in radians, clean up "drop-out" pixels
	* @param 	angle angle of rotation in radians
	* @return 	Picture that is rotated
	*/
	public Picture rotate(double angle) 
	{ 
	  Pixel[][] pixels = this.getPixels2D(); 
	  int width = pixels[0].length;
	  int height = pixels.length;
	  
	  int newWidth = (int) (Math.abs(width * Math.cos(angle)) + Math.abs(height * Math.sin(angle)));
	  int newHeight = (int) (Math.abs(width * Math.sin(angle)) + Math.abs(height * Math.cos(angle)));
	  
	  Picture rotatedImage = new Picture(newHeight, newWidth);
	  Pixel[][] newPixels = rotatedImage.getPixels2D();
	  
	  int centerX = width/2;
	  int centerY = height/2;
	  int newCenterX = newWidth/2;
	  int newCenterY = newHeight/2;
	  
	  for (int y = 0; y < height; y++) 
	  {
		  for (int x = 0; x < width; x++) 
		  {
			  int adjustedX = x - centerX;
			  int adjustedY = y - centerY;
			  
			  int newX = (int) (adjustedX * Math.cos(angle) - adjustedY * Math.sin(angle)) + newCenterX;
			  int newY = (int) (adjustedX * Math.sin(angle) + adjustedY * Math.cos(angle)) + newCenterY;
			  
			  if (newX >= 0 && newX < newWidth && newY >= 0 && newY < newHeight)
				  newPixels[newY][newX].setColor(pixels[y][x].getColor());
		  }
	  }
	  
	  for (int y = 1; y < newHeight - 1; y++) 
	  {
		  for (int x = 1; x < newWidth - 1; x++) 
		  {
			  if (newPixels[y][x].getColor().equals(Color.WHITE)) 
			  {
				  Color averageColor = averageSurroundingPixels(newPixels, x, y);
				  newPixels[y][x].setColor(averageColor);
			  }
		  }
	  }
	  
	  return rotatedImage;
	}

	/**
	* Averages the colors of surrounding pixels to fill dropout pixels
	* @return the average color to fill the drop out pixels with
	*/
	private Color averageSurroundingPixels(Pixel[][] pixels, int x, int y) 
	{
	  int red = 0, green = 0, blue = 0, count = 0;
	  
	  for (int i = -1; i <= 1; i++) 
	  {
		  for (int j = -1; j <= 1; j++) 
		  {
			  if (i != 0 || j != 0)
			  {
				int newX = x + i, newY = y + j;
				if (newX >= 0 && newX < pixels[0].length && newY >= 0 && newY < pixels.length) 
				{
					Color color = pixels[newY][newX].getColor();
					red += color.getRed();
					green += color.getGreen();
					blue += color.getBlue();
					count++;
				}
			  }
		  }
	  }
	  return new Color(red/count, green/count, blue/count);
	}

	/** Method that creates an edge detected black/white picture
	 * @param threshold threshold as determined by Pixel’s colorDistance method
	 * @return edge detected picture
	 */
	public Picture edgeDetectionBelow(int threshold)
	{
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();
		
		for(int i = 0; i < pixels.length - 1; i++)
		{
			for(int j = 0; j < pixels[0].length; j++)
			{
				int redDiff = pixels[i][j].getRed() - pixels[i+1][j].getRed();
				int greenDiff = pixels[i][j].getGreen() - pixels[i+1][j].getGreen();
				int blueDiff = pixels[i][j].getRed() - pixels[i+1][j].getBlue();
				double totalDiff = Math.pow(redDiff,2) + Math.pow(greenDiff,2) + 
					Math.pow(blueDiff,2);
				if((int) Math.sqrt(totalDiff) > threshold)
					resultPixels[i][j].setColor(Color.BLACK);
				else
					resultPixels[i][j].setColor(Color.WHITE);
			}
		}
		
		return result;
	}
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
	Picture beach = new Picture("images/beach.jpg");
	beach.explore();
	beach.zeroBlue();
	beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
