package Tetris;
import java.awt.Color;
import java.awt.Graphics;

public class LBlockRight extends Block implements Locatable
{
	private int xPos=200;
	private int yPos=0;
	private int width=40;
	private int height=120;
	private int speed=1;
	private int orientation=0;

	private Color color;

	public LBlockRight()
	{
	  color=Color.BLACK;

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
	   if (orientation==0)
	   {
		   window.fillRect(this.getX(), this.getY(), getWidth(), getHeight());
		   window.fillRect(this.getX()+40, this.getY()+80, 40, 40);
	   }
	   if (orientation==1)
	   {
		   window.fillRect(this.getX(), this.getY(), getWidth(), getHeight());
		   window.fillRect(this.getX(), this.getY()+40, 40, 40);
	   }
	   if (orientation==2)
	   {
		   window.fillRect(this.getX()+40, this.getY(), getWidth(), getHeight());
		   window.fillRect(this.getX(), this.getY(), 40, 40);
	   }
	   if (orientation==3)
	   {
		   window.fillRect(this.getX(), this.getY(), getWidth(), getHeight());
		   window.fillRect(this.getX()+80, this.getY()-40, 40, 40);
	   }
     
   }

   public void draw(Graphics window, Color col)
   {
     window.setColor(col);
     window.fillRect(this.getX(), this.getY(), getWidth(), getHeight());
     
   }
   public void rotate()
   {
	   int temp=0;
	   if (orientation==0&&getX()<320)
	   {
		   temp=getWidth();
		   setWidth(getHeight());
		   setHeight(temp);
		   orientation=1;
		   System.out.println("1");
	   }
	   else if(orientation==1&&getX()<320)
	   {
		   temp=getWidth();
		   setWidth(getHeight());
		   setHeight(temp);
		   orientation=2;
		   System.out.println("2");
	   }
	   else if(orientation==2&&getX()<320)
	   {
		   temp=getWidth();
		   setWidth(getHeight());
		   setHeight(temp);
		   orientation=3;
		   System.out.println("3");
	   }
	   else if(orientation==3&&getX()<320)
	   {
		   temp=getWidth();
		   setWidth(getHeight());
		   setHeight(temp);
		   orientation=0;
		   System.out.println("0");
	   }
   }
   public boolean hitBottom(Block[][] blockActive, int[][] activated)
   {
	   if (getY()>620&&orientation==0)
	   {
		   for (int r=0;r<blockActive.length;r++)
			{
				for (int c=0;c<blockActive[0].length;c++)
				{
					if (blockActive[r][c].getX()==getX()&&blockActive[r][c].getY()==getY())
					{
						activated[r][c]=1;
						activated[r][c+1]=1;
						activated[r][c+2]=1;
						activated[r+1][c+2]=1;
						setY(0);
					}
				}
			}
		   return true;
	   }
	   if (getY()>660&&orientation==1)
	   {
		   for (int r=0;r<blockActive.length;r++)
			{
				for (int c=0;c<blockActive[0].length;c++)
				{
					if (blockActive[r][c].getX()==getX()&&blockActive[r][c].getY()==getY())
					{
						activated[r][c]=1;
						activated[r][c+1]=1;
						activated[r+1][c]=1;
						activated[r+2][c]=1;
						setY(0);
					}
				}
			}
		   return true;
	   }
	   if (getY()>620&&orientation==2)
	   {
		   for (int r=0;r<blockActive.length;r++)
			{
				for (int c=0;c<blockActive[0].length;c++)
				{
					if (blockActive[r][c].getX()==getX()&&blockActive[r][c].getY()==getY())
					{
						activated[r][c]=1;
						activated[r+1][c]=1;
						activated[r+1][c+1]=1;
						activated[r+1][c+2]=1;
						setY(0);
					}
				}
			}
		   return true;
	   }
	   if (getY()>660&&orientation==3)
	   {
		   for (int r=0;r<blockActive.length;r++)
			{
				for (int c=0;c<blockActive[0].length;c++)
				{
					if (blockActive[r][c].getX()==getX()&&blockActive[r][c].getY()==getY())
					{
						activated[r][c+1]=1;
						activated[r+1][c+1]=1;
						activated[r+2][c+1]=1;
						activated[r+2][c]=1;
						setY(0);
					}
				}
			}
		   return true;
	   }
	   return false;
   }
   public boolean collided(Block[][] blockActive, int[][] activated)
   {
	   if (orientation==0)
	   {
		   for (int r=0;r<blockActive.length;r++)
			{
				for (int c=0;c<blockActive[0].length;c++)
				{
					if (blockActive[r][c].getX()==getX()&&blockActive[r][c].getY()==getY()&&((activated[r][c+3]==1)||(activated[r+1][c+3]==1)))
					{
						activated[r][c]=1;
						activated[r][c+1]=1;
						activated[r][c+2]=1;
						activated[r+1][c+2]=1;
						setY(0);
						return true;
					}
				}
			}
	   }
	   if (orientation==1)
	   {
		   for (int r=0;r<blockActive.length;r++)
			{
				for (int c=0;c<blockActive[0].length;c++)
				{
					if (blockActive[r][c].getX()==getX()&&blockActive[r][c].getY()==getY()&&(activated[r][c+2]==1||activated[r+1][c+1]==1||activated[r+2][c+1]==1))
					{
						activated[r][c]=1;
						activated[r][c+1]=1;
						activated[r+1][c]=1;
						activated[r+2][c]=1;
						setY(0);
						return true;
					}
				}
			}
	   }
	   if (orientation==2)
	   {
		   for (int r=0;r<blockActive.length;r++)
			{
				for (int c=0;c<blockActive[0].length;c++)
				{
					if (blockActive[r][c].getX()==getX()&&blockActive[r][c].getY()==getY()&&(activated[r][c+1]==1||activated[r+1][c+3]==1))
					{
						activated[r][c]=1;
						activated[r+1][c]=1;
						activated[r+1][c+1]=1;
						activated[r+1][c+2]=1;
						setY(0);
						return true;
					}
				}
			}
	   }
	   if (orientation==3)
	   {
		   for (int r=0;r<blockActive.length;r++)
			{
				for (int c=0;c<blockActive[0].length;c++)
				{
					if (blockActive[r][c].getX()==getX()&&blockActive[r][c].getY()==getY()&&(activated[r][c+2]==1||activated[r+1][c+2]==1||activated[r+2][c+2]==1))
					{
						activated[r][c+1]=1;
						activated[r+1][c+1]=1;
						activated[r+2][c+1]=1;
						activated[r+2][c]=1;
						setY(0);
						return true;
					}
				}
			}
	   }
	   return false;
   }
   public boolean inBoundRight()
   {
	   if (orientation==0)
	   {
		   if (getX()<320)
		   {
			   return true;
		   }
	   }
	   else if (orientation==1)
	   {
		   if (getX()<280)
		   {
			   return true;
		   }
	   }
	   else if (orientation==2)
	   {
		   if (getX()<320)
		   {
			   return true;
		   }
	   }
	   else if (orientation==3)
	   {
		   if (getX()<280)
		   {
			   return true;
		   }
	   }
	   
	   return false;
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