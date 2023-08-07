package MonkeyIsland_New_OK;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class ParticleEffects {
	private Particle[] particles;

	public ParticleEffects(int amount, float start_x, float start_y, float max_vel_x, float min_vel_x, float max_vel_y,
			float min_vel_y, float size, float max_size_subtraction, float min_size_subtraction, float alpha,
			float max_alpha_subtraction, float min_alpha_subtraction, int max_color_r, int min_color_r, int max_color_g,
			int min_color_g, int max_color_b, int min_color_b, String shape, boolean repeat) {
		particles = new Particle[amount];
		for (int i = 0; i < particles.length; i++) {
			particles[i] = new Particle(start_x, start_y, max_vel_x, min_vel_x, max_vel_y, min_vel_y, size,
					max_size_subtraction, min_size_subtraction, alpha, max_alpha_subtraction, min_alpha_subtraction,
					max_color_r, min_color_r, max_color_g, min_color_g, max_color_b, min_color_b, shape, repeat);
		}
	}
	public void draw(Graphics g) {
		for (int i = 0; i < particles.length; i++) {
			particles[i].draw(g);
		}
	}
	public void setXY(int startX, int startY) {
		for (int i = 0; i < particles.length; i++) {
			particles[i].setXY(startX, startY);
		}
	}

	private class Particle {
		private Random random = new Random();
		private Color color;
		private float start_x, start_y, x, y, vel_x, vel_y, max_vel_x, min_vel_x, max_vel_y,
				min_vel_y, size, size_start, size_subtraction, alpha, alpha_start,
				alpha_subtraction;
		private int color_r, color_g, color_b;
		private String shape;
		boolean repeat;

		public Particle(float start_x, float start_y, float max_vel_x, float min_vel_x, float max_vel_y,
				float min_vel_y, float size, float max_size_subtraction, float min_size_subtraction, float alpha,
				float max_alpha_subtraction, float min_alpha_subtraction, int max_color_r, int min_color_r,
				int max_color_g, int min_color_g, int max_color_b, int min_color_b, String shape,
				boolean repeat) {
			this.start_x = start_x;
			this.start_y = start_y;
			this.x = this.start_x;
			this.y = this.start_y;
			this.max_vel_x = max_vel_x;
			this.min_vel_x = min_vel_x;
			this.max_vel_y = max_vel_y;
			this.min_vel_y = min_vel_y;
			this.vel_x = randomValue(max_vel_x, min_vel_x);
			this.vel_y = randomValue(max_vel_y, min_vel_y);
			this.size = size;
			this.size_start = size;
			this.size_subtraction = randomValue(max_size_subtraction, min_size_subtraction);
			this.alpha = alpha;
			this.alpha_start = alpha;
			this.alpha_subtraction = randomValue(max_alpha_subtraction, min_alpha_subtraction);
			this.shape = shape;
			this.repeat = repeat;

			color_r = (int) randomValue(max_color_r, min_color_r);
			color_g = (int) randomValue(max_color_g, min_color_g);
			color_b = (int) randomValue(max_color_b, min_color_b);
		}

		public void setXY(int startX, int startY) {
			this.start_x = startX;
			this.start_y = startY;
		}

		private float randomValue(float max, float min) {
			float randomValue = this.random.nextFloat() * (max - min) + min;
			return randomValue;
		}

		private void repeat() {
			this.alpha -= this.alpha_subtraction;
			this.size -= this.size_subtraction;
			this.x += this.vel_x;
			this.y += this.vel_y;
			if (this.alpha - this.alpha_subtraction < 0 && !repeat) {
				this.alpha_subtraction = 0;
				this.alpha = 0;
			} else if (this.alpha - this.alpha_subtraction < 0 && repeat) {
				this.alpha = this.alpha_start;
				this.size = this.size_start;
				this.x = this.start_x;
				this.y = this.start_y;
				this.vel_x = randomValue(max_vel_x, min_vel_x);
				this.vel_y = randomValue(max_vel_y, min_vel_y);				
			}
			color = new Color(color_r, color_g, color_b, (int) alpha);
		}

		public void draw(Graphics g) {
			repeat();
			g.setColor(color);
			switch (this.shape) {
			case "rect":
				g.fillRect((int) x, (int) y, (int) this.size, (int) this.size);
				break;
			case "oval":
				g.fillOval((int) x, (int) y, (int) this.size, (int) this.size);
			default:
				break;
			}
		}
	}
}
