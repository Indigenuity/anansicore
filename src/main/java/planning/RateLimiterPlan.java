package planning;

import com.google.common.util.concurrent.RateLimiter;

import java.util.function.Supplier;

public class RateLimiterPlan extends Plan {

    public static final double DEFAULT_PERMITS_PER_SECOND = Double.MAX_VALUE;

    public static final Supplier<RateLimiter> DEFAULT_RATE_LIMITER_SUPPLIER = () -> {
            return RateLimiter.create(DEFAULT_PERMITS_PER_SECOND);
    };

    private Supplier<RateLimiter> supplier;

    public RateLimiterPlan() {}

    public RateLimiterPlan(Supplier<RateLimiter> supplier) {
        this.supplier = supplier;
    }

    public Supplier<RateLimiter> getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier<RateLimiter> supplier) {
        this.supplier = supplier;
    }
}
