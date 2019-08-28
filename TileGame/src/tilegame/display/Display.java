package tilegame.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {

	private JFrame frame;
	private Canvas canvas;
	
	private String title;
	private int width, height;
	
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		createDisplay();
					
	}
	
	private void createDisplay() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));//makes sure it stays at width/height
		canvas.setMinimumSize(new Dimension(width, height));//makes sure it stays at width/height
		canvas.setFocusable(false);//might not need on newer computers
		
		frame.add(canvas);//adds canvas
		frame.pack();//resizes window little bit to see canvas fully; w/o it you might not see entire canvas
	}
	
	public Canvas getCanvas() {//can do this or make canvas public, i.e. public Canvas canvas
		return canvas;
	}
	
	public JFrame getFrame() {//returns jframe in display class
		return frame;
	}
	
	
	
	
	
	
	
	
	
	
}
