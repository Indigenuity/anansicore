package planning.services;

import context.ServiceContext;
import planning.Plan;
import licensing.MinimalLicensor;

public class MinimalLicensorPlan extends Plan {

    private long maxTotalLicenses = MinimalLicensor.DEFAULT_MAX_TOTAL;
    private long maxConcurringLicenses = MinimalLicensor.DEFAULT_MAX_CONCURRING;

    public MinimalLicensorPlan(){}

    public MinimalLicensorPlan(String name) {
        super(name);
    }

    public MinimalLicensor build(ServiceContext serviceContext){
        // TODO: Figure out what to do to make this
        return new MinimalLicensor(maxTotalLicenses, maxConcurringLicenses);
    }

    public long getMaxTotalLicenses() {
        return maxTotalLicenses;
    }

    public void setMaxTotalLicenses(long maxTotalLicenses) {
        this.maxTotalLicenses = maxTotalLicenses;
    }

    public long getMaxConcurringLicenses() {
        return maxConcurringLicenses;
    }

    public void setMaxConcurringLicenses(long maxConcurringLicenses) {
        this.maxConcurringLicenses = maxConcurringLicenses;
    }
}
