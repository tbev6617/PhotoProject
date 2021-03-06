package pixLab.classes;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
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
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  public void duckFilter()
  {
	  Pixel fromPixel;
	  Pixel toPixel;
	  Picture ducky = new Picture(/*TODO ADD NAME of Picture*/);
  }
  public void glitch()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  int shiftAmount = (int)(.33 * pixels[0].length);
	  int width = pixels[0].length;
	  
	  //
	  for(int row = 0; row < pixels.length; row++)
	  {
		  Color [] currentColors = new Color[width];
		  for(int col = 0; col < width; col++)
		  {
			  currentColors[col] = pixels[row][col].getColor();
		  }
		  for(int col = 0; col < width; col++)
		  {
			  pixels[row][col].setColor(currentColors[(col + shiftAmount) % width]);
		  }
	  }
	  
	  
  }
  public void classFilter()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  int width = pixels[0].length;
	  int height = pixels.length;
	  int border = (int) (width * .05);
	  for (int row = border; row < height - border; row++)
	  {
		  for(int col = border; col < (border + 25); col++)
		  {
			  
			  pixels[row][col].setColor(pixels[row][border + 25 - col].getColor());
		  }
	  }
  }
  public void shiftGlitch()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  int width = pixels[0].length;
	  int height = pixels.length;
	  int border = (int)((Math.random() * .2 + .2) * width);
	  for(int i = 0; i < border; i++)
	  {
		  pixels = shiftByOne(pixels, height, width);
	  } //Rectangles
	  for(int i = 0; i < 100; i++)
	  {
		  for(int k = 0; k < 100; k++)
		  {
			  pixels[i + 10][k + 10].setColor(randColor());
			  pixels[i + 100][k + 100].setColor(randColor());
		  }
	  } //RED and BLUE copy
	  for(int h = 0; h < 100; h++)
	  {
		  for(int k = 0; k < 100; k++)
		  {
			  int red = pixels[h + 200][k + 300].getRed();
			  int blue = pixels[h + 200][k + 300].getBlue();
			  int green = pixels[h + 100][k + 500].getGreen();
			  pixels[h + 100][k + 500].setColor(new Color(red, green, blue));
		  }
	  }
  }
  public Pixel[][] shiftByOne(Pixel[][] pixels, int height, int width)
  {
	  Pixel[] lastCol = new Pixel[height];
	  for(int row = 0; row < height; row++)
	  {
		  lastCol[row] = pixels[row][width - 1];
	  }
	  
	  for(int row = 0; row < height; row++)
	  {
		  for(int col = width - 1; col > 0; col--)
		  {
			  pixels[row][col].setColor(pixels[row][col - 1].getColor());
		  }
	  }
	  for(int row = 0; row < height; row++)
	  {
		  pixels[row][1].setColor(lastCol[row].getColor());
	  }
	  return pixels;
  }
  public void addMessage(Color color, String message, int xPos, int yPos)
  {
	// get a graphics context to use to draw on the buffered image
	   Graphics2D graphics2d = this.createGraphics();
	   
	   // set the color to what is supplied
	   graphics2d.setPaint(color);
	   
	   // set the font to Helvetica bold style and size 16
	   graphics2d.setFont(new Font("Helvetica",Font.BOLD,16));
	   
	   // draw the message
	   graphics2d.drawString(message,xPos,yPos);
  }
  public Color randColor()
  {
	  int red = (int)(Math.random() * 255);
	  int green = (int)(Math.random() * 255);
	  int blue = (int)(Math.random() * 255);
	  return new Color(red, green, blue);
  }
  
  
  
} 
