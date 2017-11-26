package planning;

import java.util.UUID;

public class PlanId {
    private final UUID id = UUID.randomUUID();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanId planId = (PlanId) o;

        return id.equals(planId.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
