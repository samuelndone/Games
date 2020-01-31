import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameplaySnake extends JPanel implements KeyListener,ActionListener
{
	private static final long serialVersionUID = 1L;
	private int[]snakexlength = new int [750]; // X length of snake
	private int[]snakeylength = new int [750];// Y length of snake
	private boolean play = false;
	private boolean left = false; // go to directions
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	private int score = 0;
	private ImageIcon head;      // Snake images.
	private ImageIcon rightmouth;
	private ImageIcon leftmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	private ImageIcon titleImage;
	private int lengthofsnake =3; // When game starts,snake's length is 3.
	private Timer timer;
	private int delay = 150;
	private ImageIcon snakeimage;
	private int moves =0;
	private int [] enemyxpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550
			,575,600,625,650,675,700,725,750,775,800,825,850};
	private int [] enemyypos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550
			,575,600,625};
	private ImageIcon enemyimage;
	private Random random = new Random();
	private int xpos = random.nextInt(34);
	private int ypos = random.nextInt(23);
	
	public GameplaySnake() 
	{
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay,this);
		timer.start();
	}
	
	public void paint (Graphics g) {
		
		if(moves ==0) {
			snakexlength[2]=50;
			snakexlength[1]=75;
			snakexlength[0]=100;
			
			snakeylength[2]=100;
			snakeylength[1]=100;
			snakeylength[0]=100;
			
		}
		
		//draw title image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		
		//draw the title image
		titleImage = new ImageIcon ("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		if (play)
		{
		// draw score
		g.setColor(Color.white);
		g.setFont(new Font ("arial",Font.BOLD,15));
		g.drawString("Score : "+score, 780, 30);
			
		//draw Message
		g.setColor(Color.white);
		g.setFont(new Font ("arial",Font.BOLD,15));
		g.drawString("Press up,down,left or right to move the snake ! ", 40, 40);
		
		//draw length
		g.setColor(Color.white);
		g.setFont(new Font ("arial",Font.BOLD,15));
		g.drawString("Length : "+lengthofsnake, 780, 50);
				
		}
		
		if (!play) {
		//draw Message
		g.setColor(Color.white);
		g.setFont(new Font ("arial",Font.BOLD,15));
		g.drawString("Press Enter to start the game ! ", 40, 40);
		}
		
		//draw borders for gameplay
		g.setColor(Color.white);
		g.drawRect(24, 74, 851, 577);
		
		//draw background for gameplay
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		head = new ImageIcon ("rightmouth.png");
		head.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		for (int a=0;a<lengthofsnake;a++) 
		{
			
			if (a==0 && right) {
				rightmouth = new ImageIcon ("rightmouth.png");
				rightmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			
			if (a==0 && left) {
				leftmouth = new ImageIcon ("leftmouth.png");
				leftmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			
			if (a==0 && up) {
				upmouth = new ImageIcon ("upmouth.png");
				upmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			
			if (a==0 && down) {
				downmouth = new ImageIcon ("downmouth.png");
				downmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if (a!=0) {
				snakeimage = new ImageIcon ("snakeimage.png");
				snakeimage.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			
		}
		
		enemyimage = new ImageIcon ("enemy.png");
		enemyimage.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);
		if ((enemyxpos[xpos]==snakexlength[0]) && (enemyypos[ypos]==snakeylength[0])) {
			lengthofsnake++;
			score++;
			xpos = random.nextInt(34);
			ypos=random.nextInt(23);
			}
						
		for(int b=1;b<lengthofsnake;b++) {
			if(snakexlength[b]==snakexlength[0] && snakeylength[b]==snakeylength[0] ) {
				up=false;
				down=false;
				right=false;
				left=false;
				play=false;
				g.setColor(Color.white);
				g.setFont(new Font ("arial",Font.PLAIN,30));
				g.drawString("Game Over", 370, 320);
				g.drawString("Score : "+score, 370, 370);
				repaint();
			}}
		
		
		g.dispose();
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	//Key Pressed actions.
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if (play) 
		{
		 // Modify snake if right is pressed.
		 if (right)
		 {
			for (int r = lengthofsnake-1;r>=0;r--)
			{
				snakeylength[r+1]=snakeylength[r];
			}
			
			for (int r = lengthofsnake;r>=0;r--) 
			{
				if (r==0) 
				{
					snakexlength[r]=snakexlength[r]+25;
				}
				else
				{
					snakexlength[r]=snakexlength[r-1];
				}
				if (snakexlength[r]>850) 
				{
					snakexlength[r]=25;
				}
			}
			repaint();
		}
	
// Modify snake if left is pressed.
if (left) 
{
	for (int r = lengthofsnake-1;r>=0;r--) 
	{
		snakeylength[r+1]=snakeylength[r];
	}
	for (int r = lengthofsnake;r>=0;r--) 
	{
		if (r==0) 
		{
			snakexlength[r]=snakexlength[r]-25;
		}
		else
		{
			snakexlength[r]=snakexlength[r-1];
		}
		if (snakexlength[r]<25) 
		{
			snakexlength[r]=850;
		}
	}
	repaint();			
}

// Modify snake if up is pressed.
if (up) 
{
	for (int r = lengthofsnake-1;r>=0;r--)
	{
		snakexlength[r+1]=snakexlength[r];
	}
	for (int r = lengthofsnake;r>=0;r--) 
	{
		if (r==0) 
		{
			snakeylength[r]=snakeylength[r]-25;
		}
		else
		{
			snakeylength[r]=snakeylength[r-1];
		}
		if (snakeylength[r]<75) 
		{
			snakeylength[r]=625;
		}
		
	}
	repaint();
}

// Modify snake if down is pressed.
if (down) 
{
	for (int r = lengthofsnake-1;r>=0;r--) 
	{
		snakexlength[r+1]=snakexlength[r];
	}
	for (int r = lengthofsnake;r>=0;r--) 
	{
		if (r==0) 
		{
			snakeylength[r]=snakeylength[r]+25;
		}
		else
		{
			snakeylength[r]=snakeylength[r-1];
		}
		if (snakeylength[r]>625) 
		{
			snakeylength[r]=75;
		}}
	repaint();
}}}
	
	//Key Pressed orders.
	@Override
	public void keyPressed(KeyEvent e) {
		if (play) {
			
		//When press Right...
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
			play=true;
			moves++;
			right=true;
			if (!left) 
			{ right=true; }
			else 
			{
				right=false;
				left=true;
			}
			up=false;
			down=false;
		}
		
		//When press Left...
       if (e.getKeyCode() == KeyEvent.VK_LEFT) 
       {
	        play=true;
			moves++;
			left=true;
			if (!right) 
			{ left=true; }
			else 
			{
				left=false;
				right=true;
			}
			up=false;
			down=false;
		}

//When press Up...
if (e.getKeyCode() == KeyEvent.VK_UP) 
{
	play=true;
	moves++;
	up=true;
	if (!down) 
	{ up=true; }
	else
	{
		up=false;
		down=true;
	}
	right=false;
	left=false;
}

//When press Down...
if (e.getKeyCode() == KeyEvent.VK_DOWN) 
{
	play=true;
	moves++;
	down=true;
	if (!up) 
	{ down=true; }
	else 
	{
		down=false;
		up=true;
	}
	right=false;
	left=false;
}}
		
//When press Enter...
if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	if (!play) {
	play=true;
	moves=0;
	score = 0;
	lengthofsnake =3;
	repaint();
}}}}