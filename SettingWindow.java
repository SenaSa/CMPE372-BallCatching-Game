import java.awt.Color;
import java.awt.Frame;
import java.awt.Window;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.SliderUI;

//this class is setting frame of game
public class SettingWindow extends JFrame {
	// defines frame,textFields,Jslider
	private static JSlider sldSpeedBall;
	private JFrame frmSettings;
	private JTextField txtTime;
	private static JSlider sldBallNumber;
	private static JSlider sldTimePeriod;
	private static JSlider sldSpeedBask;
	private JTextField txtSpeedBall;
	private JTextField txtSpeedBask;
	private static JTextField txtName;
	private JTextField txtNumbBall;
	
	/**
	 * Create the application.
	 */
	public SettingWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// defines frame and specifies size,exit
		// operation,bound,color,font,title and add image

		frmSettings = new JFrame("Settings");
		 frmSettings.setResizable(false);

		frmSettings.setIconImage(new ImageIcon(getClass().getResource(
				"Setting.png")).getImage());
		frmSettings.setType(Type.POPUP);

		frmSettings.setForeground(Color.BLACK);
		frmSettings.getContentPane().setBackground(new Color(179, 158, 181));
		frmSettings.setBounds(100, 100, 348, 352);
		frmSettings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSettings.setLocationRelativeTo(null);
		frmSettings.setLayout(null);

		JLabel lblPName = new JLabel("Enter Player a Name:");
		lblPName.setBounds(21, 23, 134, 20);
		frmSettings.add(lblPName);

		txtName = new JTextField();
		txtName.setBounds(156, 23, 165, 20);
		frmSettings.add(txtName);
		txtName.setColumns(10);
		txtName.setText("Please enter your name");

		JLabel lblEnterNumbersOf = new JLabel("Enter Numbers of Ball:");
		lblEnterNumbersOf.setBounds(21, 63, 134, 20);
		frmSettings.add(lblEnterNumbersOf);

		txtNumbBall = new JTextField();

		txtNumbBall.setBounds(288, 63, 33, 20);
		frmSettings.add(txtNumbBall);
		txtNumbBall.setColumns(10);
		txtNumbBall.setText("5");

		JButton btnOK = new JButton("OK");

		/*************************** BtnOK Controls Events ************************************/
		// when Cancel button is clicked in keyboard Enter, Game area is opened.
		btnOK.addActionListener(new ActionListener() {
		
			 
			

			public void actionPerformed(ActionEvent arg0) {
			 boolean mainPage=true;
				if(mainPage){
					setSettings();	
					frmSettings.setVisible(false);
					new MainWindow();
					
				}else{
					setSettings();
					frmSettings.setVisible(false);
					new GameWindow();
				}
				
				

		
				

			}
		});
		// when Ok button is focused and clicked in keyboard Enter, Game area is
		// opened.
		btnOK.addKeyListener(new KeyListener() {

		
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				
					
					
					frmSettings.setVisible(false);
					
				

				}

			}
		});

		btnOK.setForeground(new Color(0, 0, 0));
		btnOK.setBackground(new Color(153, 102, 153));
		btnOK.setBounds(225, 241, 92, 31);
		frmSettings.add(btnOK);

		JButton btnCancel = new JButton("Cancel");
		// Button Action
		/*************************** BtnCANCEL Controls Events ************************************/
		// when Cancel button is clicked ,quit frame .
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int selectedOption = JOptionPane.showConfirmDialog(null,
						"Do you want close the window?", "Choose",
						JOptionPane.YES_NO_OPTION);
				if (selectedOption == JOptionPane.YES_OPTION) {
					frmSettings.setVisible(false);

				}

			}
		});

		// when Cancel button is focused and clicked in keyboard Q,quit frame .
		btnCancel.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_Q) {
					int selectedOption = JOptionPane.showConfirmDialog(null,
							"Do you want close the window?", "Choose",
							JOptionPane.YES_NO_OPTION);
					if (selectedOption == JOptionPane.YES_OPTION) {
						frmSettings.setVisible(false);

					}

				}
			}
		});

		btnCancel.setForeground(new Color(0, 0, 0));
		btnCancel.setBackground(new Color(153, 102, 153));
		btnCancel.setBounds(21, 241, 92, 31);
		frmSettings.add(btnCancel);

		JLabel lblEnterPeriodOf = new JLabel("Enter Period of Time: ");
		lblEnterPeriodOf.setBounds(21, 105, 134, 20);
		frmSettings.add(lblEnterPeriodOf);

		txtTime = new JTextField();

		txtTime.setColumns(10);
		txtTime.setBounds(288, 105, 33, 20);
		txtTime.setText("5");
		frmSettings.add(txtTime);

		sldBallNumber = new JSlider();
		sldBallNumber.setMinimum(1);
		sldBallNumber.setValue(5);
		sldBallNumber.setMaximum(10);
		sldBallNumber.setBounds(156, 63, 122, 20);
		frmSettings.add(sldBallNumber);

		sldTimePeriod = new JSlider();
		sldTimePeriod.setValue(5);
		sldTimePeriod.setMaximum(10);
		sldTimePeriod.setMinimum(1);
		sldTimePeriod.setBounds(156, 105, 122, 20);
		frmSettings.add(sldTimePeriod);

		JLabel lblSpeedOfBalls = new JLabel("Enter Speed of Balls:");
		lblSpeedOfBalls.setBounds(21, 141, 134, 20);
		frmSettings.add(lblSpeedOfBalls);

		JLabel lblSpeedBasket = new JLabel("Enter Speed of Basket:");
		lblSpeedBasket.setBounds(21, 186, 134, 20);
		frmSettings.add(lblSpeedBasket);

		sldSpeedBall = new JSlider();
		sldSpeedBall.setValue(3);
		sldSpeedBall.setMinimum(1);
		sldSpeedBall.setMaximum(5);
		sldSpeedBall.setBounds(156, 141, 122, 20);
		frmSettings.add(sldSpeedBall);

		sldSpeedBask = new JSlider();
		sldSpeedBask.setValue(2);
		sldSpeedBask.setMinimum(1);
		sldSpeedBask.setMaximum(5);
		sldSpeedBask.setBounds(156, 186, 122, 20);
		frmSettings.add(sldSpeedBask);

		txtSpeedBall = new JTextField();
		txtSpeedBall.setColumns(10);
		txtSpeedBall.setText("3");
		txtSpeedBall.setBounds(288, 141, 33, 20);
		frmSettings.add(txtSpeedBall);

		txtSpeedBask = new JTextField();
		txtSpeedBask.setColumns(10);
		txtSpeedBask.setText("2");
		txtSpeedBask.setBounds(288, 186, 33, 20);
		frmSettings.add(txtSpeedBask);

		JButton btnReset = new JButton("Reset");

		/*************************** BtnRESET Controls Events ************************************/
		// when reset button is focused and clicked in keyboard R, cleans
		// everything in setting area .
		btnReset.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent arg0) {

				if (arg0.getKeyCode() == KeyEvent.VK_R) {
					resetItems();

				}

			}

		});

		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetItems();
			}
		});
		btnReset.setBackground(new Color(153, 102, 153));
		btnReset.setForeground(new Color(0, 0, 0));
		btnReset.setBounds(123, 241, 92, 31);
		frmSettings.add(btnReset);

		/*************************** Slider Controls Events ************************************/
		// When Changing sldBallNumber do this ,changing value writes to text
		sldBallNumber.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {

				txtNumbBall.setText(String.valueOf(sldBallNumber.getValue()));
			}
		});
		// When Changing sldSpeedBall do this ,changing value writes to text
		sldSpeedBall.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {

				txtSpeedBall.setText(String.valueOf(sldSpeedBall.getValue()));
			}
		});
		// When Changing sldSpeedBask do this,changing value writes to text
		sldSpeedBask.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {

				txtSpeedBask.setText(String.valueOf(sldSpeedBask.getValue()));
			}
		});
		// When Changing sldSpeedBask do this,changing value writes to text
		sldTimePeriod.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {

				txtTime.setText(String.valueOf(sldTimePeriod.getValue()));
			}
		});

		frmSettings.setVisible(true);

	}
	/*This method provides 
	 * settings that is made in the setting window transfer variables in the game window  
	 */


	public static void setSettings(){
		
		GameWindow.PlayerName=(txtName.getText().toString());
		GameWindow.txtName.setText(txtName.getText().toString());
		GameWindow.Number_Ball=sldBallNumber.getValue();
		GameWindow.time=sldTimePeriod.getValue();
		GameWindow.ballSpeed=sldSpeedBall.getValue();
		GameWindow.basketSpeed=sldSpeedBask.getValue();

		
		
	}
	
	
	// This method cleans everything in setting area
	public void resetItems() {

		txtName.setText(null);
		sldBallNumber.setValue(0);
		sldSpeedBall.setValue(0);
		sldTimePeriod.setValue(0);
		sldSpeedBask.setValue(0);
		txtNumbBall.setText(null);
		txtTime.setText(null);
		txtSpeedBask.setText(null);
		txtSpeedBall.setText(null);

	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new SettingWindow();

	}

}
