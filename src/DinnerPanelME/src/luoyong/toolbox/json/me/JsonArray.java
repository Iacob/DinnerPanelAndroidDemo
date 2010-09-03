package luoyong.toolbox.json.me;

import java.util.Vector;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class JsonArray extends JsonValue {

    private Vector array = new Vector();

    public JsonArray(Vector array) {
        this.array = array;
    }

    public void add(Object ojbect) {
        array.addElement(ojbect);
    }

    public int getSize() {
        return array.size();
    }

    public JsonValue get(int index) {
       Object result = array.elementAt(index);
       if (result == null) {
          return null;
       }else if (result instanceof JsonValue) {
          return (JsonValue)result;
       }else {
          return null;
       }
    }
}
