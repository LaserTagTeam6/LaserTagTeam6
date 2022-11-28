import java.util.ArrayList;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import java.awt.Window.Type;
import java.awt.Component;
import javax.swing.Box;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.SpringLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Image;
import java.lang.Object;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.awt.SystemColor;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.SECONDS;




class View extends JPanel{

	protected int numPerTeam = 15;
	public int minutesRemaining = 6;
	public int secondsRemaining = 30;
	public int warningTime = 30;
	public int waitTime = 360;
	DecimalFormat formatter = new DecimalFormat("00");
	String secondsFormatted = formatter.format(secondsRemaining);
	final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	
	Model model;
	DBConnector db;
	//UDPSystem server;
	public boolean addNum = false;
	public int IDnum = 0;
	public int playerIndex = 0;
	public boolean deleteNum = false;
	public String lastNum = "Nothing";
	JFrame frmLasertagCharacter, frmLasertagGame;
	
	//green team data
	private JLabel[] GreenLabels = new JLabel[numPerTeam];
	public JTextField[] GreenUsers = new JTextField[numPerTeam];
	public JTextField[] GreenIDs = new JTextField[numPerTeam];
	private GridBagConstraints[] gbc_lblGreen = new GridBagConstraints[numPerTeam];
	private GridBagConstraints[] gbc_GreenID = new GridBagConstraints[numPerTeam];
	private GridBagConstraints[] gbc_GreenUser = new GridBagConstraints[numPerTeam];
	//red team data
	private JLabel[] RedLabels = new JLabel[numPerTeam];
	public JTextField[] RedUsers = new JTextField[numPerTeam];
	public JTextField[] RedIDs = new JTextField[numPerTeam];
	private GridBagConstraints[] gbc_lblRed = new GridBagConstraints[numPerTeam];
	private GridBagConstraints[] gbc_RedID = new GridBagConstraints[numPerTeam];
	private GridBagConstraints[] gbc_RedUser = new GridBagConstraints[numPerTeam];
	//buttons
	public JButton btnConfirmID, btnStartGame, btnMoveRight, btnMoveLeft, btnFinishUsername, btnNewButton_5;
	private JPanel contentPane;
	//game screen score data
	//red
	public static JLabel lblRedTotalScore;
	public JLabel[] lblRedUsers = new JLabel[numPerTeam];
	private GridBagConstraints[] gbc_lblRedUsers = new GridBagConstraints[numPerTeam];
	public static JLabel[] lblRedScores = new JLabel[15]; //DO NOT MAKE numPerTeam
	private GridBagConstraints[] gbc_lblRedScore = new GridBagConstraints[numPerTeam];
	//green
	public static JLabel lblGreenTotalScore;
	public JLabel[] lblGreenUsers = new JLabel[numPerTeam];
	private GridBagConstraints[] gbc_lblGreenUsers = new GridBagConstraints[numPerTeam];
	public static JLabel[] lblGreenScores = new JLabel[15]; //DO NOT MAKE numPerTeam
	private GridBagConstraints[] gbc_lblGreenScore = new GridBagConstraints[numPerTeam];
	
	//private BufferedImage splashScreen;
	private Image splashScreen;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JPanel panel_1;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

	public JPanel time_display;
	public JLabel lblSecondsRemaining;
	public JLabel lblMinutesRemaining;

	//hit_histroy declarations
	public static JTextArea hit_counter_display;

	//begings listener when game actually starts
	public static boolean ListenerEnable  = false;
	


	// Create the data for each application screen.
	View(Model m, DBConnector d) throws SocketException, UnknownHostException {
		model = m;
		db = d;
		//server = new UDPSystem(7501, 7500, db);
		
		//-------Splash/Character Creation Screen Frame Data
		frmLasertagCharacter = new JFrame();
		frmLasertagCharacter.setIconImage(Toolkit.getDefaultToolkit().getImage(View.class.getResource("/Images/360_F_330853301_Ymj2OjSqxhYYFVXE7WiRzuRgYNUVgvy5.jpg")));
		frmLasertagCharacter.getContentPane().setBackground(new Color(0, 0, 0));
		frmLasertagCharacter.setTitle("Photon Laser Tag");
		frmLasertagCharacter.setBounds(100, 100, 600, 550);
		frmLasertagCharacter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//-------Game Screen Frame Data
		frmLasertagGame = new JFrame();
		frmLasertagGame.setIconImage(Toolkit.getDefaultToolkit().getImage(View.class.getResource("/Images/360_F_330853301_Ymj2OjSqxhYYFVXE7WiRzuRgYNUVgvy5.jpg")));
		frmLasertagGame.getContentPane().setBackground(new Color(0, 0, 0));
		frmLasertagGame.setTitle("LaserTag Game Screen");
		frmLasertagGame.setBounds(100, 100, 600, 600);
		frmLasertagGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frmLasertagGame.setVisible(false);
		initializeCharacterScreen();
		
	}
	
	public void showCharacterScreen(View window)
	{
		window.frmLasertagCharacter.setVisible(true);
	}
	
	public void showGameScreen(View window)
	{
		window.frmLasertagGame.setVisible(true);
	}

	
	 //----------------------------Initialize the contents of the frame---------------------------------------//
	public void initializeCharacterScreen() {
		
		//-------------------------------------------Splash Screen-------------------------------------------//
		JPanel panelsplash = new JPanel();
		panelsplash.setForeground(new Color(0, 0, 0));
		panelsplash.setBackground(new Color(0, 0, 0));
		frmLasertagCharacter.getContentPane().add(panelsplash, BorderLayout.SOUTH);
		GridBagLayout gbl_panelsplash = new GridBagLayout();
		gbl_panelsplash.columnWidths = new int[]{50, 146, 50, 0};
		gbl_panelsplash.rowHeights = new int[]{14, 50, 0};
		gbl_panelsplash.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelsplash.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panelsplash.setLayout(gbl_panelsplash);
				
		
		
		JProgressBar progressBar = new JProgressBar();
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.insets = new Insets(0, 0, 0, 5);
		gbc_progressBar.anchor = GridBagConstraints.NORTH;
		gbc_progressBar.gridx = 1;
		gbc_progressBar.gridy = 0;
		panelsplash.add(progressBar, gbc_progressBar);
				
		JPanel panelsplash_1 = new JPanel();
		panelsplash_1.setBackground(new Color(0, 0, 0));
		GridBagConstraints gbc_panelsplash_1 = new GridBagConstraints();
		gbc_panelsplash_1.anchor = GridBagConstraints.SOUTH;
		gbc_panelsplash_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelsplash_1.gridx = 1;
		gbc_panelsplash_1.gridy = 1;
		panelsplash.add(panelsplash_1, gbc_panelsplash_1);

		try
		{
			//this.splashScreen = ImageIO.read(new File("NewSplashScreen.jpg"));
			this.splashScreen = Toolkit.getDefaultToolkit().getImage(View.class.getResource("./Images/NewSplashScreen.jpg"));

		}
		catch(Exception e)
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}
		ImageIcon icon = new ImageIcon(this.splashScreen);
		JLabel lblNewLabelSplash = new JLabel();
		lblNewLabelSplash.setIcon(icon);
		frmLasertagCharacter.add(lblNewLabelSplash);
		frmLasertagCharacter.setVisible(true);
		lblNewLabelSplash.setHorizontalAlignment(SwingConstants.CENTER);
		frmLasertagCharacter.getContentPane().add(lblNewLabelSplash, BorderLayout.CENTER);

		//----------------------------------------Removes Splash Screen-------------------------------------------//
		for(int i = 0; i < 100; i++){
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
			}
			progressBar.setValue(i);
		}

		frmLasertagCharacter.getContentPane().remove(lblNewLabelSplash);
		frmLasertagCharacter.getContentPane().remove(progressBar);
		frmLasertagCharacter.getContentPane().remove(panelsplash);
		frmLasertagCharacter.getContentPane().remove(panelsplash_1);
		frmLasertagCharacter.repaint();


		//-------------------------------------------Red Team------------------------------------------------//
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{100, 100, 100, 100, 0};
		gridBagLayout.rowHeights = new int[]{0, 193, 23, 23, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		frmLasertagCharacter.getContentPane().setLayout(gridBagLayout);
		
		lblNewLabel_2 = new JLabel("Character Creation");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setForeground(new Color(0, 255, 255));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 4;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 0;
		frmLasertagCharacter.getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 215, 0), 4, true));
		panel.setBackground(new Color(0, 0, 0));
		panel.setForeground(new Color(0, 0, 0));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		frmLasertagCharacter.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{200, 0};
		gbl_panel.rowHeights = new int[]{0, 233, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblNewLabel = new JLabel("Red Team");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(250, 128, 114));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		panel.add(scrollPane, gbc_scrollPane);
		scrollPane.setViewportBorder(null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		JPanel redpanel = new JPanel();
		redpanel.setBorder(null);
		redpanel.setBackground(new Color(240, 128, 128));
		scrollPane.setViewportView(redpanel);
		GridBagLayout gbl_redpanel = new GridBagLayout();
		gbl_redpanel.columnWidths = new int[]{20, 0, 0, 0};
		gbl_redpanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_redpanel.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_redpanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		redpanel.setLayout(gbl_redpanel);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(255, 215, 0), 4, true));
		panel_1.setBackground(new Color(0, 0, 0));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 1;
		frmLasertagCharacter.getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{200, 0};
		gbl_panel_1.rowHeights = new int[]{0, 11, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		

		//--------------------------------------------Red Labels------------------------------------------//
		for(int i = 0; i<numPerTeam; i++) {
			RedLabels[i] = new JLabel(String.valueOf(i));
			RedLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			gbc_lblRed[i] = new GridBagConstraints();
			gbc_lblRed[i].insets = new Insets(0, 0, 5, 5);
			gbc_lblRed[i].gridx = 0;
			gbc_lblRed[i].gridy = i;
			redpanel.add(RedLabels[i], gbc_lblRed[i]);
		}

		
		//-----------------------------------------Red ID Numbers------------------------------------------//
		for(int i = 0; i<numPerTeam; i++) {
			RedIDs[i] = new JTextField();
			gbc_RedID[i] = new GridBagConstraints();
			gbc_RedID[i].insets = new Insets(0, 0, 5, 5);
			gbc_RedID[i].fill = GridBagConstraints.HORIZONTAL;
			gbc_RedID[i].gridx = 1;
			gbc_RedID[i].gridy = i;
			redpanel.add(RedIDs[i], gbc_RedID[i]);
			RedIDs[i].setColumns(10);
		}

		
		//--------------------------------------------Red Users------------------------------------------//
		for(int i = 0; i<numPerTeam; i++) {
			RedUsers[i] = new JTextField();
			RedUsers[i].setEnabled(true);
			gbc_RedUser[i] = new GridBagConstraints();
			gbc_RedUser[i].insets = new Insets(0, 0, 5, 0);
			gbc_RedUser[i].fill = GridBagConstraints.HORIZONTAL;
			gbc_RedUser[i].gridx = 2;
			gbc_RedUser[i].gridy = i;
			redpanel.add(RedUsers[i], gbc_RedUser[i]);
			RedUsers[0].setColumns(10);
		}
				
				lblNewLabel_1 = new JLabel("Green Team");
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_1.setForeground(new Color(144, 238, 144));
				lblNewLabel_1.setBackground(new Color(0, 0, 0));
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
				gbc_lblNewLabel_1.gridx = 0;
				gbc_lblNewLabel_1.gridy = 0;
				panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		//--------------------------------------------Green Team------------------------------------------//
				JScrollPane scrollPane_1 = new JScrollPane();
				GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
				gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
				gbc_scrollPane_1.gridx = 0;
				gbc_scrollPane_1.gridy = 1;
				panel_1.add(scrollPane_1, gbc_scrollPane_1);
				scrollPane_1.setViewportBorder(null);
				scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				
				JPanel greenpanel = new JPanel();
				greenpanel.setBackground(new Color(144, 238, 144));
				scrollPane_1.setViewportView(greenpanel);
				GridBagLayout gbl_greenpanel = new GridBagLayout();
				gbl_greenpanel.columnWidths = new int[]{20, 0, 0, 0};
				gbl_greenpanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
				gbl_greenpanel.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
				gbl_greenpanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				greenpanel.setLayout(gbl_greenpanel);
				
		//--------------------------------------------Green Labels------------------------------------------//
		for(int i = 0; i<numPerTeam; i++) {
			GreenLabels[i] = new JLabel(String.valueOf(i));
			GreenLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			gbc_lblGreen[i] = new GridBagConstraints();
			gbc_lblGreen[i].insets = new Insets(0, 0, 5, 5);
			gbc_lblGreen[i].gridx = 0;
			gbc_lblGreen[i].gridy = i;
			greenpanel.add(GreenLabels[i], gbc_lblGreen[i]);
		}


		//--------------------------------------------Green ID Numbers------------------------------------------//
		for(int i = 0; i<numPerTeam; i++) {
			GreenIDs[i] = new JTextField();
			gbc_GreenID[i] = new GridBagConstraints();
			gbc_GreenID[i].insets = new Insets(0, 0, 5, 5);
			gbc_GreenID[i].fill = GridBagConstraints.HORIZONTAL;
			gbc_GreenID[i].gridx = 1;
			gbc_GreenID[i].gridy = i;
			greenpanel.add(GreenIDs[i], gbc_GreenID[i]);
			GreenIDs[i].setColumns(10);
		}
		

		//--------------------------------------------Green Users------------------------------------------//
		for(int i = 0; i<numPerTeam; i++) {
			GreenUsers[i] = new JTextField();
			GreenUsers[i].setEnabled(true); 
			gbc_GreenUser[i] = new GridBagConstraints();
			gbc_GreenUser[i].insets = new Insets(0, 0, 5, 0);
			gbc_GreenUser[i].fill = GridBagConstraints.HORIZONTAL;
			gbc_GreenUser[i].gridx = 2;
			gbc_GreenUser[i].gridy = i;
			greenpanel.add(GreenUsers[i], gbc_GreenUser[i]);
			GreenUsers[i].setColumns(10);
		}


		//--------------------------------------------Buttons------------------------------------------//
		btnConfirmID = new JButton("Finish ID Entry");
		btnConfirmID.setForeground(new Color(0, 0, 0));
		btnConfirmID.setBackground(UIManager.getColor("Button.background"));
		GridBagConstraints gbc_btnConfirmID = new GridBagConstraints();
		gbc_btnConfirmID.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnConfirmID.insets = new Insets(0, 0, 5, 5);
		gbc_btnConfirmID.gridx = 0;
		gbc_btnConfirmID.gridy = 2;
		
		btnFinishUsername = new JButton("Finish Username");
		btnFinishUsername.setBackground(new Color(240, 240, 240));
		btnFinishUsername.setEnabled(false);
		GridBagConstraints gbc_btnFinishUsername = new GridBagConstraints();
		gbc_btnFinishUsername.anchor = GridBagConstraints.EAST;
		gbc_btnFinishUsername.fill = GridBagConstraints.VERTICAL;
		gbc_btnFinishUsername.insets = new Insets(0, 0, 5, 5);
		gbc_btnFinishUsername.gridx = 1;
		gbc_btnFinishUsername.gridy = 2;
		
		btnMoveRight = new JButton("----->");
		btnMoveRight.setEnabled(false);
		GridBagConstraints gbc_btnMoveRight = new GridBagConstraints();
		gbc_btnMoveRight.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnMoveRight.insets = new Insets(0, 0, 5, 5);
		gbc_btnMoveRight.gridx = 2;
		gbc_btnMoveRight.gridy = 2;
		
		btnNewButton_5 = new JButton("Placeholder");
		btnNewButton_5.setEnabled(false);
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_5.gridx = 3;
		gbc_btnNewButton_5.gridy = 2;
		
		btnStartGame = new JButton("Start Game");
		btnStartGame.setEnabled(false);
		GridBagConstraints gbc_btnStartGame = new GridBagConstraints();
		gbc_btnStartGame.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnStartGame.insets = new Insets(0, 0, 0, 5);
		gbc_btnStartGame.gridx = 0;
		gbc_btnStartGame.gridy = 3;
		
		btnMoveLeft = new JButton("<-----");
		btnMoveLeft.setEnabled(false);
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_3.gridx = 2;
		gbc_btnNewButton_3.gridy = 3;
		
		frmLasertagCharacter.getContentPane().add(btnConfirmID, gbc_btnConfirmID);
		frmLasertagCharacter.getContentPane().add(btnFinishUsername, gbc_btnFinishUsername);
		frmLasertagCharacter.getContentPane().add(btnMoveRight, gbc_btnMoveRight);
		frmLasertagCharacter.getContentPane().add(btnNewButton_5, gbc_btnNewButton_5);
		frmLasertagCharacter.getContentPane().add(btnStartGame, gbc_btnStartGame);
		frmLasertagCharacter.getContentPane().add(btnMoveLeft, gbc_btnNewButton_3);
		
		
	}


	public void initializeGameScreen() {
		GridBagLayout gridBagLayoutGame = new GridBagLayout();
		gridBagLayoutGame.columnWidths = new int[]{0, 0};
		gridBagLayoutGame.rowHeights = new int[]{0, 0, 40, 0};
		gridBagLayoutGame.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayoutGame.rowWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		frmLasertagGame.getContentPane().setLayout(gridBagLayoutGame);
		
		JPanel score_panels = new JPanel();
		score_panels.setBackground(new Color(0, 0, 0));
		GridBagConstraints gbc_score_panels = new GridBagConstraints();
		gbc_score_panels.insets = new Insets(0, 0, 5, 0);
		gbc_score_panels.fill = GridBagConstraints.BOTH;
		gbc_score_panels.gridx = 0;
		gbc_score_panels.gridy = 0;
		frmLasertagGame.getContentPane().add(score_panels, gbc_score_panels);
		
		GridBagLayout gbl_score_panels = new GridBagLayout();
		gbl_score_panels.columnWidths = new int[]{0, 0, 0};
		gbl_score_panels.rowHeights = new int[]{0, 160, 0};
		gbl_score_panels.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_score_panels.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		score_panels.setLayout(gbl_score_panels);
		
		JLabel lblCurrentScores = new JLabel("Current Scores");
		lblCurrentScores.setForeground(new Color(0, 255, 255));
		lblCurrentScores.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		score_panels.add(lblCurrentScores, gbc_lblNewLabel_1);
		
		JPanel red_team_panel = new JPanel();
		red_team_panel.setBackground(new Color(0, 0, 0));
		red_team_panel.setBorder(new LineBorder(new Color(255, 215, 0), 4, true));
		GridBagConstraints gbc_red_team_panel = new GridBagConstraints();
		gbc_red_team_panel.insets = new Insets(0, 0, 0, 5);
		gbc_red_team_panel.fill = GridBagConstraints.BOTH;
		gbc_red_team_panel.gridx = 0;
		gbc_red_team_panel.gridy = 1;
		score_panels.add(red_team_panel, gbc_red_team_panel);
		GridBagLayout gbl_red_team_panel = new GridBagLayout();
		gbl_red_team_panel.columnWidths = new int[]{0, 70, 0};
		gbl_red_team_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_red_team_panel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_red_team_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		red_team_panel.setLayout(gbl_red_team_panel);
		
		
		//--------------------------------------------Red Team------------------------------------------//
		JLabel lblRedTeamGame = new JLabel("Red Team");
		lblRedTeamGame.setForeground(new Color(250, 128, 114));
		GridBagConstraints gbc_lblRedTeamGame = new GridBagConstraints();
		gbc_lblRedTeamGame.anchor = GridBagConstraints.NORTH;
		gbc_lblRedTeamGame.gridwidth = 2;
		gbc_lblRedTeamGame.insets = new Insets(0, 0, 5, 0);
		gbc_lblRedTeamGame.gridx = 0;
		gbc_lblRedTeamGame.gridy = 0;
		red_team_panel.add(lblRedTeamGame, gbc_lblRedTeamGame);
		lblRedTeamGame.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		
		//--------------------------------------------Red Users Game Display------------------------------------------//
		ArrayList<String> redCodenames = db.pullPlayer("red");
		
		for(int i = 0; i<redCodenames.size(); i++) {
			lblRedUsers[i] = new JLabel(redCodenames.get(i));
			lblRedUsers[i].setForeground(new Color(250, 128, 114));
			gbc_lblRedUsers[i] = new GridBagConstraints();
			gbc_lblRedUsers[i].insets = new Insets(0, 0, 5, 5);
			gbc_lblRedUsers[i].gridx = 0;
			gbc_lblRedUsers[i].gridy = i+1;
			red_team_panel.add(lblRedUsers[i], gbc_lblRedUsers[i]);
		}
		
		
		//--------------------------------------------Red Scores------------------------------------------//
		for(int i = 0; i<redCodenames.size(); i++) {
			lblRedScores[i] = new JLabel("00");
			lblRedScores[i].setForeground(new Color(250, 128, 114));
			gbc_lblRedScore[i] = new GridBagConstraints();
			gbc_lblRedScore[i].insets = new Insets(0, 0, 5, 5);
			gbc_lblRedScore[i].gridx = 1;
			gbc_lblRedScore[i].gridy = i+1;
			red_team_panel.add(lblRedScores[i], gbc_lblRedScore[i]);
		}
		
		//--------------------------------------------Red Team Total Score------------------------------------------//	
		lblRedTotalScore = new JLabel("00");
		lblRedTotalScore.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRedTotalScore.setForeground(new Color(250, 128, 114));
		GridBagConstraints gbc_lblRedTotalScore = new GridBagConstraints();
		gbc_lblRedTotalScore.anchor = GridBagConstraints.SOUTH;
		gbc_lblRedTotalScore.gridx = 1;
		gbc_lblRedTotalScore.gridy = numPerTeam + 1;
		red_team_panel.add(lblRedTotalScore, gbc_lblRedTotalScore);
		
		JPanel green_team_panel = new JPanel();
		green_team_panel.setBackground(new Color(0, 0, 0));
		green_team_panel.setBorder(new LineBorder(new Color(255, 215, 0), 4, true));
		GridBagConstraints gbc_green_team_panel = new GridBagConstraints();
		gbc_green_team_panel.fill = GridBagConstraints.BOTH;
		gbc_green_team_panel.gridx = 1;
		gbc_green_team_panel.gridy = 1;
		score_panels.add(green_team_panel, gbc_green_team_panel);
		GridBagLayout gbl_green_team_panel = new GridBagLayout();
		gbl_green_team_panel.columnWidths = new int[]{0, 70, 0};
		gbl_green_team_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_green_team_panel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_green_team_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		green_team_panel.setLayout(gbl_green_team_panel);
		
		//--------------------------------------------Green Team------------------------------------------//
		JLabel lblGreenTeamGame = new JLabel("Green Team");
		lblGreenTeamGame.setForeground(new Color(144, 238, 144));
		GridBagConstraints gbc_lblGreenTeamGame = new GridBagConstraints();
		gbc_lblGreenTeamGame.anchor = GridBagConstraints.NORTH;
		gbc_lblGreenTeamGame.gridwidth = 2;
		gbc_lblGreenTeamGame.insets = new Insets(0, 0, 5, 0);
		gbc_lblGreenTeamGame.gridx = 0;
		gbc_lblGreenTeamGame.gridy = 0;
		green_team_panel.add(lblGreenTeamGame, gbc_lblGreenTeamGame);
		lblGreenTeamGame.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		//--------------------------------------------Green Users Game Display------------------------------------------//
		ArrayList<String> greenCodenames = db.pullPlayer("green");

		for(int i = 0; i < greenCodenames.size(); i++) {
			lblGreenUsers[i] = new JLabel(greenCodenames.get(i));
			lblGreenUsers[i].setForeground(new Color(144, 238, 144));
			gbc_lblGreenUsers[i] = new GridBagConstraints();
			gbc_lblGreenUsers[i].insets = new Insets(0, 0, 5, 5);
			gbc_lblGreenUsers[i].gridx = 0;
			gbc_lblGreenUsers[i].gridy = i+1;
			green_team_panel.add(lblGreenUsers[i], gbc_lblGreenUsers[i]);
		}
		
		//--------------------------------------------Green Scores------------------------------------------//
		for(int i = 0; i< greenCodenames.size(); i++) {
			lblGreenScores[i] = new JLabel("00");
			lblGreenScores[i].setForeground(new Color(144, 238, 144));
			gbc_lblGreenScore[i] = new GridBagConstraints();
			gbc_lblGreenScore[i].insets = new Insets(0, 0, 5, 0);
			gbc_lblGreenScore[i].gridx = 1;
			gbc_lblGreenScore[i].gridy = i+1;
			green_team_panel.add(lblGreenScores[i], gbc_lblGreenScore[i]);
		}
		
		//--------------------------------------------Green Team Total Scores------------------------------------------//
		lblGreenTotalScore = new JLabel("00");
		lblGreenTotalScore.setForeground(new Color(144, 238, 144));
		lblGreenTotalScore.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblGreenTotalScore = new GridBagConstraints();
		gbc_lblGreenTotalScore.anchor = GridBagConstraints.SOUTH;
		gbc_lblGreenTotalScore.gridx = 1;
		gbc_lblGreenTotalScore.gridy = numPerTeam+1;
		green_team_panel.add(lblGreenTotalScore, gbc_lblGreenTotalScore);
		
		
		//--------------------------------------------Hit History------------------------------------------//
		hit_counter_display = new JTextArea();
		hit_counter_display.setEnabled(false);
		hit_counter_display.setForeground(new Color(0,0,0));
		hit_counter_display.setFont(new Font("Tahoma", Font.BOLD, 11));
		hit_counter_display.setBorder(new LineBorder(new Color(255, 215, 0), 4, true));
		hit_counter_display.setBackground(new Color(30, 144, 255));
		GridBagConstraints gbc_hit_counter_display = new GridBagConstraints();
		gbc_hit_counter_display.insets = new Insets(0, 0, 5, 0);
		gbc_hit_counter_display.fill = GridBagConstraints.BOTH;
		gbc_hit_counter_display.gridx = 0;
		gbc_hit_counter_display.gridy = 1;
		frmLasertagGame.getContentPane().add(hit_counter_display, gbc_hit_counter_display);
	}

	public void warningTimer()
	{
		// Timer Panel
		System.out.println("Warning, the Game Starts in 30 Seconds");
		JPanel warning_time_display = new JPanel();
		warning_time_display.setBorder(new LineBorder(new Color(255, 215, 0), 4, true));
		warning_time_display.setBackground(new Color(0, 0, 0));
		GridBagConstraints gbc_warning_time_display = new GridBagConstraints();
		gbc_warning_time_display.anchor = GridBagConstraints.SOUTH;
		gbc_warning_time_display.fill = GridBagConstraints.HORIZONTAL;
		gbc_warning_time_display.gridx = 0;
		gbc_warning_time_display.gridy = 2;
		frmLasertagGame.getContentPane().add(warning_time_display, gbc_warning_time_display);
		warning_time_display.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// "Time Remaining:" text label
		JLabel lblDNCTimeRemaining = new JLabel("Time Remaining:");
		lblDNCTimeRemaining.setForeground(new Color(255, 255, 255));
		lblDNCTimeRemaining.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDNCTimeRemaining.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		warning_time_display.add(lblDNCTimeRemaining);

		// Countdown timer label
		JLabel lblTimeRemaining = new JLabel(String.valueOf(minutesRemaining) + ":" + String.valueOf(secondsRemaining));
		lblTimeRemaining.setForeground(new Color(255, 255, 255));
		lblTimeRemaining.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTimeRemaining.setFont(new Font("Yu Gothic", Font.BOLD, 16));

		// Displays the timer counting down
		final Runnable runnable = new Runnable() {

            public void run() {
				if(warningTime >= 0){
					lblTimeRemaining.setText(String.valueOf(warningTime / 60) + ":" + String.valueOf(formatter.format(warningTime % 60)));
					warningTime--;
					if (warningTime == 0) {
						System.out.println("The Game has Begun!");
						View.ListenerEnable = true;

						//System.out.println(View.ListenerEnable);
						return;
					}
				}
            }
        };
		scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
		warning_time_display.add(lblTimeRemaining);
	}


	//--------------------------------------------Time Remaining Display------------------------------------------//
	public void waitTimer()
	{
			// Timer Panel
			JPanel time_display = new JPanel();
			time_display.setBorder(new LineBorder(new Color(255, 215, 0), 4, true));
			time_display.setBackground(new Color(0, 0, 0));
			GridBagConstraints gbc_time_display = new GridBagConstraints();
			gbc_time_display.anchor = GridBagConstraints.SOUTH;
			gbc_time_display.fill = GridBagConstraints.HORIZONTAL;
			gbc_time_display.gridx = 0;
			gbc_time_display.gridy = 2;
			frmLasertagGame.getContentPane().add(time_display, gbc_time_display);
			time_display.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			// "Time Remaining:" text label
			JLabel lblDNCTimeRemaining = new JLabel("Time Remaining:");
			lblDNCTimeRemaining.setForeground(new Color(255, 255, 255));
			lblDNCTimeRemaining.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDNCTimeRemaining.setFont(new Font("Yu Gothic", Font.BOLD, 16));
			time_display.add(lblDNCTimeRemaining);

			// Countdown timer label
			JLabel lblTimeRemaining = new JLabel(String.valueOf(minutesRemaining) + ":" + String.valueOf(secondsRemaining));
			lblTimeRemaining.setForeground(new Color(255, 255, 255));
			lblTimeRemaining.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTimeRemaining.setFont(new Font("Yu Gothic", Font.BOLD, 16));

			// Displays the timer counting down
			final Runnable runnable = new Runnable() {

				public void run() {
					if(warningTime < 0){
						lblTimeRemaining.setText(String.valueOf(waitTime / 60) + ":" + String.valueOf(formatter.format(waitTime % 60)));
						waitTime--;

						if (waitTime < 0) {
							System.out.println("The Game is Over!");
							View.ListenerEnable = false;
							scheduler.shutdown();
						}
					}
				}
			};
			scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
			time_display.add(lblTimeRemaining);

	}
	
}

