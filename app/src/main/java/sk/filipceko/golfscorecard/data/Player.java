package sk.filipceko.golfscorecard.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
import org.bson.types.ObjectId;

public class Player extends RealmObject {

    @PrimaryKey
    private ObjectId playerId = new ObjectId();
    @Required
    private String name;
    private String surname;
    private float hcp;

    public ObjectId getPlayerID() {
        return playerId;
    }

    public void setPlayerID(ObjectId playerID) {
        this.playerId = playerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public float getHcp() {
        return hcp;
    }

    public String getHcpString(){
        return Float.toString(hcp);
    }

    public void setHcp(float hcp) {
        this.hcp = hcp;
    }
}
