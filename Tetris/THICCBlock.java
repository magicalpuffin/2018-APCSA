package Tetris;
import java.awt.Color;
import java.awt.Graphics;

public class THICCBlock extends MovingThing implements Locatable
{
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private int speed=1;

	private Color color;

	public THICCBlock()
	{
	  xPos=0;
	  yPos=0;
	  width=0;
	  height=0;
	  color=Color.BLACK;

	}
	
	public THICCBlock(int x, int y)
	{
	  xPos=x;
	  yPos=y;
	  width=10;
	  height=10;
	  color=Color.BLACK;
	}
	
	public THICCBlock(int x, int y, int width, int height)
	{
	  xPos=x;
	  yPos=y;
	  this.width=width;
	  this.height=height;
	  color=Color.BLACK;
	}

	//add other Block constructors - x , y , width, height, color
	public THICCBlock(int x, int y, int width, int height, Color color)
	{
	  xPos=x;
	  yPos=y;
	  this.width=width;
	  this.height=height;
	  this.color=color;
	}
	
	
	
   //add the other set methods
   

   public void setColor(Color col)
   {
     color=col;
   }
   public void setPos(int x, int y)
   {
     setX(x);
     setY(y);
   }
   public void setX(int x)
   {
     xPos=x;
   }
   public void setY(int y)
   {
     yPos=y;
   }
   public void setWidth(int w)
   {
     width=w;
   }
   public void setHeight(int h)
   {
     height=h;
   }
   public void setSpeed(int s)
   {
	   speed=s;
   }
   
   public int getX()
   {
     return xPos;
   }
   public int getY()
   {
     return yPos;
   }
   public int getWidth()
   {
     return width;
   }
   public int getHeight()
   {
     return height;
   }
   public Color getColor()
   {
     return color;
   }
   public int getSpeed()
   {
	   return speed;
   }
   public void draw(Graphics window)
   {
     window.setColor(color);
     window.fillRect(this.getX(), this.getY(), getWidth(), getHeight());
   }

   public void draw(Graphics window, Color col)
   {
     window.setColor(col);
     window.fillRect(this.getX(), this.getY(), getWidth(), getHeight());
     
   }
   
	public boolean equals(Object obj)
	{
	  if (((Block)obj).getHeight()==getHeight()
	  &&((Block)obj).getWidth()==getWidth()
	  &&((Block)obj).getColor().equals(getColor()))
	  {
	    return true;
	  }



		return false;
	}   

   //add the other get methods
    

   //add a toString() method  - x , y , width, height, color
   public String toString()
   {
     String output="";
     output+=xPos+","+yPos+" Width:"+width+" Height:"+height+" "+color+"";
     return output;
   }


}