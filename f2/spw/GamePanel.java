package f2.spw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel {
	
	private BufferedImage bi,bg;	
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public GamePanel() {
		bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		try{
			bg = ImageIO.read(new File("f2/background.jpg"));
		}
		catch(IOException e){
			//big.setBackground(Color.BLACK);
		}
	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 400, 600);
		big.drawImage(bg, 0, 0, 400, 650 , null);
		big.setColor(Color.WHITE);		
		big.drawString(String.format("%s     %08d", reporter.getName() , reporter.getScore()), 300, 20);
		big.drawString(String.format("Life : %d", reporter.getLife()), 0, 590);
		for(Sprite s : sprites){
			s.draw(big);
		}
		
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}
