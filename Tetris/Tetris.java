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
	private int p1=0;
	private int p2=0;
	
	private Block blockBoi;
	private int blockIndex=0;
	
	private int moved=0;
	private int dmoved=0;
	private int rotated=0;
	
	private Block[][] blockBoiz;
	private Block[][] blockActive;
	private int[][] activated;
	private boolean selected=false;
	private int selectedx=5;
	private int selectedy=0;
	
	private THICCBlock tBlock;


	public Tetris()
	{
		//blockBoi=new THICCBlock(200,0,80,80);
		//blockBoi=new Block(200,0,40,40);
		blockBoi=new THINNBlock(200,0,40,160);
		
		blockActive=new Block[10][20];
		activated=new int[10][20];
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

		
		graphToBack.setColor(Color.BLACK);
		graphToBack.drawString(p1+":"+p2, 400, 100);
		graphToBack.setColor(Color.WHITE);
		graphToBack.fillRect(0, 0, 420, 800);
		
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
		/*
		if (blockActive[selectedx][selectedy].getY()<700&&dmoved==0)
		{
			activated[selectedx][selectedy]=0;
			activated[selectedx][selectedy+1]=1;
			blockActive[selectedx][selectedy].move("DOWN");
			blockActive[selectedx][selectedy].draw(graphToBack);
			selectedy++;
			dmoved=100;
		}
		
		if (dmoved>0)
		{
			dmoved--;
		}
		
		if (selectedy>7)
		{
			selectedx=5;
			selectedy=0;
			dmoved=0;
			selected=false;
			System.out.println("Bottom");
		}
		
		
		if (selected==false)
		{
			selected=true;
			activated[selectedx][selectedy]=1;
		}
		*/
		
		blockBoi.draw(graphToBack);
		if (dmoved==0&&blockBoi.getY()<700)
		{
			blockBoi.move("DOWN");
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
		
		blockBoi.hitBottom(blockActive, activated);
		blockBoi.collided(blockActive, activated);
		

		//see if the paddles need to be moved


		if (moved>0)
		{
			moved--;
		}
		if(keys[0] == true)
		{
			if (moved==0)
			{
				blockBoi.move("LEFT");
				moved=100;
			}
		}
		if(keys[1] == true)
		{
			if (moved==0)
			{
				blockBoi.move("RIGHT");
				moved=100;
			}
		}
		if(keys[2] == true)
		{
			if (rotated==0)
			{
				blockBoi.rotate();
				rotated=50;
			}
		}
		if(keys[3] == true)
		{
			if (moved==0)
			{
				blockBoi.move("DOWN");
				moved=100;
			}
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