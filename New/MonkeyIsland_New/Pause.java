package MonkeyIsland_New;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.util.Random;

public class Pause {
	
	private Random random = new Random();
	
	private Rectangle rectHome = new Rectangle();
	private Rectangle rectContinue = new Rectangle();
	private Rectangle rectSave = new Rectangle();
	private Rectangle rectExplosion = new Rectangle();
	
	private Rectangle[] rects = {rectHome, rectContinue, rectSave, rectExplosion};
	
	private int width, height, buttonSize;
	private float mouseX, mouseY, frameX, frameY;
	
	private char mode, choice = 'c';
	
	private boolean explosion, explosionDone, exploded;
	
	private ParticleEffects particlesEffects;
	
	public Pause(int width, int height, char mode) {
		this.width = width;
		this.height = height;
		this.mode = mode;
		this.buttonSize = (int) (width / 10f);
		
		rectHome.setBounds((int)(width*0.5f - 2 * buttonSize), (int)(height * 0.5f - buttonSize*0.5f), buttonSize, buttonSize);
		rectContinue.setBounds((int)(width*0.5f + buttonSize), (int)(height * 0.5f - buttonSize*0.5f), buttonSize, buttonSize);
		rectSave.setBounds((int)(width*0.5f - buttonSize*0.5f), (int)(height * 0.5f - buttonSize*0.5f), buttonSize, buttonSize);
		
		particlesEffects = new ParticleEffects(150, width/2, height/2, 8, -8, 8, -8, 60, 1, 0, 100, 2, 1, 255, 230, 255, 0, 0, 0, "rect", false);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		if(buttons()[0] && mode == 'k' || choice == 'h' && mode == 'c') {
			g.fillRect((int)(width*0.5f - 2 * buttonSize) - 10, (int)(height * 0.5f - buttonSize*0.5f) - 10, buttonSize + 20, buttonSize + 20);			
		}
		if(buttons()[1] && mode == 'k' || choice == 'c' && mode == 'c') {
			g.fillRect((int)(width*0.5f + buttonSize) - 10, (int)(height * 0.5f - buttonSize*0.5f) -10, buttonSize + 20, buttonSize + 20);			
		}
		if(buttons()[2] && mode == 'k' || choice == 's' && mode == 'c') {
			g.fillRect((int)(width*0.5f - buttonSize*0.5f) - 10, (int)(height * 0.5f - buttonSize*0.5f) -10, buttonSize + 20, buttonSize + 20);			
		}
		
		
		g.setColor(Color.BLACK);
		
		for (int i = 0; i < rects.length-1; i++) {
			g.fillRect(rects[i].x, rects[i].y, rects[i].width, rects[i].height);
		}
		
		if(!explosion) {
			g.fillRect(rectExplosion.x, rectExplosion.y, rectExplosion.width, rectExplosion.height);
		}
		if(explosion && !explosionDone) {
			particlesEffects.draw(g);
			exploded = true;
		}
	}
	
	public void randomExplosionButton() {
		if(random.nextInt(100) > 60) {
			rectExplosion.setBounds(random.nextInt(width - 30), random.nextInt(height - 30), 30, 30);
		}else {
			rectExplosion.setBounds(0,0,0,0);			
		}
	}
	
	public boolean[] buttons() {
		mouseX = MouseInfo.getPointerInfo().getLocation().x;
		mouseY = MouseInfo.getPointerInfo().getLocation().y;
		boolean[] buttons = {false, false, false, false};
		
		for (int i = 0; i < rects.length; i++) {
			buttons[i] = mouseX > frameX + rects[i].x && mouseX < frameX + rects[i].x + rects[i].width 
					&& mouseY > frameY + rects[i].y && mouseY < frameY + rects[i].y + rects[i].height
					&& mode == 'k';
		}
		return buttons;
	}
	
	public void update(float frameX, float frameY) {
		this.frameX = frameX;
		this.frameY = frameY;
	}
	
	public void setChoice(char choice) {
		this.choice = choice;
	}
	
	public char getChoice() {
		return choice;
	}
	
	public void setExplosion(boolean explosion) {
		this.explosion = explosion;
	}
	public boolean getExplosion() {
		return explosion;
	}
	
	public void setExplosionDone(boolean explosionDone) {
		this.explosionDone = explosionDone;
	}
	public boolean getExploded() {
		return exploded;
	}
}
