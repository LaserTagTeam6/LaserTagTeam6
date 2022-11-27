import javax.swing.JFrame;
import java.awt.Toolkit;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.awt.KeyboardFocusManager;

public class Main extends JFrame
{
	Model model;
	View view;
	Controller controller;
	DBConnector db;
	//UDPSystem server;
	
	public Main() throws SocketException, UnknownHostException
	{
		model = new Model();
		db = new DBConnector();
		view = new View(model, db);
		controller = new Controller(model, view, db);
		//server = new UDPSystem(7501, 7500, db);
		view.showCharacterScreen(view);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(controller);
	}
	
	//Do we even need this function???
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
			//System.out.println("This is running");
		}
	}

	public static void main(String[] args) throws SocketException, UnknownHostException
	{
		Main g = new Main();
		g.db.getConnection();
		g.db.clearTable(); //Don't uncomment this, or the tables might clutter with data
		try {
			UDPSystem server = new UDPSystem(7501, 7500, g.db);
			server.listener();

        } catch (Exception exception) {
            System.out.println("Server exception:");
            exception.printStackTrace();
		}
		System.out.println("We made it here2");
		g.run();
	}
}
