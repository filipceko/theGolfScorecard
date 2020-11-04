package sk.filipceko.golfscorecard;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import io.realm.Realm;
import io.realm.RealmResults;
import sk.filipceko.golfscorecard.data.Player;
import sk.filipceko.golfscorecard.table.Row;
import sk.filipceko.golfscorecard.table.Table;
import sk.filipceko.golfscorecard.table.TextCell;

public class PlayersActivity extends AppCompatActivity {

    private Table playersTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        loadPlayers();
    }

    private void loadPlayers(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Player> players = realm.where(Player.class).findAll();
        players.addChangeListener(this::refreshPlayers);
        playersTable = new Table(findViewById(R.id.playersTable), this);
        Resources resources = getResources();
        playersTable.setHeader(
                resources.getString(R.string.player_name),
                resources.getString(R.string.player_surname),
                resources.getString(R.string.player_hcp)
        );
        refreshPlayers(players);
    }

    private void refreshPlayers(RealmResults<Player> players){
        playersTable.clearTable();
        for (Player player : players){
            Row<Player> newRow = new Row<>(this, player);
            TextCell nameCell = new TextCell(this, player.getName());
            newRow.addCell(nameCell);
            TextCell surnameCell = new TextCell(this, player.getSurname());
            newRow.addCell(surnameCell);
            TextCell hcpCell = new TextCell(this, player.getHcpString());
            newRow.addCell(hcpCell);
            playersTable.addRow(newRow);
            newRow.setOnClickListener(this::startPlayerEdit);
        }
        playersTable.buildTableLayout();
    }

    public void startPlayerEdit(View view, Context context, Player player) {
        Intent intent = new Intent(context, PlayerActivity.class);
        intent.putExtra(PlayerActivity.EDIT_PLAYER_KEY, player.getPlayerID());
        startActivity(intent);
    }

    public void createPlayer(View view) {
        Intent newIntent = new Intent(this, PlayerActivity.class);
        newIntent.putExtra(PlayerActivity.CREATE_PLAYER_KEY, true);
        startActivity(newIntent);
    }
}