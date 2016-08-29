import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Toolkit;
/*Sena Sarigul
 * Project
 * Ball catching game
 */

//this class is main frame of game

public class MainWindow {
	//defines frame,buttons,labels and image
	private JFrame frmMainWindow;
	private Image icon;
	private JButton btnExit;
	private JButton btnScoreboard;
	private JButton btnPlay;
	private JLabel lblPicture3;
	private JLabel label_1;
	private ImageIcon searchIcon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmMainWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// defines frame and specifies size,exit operation,bound,color,font,title and add image 
		frmMainWindow= new JFrame();
		frmMainWindow.setResizable(false);
	
		frmMainWindow.setIconImage(new ImageIcon(getClass().getResource("Basket Game.png")).getImage());
	
		frmMainWindow.getContentPane().setBackground(new Color(179, 158, 181));

		frmMainWindow.getContentPane().setForeground(Color.BLACK);
		frmMainWindow.setTitle("Basket Game");
		frmMainWindow.setBounds(100, 100, 475, 513);
		frmMainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMainWindow.setLocationRelativeTo(null);
		frmMainWindow.getContentPane().setLayout(null);
		
		//ButtonPlay
		btnPlay = new JButton("Play");
		
		/*************************** btnPLAY Controls Events ************************************/
		//When btnPlay  is clicked,Main frame is closed and opens game area
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			//	frmMainWindow.setVisible(false);
					GameWindow.main(null);
					
			}
		});
		
		
		btnPlay.setBounds(146, 226, 177, 50);
		btnPlay.setFont(new Font("Verdana", Font.BOLD, 12));
		btnPlay.setBackground(new Color(153, 102, 153));
		frmMainWindow.getContentPane().add(btnPlay);
		
		/*************************** btnScoreBoard Controls Events ************************************/
		//When btnScoreboard is clicked,report frame is opened 
		
		btnScoreboard = new JButton("Scoreboard");
		btnScoreboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Report.main(null);
			try {
				Report.LoadMyTable();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			}
		});
		btnScoreboard.setBackground(new Color(153, 102, 153));
		btnScoreboard.setFont(new Font("Verdana", Font.BOLD, 12));
		btnScoreboard.setBounds(146, 300, 177, 50);
		frmMainWindow.getContentPane().add(btnScoreboard);
		
		 btnExit = new JButton("Exit");
		
		 /*************************** btnExit Controls Events ************************************/

			//When btnExit is clicked,Main menu frame is closed
		 
				
		 
		 btnExit.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		System.exit(0);
		 	}
		 });
		 btnExit.setBackground(new Color(153, 102, 153));
		 btnExit.setFont(new Font("Verdana", Font.BOLD, 12));
		btnExit.setBounds(146, 404, 177, 50);
		frmMainWindow.getContentPane().add(btnExit);
		
		//LabelPictures
		lblPicture3 = new JLabel("");
		searchIcon = new ImageIcon( getClass().getResource("BallGray.png") );
		
		lblPicture3.setIcon(searchIcon);
		lblPicture3.setBounds(167, 0, 117, 133);
		frmMainWindow.getContentPane().add(lblPicture3);
		
		JLabel label = new JLabel("");
		searchIcon = new ImageIcon( getClass().getResource("BallPink.png") );
		label.setIcon(searchIcon);
		label.setBounds(252, 108, 71, 70);
		frmMainWindow.getContentPane().add(label);
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(getClass().getResource("BallPink.png")));
		label_1.setBounds(148, 108, 71, 70);
		frmMainWindow.getContentPane().add(label_1);
		
		 

	
	
	
	}

	
}
