package luoyong.toolbox.json.me;

/**
 *
 * @author yong
 */
public class JsonBoolean extends JsonValue {

   private boolean booleanValue = false;

   public JsonBoolean(boolean value) {
      this.booleanValue = value;
   }

   public boolean getValue() {
      return booleanValue;
   }

   public void setValue(boolean booleanValue) {
      this.booleanValue = booleanValue;
   }
}
