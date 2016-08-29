import java.awt.EventQueue;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.event.MenuEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;



/*Ball catching game is to click the circles and put them into the moving basket bottom of 
 *the window.In this game, a user try to click the center of a random moving circles.
 *If the user able to click the center,then the circle moves to downward straightly.
 *In case the ball dropped in to the moving basked, then the user get some point, other case, 
 *the user fail to win points. A user fail to win point in two cases, not able to click the center
 *of balls and not able to put balls into the basket. There are some forms for this application.
 * 
 */

//this class is game area frame

public class GameWindow extends JPanel {
	/*
	 * thBallLoop that is thread allowing the movement of the ball.Firstly,this
	 * is defined empty thBasketLoop that is thread allowing the movement of the
	 * basket.This is defined empty. thBallBottom which is thread allowing
	 * moving to downward straightly of the ball.This is defined empty.
	 */
	static private Thread thBallLoop = null;
	static private Thread thBasketLoop = null;
	static private Thread thBallBottom = null;

	// Four flags are occurred.Firstly,isNewGame is defined
	// true,isGameResetted,isGamePaused,isGameStopped are defined false.
	private static boolean isNewGame = true; // whether Game new or not
	private boolean isGameResetted = false; // whether Game reset or not
	private static boolean isGamePaused = false; // whether Game pause or not
	private static boolean isGameStopped = false;//// whether Game stop or not
	// defines some things needed
	private JFrame frmBasketGame;
	static JTextField txtName;
	static public String PlayerName;
	static int time;
	static Time countDown;
	static int Number_Ball;
	static int ballSpeed;
	static int basketSpeed;
	// fail and win counts are defined zero in the beginning
	public static int failCount = 0;
	public static int winCount = 0;
	// panel is defined as empty in the beginning
	private static JPanel pnlGameBoard = null;
	// basket is defined as empty in the beginning
	private static BasketRectangle basket = null;

	// ballColor that is string array keeps the colors of the balls
	String[] ballColor = { "Ball1", "Ball2", "Ball3", "Ball4", "Ball5" };
	// Array list is occurred balls to hold in place
	static ArrayList<Ball> balls = new ArrayList<Ball>(Number_Ball);
	// Array list is created and keep players who play game
	public static ArrayList<Player> players = new ArrayList<Player>();

	private JMenuItem mnitmReset;
	private JMenuItem mnitmPause;
	private JMenuItem mnitmStop;
	private JMenuItem mnitmStart;
	private JMenu menuGame;
	private JMenuBar menuBar;
	private JMenu menuSettings;
	private JMenuItem mnitmExit;

	private static JLabel lblDate;
	private Date date;
	private JPanel panelTime;
	private static JLabel lblTime;
	public static JLabel lblWin;
	public static JLabel lblFail;

	// timer is defined as empty in the beginning
	private Timer gameTimer = null;

	// Method is occurred to get number of balls and change number of balls
	public int getNumberBall() {
		return Number_Ball;
	}

	public void setNumberBall(int number) {
		Number_Ball = number;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow();

					window.frmBasketGame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// defines frame and specifies size,exit
		// operation,bound,color,font,title and add image
		frmBasketGame = new JFrame();
		frmBasketGame.setIconImage(new ImageIcon(getClass().getResource("Basket Game.png")).getImage());
		frmBasketGame.getContentPane().setBackground(new Color(179, 158, 181));
		frmBasketGame.getContentPane().setForeground(Color.BLACK);
		frmBasketGame.setTitle("Basket Game");
		frmBasketGame.setBounds(100, 100, 511, 513);
		frmBasketGame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmBasketGame.setLocationRelativeTo(null);
		frmBasketGame.getContentPane().setLayout(null);

		// defines labels,text fields and panels. specifies sizes,bounds
		// ,font,color.And adds game area frame.

		// Player label
		JLabel lblNameOf = new JLabel("Name of the Player:");
		lblNameOf.setBackground(Color.LIGHT_GRAY);
		lblNameOf.setFont(new Font("Verdana", Font.BOLD, 15));

		lblNameOf.setHorizontalAlignment(SwingConstants.LEFT);
		lblNameOf.setBounds(20, 11, 183, 20);
		frmBasketGame.getContentPane().add(lblNameOf);
		// textNAME
		txtName = new JTextField();
		txtName.setEnabled(false);

		txtName.setBackground(new Color(255, 255, 255));
		txtName.setBounds(201, 11, 183, 20);
		frmBasketGame.getContentPane().add(txtName);

		JPanel panelWin = new JPanel();
		panelWin.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		panelWin.setBackground(new Color(153, 102, 153));
		panelWin.setBounds(20, 45, 105, 37);
		frmBasketGame.getContentPane().add(panelWin);
		panelWin.setLayout(null);

		lblWin = new JLabel("  Win: 0");
		lblWin.setBounds(0, 0, 105, 37);
		panelWin.add(lblWin);
		lblWin.setHorizontalAlignment(SwingConstants.LEFT);
		lblWin.setForeground(Color.BLACK);
		lblWin.setBackground(new Color(255, 255, 255));

		JPanel panelFail = new JPanel();
		panelFail.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), new Color(0, 0, 0),
				new Color(0, 0, 0), new Color(0, 0, 0)));
		panelFail.setBackground(new Color(153, 102, 153));
		panelFail.setBounds(135, 45, 105, 37);
		panelWin.setBackground(new Color(153, 102, 153));
		frmBasketGame.getContentPane().add(panelFail);
		panelFail.setLayout(null);

		lblFail = new JLabel("  Fail: 0");
		lblFail.setBounds(0, 0, 105, 37);
		panelFail.add(lblFail);
		lblFail.setHorizontalAlignment(SwingConstants.LEFT);
		lblFail.setForeground(Color.BLACK);
		lblFail.setBackground(Color.WHITE);

		panelTime = new JPanel();
		panelTime.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		panelTime.setBackground(new Color(153, 102, 153));
		panelTime.setBounds(250, 45, 105, 37);
		frmBasketGame.getContentPane().add(panelTime);
		panelTime.setLayout(null);

		lblTime = new JLabel(" Time: 00:00");
		lblTime.setBounds(0, 0, 105, 37);
		panelTime.add(lblTime);
		lblTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblTime.setForeground(Color.BLACK);
		lblTime.setBackground(Color.WHITE);

		// Label and Panel Date
		// Initialize date
		date = new Date();

		JPanel panelDate = new JPanel();
		panelDate.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), new Color(0, 0, 0),
				new Color(0, 0, 0), new Color(0, 0, 0)));
		panelDate.setBackground(new Color(153, 102, 153));
		panelDate.setBounds(365, 45, 105, 37);
		frmBasketGame.getContentPane().add(panelDate);
		panelDate.setLayout(null);

		lblDate = new JLabel(" Date: -");
		lblDate.setBounds(0, 0, 105, 37);

		panelDate.add(lblDate);

		lblDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblDate.setForeground(Color.BLACK);
		lblDate.setBackground(Color.WHITE);

		// Panel is occurred for area which plays games
		pnlGameBoard = new JPanel();
		pnlGameBoard.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), new Color(0, 0, 0),
				new Color(0, 0, 0), new Color(0, 0, 0)));
		pnlGameBoard.setBounds(10, 95, 470, 347);
		pnlGameBoard.setBackground(new Color(179, 158, 181));
		frmBasketGame.getContentPane().add(pnlGameBoard);
		pnlGameBoard.setLayout(null);

		// Initialize MENU BAR
		menuBar = new JMenuBar();
		menuBar.setBackground(UIManager.getColor("ToolBar.light"));
		frmBasketGame.setJMenuBar(menuBar);
		// Initialize MENU
		menuGame = new JMenu("Game");
		menuBar.add(menuGame);

		// Initialize MENU ITEMS
		/*
		 * Menu item start provide to begin game. If first if is true,second if
		 * is look then check true or false.if statement is false ,a new game
		 * begin.Then run a game. second if statement is false,cleans every
		 * things in panel game board and start new game.
		 * 
		 * First if is false,pause flag is make false and run game.
		 * 
		 */
		mnitmStart = new JMenuItem("Start   Alt+E", KeyEvent.VK_E);
		mnitmStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (isNewGame || isGameResetted || isGameStopped) {

					if (isGameStopped)
						clearGameBoard();

					startNewGame();
					RunGame();
				} else {
					isGamePaused = false;
					RunGame();
				}

			}
		});
		mnitmStart.setIcon(new ImageIcon(getClass().getResource("Start.png")));
		mnitmStart.setMnemonic(KeyEvent.VK_E);
		menuGame.add(mnitmStart);
		/*
		 * When menu item stop is clicked,Game is stopped. Pause and stop flags
		 * make true. Game is finished.And show game result and player is saved
		 * in report
		 */
		mnitmStop = new JMenuItem("Stop  Alt+S", KeyEvent.VK_S);
		mnitmStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isGamePaused = true;
				isGameStopped = true;
				showGameResult(false);
				showPlayer();
			}
		});
		mnitmStop.setIcon(new ImageIcon(getClass().getResource("Stop.png")));
		mnitmStop.setMnemonic(KeyEvent.VK_S);
		menuGame.add(mnitmStop);
		/*
		 * When menu item pause is clicked, game is paused.isGamePaused flag is
		 * true. Threads is occurred to run the game again.
		 */

		mnitmPause = new JMenuItem("Pause   Alt+P", KeyEvent.VK_P);
		mnitmPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isGamePaused = true;
				thBallLoop = new Thread(new BallLoop());
				thBasketLoop = new Thread(new BasketLoop());
			}
		});
		mnitmPause.setIcon(new ImageIcon(getClass().getResource("Pause.png")));
		mnitmPause.setMnemonic(KeyEvent.VK_P);
		menuGame.add(mnitmPause);

		mnitmReset = new JMenuItem("Reset   Alt+D", KeyEvent.VK_R);
		mnitmReset.setMnemonic(KeyEvent.VK_R);
		mnitmReset.setIcon(new ImageIcon(getClass().getResource("Reset.png")));

		/***************************
		 * MnuItemRESET Controls Events
		 ************************************/
		// When menu reset is clicked,resets everything in game area frame
		mnitmReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearGameBoard();
			}
		});

		menuGame.add(mnitmReset);

		menuGame.addSeparator();

		/***************************
		 * MnuItemEXIT Controls Events
		 ************************************/
		// When menu exit is clicked,close game area frame
		mnitmExit = new JMenuItem("Exit  Alt+Q", KeyEvent.VK_Q);
		mnitmExit.setMnemonic(KeyEvent.VK_Q);

		mnitmExit.setIcon(new ImageIcon(getClass().getResource("Exit.png")));
		mnitmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		menuGame.add(mnitmExit);
		/***************************
		 * MnuItemSETTING Controls Events
		 ************************************/
		/*
		 * When menu setting is clicked, open setting frame.When this item is
		 * clicked, a form will be appeared and a user set parameters related to
		 * this game before playing game
		 */
		menuSettings = new JMenu("Setting");
		menuSettings.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				new SettingWindow();

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		menuBar.add(menuSettings);

		JMenu menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);

		JMenuItem mnitmAboutGame = new JMenuItem("About Game  Alt+H", KeyEvent.VK_H);

		/***************************
		 * MnuITEMAbout Controls Events
		 ************************************/
		// When menu item about is clicked, open a information to help to user
		// about game
		mnitmAboutGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Firstly, make the settings before starting the game." + "\n"
						+ "If click the center of ball then the  moves to downward straightly." + "\n"
						+ "When ball dropped in to the moving basked ,you get some point other case, you fail to win points");

			}
		});
		mnitmAboutGame.setIcon(new ImageIcon(getClass().getResource("About Help.png")));
		mnitmAboutGame.setMnemonic(KeyEvent.VK_H);
		menuHelp.add(mnitmAboutGame);

		JMenu menuReport = new JMenu("Report");

		/***************************
		 * MnuReport Controls Events
		 ************************************/
		// When menu report is clicked, open report frame

		menuReport.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				Report.main(null);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		{

		}

		menuBar.add(menuReport);
	}

	/*
	 * A method is occurred to begin game.Firstly
	 */
	private void startNewGame() {

		countDown = new Time(0, time, 0);// specified time places countDown.
		// providing thread the movement of ball and basket is occurred again.
		thBallLoop = new Thread(new BallLoop());
		thBasketLoop = new Thread(new BasketLoop());
		// when start new game,stopped,resetted,paused are false.
		isGameStopped = false;
		isGameResetted = false;
		isGamePaused = false;
		System.out.println("Start game clicked.");
		// basket is created and add panel game board.
		basket = new BasketRectangle();
		pnlGameBoard.add(basket);
		// the position X and Y of basket is specified in the beginning.
		basket.setPositionX((pnlGameBoard.getWidth() / 2) - (basket.getWidth() / 2));
		basket.setPositionY(pnlGameBoard.getHeight() - 50);

		txtName.setText(PlayerName);
		// time is showed in the game window
		int a = time;
		String b = Integer.toString(a);
		lblTime.setText(" Time: " + b);

		// set date that playing.date is showed in the game window
		SimpleDateFormat sdf_date = new SimpleDateFormat("dd.MM.yyyy");
		lblDate.setText(" Date: " + sdf_date.format(date));
		/*
		 * According to given number of ball,the colors of the balls is
		 * determined.Add balls in panel game board.
		 */
		Random rand = new Random();
		int ballColorIndex = 0;

		for (int i = 0; i < Number_Ball; i++) {
			if (ballColorIndex == ballColor.length) {
				ballColorIndex = 0;
			}
			Ball ball = new Ball(ballColor[ballColorIndex]);
			balls.add(ball);
			pnlGameBoard.add(balls.get(i));

			/*
			 * Rectangle is used to specified the position of balls positions
			 * the ball is specified randomly and place this position in panel
			 * game board.
			 * 
			 */
			Rectangle r = balls.get(i).getBounds();
			r.x = rand.nextInt(470 - 25);
			r.y = rand.nextInt(170);
			balls.get(i).setBounds(r);
			balls.get(i).setVisible(true);

			ballColorIndex++; // increment color index
			ball.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				/*
				 * When the ball is pressed,to moves to downward straightly; The
				 * ispressed feature of ball is made true. Thread that is
				 * allowing moving to downward straightly of the ball is created
				 * and start .
				 * 
				 */
				@Override
				public void mousePressed(MouseEvent e) {
					ball.setIsPressed(true);
					new Thread(new BallLoopBottom(ball)).start();
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub

				}
			});

		}

		rand = null;

	}

	// This method cleans everything in game area
	private void clearGameBoard() {
		isNewGame = true;
		isGamePaused = false;
		isGameStopped = false;
		pnlGameBoard.removeAll();
		pnlGameBoard.repaint();
		balls = new ArrayList<Ball>();
		winCount = 0;
		failCount = 0;
		lblFail.setText(" Fail=0");
		lblWin.setText(" Win=0");
		lblTime.setText("Time= 00:00");
		lblDate.setText(" Date: -");
		txtName.setText("");
		gameTimer.cancel();
	}

	@SuppressWarnings("deprecation")
	/*
	 * Method that is run game.Therefore,isnewGame is made false.if ball thread
	 * doesn't run ,ball and basket thread are run. Timer is created.if the game
	 * runs, timer is runs and show in the game window. if given time equals
	 * zero or less than zero,game is stop. And show game result and save player
	 * record.
	 */
	private void RunGame() {
		isNewGame = false;
		if (!thBallLoop.isAlive()) {
			thBallLoop.start();
			thBasketLoop.start();
			gameTimer = new Timer();
			gameTimer.scheduleAtFixedRate(new TimerTask() {
				public void run() {
					if (!isGameStopped && !isGamePaused && !isNewGame) {
						countDown = new Time(countDown.getTime() - 1000);
						SimpleDateFormat sfp = new SimpleDateFormat("mm:ss");
						lblTime.setText(" Time: " + sfp.format(countDown));

						if (0 >= countDown.getMinutes() && 0 >= countDown.getSeconds()) {
							isGameStopped = true;
							isGamePaused = true;

							showGameResult(true);
							showPlayer();
						}

					}
				}
			}, 0, 1000);
		}
	}
	/*
	 * thBallLoop that is thread allowing the movement of the ball. while game
	 * is run,thread runs indefinitely. if balls doesn't click,the position of
	 * ball is specified.
	 * 
	 */

	private static class BallLoop implements Runnable {
		public void run() {
			Random rnd = new Random();
			while (!isGamePaused && !isNewGame) {
				try {
					for (Ball ball : balls) {
						if (!ball.getIsPressed()) {
							Rectangle r = ball.getBounds();
							/*
							 * the position x of ball is showing changes
							 * depending direction. provides to ball moves
							 * forward by adding value to the x-axis or provides
							 * to ball go back by removing
							 */
							r.x += ball.getDirectionForward() * rnd.nextInt(25);
							/*
							 * 
							 * The total of ball is looked.if this length is
							 * greater than the width of game panel and i f
							 * directionForward=1 the axis x of ball equals the
							 * width of game panel removes the width of ball.
							 * 
							 */
							if (r.x + r.getWidth() > pnlGameBoard.getWidth() && ball.getDirectionForward() == 1)
								r.x = (int) (pnlGameBoard.getWidth() - r.getWidth());
							// if x axis of ball is the outside of the game
							// panel,the position of ball is made starting
							// point.
							if (r.x < 0 && ball.getDirectionForward() == -1)
								r.x = 0;
							// if the total of ball is greater than the width of
							// panel ,directionforward is made false to move
							// left hand
							if (r.x + r.getWidth() >= pnlGameBoard.getWidth())
								ball.setDirectionForward(false);
							// if position is less than zero ,flag is made true
							// to move right hand side
							else if (0 >= r.x) {
								ball.setDirectionForward(true);
							}
							// setting new position of the ball and repaint the
							// game board to show new position of the ball
							ball.setBounds(r);
							pnlGameBoard.repaint();

							try {
								// 200 milliseconds sleep
								thBallLoop.sleep(200 / ballSpeed);
							} catch (InterruptedException e) {
							}
						}
					}
				} catch (Exception e) {

				}
			}
		}
	}
	/*
	 * thBallBottom which is thread allowing moving to downward straightly of
	 * the ball.
	 */

	private static class BallLoopBottom implements Runnable {
		private Ball ball = null; // creates new ball

		public BallLoopBottom(Ball b) { // constructor with one parameter
			ball = b;
		}

		// isOk
		public void run() {
			Random rnd = new Random();
			if (!isGamePaused && !isNewGame) {
				/*
				 * This flag controls the event of falling down of ball, if it
				 * is true continue to fall down, if it is false stop falling
				 * down
				 */
				boolean isOk = true;
				while (isOk) {
					Rectangle r = ball.getBounds();
					Rectangle rBasket = basket.getBounds();
					// for provide falling down effect of ball on y axis
					r.y += rnd.nextInt(25);
					// checking the bottom point of ball is into the limits of
					// basket bounds or not
					if (((r.y + r.getHeight()) >= rBasket.y)
							&& ((r.y + r.getHeight()) <= rBasket.y + rBasket.getHeight())
							&& ((r.x >= rBasket.x) && (r.x <= rBasket.x + rBasket.getWidth()))) {

						// if code goes to here this means that ball enters to
						// the basket so win point upload show the result
						ball.setVisible(false);
						isOk = false;
						winCount += 1;
						lblWin.setText(" Win :" + winCount);
					}
					// checking the top point of the ball is out of bounds of
					// the play ground or not
					if (r.y > 400 + r.getHeight()) {
						/*
						 * if code goes to here this means that ball has gone
						 * out of the play ground bounds so fail point update
						 * show the result
						 */

						isOk = false;
						failCount += 1;
						lblFail.setText(" Fail :" + failCount);
					}
					// setting new position of the ball and repaint the game
					// board to show new position of the ball
					ball.setBounds(r);
					pnlGameBoard.repaint();

					try {
						// 100 milliseconds sleep
						thBallBottom.sleep(100 / ballSpeed);
					} catch (InterruptedException e) {
					}
				}
				/*
				 * all balls whether pressed or not. This is defined true.if all
				 * the balls dont't click flag is made false and this loop is
				 * exited.
				 */
				boolean isAllBallsPressed = true;
				for (Ball ball : balls) {
					if (!ball.getIsPressed()) {
						isAllBallsPressed = false;
						break;
					}
				}
				// if balls are pressed, Game over and show game result and save
				// report.
				if (isAllBallsPressed) {
					isGameStopped = true;
					isGamePaused = true;
					showGameResult(false);
					showPlayer();
				}
			}
		}
	}

	/*
	 * thBasketLoop that is thread allowing the movement of the basket. while
	 * game is run,thread runs indefinitely.
	 * 
	 */
	private static class BasketLoop implements Runnable {
		public void run() {
			Random rnd = new Random();
			int direction = 1;
			while (!isGamePaused && !isNewGame) {
				Rectangle r = basket.getBounds();
				/*
				 * the position x of basket is showing changes depending
				 * direction. provides to basket moves forward by adding value
				 * to the x-axis or provides to basket go back by removing
				 */

				r.x += direction * rnd.nextInt(25);
				/*
				 * The total of basket is looked.if basket pass the width of
				 * panel and move forward,the position of basket is the width of
				 * game panel removes the width of basket
				 */

				if (r.x + r.getWidth() > pnlGameBoard.getWidth() && direction == 1)
					r.x = (int) (pnlGameBoard.getWidth() - r.getWidth());
				// if basket is the outside of the game panel,the position of
				// ball is made starting point.
				if (r.x < 0 && direction == -1)
					r.x = 0;
				// if the total of basket is greater than the width of panel
				// ,direction is made -1 to move left hand
				if (r.x + r.getWidth() >= pnlGameBoard.getWidth())
					direction = -1;
				// if position is less than zero ,direction is made 1 to move
				// right hand side
				else if (0 >= r.x) {
					direction = 1;
				}
				// setting new position of the basket and repaint the game board
				// to show new position of the basket
				basket.setBounds(r);
				pnlGameBoard.repaint();

				try {
					// 200 Milliseconds sleep
					thBasketLoop.sleep(200 / basketSpeed); // the speed of
															// basket is
															// specified
				} catch (InterruptedException e) {
				}
			}
		}
	}

	/*
	 * this method provides to show score result finishing the game. if time
	 * over if time has ended, show time over message and win and fail count. if
	 * the balls has ended,show game over message and win and fail count.
	 */
	private static void showGameResult(boolean isTimeEnd) {
		if (isTimeEnd) {
			// Time over

			JOptionPane.showMessageDialog(null, "Time Over \n" + "Win:" + winCount + " " + " Fail:" + failCount);
		} else {
			JOptionPane.showMessageDialog(null, "Game Over \n" + "Win:" + winCount + " " + " Fail:" + failCount);
		}
	}

	/*
	 * The arraylist of player add results of players after finishing game and
	 * save text file.
	 */
	private static void showPlayer() {

		String name = txtName.getText();
		String winPoint = String.valueOf(winCount);
		String failPoint = String.valueOf(failCount);
		String time = String.valueOf(countDown);
		String date = lblDate.getText().substring(7);
		players.add(new Player(name, winPoint, failPoint, time, date));
		saveReport();
	}

	// This method provides to save the scores of players to text file.
	private static void saveReport() {

		try {
			File reportDir = new File("ScoreBoard");
			if (!reportDir.exists()) { // if file doesn't exist,ScoreBoard file
										// is created.if file does,this if
										// statement is passing.
				reportDir.mkdir();
			}

			File file = new File("ScoreBoard/" + "Report.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
			for (Player player : players) {
				if (!player.getIsSaved()) {
					player.setIsSaved(true);
					bw.write(player.getName() + ";" + player.getWinPoint() + ";" + player.getFailPoint() + ";"
							+ player.getTime() + ";" + player.getDate());
					bw.newLine();
				}
			}
			bw.flush();
			bw.close();
			System.out.println("Done!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
