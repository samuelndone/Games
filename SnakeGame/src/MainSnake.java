import java.awt.Color;
import javax.swing.JFrame;
public class MainSnake {

	public static void main(String[] args) {

		//Create game frame and add gameplay inside it.
		JFrame obj = new JFrame();
		GameplaySnake gameplay = new GameplaySnake();
		obj.setBounds(10, 10, 905, 700);
		obj.setBackground(Color.gray);
		obj.setTitle("Snake");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gameplay);
		}}