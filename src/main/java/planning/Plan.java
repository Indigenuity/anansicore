package planning;

public class Plan {

    public static final String DEFAULT_PLAN_NAME = "Unnamed plan";

    private final PlanId planId = new PlanId();
    private String name = DEFAULT_PLAN_NAME;

    public Plan(){}
    public Plan(String name){
        this.name = name;
    }

    public PlanId getPlanId() {
        return planId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
