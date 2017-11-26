package licensing;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public interface Licensor {

    public boolean tryAcquire();
    public boolean tryAcquire(long timeout, TimeUnit timeUnit);
    public void acquireAsync(Consumer<Boolean> callback);
    public void tryAcquireAsync(Consumer<Boolean> callback, long timeout, TimeUnit timeUnit);
    public boolean acquire();
    public void returnExpended();

}
