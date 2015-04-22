package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private SpaceShip v;	
	
	private Timer timer;
	private long score = 0;
	private double difficulty = 0.1;
	private boolean p;
	private String name;
	private int life = 3;

	public GameEngine(GamePanel gp, SpaceShip v, String name) {
		this.gp = gp;
		this.v = v;		
		this.name = name;
		gp.sprites.add(v);
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
		p = true;
	}

	public void stop(){
		timer.stop();
		p = false;
	}

	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}
	
	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 100;
			}
		}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				if(life != 0)
					die();
				else
					gameover();
			return;
			}
		}
	}

	public void gameover(){
		stop();
		JOptionPane.showMessageDialog(null, "Game Over !\n"+"Name : " + getName()+"\n"+"Score : " + getScore(), "Reporter", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void die(){
		stop();
		life--;
		JOptionPane.showMessageDialog(null, "You are die !\n"+"Life : " + life, "Reporter", JOptionPane.INFORMATION_MESSAGE);
		start();
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move_x(-1);
			break;
		case KeyEvent.VK_RIGHT:
			v.move_x(1);
			break;
		case KeyEvent.VK_DOWN:
			v.move_y(1);
			break;
		case KeyEvent.VK_UP:
			v.move_y(-1);
			break;
		case KeyEvent.VK_D:
			difficulty += 0.1;
			break;
		case KeyEvent.VK_P:
			if(p)
				stop();
			else
				start();
			break;
		}
	}

	public String getName(){
		return name;
	}

	public long getScore(){
		return score;
	}

	public int getLife(){
		return life;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
