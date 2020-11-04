package sk.filipceko.golfscorecard.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
import org.bson.types.ObjectId;
import sk.filipceko.golfscorecard.R;

public class Tee extends RealmObject {
    @PrimaryKey
    private ObjectId teeId = new ObjectId();
    @Required
    private String teeColor;
    @Required
    private String teeName;
    private float cr;
    private int sr;

    public ObjectId getTeeId() {
        return teeId;
    }

    public void setTeeId(ObjectId teeId) {
        this.teeId = teeId;
    }

    public TeeColor getTeeColor() throws ColorNotRecognized {
        return TeeColor.parse(teeColor);
    }

    public void setTeeColor(TeeColor teeColor) {
        this.teeColor = teeColor.colorName;
    }

    public String getTeeName() {
        return teeName;
    }

    public void setTeeName(String teeName) {
        this.teeName = teeName;
    }

    public float getCr() {
        return cr;
    }

    public void setCr(float cr) {
        this.cr = cr;
    }

    public int getSr() {
        return sr;
    }

    public void setSr(int sr) {
        this.sr = sr;
    }

    public enum TeeColor {
        Black("Black", R.color.black),
        Blue("Blue", R.color.blue),
        White("White", R.color.white),
        Red("Red", R.color.red),
        Yellow("Yellow", R.color.yellow),
        Purple("Purple", R.color.purple),
        Green("Green", R.color.green),
        Orange("Orange", R.color.orange);

        String colorName;
        int colorInt;

        TeeColor(String colorName, int colorInt){
            this.colorName = colorName;
            this.colorInt = colorInt;
        }

        public static TeeColor parse(String name) throws ColorNotRecognized {
            switch (name) {
                case "Black":
                    return Black;
                case "Blue":
                    return Blue;
                case "White":
                    return White;
                case "Red":
                    return Red;
                case "Yellow":
                    return Yellow;
                case "Purple":
                    return Purple;
                case "Green":
                    return Green;
                case "Orange":
                    return Orange;
                default:
                    throw new ColorNotRecognized(name);
            }
        }
    }

    public static class ColorNotRecognized extends Exception {
        public ColorNotRecognized(String message){
            super(message);
        }
    }
}
