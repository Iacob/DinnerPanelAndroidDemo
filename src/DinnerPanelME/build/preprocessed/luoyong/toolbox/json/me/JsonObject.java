package luoyong.toolbox.json.me;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class JsonObject extends JsonValue {

    private Hashtable hash = new Hashtable();

    public JsonObject(Hashtable hash) {
        this.hash = hash;
    }

    public void put(String key, Object value) {
        hash.put(key, value);
    }

    public JsonValue get(String key) {
       Object result = hash.get(key);
       if (result == null) {
          return null;
       } else if (result instanceof JsonValue) {
          return (JsonValue) result;
       } else {
          return null;
       }
    }

    public Enumeration getKeys() {
        return hash.keys();
    }

    public int getSize() {
        return hash.size();
    }
}
