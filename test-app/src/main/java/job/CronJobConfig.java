package job;

public class CronJobConfig {
    private final Long delay;
    private final Long period;

    public CronJobConfig(Long delay, Long period) {
        this.delay = delay;
        this.period = period;
    }

    public Long getDelay() {
        return delay;
    }

    public Long getPeriod() {
        return period;
    }
}
