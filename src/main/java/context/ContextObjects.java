package context;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class ContextObjects {

    private final Map<String, Object> contextObjects = new ConcurrentHashMap<String, Object>();


    public ContextObjects() {
    }

    public int size() { return contextObjects.size(); }

    public boolean isEmpty() {
        return contextObjects.isEmpty();
    }

    public boolean containsKey(Object key) {
        return contextObjects.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return contextObjects.containsValue(value);
    }

    public Object get(Object key) {
        return contextObjects.get(key);
    }

    public Object put(String key, Object value) {
        return contextObjects.put(key, value);
    }

    public Object remove(Object key) {
        return contextObjects.remove(key);
    }

    public Object getOrDefault(Object key, Object defaultValue) {
        return contextObjects.getOrDefault(key, defaultValue);
    }

    public Object putIfAbsent(String key, Object value) {
        return contextObjects.putIfAbsent(key, value);
    }

    public boolean remove(Object key, Object value) {
        return contextObjects.remove(key, value);
    }

    public boolean replace(String key, Object oldValue, Object newValue) {
        return contextObjects.replace(key, oldValue, newValue);
    }

    public Object replace(String key, Object value) {
        return contextObjects.replace(key, value);
    }

    public void putAll(Map<? extends String, ?> m) {
        synchronized(contextObjects){
            contextObjects.putAll(m);
        }
    }

    public void clear() {
        synchronized(contextObjects) {
            contextObjects.clear();
        }
    }

    public Map<String, Object> getAll(){
        synchronized(contextObjects){
            return new HashMap<String, Object>(contextObjects);
        }
    }
}
