package sk.filipceko.golfscorecard.data;

import io.realm.RealmObject;

public class PlayersTee extends RealmObject {
    private Player player;
    private Tee tee;

    public PlayersTee(){
        //nothing to be done
    }

    public PlayersTee(Player player, Tee tee){
        this.player = player;
        this.tee = tee;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Tee getTee() {
        return tee;
    }

    public void setTee(Tee tee) {
        this.tee = tee;
    }
}
