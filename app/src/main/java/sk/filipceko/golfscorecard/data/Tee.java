package sk.filipceko.golfscorecard.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
import org.bson.types.ObjectId;

public class Tee extends RealmObject {
    @PrimaryKey
    private ObjectId teeId = new ObjectId();
    @Required
    private String teeColor = null;
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

    public TeeColor getTeeColor() {
        return TeeColor.parse(teeColor);
    }

    public void setTeeColor(TeeColor teeColor) {
        this.teeColor = teeColor.getColorName();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tee)) return false;
        Tee tee = (Tee) o;
        //Fields
        if (teeId.equals(tee.getTeeId())) return true;
        if (!teeColor.equals(tee.teeColor)) return false;
        return teeName.equals(tee.teeName);
    }

    @Override
    public int hashCode() {
        return 31 * teeColor.hashCode() + teeName.hashCode();
    }
}
