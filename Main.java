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
	static int temp = 1;
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
	
	public void run()
	{

	}

	public static void main(String[] args) throws SocketException, UnknownHostException, InterruptedException
	{
		Main g = new Main();
		g.db.getConnection();
		g.db.clearTable(); //Don't uncomment this, or the tables might clutter with data
		while(true){
			if(View.ListenerEnable){
				try {
					UDPSystem server = new UDPSystem(7501, 7500, g.db);
					server.listener();
		
				} catch (Exception exception) {
					System.out.println("Server exception (Main): ");
					exception.printStackTrace();
				}
			}
			else{
				Thread.sleep(1); //DO NOT DELETE
			}
		}
	}
}
