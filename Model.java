import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.SECONDS;


class Model
{
	View view;
	final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	int countdownStarter = 35;

	Model()
	{
		System.out.println("Welcome to Laser Tag");
	}
  
	public void update() { }
}
