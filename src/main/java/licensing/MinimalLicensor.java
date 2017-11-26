package licensing;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class MinimalLicensor implements Licensor {

    public static final long DEFAULT_MAX_CONCURRING = Long.MAX_VALUE;
    public static final long DEFAULT_MAX_TOTAL = Long.MAX_VALUE;

    protected volatile long totalLicenses = 0;
    protected volatile long currentLicenses = 0;
    protected long maxTotalLicenses = DEFAULT_MAX_TOTAL;
    protected long maxConcurringLicenses = DEFAULT_MAX_CONCURRING;

    private final Object mutexNotForDirectUse = new Object();

    public MinimalLicensor(){}

    public MinimalLicensor(long maxTotalLicenses, long maxConcurringLicenses) {
        this.maxTotalLicenses = maxTotalLicenses;
        this.maxConcurringLicenses = maxConcurringLicenses;
    }

    protected Object mutex(){
        return this.mutexNotForDirectUse;
    }

    @Override
    public boolean tryAcquire() {
        synchronized(mutex()){
            return processAcquisition(anyAvailable());
        }
    }

    /*
    No blocking is implemented in any acquisition methods.  For blocking, use another Licenser implementation.
     */
    @Override
    public boolean tryAcquire(long timeout, TimeUnit timeUnit) {
        return tryAcquire();
    }

    @Override
    public boolean acquire() {
        return tryAcquire();
    }

    @Override
    public void acquireAsync(Consumer<Boolean> callback) {
        callback.accept(acquire());
    }

    @Override
    public void tryAcquireAsync(Consumer<Boolean> callback, long timeout, TimeUnit timeUnit) {
        callback.accept(tryAcquire(timeout, timeUnit));
    }

    // Makes it clean to separate the logic of whether to issue licenses from the logic of actually issuing them
    protected boolean processAcquisition(boolean acquired) {
        if(acquired) {
            incrementCurrentCount();
            incrementTotal();
        }
        return acquired;
    }

    protected void processReturn() {
        decrementCurrentCount();
    }

    @Override
    public void returnExpended() {
        synchronized (mutex()){
            processReturn();
        }
    }

    public long getMaxTotalLicenses() {
        return this.maxConcurringLicenses;
    }

    protected void setMaxTotalLicenses(long maxTotalLicenses) {
        synchronized(mutex()) {
            this.maxTotalLicenses = maxTotalLicenses;
        }
    }

    public long getTotalCount() {
        return totalLicenses;
    }

    protected long getTotalRemaining(){
        synchronized(mutex()){
            return getMaxTotalLicenses() - this.getTotalCount();
        }
    }

    protected long incrementTotal(){
        synchronized(mutex()){
            return ++this.totalLicenses;
        }
    }

    public long getMaxConcurringLicenses() {
        return this.maxConcurringLicenses;
    }

    protected void setMaxConcurringLicenses(long maxConcurringLicenses) {
        synchronized(mutex()){
            this.maxConcurringLicenses = maxConcurringLicenses;
        }
    }

    public long getCurrentCount() {
        return this.currentLicenses;
    }

    protected long incrementCurrentCount(){
        synchronized(mutex()){
            return ++this.currentLicenses;
        }
    }

    protected long decrementCurrentCount(){
        synchronized(mutex()){
            return --this.currentLicenses;
        }
    }

    protected long getConcurringRemaining(){
        synchronized(mutex()){
            return getMaxConcurringLicenses() - this.getCurrentCount();
        }
    }

    protected long getAvailable(){
        synchronized(mutex()){
            return Math.min(getConcurringRemaining(), getTotalRemaining());
        }
    }

    protected boolean anyAvailable(){
        synchronized(mutex()){
            return getAvailable() > 0;
        }
    }
}
