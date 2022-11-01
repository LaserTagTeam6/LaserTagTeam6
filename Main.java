/*
Name: Team 6
Project: Laser Tag
Date: 09/30/2022
*/

import javax.swing.JFrame;
import java.awt.Toolkit;

public class Main extends JFrame
{
	Model model;
	View view;
	Controller controller;
	DBConnector db;
	
	public Main()
	{
		db = new DBConnector();
		model = new Model();
		view = new View(model);
		controller = new Controller(model, view, db);
		view.showCharacterScreen(view);
		view.showGameScreen(view);
		view.addMouseListener(controller);
		view.addKeyListener(controller);
	}
	
	public void run()
	{
		while(true)
		{
			controller.update();
			model.update();
			//view.repaint();
			Toolkit.getDefaultToolkit().sync();
			try{
				Thread.sleep(1);
			} catch(Exception e){
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	public static void main(String[] args)
	{
		DBConnector d = new DBConnector();
		Main g = new Main();
		d.getConnection();
		//d.clearTable(); //Uncomment this line to delete all the rows in Table
		g.run();
	}
}
