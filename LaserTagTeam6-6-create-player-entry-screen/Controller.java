/*
Name: Team 6
Project: Laser Tag
Date: 09/30/2022
*/
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	View view;
	Model model;
	
	
	Controller(Model m, View v){
		model = m;
		view = v;

		// Button that confirms the ID is from the Database
		view.btnConfirmID.addActionListener(e ->
		{
		    // your code here
			System.out.println("database player ID query");
			view.btnFinishUsername.setEnabled(true);
		});

		// Button that updates the Database with usernames
		view.btnFinishUsername.addActionListener(e ->
		{
		    // your code here
			System.out.println("database add new usernames and IDs");
			view.btnStartGame.setEnabled(true);
		});

		// Button that starts the game
		view.btnStartGame.addActionListener(e ->
		{
		    // your code here
			System.out.println("game start");
			//view.frmLasertag.dispose();
			System.exit(0);
		});

		// Looks for the user keyboard inputs
		view.frmLasertag.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) { }
		
			@Override
			public void keyReleased(KeyEvent e){
				switch(e.getKeyCode())
				{	
					case KeyEvent.VK_NUMPAD0:
						view.IDnum = 0;
						view.addNum = true;
						break;
					
					case KeyEvent.VK_NUMPAD1:
						view.IDnum = 1;
						view.addNum = true;
						break;
					
					case KeyEvent.VK_NUMPAD2:
						view.IDnum = 2;
						view.addNum = true;
						break;

					case KeyEvent.VK_NUMPAD3:
						view.IDnum = 3;
						view.addNum = true;
						break;

					case KeyEvent.VK_NUMPAD4:
						view.IDnum = 4;
						view.addNum = true;
						break;

					case KeyEvent.VK_NUMPAD5:
						view.IDnum = 5;
						view.addNum = true;
						break;
						
					case KeyEvent.VK_NUMPAD6:
						view.IDnum = 6;
						view.addNum = true;
						break;

					case KeyEvent.VK_NUMPAD7:
						view.IDnum = 7;
						view.addNum = true;
						break;

					case KeyEvent.VK_NUMPAD8:
						view.IDnum = 8;
						view.addNum = true;
						break;

					case KeyEvent.VK_NUMPAD9:
						view.IDnum = 9;
						view.addNum = true;
						break;
					
					// Goes to the next ID number
					case KeyEvent.VK_ENTER:
						view.playerIndex++;
						break;

					// Removes the last number in ID number
					case KeyEvent.VK_BACK_SPACE:
						view.deleteNum = true;
						break;

					//Starts the Game
					case KeyEvent.VK_F5:
						// Future code here
						System.out.println("game start");
						System.exit(0);
						break;
					
					//Exits the Game
					case KeyEvent.VK_ESCAPE: 	
						System.out.println("Exiting program...");
						System.exit(0);
						break;
				}
			}
			
			@Override
			public void keyTyped(KeyEvent e) { }
				
		});

	}

	public void actionPerformed(ActionEvent e) { }

	public void mousePressed(MouseEvent e) { }
	
	public void mouseReleased(MouseEvent e) { }
	
	public void mouseEntered(MouseEvent e) { }
	
	public void mouseExited(MouseEvent e) { }
	
	public void mouseClicked(MouseEvent e) { }
	
	public void keyPressed(KeyEvent e) { }

	public void keyReleased(KeyEvent e) { }

	public void keyTyped(KeyEvent e) { }

	void update()
	{
		if(view.addNum)
		{
			if(view.playerIndex >= 6)
				view.playerIndex = 0;
			
			switch(view.playerIndex)
			{
				// Adds text to Red0
				case 0:
					if(view.RedID0.getText().length() < 10)
					{
						view.RedID0.setText(view.RedID0.getText()+view.IDnum);
					}
					break;
				
				// Adds text to Red1
				case 1:
					if(view.RedID1.getText().length() < 10)
					{
						view.RedID1.setText(view.RedID1.getText()+view.IDnum);
					}
					break;

				// Adds text to Red2
				case 2:
					if(view.RedID2.getText().length() < 10)
					{
						view.RedID2.setText(view.RedID2.getText()+view.IDnum);
					}
					break;

				// Adds text to Green0
				case 3:
					if(view.GreenID0.getText().length() < 10)
					{
						view.GreenID0.setText(view.GreenID0.getText()+view.IDnum);
					}
					break;

				// Adds text to Green1
				case 4:
					if(view.GreenID1.getText().length() < 10)
					{
						view.GreenID1.setText(view.GreenID1.getText()+view.IDnum);
					}
					break;

				// Adds text to Green2
				case 5:
					if(view.GreenID2.getText().length() < 10)
					{
						view.GreenID2.setText(view.GreenID2.getText()+view.IDnum);
					}
					break;
				
			}

			view.addNum = false;
		}

		if(view.deleteNum)
		{
			if(view.playerIndex >= 6)
				view.playerIndex = 0;
			
			switch(view.playerIndex)
			{
				// Deletes text to Red0
				case 0:
					if(view.RedID0.getText().length() != 0)
					{
						view.RedID0.setText(view.RedID0.getText().substring(0, view.RedID0.getText().length() - 1));
					}
					break;

				// Deletes text to Red1
				case 1:
					if(view.RedID1.getText().length() != 0)
					{
						view.RedID1.setText(view.RedID1.getText().substring(0, view.RedID1.getText().length() - 1));
					}
					break;

				// Deletes text to Red2
				case 2:
					if(view.RedID2.getText().length() != 0)
					{
						view.RedID2.setText(view.RedID2.getText().substring(0, view.RedID2.getText().length() - 1));
					}
					break;

				// Adds text to Green0
				case 3:
					if(view.GreenID0.getText().length() != 0)
					{
						view.GreenID0.setText(view.GreenID0.getText().substring(0, view.GreenID0.getText().length() - 1));
					}
					break;

				// Adds text to Green1
				case 4:
					if(view.GreenID1.getText().length() != 0)
					{
						view.GreenID1.setText(view.GreenID1.getText().substring(0, view.GreenID1.getText().length() - 1));
					}
					break;	

				// Adds text to Green2
				case 5:
					if(view.GreenID2.getText().length() != 0)
					{
						view.GreenID2.setText(view.GreenID2.getText().substring(0, view.GreenID2.getText().length() - 1));
					}
					break;
			}

			view.deleteNum = false;
		}
	}
	
}
