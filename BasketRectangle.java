import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JPanel;


public class BasketRectangle extends JPanel {
	/**
	 *  This class is Basket rectangle.Firstly,initializes the specific feature of rectangle.
	 *  
	 */
	
	//Basket Rectangle class has one variable.The height and width of rectangle is determined
	private Color color = (new Color(100, 20, 100));
	final static int height = 20;
	final static int width = 90;

	//occurs Basket rectangle constructor
	public BasketRectangle() {
		
		this.setSize(width,height);
		this.setBackground(color);
	}
	//the other constructor
	public BasketRectangle(Color color, int positionX, int positionY) {
		this.color = color;
		this.setBounds(positionX,positionY,width,height);
	}
 //Generates getter and setter methods
	public int getPositionX() {
		return this.getBounds().x;
	}

	public void setPositionX(int positionX) {
		Rectangle bound=this.getBounds();
		bound.x=positionX;
		this.setBounds(bound);
	}

	public int getPositionY() {
		return this.getBounds().y;
	}

	public void setPositionY(int positionY) {
		Rectangle bound=this.getBounds();
		bound.y=positionY;
		this.setBounds(bound);
	}

	public Color getColor() {
		return color;
	}
	public void setColor(Color c){
		color = c;
		this.setBackground(color);
	}
	
	//This method helps to specify the color of basket rectangle and draw basket rectangle 
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(this.getColor());
		g2.fillRect(this.getPositionX(),this.getPositionY(), width,height);
	}	
}
