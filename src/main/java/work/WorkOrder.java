package work;

import java.util.UUID;

public class WorkOrder {

    //The UUID belongs to the work, and should stay with any subsequent WorkResult
    protected final UUID uuid = UUID.randomUUID();
    protected final Object message;

    public UUID getUuid() {
        return uuid;
    }

    public WorkOrder() {
        this.message = null;
    }

    public WorkOrder(Object message) {
        this.message = message;
    }
}
