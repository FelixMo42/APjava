package library.graphics;

import java.awt.*;
import java.util.*;
import javax.swing.*;
 
public class Canvas extends JFrame {
	private static final long serialVersionUID = 1L;
 
	public ArrayList<Drawable> drawables;
	public Color BGColor = Color.BLACK;
	
	private DrawCanvas canvas;
 
	public Canvas(String name, int width, int height) {
		SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
				drawables = new ArrayList<Drawable>();
				
				canvas = new DrawCanvas();
				canvas.setPreferredSize(new Dimension(width, height));
		 
				Container cp = getContentPane();
				cp.add(canvas);
		 
				setDefaultCloseOperation(EXIT_ON_CLOSE);
				pack();
				setTitle(name);
				setVisible(true);
			}
		});
	}
 	
	private class DrawCanvas extends JPanel {
		private static final long serialVersionUID = 1L;
	
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			setBackground( BGColor );
 
			for (Drawable d : drawables) {
				d.draw.call(g);
			}
		}
	}
}