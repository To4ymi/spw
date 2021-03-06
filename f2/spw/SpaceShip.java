package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;

public class SpaceShip extends Sprite{

	int step = 8;
	
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.YELLOW);
		//g.fillRect(x, y, width, height);
		g.fillRect(x-5, y+10, 10, 35);
		g.fillRect(x-16, y+20, 33,7);
		g.fillRect(x-20, y+40, 40,7);
		g.setColor(Color.RED);
		g.fillOval(x-6,y+16,11,20);
		g.setColor(Color.RED);

		g.fillRect(x-20, y+50, 10, 1);
		g.fillRect(x+10, y+50, 10, 1);
	}


	public void move_x(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 400 - width)
			x = 400 - width;
	}

	public void move_y(int direction){
		y += (step * direction);
		if(y < 0)
			y = 0;
		if(y > 600 - height)
			y = 600 - height;
	}

}