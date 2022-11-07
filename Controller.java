import java.awt.event.KeyEvent;
import java.awt.KeyEventDispatcher;

class Controller implements KeyEventDispatcher
{
	View view;
	Model model;
	DBConnector db;
	
	Controller(Model m, View v, DBConnector d){
		model = m;
		view = v;
		db = d;

		// Button that confirms the ID is from the Database
		view.btnConfirmID.addActionListener(e ->
		{
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
			view.initializeGameScreen();
			view.frmLasertagCharacter.setVisible(false);
			view.showGameScreen(view);
			view.warningTimer();
			view.waitTimer();
			view.btnStartGame.setEnabled(false);
		});
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		switch(e.getKeyCode())
				{	
					//Starts the Game
					case KeyEvent.VK_F5:
						// Future code here
						if(view.btnStartGame.isEnabled())
						{
							view.initializeGameScreen();
							view.frmLasertagCharacter.setVisible(false);
							view.showGameScreen(view);
							if(view.warningTime >= 0)
								view.warningTimer();
							else
								view.waitTimer();
							view.btnStartGame.setEnabled(false);
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
		return false;
	}
	void update()
	{ }
}
