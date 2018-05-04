package Tetris;
import javax.swing.JFrame;
import java.awt.Component;

public class TheGame extends JFrame
{
	private static final int WIDTH = 420;
	private static final int HEIGHT = 800;

	public TheGame()
	{
		super("Tetris");
		setSize(WIDTH,HEIGHT);
		
		Tetris game = new Tetris();
		
		((Component)game).setFocusable(true);			
		getContentPane().add(game);
						
		setVisible(true);
	}
	
	public static void main( String args[] )
	{
		TheGame run = new TheGame();
	}
}