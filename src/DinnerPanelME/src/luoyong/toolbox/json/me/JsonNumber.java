package luoyong.toolbox.json.me;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class JsonNumber extends JsonValue {

    private String textString = null;
    private double number = 0.0;

    public JsonNumber(String textString, double number) {
        this.textString = textString;
        this.number = number;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public String getTextString() {
        return textString;
    }

    public void setTextString(String textString) {
        this.textString = textString;
    }
}
