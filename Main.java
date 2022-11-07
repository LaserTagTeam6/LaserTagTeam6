import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.KeyboardFocusManager;

public class Main extends JFrame
{
	Model model;
	View view;
	Controller controller;
	DBConnector db;
	
	public Main()
	{
		model = new Model();
		db = new DBConnector();
		view = new View(model, db);
		controller = new Controller(model, view, db);
		view.showCharacterScreen(view);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(controller);
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
		Main g = new Main();
		g.db.getConnection();
		g.db.clearTable(); //Don't uncomment this, or the tables might clutter with data
		g.run();
	}
}
