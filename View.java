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



class View extends JPanel{

	protected int numPerTeam = 15;
	
	
	Model model;
	public boolean addNum = false;
	public int IDnum = 0;
	public int playerIndex = 0;
	public boolean deleteNum = false;
	public String lastNum = "Nothing";
	JFrame frmLasertag;
	
	//green team data
	private JLabel[] GreenLabels = new JLabel[numPerTeam];
	JTextField[] GreenUsers = new JTextField[numPerTeam];
	JTextField[] GreenIDs = new JTextField[numPerTeam];
	private GridBagConstraints[] gbc_lblGreen = new GridBagConstraints[numPerTeam];
	private GridBagConstraints[] gbc_GreenID = new GridBagConstraints[numPerTeam];
	private GridBagConstraints[] gbc_GreenUser = new GridBagConstraints[numPerTeam];
	//red team data
	private JLabel[] RedLabels = new JLabel[numPerTeam];
	JTextField[] RedUsers = new JTextField[numPerTeam];
	JTextField[] RedIDs = new JTextField[numPerTeam];
	private GridBagConstraints[] gbc_lblRed = new GridBagConstraints[numPerTeam];
	private GridBagConstraints[] gbc_RedID = new GridBagConstraints[numPerTeam];
	private GridBagConstraints[] gbc_RedUser = new GridBagConstraints[numPerTeam];
	
	public JButton btnConfirmID, btnStartGame, btnMoveRight, btnMoveLeft, btnFinishUsername, btnNewButton_5;
	private JPanel contentPane;
	private BufferedImage splashScreen;
	


	// Create the application.
	View(Model m) {
		model = m;
		frmLasertag = new JFrame();
		frmLasertag.setIconImage(Toolkit.getDefaultToolkit().getImage(View.class.getResource("/Images/360_F_330853301_Ymj2OjSqxhYYFVXE7WiRzuRgYNUVgvy5.jpg")));
		frmLasertag.getContentPane().setBackground(new Color(0, 0, 205));
		frmLasertag.setTitle("LaserTag");
		frmLasertag.setBounds(100, 100, 600, 562);
		frmLasertag.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLasertag.setVisible(true);
		initialize();
		
	}
	
	public void show(View window)
	{
		window.frmLasertag.setVisible(true);
	}

	
	 //----------------------------Initialize the contents of the frame---------------------------------------//
	
	
	private void initialize() {
		
		//-------------------------------------------Splash Screen-------------------------------------------//
		JPanel panelsplash = new JPanel();
		panelsplash.setForeground(new Color(0, 0, 205));
		panelsplash.setBackground(new Color(0, 0, 205));
		frmLasertag.getContentPane().add(panelsplash, BorderLayout.SOUTH);
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
		panelsplash_1.setBackground(new Color(0, 0, 205));
		GridBagConstraints gbc_panelsplash_1 = new GridBagConstraints();
		gbc_panelsplash_1.anchor = GridBagConstraints.SOUTH;
		gbc_panelsplash_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelsplash_1.gridx = 1;
		gbc_panelsplash_1.gridy = 1;
		panelsplash.add(panelsplash_1, gbc_panelsplash_1);

		try
		{
			this.splashScreen = ImageIO.read(new File("NewSplashScreen.jpg"));

		}
		catch(Exception e)
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}
		ImageIcon icon = new ImageIcon(this.splashScreen);
		JLabel lblNewLabelSplash = new JLabel();
		lblNewLabelSplash.setIcon(icon);
		frmLasertag.add(lblNewLabelSplash);
		frmLasertag.setVisible(true);
		lblNewLabelSplash.setHorizontalAlignment(SwingConstants.CENTER);
		frmLasertag.getContentPane().add(lblNewLabelSplash, BorderLayout.CENTER);

		//----------------------------------------Removes Splash Screen-------------------------------------------//
		for(int i = 0; i < 100; i++){
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
			}
			progressBar.setValue(i);
		}

		frmLasertag.getContentPane().remove(lblNewLabelSplash);
		frmLasertag.getContentPane().remove(progressBar);
		frmLasertag.getContentPane().remove(panelsplash);
		frmLasertag.getContentPane().remove(panelsplash_1);
		frmLasertag.repaint();


		//-------------------------------------------Red Team------------------------------------------------//
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{100, 100, 100, 100, 0};
		gridBagLayout.rowHeights = new int[]{193, 23, 23, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		frmLasertag.getContentPane().setLayout(gridBagLayout);
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		
		
		JPanel redpanel = new JPanel();
		redpanel.setBackground(new Color(240, 128, 128));
		scrollPane.setViewportView(redpanel);
		GridBagLayout gbl_redpanel = new GridBagLayout();
		gbl_redpanel.columnWidths = new int[]{20, 0, 0, 0};
		gbl_redpanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_redpanel.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_redpanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		redpanel.setLayout(gbl_redpanel);
		

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
			RedUsers[i].setEnabled(false);
			gbc_RedUser[i] = new GridBagConstraints();
			gbc_RedUser[i].insets = new Insets(0, 0, 5, 0);
			gbc_RedUser[i].fill = GridBagConstraints.HORIZONTAL;
			gbc_RedUser[i].gridx = 2;
			gbc_RedUser[i].gridy = i;
			redpanel.add(RedUsers[i], gbc_RedUser[i]);
			RedUsers[0].setColumns(10);
		}
		
		
		//--------------------------------------------Red Team Label------------------------------------------//
		JLabel lblRedTeam = new JLabel("Red Team");
		lblRedTeam.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblRedTeam);
		
		
		//--------------------------------------------Green Team------------------------------------------//
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.gridx = 2;
		gbc_scrollPane_1.gridy = 0;
		
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
			GreenUsers[i].setEnabled(false); 
			gbc_GreenUser[i] = new GridBagConstraints();
			gbc_GreenUser[i].insets = new Insets(0, 0, 5, 0);
			gbc_GreenUser[i].fill = GridBagConstraints.HORIZONTAL;
			gbc_GreenUser[i].gridx = 2;
			gbc_GreenUser[i].gridy = i;
			greenpanel.add(GreenUsers[i], gbc_GreenUser[i]);
			GreenUsers[i].setColumns(10);
		}
		
		
		//--------------------------------------------Green Team Label------------------------------------------//
		JLabel lblGreenTeam = new JLabel("Green Team");
		lblGreenTeam.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_1.setColumnHeaderView(lblGreenTeam);


		//--------------------------------------------Buttons------------------------------------------//
		btnConfirmID = new JButton("Finish ID Entry");
		GridBagConstraints gbc_btnConfirmID = new GridBagConstraints();
		gbc_btnConfirmID.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnConfirmID.insets = new Insets(0, 0, 5, 5);
		gbc_btnConfirmID.gridx = 0;
		gbc_btnConfirmID.gridy = 1;
		
		btnFinishUsername = new JButton("Finish Username");
		btnFinishUsername.setEnabled(false);
		GridBagConstraints gbc_btnFinishUsername = new GridBagConstraints();
		gbc_btnFinishUsername.anchor = GridBagConstraints.EAST;
		gbc_btnFinishUsername.fill = GridBagConstraints.VERTICAL;
		gbc_btnFinishUsername.insets = new Insets(0, 0, 5, 5);
		gbc_btnFinishUsername.gridx = 1;
		gbc_btnFinishUsername.gridy = 1;
		
		btnMoveRight = new JButton("----->");
		btnMoveRight.setEnabled(false);
		GridBagConstraints gbc_btnMoveRight = new GridBagConstraints();
		gbc_btnMoveRight.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnMoveRight.insets = new Insets(0, 0, 5, 5);
		gbc_btnMoveRight.gridx = 2;
		gbc_btnMoveRight.gridy = 1;
		
		btnNewButton_5 = new JButton("Placeholder");
		btnNewButton_5.setEnabled(false);
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_5.gridx = 3;
		gbc_btnNewButton_5.gridy = 1;
		
		btnStartGame = new JButton("Start Game");
		btnStartGame.setEnabled(false);
		GridBagConstraints gbc_btnStartGame = new GridBagConstraints();
		gbc_btnStartGame.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnStartGame.insets = new Insets(0, 0, 0, 5);
		gbc_btnStartGame.gridx = 0;
		gbc_btnStartGame.gridy = 2;
		
		btnMoveLeft = new JButton("<-----");
		btnMoveLeft.setEnabled(false);
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_3.gridx = 2;
		gbc_btnNewButton_3.gridy = 2;
		
		
		
		
		frmLasertag.getContentPane().add(scrollPane, gbc_scrollPane);
		frmLasertag.getContentPane().add(scrollPane_1, gbc_scrollPane_1);
		frmLasertag.getContentPane().add(btnConfirmID, gbc_btnConfirmID);
		frmLasertag.getContentPane().add(btnFinishUsername, gbc_btnFinishUsername);
		frmLasertag.getContentPane().add(btnMoveRight, gbc_btnMoveRight);
		frmLasertag.getContentPane().add(btnNewButton_5, gbc_btnNewButton_5);
		frmLasertag.getContentPane().add(btnStartGame, gbc_btnStartGame);
		frmLasertag.getContentPane().add(btnMoveLeft, gbc_btnNewButton_3);
	}

}
