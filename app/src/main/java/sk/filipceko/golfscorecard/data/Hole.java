package sk.filipceko.golfscorecard.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import org.bson.types.ObjectId;

public class Hole extends RealmObject {
    @PrimaryKey
    private ObjectId holeId = new ObjectId();
    private int number;
    private int par;
    private int hcp;

    public ObjectId getHoleId() {
        return holeId;
    }

    public void setHoleId(ObjectId holeId) {
        this.holeId = holeId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }

    public int getHcp() {
        return hcp;
    }

    public void setHcp(int hcp) {
        this.hcp = hcp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hole)) return false;
        Hole hole = (Hole) o;
        if (holeId != null && hole.holeId != null && holeId.equals(hole.holeId)) return true;
        return number == hole.number;
    }

    @Override
    public int hashCode() {
        if (holeId != null) {
            return holeId.hashCode();
        } else {
              return 31 + number;
        }
    }
}
