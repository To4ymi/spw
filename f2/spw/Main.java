package f2.spw;

import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.JFrame;



public class Main {
	public static void main(String[] args){
		String name;

		Music bgsound = new Music();
		name = JOptionPane.showInputDialog("Enter your name.");

		JFrame frame = new JFrame("Space War");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 640);
		frame.getContentPane().setLayout(new BorderLayout());
		
		SpaceShip v = new SpaceShip(180, 550, 20, 20);
		GamePanel gp = new GamePanel();
		GameEngine engine = new GameEngine(gp, v, name);
		frame.addKeyListener(engine);
		frame.getContentPane().add(gp, BorderLayout.CENTER);
		frame.setVisible(true);
		
		engine.start();
		bgsound.playSound(3);

	
	}
	

}
