package Tetris;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;

public class Tetris extends Canvas implements KeyListener, Runnable
{
	private boolean[] keys;
	private BufferedImage back;
	private int score=0;
	private int multiplier=0;
	private boolean mainMenu=false;
	
	private Block blockBoi;
	private int blockIndex=0;
	
	private int moved=0;
	private int dmoved=0;
	private int rotated=0;
	
	private Block[] blockBoiz=new Block[100];
	private Block[][] blockActive;
	private int[][] activated;
	private int[][] update;
	private boolean selected=false;
	private int selectedx=5;
	private int selectedy=0;
	
	private THICCBlock tBlock;


	public Tetris()
	{
		for(int i=0;i<blockBoiz.length;i++)
		{
			int r=(int) (Math.random()*3);
			if (r==0)
			{
				//blockBoiz[i]=new Block(200,0,40,40);
				blockBoiz[i]=new LBlockRight();
			}
			else if(r==1)
			{
				blockBoiz[i]=new THINNBlock(200,0,40,160);
			}
			else if (r==2)
			{
				blockBoiz[i]=new THICCBlock(200,0,80,80);
			}
		}
		
		
		blockActive=new Block[10][20];
		activated=new int[10][20];
		update=new int [10][20];
		for (int r=0;r<blockActive.length;r++)
		{
			for (int c=0;c<blockActive[0].length;c++)
			{
				blockActive[r][c]=new Block(r*40,c*40,40,40);
				activated[r][c]=0;
			}
		}



		keys = new boolean[4];
    	
		
		new Thread(this).start();
		addKeyListener(this);		//starts the key thread to log key strokes
	}
	
   public void update(Graphics window){
	   paint(window);
   }

   public void paint(Graphics window)
   {
	   
		//set up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D)window;

		//take a snap shop of the current screen and same it as an image
		//that is the exact same width and height as the current screen
		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));

		//create a graphics reference to the back ground image
		//we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();

		
		graphToBack.setColor(Color.WHITE);
		graphToBack.fillRect(0, 0, 420, 800);
		graphToBack.setColor(Color.BLACK);
		graphToBack.drawString("SCORE: "+score, 0, 10);
		
		if (mainMenu)
		{
			for (int r=0;r<blockActive.length;r++)
			{
				for (int c=0;c<blockActive[0].length;c++)
				{
					if (activated[r][c]==1)
					{
						blockActive[r][c].draw(graphToBack);
					}
				}
			}
			int test=0;
			for (int c=0;c<blockActive[0].length;c++)
			{
				for (int r=0;r<blockActive.length;r++)
				{
					if (activated[r][c]==1)
					{
						test++;
					}
				}
				if (test==10)
				{
					score+=10;
					for (int r=0;r<blockActive.length;r++)
					{
						activated[r][c]=0;
					}
					update=activated;
					for (int y=c-1;y>0;y--)
					{
						for (int r=0;r<blockActive.length;r++)
						{
							if (update[r][y]==1)
							{
								update[r][y]=0;
								update[r][y+1]=1;
							}
						}
					}
					activated=update;
				}
				
				test=0;
			}
			
			for (int i=0;i<activated.length;i++)
			{
				if (activated[i][0]==1)
				{
					score=0;
					for (int[] a:activated)
					{
						for (int u=0;u<a.length;u++)
						{
							a[u]=0;
						}
					}
				}
			}
			
			blockBoiz[blockIndex].draw(graphToBack);
			if (dmoved==0&&blockBoiz[blockIndex].getY()<700)
			{
				blockBoiz[blockIndex].move("DOWN");
				dmoved=100;
			}
			if (dmoved>0)
			{
				dmoved--;
			}
			if (moved>0)
			{
				moved--;
			}
			if (rotated>0)
			{
				rotated--;
			}
			
			if (blockBoiz[blockIndex].hitBottom(blockActive, activated))
			{
				blockIndex++;
				if (blockIndex==3)
				{
					blockIndex=0;
				}
			}
			if(blockBoiz[blockIndex].collided(blockActive, activated))
			{
				blockIndex++;
				if (blockIndex==100)
				{
					blockIndex=0;
				}
			}
			

			//see if the paddles need to be moved


			if (moved>0)
			{
				moved--;
			}
			if(keys[0] == true)
			{
				if (moved==0&&blockBoiz[blockIndex].getX()>0)
				{
					blockBoiz[blockIndex].move("LEFT");
					moved=100;
				}
			}
			if(keys[1] == true)
			{
				if (moved==0&&blockBoiz[blockIndex].inBoundRight())
				{
					blockBoiz[blockIndex].move("RIGHT");
					moved=100;
				}
			}
			if(keys[2] == true)
			{
				if (rotated==0&&blockBoiz[blockIndex].canRotate())
				{
					blockBoiz[blockIndex].rotate();
					rotated=50;
				}
			}
			if(keys[3] == true)
			{
				if (moved==0)
				{
					blockBoiz[blockIndex].move("DOWN");
					moved=25;
				}
			}
		}
		
		if (!mainMenu)
		{
			if(keys[2] == true)
			{
				mainMenu=true;
			}
			graphToBack.setColor(Color.BLACK);
			graphToBack.drawString("TETRIS", 100, 10);
			graphToBack.drawString("Michael Li, use arrow keys to move blocks,press up to start playing", 10, 50);
		}
		











		
		twoDGraph.drawImage(back, null, 0, 0);
	}
   

   public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = true;
		}
		repaint();
	}

	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = false;
		}
		repaint();
	}

	public void keyTyped(KeyEvent e)
	{

	}
	
   public void run()
   {
   	try
   	{
   		while(true)
   		{
   		   Thread.currentThread().sleep(8);
            repaint();
         }
      }catch(Exception e)
      {
      }
  	}	
}