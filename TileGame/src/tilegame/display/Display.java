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
		//makes sure it stays at width/height
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		//might not need on newer computers
		canvas.setFocusable(false);
		
		frame.add(canvas);
		//resizes window little bit to see canvas fully; w/o it you might not see entire canvas
		frame.pack();
	}
	//can do this or make canvas public, i.e. public Canvas canvas
	public Canvas getCanvas() {
		return canvas;
	}
	//returns jframe in display class
	public JFrame getFrame() {
		return frame;
	}
	
	
	
	
	
	
	
	
	
	
}
