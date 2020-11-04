package sk.filipceko.golfscorecard.data;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.LinkedList;

public class Game extends RealmObject {
    @PrimaryKey
    private ObjectId gameId = new ObjectId();
    private Course course;
    private Date time;
    private RealmList<Player> players;
    private RealmList<PlayersTee> playerTees;
    private RealmList<Play> plays;

    public ObjectId getGameId() {
        return gameId;
    }

    public void setGameId(ObjectId gameId) {
        this.gameId = gameId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public RealmList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(RealmList<Player> players) {
        this.players = players;
    }

    public void setPlayerTees(RealmList<PlayersTee> playerTees) {
        this.playerTees = playerTees;
    }

    public RealmList<PlayersTee> getPlayerTees() {
        return playerTees;
    }

    public void setPlayerTee(Player player, Tee tee){
        PlayersTee pt = Realm.getDefaultInstance().createObject(PlayersTee.class);
        getPlayerTees().add(pt);
    }

    public RealmList<Play> getPlays() {
        return plays;
    }

    public void setPlays(RealmList<Play> plays) {
        this.plays = plays;
    }

    public Play[] getPlaysForPlayer(final Player player){
        LinkedList<Play> result = new LinkedList<>();
        for (Play play : getPlays()){
            if (play.getPlayer().equals(player)){
                result.add(play);
            }
        }
        return (Play[]) result.toArray();
    }

    public Play[] getPlaysForHole(int hole){
        LinkedList<Play> result = new LinkedList<>();
        for (Play play : getPlays()){
            if (play.getHole() == hole){
                result.add(play);
            }
        }
        return (Play[]) result.toArray();
    }
}
