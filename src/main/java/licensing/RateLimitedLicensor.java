package licensing;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

public class RateLimitedLicensor extends MinimalLicensor {

    protected final RateLimiter rateLimiter;

    public RateLimitedLicensor(RateLimiter rateLimiter){
        this.rateLimiter = rateLimiter;
    }

    public RateLimitedLicensor(RateLimiter rateLimiter, long maxTotalLicenses, long maxConcurringLicenses){
        super(maxTotalLicenses, maxConcurringLicenses);
        this.rateLimiter = rateLimiter;
    }

    @Override
    public boolean tryAcquire() {
        synchronized(mutex()){
            return processAcquisition(this.anyAvailable() && rateLimiter.tryAcquire());
        }
    }

    @Override
    public boolean tryAcquire(long timeout, TimeUnit timeUnit) {
        synchronized(mutex()){
            return processAcquisition(this.anyAvailable() && rateLimiter.tryAcquire(timeout, timeUnit));
        }
    }

    @Override
    public boolean acquire() {
        synchronized(mutex()){
            boolean acquired = false;
            if(this.anyAvailable()){
                rateLimiter.acquire();
                acquired = true;
            }
            return processAcquisition(acquired);
        }
    }

}
