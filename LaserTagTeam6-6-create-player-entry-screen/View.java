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

	Model model;
	public boolean addNum = false;
	public int IDnum = 0;
	public int playerIndex = 0;
	public boolean deleteNum = false;
	public String lastNum = "Nothing";
	JFrame frmLasertag;
	JTextField GreenUser0;
	JTextField GreenID0;
	JTextField GreenID1;
	JTextField GreenUser1;
	JTextField GreenID2;
	JTextField GreenUser2;
	JTextField RedUser0;
	JTextField RedID0;
	public JButton btnConfirmID, btnStartGame, btnNewButton_2, btnNewButton_3, btnFinishUsername, btnNewButton_5;
	private JLabel lblGreen1;
	private JLabel lblGreen2;
	JTextField RedID1;
	JTextField RedUser1;
	JTextField RedID2;
	JTextField RedUser2;
	private JLabel lblRed1;
	private JLabel lblRed2;
	private JPanel contentPane;
	private BufferedImage splashScreen;
	


	// Create the application.
	View(Model m) {
		model = m;
		frmLasertag = new JFrame();
		frmLasertag.setIconImage(Toolkit.getDefaultToolkit().getImage(View.class.getResource("/Images/360_F_330853301_Ymj2OjSqxhYYFVXE7WiRzuRgYNUVgvy5.jpg")));
		frmLasertag.getContentPane().setBackground(new Color(0, 0, 205));
		frmLasertag.setTitle("LaserTag");
		frmLasertag.setBounds(100, 100, 600, 420);
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
		gbl_redpanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_redpanel.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_redpanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		redpanel.setLayout(gbl_redpanel);
		

		//--------------------------------------------Red Labels------------------------------------------//
		JLabel lblRed0 = new JLabel("0");
		GridBagConstraints gbc_lblRed0 = new GridBagConstraints();
		gbc_lblRed0.insets = new Insets(0, 0, 5, 5);
		gbc_lblRed0.gridx = 0;
		gbc_lblRed0.gridy = 0;
		redpanel.add(lblRed0, gbc_lblRed0);
		
		lblRed1 = new JLabel("1");
		lblRed1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblRed1 = new GridBagConstraints();
		gbc_lblRed1.insets = new Insets(0, 0, 5, 5);
		gbc_lblRed1.gridx = 0;
		gbc_lblRed1.gridy = 1;
		redpanel.add(lblRed1, gbc_lblRed1);

		lblRed2 = new JLabel("2");
		lblRed2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblRed2 = new GridBagConstraints();
		gbc_lblRed2.insets = new Insets(0, 0, 0, 5);
		gbc_lblRed2.gridx = 0;
		gbc_lblRed2.gridy = 2;
		redpanel.add(lblRed2, gbc_lblRed2);


		//-----------------------------------------Red ID Numbers------------------------------------------//
		RedID0 = new JTextField();
		GridBagConstraints gbc_RedID0 = new GridBagConstraints();
		gbc_RedID0.insets = new Insets(0, 0, 5, 5);
		gbc_RedID0.fill = GridBagConstraints.HORIZONTAL;
		gbc_RedID0.gridx = 1;
		gbc_RedID0.gridy = 0;
		redpanel.add(RedID0, gbc_RedID0);
		RedID0.setColumns(10);
		
		RedID1 = new JTextField();
		GridBagConstraints gbc_RedID1 = new GridBagConstraints();
		gbc_RedID1.insets = new Insets(0, 0, 5, 5);
		gbc_RedID1.fill = GridBagConstraints.HORIZONTAL;
		gbc_RedID1.gridx = 1;
		gbc_RedID1.gridy = 1;
		redpanel.add(RedID1, gbc_RedID1);
		RedID1.setColumns(10);

		RedID2 = new JTextField();
		GridBagConstraints gbc_RedID2 = new GridBagConstraints();
		gbc_RedID2.insets = new Insets(0, 0, 0, 5);
		gbc_RedID2.fill = GridBagConstraints.HORIZONTAL;
		gbc_RedID2.gridx = 1;
		gbc_RedID2.gridy = 2;
		redpanel.add(RedID2, gbc_RedID2);
		RedID2.setColumns(10);


		//--------------------------------------------Red Users------------------------------------------//
		RedUser0 = new JTextField();
		RedUser0.setEnabled(false);
		GridBagConstraints gbc_RedUser0 = new GridBagConstraints();
		gbc_RedUser0.insets = new Insets(0, 0, 5, 0);
		gbc_RedUser0.fill = GridBagConstraints.HORIZONTAL;
		gbc_RedUser0.gridx = 2;
		gbc_RedUser0.gridy = 0;
		redpanel.add(RedUser0, gbc_RedUser0);
		RedUser0.setColumns(10);
		
		RedUser1 = new JTextField();
		RedUser1.setEnabled(false);
		GridBagConstraints gbc_RedUser1 = new GridBagConstraints();
		gbc_RedUser1.insets = new Insets(0, 0, 5, 0);
		gbc_RedUser1.fill = GridBagConstraints.HORIZONTAL;
		gbc_RedUser1.gridx = 2;
		gbc_RedUser1.gridy = 1;
		redpanel.add(RedUser1, gbc_RedUser1);
		RedUser1.setColumns(10);
		
		RedUser2 = new JTextField();
		RedUser2.setEnabled(false);
		GridBagConstraints gbc_RedUser2 = new GridBagConstraints();
		gbc_RedUser2.fill = GridBagConstraints.HORIZONTAL;
		gbc_RedUser2.gridx = 2;
		gbc_RedUser2.gridy = 2;
		redpanel.add(RedUser2, gbc_RedUser2);
		RedUser2.setColumns(10);

		//--------------------------------------------Red Team Label------------------------------------------//
		JLabel lblRedTeam = new JLabel("Red Team");
		lblRedTeam.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblRedTeam);
		




		//--------------------------------------------Green Team------------------------------------------//
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.gridx = 2;
		gbc_scrollPane_1.gridy = 0;
		

		JPanel greenpanel = new JPanel();
		greenpanel.setBackground(new Color(144, 238, 144));
		scrollPane_1.setViewportView(greenpanel);
		GridBagLayout gbl_greenpanel = new GridBagLayout();
		gbl_greenpanel.columnWidths = new int[]{20, 0, 0, 0};
		gbl_greenpanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_greenpanel.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_greenpanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		greenpanel.setLayout(gbl_greenpanel);
		

		//--------------------------------------------Green Labels------------------------------------------//
		JLabel lblGreen0 = new JLabel("0");
		lblGreen0.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblGreen0 = new GridBagConstraints();
		gbc_lblGreen0.insets = new Insets(0, 0, 5, 5);
		gbc_lblGreen0.gridx = 0;
		gbc_lblGreen0.gridy = 0;
		greenpanel.add(lblGreen0, gbc_lblGreen0);
		
		lblGreen1 = new JLabel("1");
		GridBagConstraints gbc_lblGreen1 = new GridBagConstraints();
		gbc_lblGreen1.insets = new Insets(0, 0, 5, 5);
		gbc_lblGreen1.gridx = 0;
		gbc_lblGreen1.gridy = 1;
		greenpanel.add(lblGreen1, gbc_lblGreen1);

		lblGreen2 = new JLabel("2");
		GridBagConstraints gbc_lblGreen2 = new GridBagConstraints();
		gbc_lblGreen2.insets = new Insets(0, 0, 0, 5);
		gbc_lblGreen2.gridx = 0;
		gbc_lblGreen2.gridy = 2;
		greenpanel.add(lblGreen2, gbc_lblGreen2);


		//--------------------------------------------Red ID Numbers------------------------------------------//
		GreenID0 = new JTextField();
		GridBagConstraints gbc_GreenID0 = new GridBagConstraints();
		gbc_GreenID0.insets = new Insets(0, 0, 5, 5);
		gbc_GreenID0.fill = GridBagConstraints.HORIZONTAL;
		gbc_GreenID0.gridx = 1;
		gbc_GreenID0.gridy = 0;
		greenpanel.add(GreenID0, gbc_GreenID0);
		GreenID0.setColumns(10);
		
		GreenID1 = new JTextField();
		GridBagConstraints gbc_GreenID1 = new GridBagConstraints();
		gbc_GreenID1.insets = new Insets(0, 0, 5, 5);
		gbc_GreenID1.fill = GridBagConstraints.HORIZONTAL;
		gbc_GreenID1.gridx = 1;
		gbc_GreenID1.gridy = 1;
		greenpanel.add(GreenID1, gbc_GreenID1);
		GreenID1.setColumns(10);
		
		GreenID2 = new JTextField();
		GridBagConstraints gbc_GreenID2 = new GridBagConstraints();
		gbc_GreenID2.insets = new Insets(0, 0, 0, 5);
		gbc_GreenID2.fill = GridBagConstraints.HORIZONTAL;
		gbc_GreenID2.gridx = 1;
		gbc_GreenID2.gridy = 2;
		greenpanel.add(GreenID2, gbc_GreenID2);
		GreenID2.setColumns(10);


		//--------------------------------------------Red Users------------------------------------------//
		GreenUser0 = new JTextField();
		GreenUser0.setEnabled(false); 
		GridBagConstraints gbc_GreenUser0 = new GridBagConstraints();
		gbc_GreenUser0.insets = new Insets(0, 0, 5, 0);
		gbc_GreenUser0.fill = GridBagConstraints.HORIZONTAL;
		gbc_GreenUser0.gridx = 2;
		gbc_GreenUser0.gridy = 0;
		greenpanel.add(GreenUser0, gbc_GreenUser0);
		GreenUser0.setColumns(10);
		
		GreenUser1 = new JTextField();
		GreenUser1.setEnabled(false);
		GridBagConstraints gbc_GreenUser1 = new GridBagConstraints();
		gbc_GreenUser1.insets = new Insets(0, 0, 5, 0);
		gbc_GreenUser1.fill = GridBagConstraints.HORIZONTAL;
		gbc_GreenUser1.gridx = 2;
		gbc_GreenUser1.gridy = 1;
		greenpanel.add(GreenUser1, gbc_GreenUser1);
		GreenUser1.setColumns(10);
		
		GreenUser2 = new JTextField();
		GreenUser2.setEnabled(false);
		GridBagConstraints gbc_GreenUser2 = new GridBagConstraints();
		gbc_GreenUser2.fill = GridBagConstraints.HORIZONTAL;
		gbc_GreenUser2.gridx = 2;
		gbc_GreenUser2.gridy = 2;
		greenpanel.add(GreenUser2, gbc_GreenUser2);
		GreenUser2.setColumns(10);
		
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
		
		btnNewButton_2 = new JButton("Placeholder");
		btnNewButton_2.setEnabled(false);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 2;
		gbc_btnNewButton_2.gridy = 1;
		
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
		
		btnNewButton_3 = new JButton("Placeholder");
		btnNewButton_3.setEnabled(false);
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_3.gridx = 2;
		gbc_btnNewButton_3.gridy = 2;
		
		
		
		
		frmLasertag.getContentPane().add(scrollPane, gbc_scrollPane);
		frmLasertag.getContentPane().add(scrollPane_1, gbc_scrollPane_1);
		frmLasertag.getContentPane().add(btnConfirmID, gbc_btnConfirmID);
		frmLasertag.getContentPane().add(btnFinishUsername, gbc_btnFinishUsername);
		frmLasertag.getContentPane().add(btnNewButton_2, gbc_btnNewButton_2);
		frmLasertag.getContentPane().add(btnNewButton_5, gbc_btnNewButton_5);
		frmLasertag.getContentPane().add(btnStartGame, gbc_btnStartGame);
		frmLasertag.getContentPane().add(btnNewButton_3, gbc_btnNewButton_3);
	}

}
