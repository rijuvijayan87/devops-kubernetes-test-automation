package persistence;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private Map<String, Object> scenarioContext;

    public ScenarioContext() {
        scenarioContext = new HashMap<>();
    }

    public void setContext(String key, Object value) {
        scenarioContext.put(key, value);
    }

    public Object getContext(String key) {
        Object value = "";
        try {
            value = scenarioContext.get(key);
        } catch (Exception e) {
            return value;
        }
        return value;
    }

    protected String getStringPersistentData(String key) {
        String data = "";
        try {
            data = ((String) getContext(key)).trim();
        } catch (Exception e) {
            data = "";
            System.out.println("Persistent data key not found in the map . Key => " + key);
        }
        return data;
    }

}