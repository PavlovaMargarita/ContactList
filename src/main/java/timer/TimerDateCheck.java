package timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Margarita on 06.08.2014.
 */
public class TimerDateCheck {
    private static TimerDateCheck ourInstance = new TimerDateCheck();

    public static TimerDateCheck getInstance() {
        return ourInstance;
    }

    private TimerDateCheck() {
        TimerTask tasknew = new MyTimer();
        Timer timer = new Timer();


        // scheduling the task at interval
        timer.schedule(tasknew,50, 24*60*60 * 1000);
    }
}
