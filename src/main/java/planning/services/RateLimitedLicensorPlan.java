package planning.services;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

public class RateLimitedLicensorPlan extends MinimalLicensorPlan {

    public static final double DEFAULT_PERMITS_PER_SECOND = Double.MAX_VALUE;
    public static final long DEFAULT_WARMUP_PERIOD = 0;
    public static final TimeUnit DEFAULT_TIME_PERIOD = TimeUnit.SECONDS;

    private double permitsPerSecond = DEFAULT_PERMITS_PER_SECOND;
    private long warmupPeriod = DEFAULT_WARMUP_PERIOD;
    private TimeUnit timeUnit = DEFAULT_TIME_PERIOD;

    public RateLimitedLicensorPlan() {
    }

    public RateLimitedLicensorPlan(String name) {
        super(name);
    }

}
