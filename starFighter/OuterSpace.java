package starFighter;
//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

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
import java.util.ArrayList;

public class OuterSpace extends Canvas implements KeyListener, Runnable
{
	private Ship ship;
	private Alien alienOne;
	private Alien alienTwo;
	private String AlienDirection="RIGHT";

	private Alien[][] aliens;
	private ArrayList<Ammo> shots;
	private Ammo ammoBoi;
	private int pellet=0;
	private int reload=0;


	private boolean[] keys;
	private BufferedImage back;

	public OuterSpace()
	{
		ammoBoi=new Ammo(-100,-100,1);
		
		shots =new ArrayList<Ammo>();
		for (int i=0;i<10;i++)
		{
			shots.add(ammoBoi);
		}
		
		setBackground(Color.black);

		keys = new boolean[5];

		ship=new Ship(100,100,2);
		
		aliens=new Alien[5][2];
		for (int c=0;c<aliens.length;c++)
		{
			for (int r=0;r<aliens[0].length;r++)
			{
				alienOne=new Alien(c*100+100,r*100+100,1);
				aliens[c][r]=alienOne;
			}
		}
		

		this.addKeyListener(this);
		new Thread(this).start();

		setVisible(true);
	}

   public void update(Graphics window)
   {
	   paint(window);
   }

	public void paint( Graphics window )
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
		
		graphToBack.setColor(Color.BLUE);
		graphToBack.drawString("StarFighter ", 25, 50 );
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0,0,800,600);
		for (Ammo s:shots)
		{
			s.draw(graphToBack);
			
		}
		if (reload>0)
		{
			reload--;
		}
		for (Ammo s:shots)
		{
			if (s.getY()<600&&s.getY()>0)
			{
				s.move("UP");
			}
		}
		
		for (int r=0;r<aliens.length;r++)
		{
			for (int c=0;c<aliens[0].length;c++)
			{
				aliens[r][c].draw(graphToBack);
				if (shots.get(pellet).getX()>aliens[r][c].getX()
						&&shots.get(pellet).getX()<aliens[r][c].getX()+80
						&&shots.get(pellet).getY()>aliens[r][c].getY()
						&&shots.get(pellet).getY()<aliens[r][c].getY()+80)
				{
					aliens[r][c].setY(-100);
					shots.get(pellet).setY(-100);
				}
				if (aliens[r][c].getX()<800&&aliens[r][c].getX()>0)
				{
					aliens[r][c].move(AlienDirection);
				}
				if (aliens[r][c].getX()>700)
				{
					AlienDirection="LEFT";
				}
				if (aliens[r][c].getX()<100)
				{
					AlienDirection="RIGHT";
				}
			}
		}
		
		
		ship.draw(graphToBack);

		if(keys[0] == true)
		{
			ship.move("LEFT");
		}
		if(keys[1] == true)
		{
			ship.move("RIGHT");
		}
		if(keys[2] == true)
		{
			ship.move("UP");
		}
		if(keys[3] == true)
		{
			ship.move("DOWN");
		}
		if(keys[4] == true)
		{
			if (reload==0)
			{
				shots.get(pellet).setPos(ship.getX()+35, ship.getY());
				pellet++;
				reload=100;
			}
			if (pellet>shots.size()-1)
			{
				pellet=0;
			}
		}



		//add in collision detection


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
   		   Thread.currentThread().sleep(5);
            repaint();
         }
      }catch(Exception e)
      {
      }
  	}
}
