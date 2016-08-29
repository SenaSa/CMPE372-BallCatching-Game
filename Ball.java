import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.DirectColorModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/*
 * This class is ball.Firstly,initializes the specific feature of ball.
 * Ball class has two variables.
 */
public class Ball extends JButton  {	
	/* isPressed is added the feature of ball. 
	 * when ball is clicked, the state of the ball falling down to understand.
	 * 
	 * 
	 * */
	private boolean isPressed=false;
	//Generates getter and setter methods
	public boolean getIsPressed(){
		return isPressed;
	}
	
	public void setIsPressed(boolean b){
		isPressed=b;
	}
	
	/*The feature is occurred to specify the direction of ball.
	 * if directionForward is true,ball goes to right.
	 * if directionForward is false, ball goes to left.  
	 */

	private boolean directionForward=true;
	//Generates getter and setter methods
	public int getDirectionForward(){
		if (directionForward)
			return 1;
		else 
			return -1;
	}
	
	public void setDirectionForward(boolean b){
		directionForward=b;
	}	
	//This constructor with one parameter  provides to occur balls 
	public Ball(String ballColor) {
		
		//JButton to make transparent
		this.setOpaque(true);
		this.setBorder(null);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		
		this.setBounds(0, 0, 25, 25);
		this.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
		
		this.setDoubleBuffered(true);
	    //The setFocusPainted method can be used to set whether or not there is a blue focus ring around the component.
		this.setFocusPainted(false);//will remove the focus ring.
		
		try {
			this.setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource(ballColor+".png"))));
		} catch (IOException e) {
		}
		this.setVisible(false);
	}
}
