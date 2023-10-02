package job;

import java.util.Timer;
import java.util.TimerTask;

public abstract class CronJob {
    abstract public CronJobConfig config();
    abstract public void execute();

    public void start() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                execute();
            }
        };

        timer.schedule(task, config().getDelay(), config().getPeriod());
    }
}
