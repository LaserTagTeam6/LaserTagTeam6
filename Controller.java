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
import java.util.Timer;
import java.util.TimerTask;

class Controller implements ActionListener, MouseListener, KeyListener
{
	View view;
	Model model;
	DBConnector db;
	
	Controller(Model m, View v, DBConnector d){
		model = m;
		view = v;
		db =  d;

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
		d.getConnection();
		for(int i=0; i < view.numPerTeam; i++)
			{
			if(!view.GreenIDs[i].getText().isEmpty() && !view.GreenUsers[i].getText().isEmpty())
			{
				d.createPlayer(i, view.GreenIDs[i].getText(), null, view.GreenUsers[i].getText(), "green"); //Do not delete, may reuse this in the future
				//d.createPlayer(Integer.parseInt(view.GreenIDs[i].getText()), null, null, view.GreenUsers[i].getText(), "green");
				System.out.println("Player creation info has pushed to DB: " + i);
				view.btnStartGame.setEnabled(true);
			}
			else if(view.GreenIDs[i].getText().isEmpty() && view.GreenUsers[i].getText().isEmpty())
			{
				//System.out.println("Pass");
			}
			else
			{
				System.out.println("Missing or Invalid input on Green Textbox " + i);
				view.btnStartGame.setEnabled(false);
			}
			if(!view.RedIDs[i].getText().isEmpty() && !view.RedUsers[i].getText().isEmpty())
			{
				d.createPlayer(i, view.RedIDs[i].getText(), null, view.RedUsers[i].getText(), "red"); //Do not delete, may reuse this in the future
				//d.createPlayer(Integer.parseInt(view.RedIDs[i].getText()), null, null, view.RedUsers[i].getText(), "red");
				System.out.println("Player creation info pushed to DB on row: " + i);
				view.btnStartGame.setEnabled(true);
			}
			else if(view.RedIDs[i].getText().isEmpty() && view.RedUsers[i].getText().isEmpty())
			{
				//System.out.println("Pass");
			}
			else
			{
				System.out.println("Missing or Invalid input on Red Textbox " + i);
				view.btnStartGame.setEnabled(false);
			}
			}
		});

		// Button that starts the game
		view.btnStartGame.addActionListener(e ->
		{
		    // your code here
			System.out.println("game start");
			//view.frmLasertag.dispose();
			//System.exit(0);
			view.initializeGameScreen();
			view.showGameScreen(v);
			model.warningTimer();
			view.frmLasertagCharacter.setVisible(false);
		});

		// Looks for the user keyboard inputs
		view.frmLasertagCharacter.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) { }
		
			@Override
			public void keyReleased(KeyEvent e){
				switch(e.getKeyCode())
				{	
					// case KeyEvent.VK_NUMPAD0:
					// 	view.IDnum = 0;
					// 	view.addNum = true;
					// 	break;
					
					// case KeyEvent.VK_NUMPAD1:
					// 	view.IDnum = 1;
					// 	view.addNum = true;
					// 	break;
					
					// case KeyEvent.VK_NUMPAD2:
					// 	view.IDnum = 2;
					// 	view.addNum = true;
					// 	break;

					// case KeyEvent.VK_NUMPAD3:
					// 	view.IDnum = 3;
					// 	view.addNum = true;
					// 	break;

					// case KeyEvent.VK_NUMPAD4:
					// 	view.IDnum = 4;
					// 	view.addNum = true;
					// 	break;

					// case KeyEvent.VK_NUMPAD5:
					// 	view.IDnum = 5;
					// 	view.addNum = true;
					// 	break;
						
					// case KeyEvent.VK_NUMPAD6:
					// 	view.IDnum = 6;
					// 	view.addNum = true;
					// 	break;

					// case KeyEvent.VK_NUMPAD7:
					// 	view.IDnum = 7;
					// 	view.addNum = true;
					// 	break;

					// case KeyEvent.VK_NUMPAD8:
					// 	view.IDnum = 8;
					// 	view.addNum = true;
					// 	break;

					// case KeyEvent.VK_NUMPAD9:
					// 	view.IDnum = 9;
					// 	view.addNum = true;
					// 	break;
					
					// // Goes to the next ID number
					// case KeyEvent.VK_ENTER:
					// 	view.playerIndex++;
					// 	break;

					// //Removes the last number in ID number
					// case KeyEvent.VK_BACK_SPACE:
					// 	view.deleteNum = true;
					// 	break;

					//Starts the Game
					case KeyEvent.VK_F5:
						// Future code here
						if(view.btnStartGame.isEnabled())
						{
							view.initializeGameScreen();
							view.showGameScreen(v);
							model.warningTimer();
							view.frmLasertagCharacter.setVisible(false);
						}
						else{
							System.out.println("You haven't entered any data yet");
						}
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
		// if(view.addNum)
		// {
		// 	if(view.playerIndex >= 6)
		// 		view.playerIndex = 0;
			
		// 	switch(view.playerIndex)
		// 	{
		// 		// Adds text to Red0
		// 		case 0:
		// 			if(view.RedIDs[0].getText().length() < 10)
		// 			{
		// 				view.RedIDs[0].setText(view.RedIDs[0].getText()+view.IDnum);
		// 			}
		// 			break;
				
		// 		// Adds text to Red1
		// 		case 1:
		// 			if(view.RedIDs[1].getText().length() < 10)
		// 			{
		// 				view.RedIDs[1].setText(view.RedIDs[1].getText()+view.IDnum);
		// 			}
		// 			break;

		// 		// Adds text to Red2
		// 		case 2:
		// 			if(view.RedIDs[2].getText().length() < 10)
		// 			{
		// 				view.RedIDs[2].setText(view.RedIDs[2].getText()+view.IDnum);
		// 			}
		// 			break;

		// 		// Adds text to Green0
		// 		case 3:
		// 			if(view.GreenIDs[0].getText().length() < 10)
		// 			{
		// 				view.GreenIDs[0].setText(view.GreenIDs[0].getText()+view.IDnum);
		// 			}
		// 			break;

		// 		// Adds text to Green1
		// 		case 4:
		// 			if(view.GreenIDs[1].getText().length() < 10)
		// 			{
		// 				view.GreenIDs[1].setText(view.GreenIDs[1].getText()+view.IDnum);
		// 			}
		// 			break;

		// 		// Adds text to Green2
		// 		case 5:
		// 			if(view.GreenIDs[2].getText().length() < 10)
		// 			{
		// 				view.GreenIDs[2].setText(view.GreenIDs[2].getText()+view.IDnum);
		// 			}
		// 			break;
				
		// 	}

		// 	view.addNum = false;
		// }

		// if(view.deleteNum)
		// {
		// 	if(view.playerIndex >= 6)
		// 		view.playerIndex = 0;
			
		// 	switch(view.playerIndex)
		// 	{
		// 		// Deletes text to Red0
		// 		case 0:
		// 			if(view.RedIDs[0].getText().length() != 0)
		// 			{
		// 				view.RedIDs[0].setText(view.RedIDs[0].getText().substring(0, view.RedIDs[0].getText().length() - 1));
		// 			}
		// 			break;

		// 		// Deletes text to Red1
		// 		case 1:
		// 			if(view.RedIDs[1].getText().length() != 0)
		// 			{
		// 				view.RedIDs[1].setText(view.RedIDs[1].getText().substring(0, view.RedIDs[1].getText().length() - 1));
		// 			}
		// 			break;

		// 		// Deletes text to Red2
		// 		case 2:
		// 			if(view.RedIDs[2].getText().length() != 0)
		// 			{
		// 				view.RedIDs[2].setText(view.RedIDs[2].getText().substring(0, view.RedIDs[2].getText().length() - 1));
		// 			}
		// 			break;

		// 		// Adds text to Green0
		// 		case 3:
		// 			if(view.GreenIDs[0].getText().length() != 0)
		// 			{
		// 				view.GreenIDs[0].setText(view.GreenIDs[0].getText().substring(0, view.GreenIDs[0].getText().length() - 1));
		// 			}
		// 			break;

		// 		// Adds text to Green1
		// 		case 4:
		// 			if(view.GreenIDs[1].getText().length() != 0)
		// 			{
		// 				view.GreenIDs[1].setText(view.GreenIDs[1].getText().substring(0, view.GreenIDs[1].getText().length() - 1));
		// 			}
		// 			break;	

		// 		// Adds text to Green2
		// 		case 5:
		// 			if(view.GreenIDs[2].getText().length() != 0)
		// 			{
		// 				view.GreenIDs[2].setText(view.GreenIDs[2].getText().substring(0, view.GreenIDs[2].getText().length() - 1));
		// 			}
		// 			break;
		// 	}

		// 	view.deleteNum = false;
		// }
	}
	
}
