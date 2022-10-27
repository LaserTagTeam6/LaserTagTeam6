/*
Name: Team 6
Project: Laser Tag
Date: 09/30/2022
*/
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.SECONDS;

class Model
{
	View view;
	final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	int countdownStarter = 30;

	Model(){
		System.out.println("The Game has begun");
	}

	public void warningTimer(){
		final Runnable runnable = new Runnable() {
            int countdownStarter = 30;

            public void run() {
                System.out.println(countdownStarter);
                countdownStarter--;

                if (countdownStarter < 0) {
                    System.out.println("Timer Over!");
                    scheduler.shutdown();
                }
            }
        };
		scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }

	public void update(){
	}
}
