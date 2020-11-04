package sk.filipceko.golfscorecard.data;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import org.bson.types.ObjectId;

public class Play extends RealmObject {
    @PrimaryKey
    private ObjectId playId = new ObjectId();
    private Player player;
    private Game game;
    private int hole;
    private int shots;
    private int putts;
    private int stablefords;
    private int playersPar;

    public ObjectId getPlayId() {
        return playId;
    }

    public void setPlayId(ObjectId playId) {
        this.playId = playId;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getHole() {
        return hole;
    }

    public void setHole(int hole) {
        this.hole = hole;
    }

    public int getShots() {
        return shots;
    }

    public void setShots(int shots) {
        this.shots = shots;
    }

    public int getPutts() {
        return putts;
    }

    public void setPutts(int putts) {
        this.putts = putts;
    }

    public int getStablefords() {
        return stablefords;
    }

    public void setStablefords(int stablefords) {
        this.stablefords = stablefords;
    }

    public int getPlayersPar() {
        return playersPar;
    }

    public void setPlayersPar(int playersPar) {
        this.playersPar = playersPar;
    }
}
