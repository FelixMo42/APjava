package library.graphics;

import java.awt.Color;
import java.awt.Graphics;

public class Drawable {
	interface DrawFunc {
		public void call(Graphics g);
	}
	
	public int x = 0;
	public int y = 0;
	public Color color = Color.PINK;
	
	public int width = 100;
	public int height = 100;
	public int arcWidth = 45;
	public int arcHeight = 45;
	public String str = "texts";
	
	public DrawFunc draw = (Graphics g) -> {
		g.setColor( color );
		g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
	};
	
	public Drawable(Canvas c, int x, int y) {
		this.x = x;
		this.y = y;
		
		c.drawables.add(this);
	}
	
	public Drawable(Canvas c, int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		
		c.drawables.add(this);
	}
	
	public Drawable(Canvas c, int x, int y, Color color, DrawFunc func) {
		this.x = x;
		this.y = y;
		this.color = color;
		draw = func;
		
		c.drawables.add(this);
	}
}