package sk.filipceko.golfscorecard;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import io.realm.Realm;
import org.bson.types.ObjectId;
import sk.filipceko.golfscorecard.data.Player;

public class PlayerActivity extends AppCompatActivity {
    public static final String CREATE_PLAYER_KEY = "sk.filipceko.golfscorecard.createPlayer";
    public static final String EDIT_PLAYER_KEY = "sk.filipceko.golfscorecard.editPlayer";

    private Player player = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        loadPlayer();
    }

    private void loadPlayer() {
        Intent intent = getIntent();
        Realm realm = Realm.getDefaultInstance();
        if (intent.getExtras().getBoolean(CREATE_PLAYER_KEY)){
            player = new Player();
        } else if (intent.hasExtra(EDIT_PLAYER_KEY)){
            ObjectId id = (ObjectId) intent.getSerializableExtra(EDIT_PLAYER_KEY);
            player = realm.where(Player.class)
                    .equalTo("playerId", id).findFirst();
            if (player == null) {
                throw new IllegalStateException("No player defined for PlayerView");
            }
            player = realm.copyFromRealm(player);
        }
        EditText name = findViewById(R.id.editPersonName);
        name.setText(player.getName(), TextView.BufferType.EDITABLE);
        EditText surname = findViewById(R.id.editPersonSurname);
        surname.setText(player.getSurname(), TextView.BufferType.EDITABLE);
        EditText hcp = findViewById(R.id.editPersonHcp);
        hcp.setText(String.valueOf(player.getHcp()), TextView.BufferType.EDITABLE);
    }

    public void savePlayer(View view) {
        EditText name = findViewById(R.id.editPersonName);
        player.setName(name.getText().toString());
        EditText surname = findViewById(R.id.editPersonSurname);
        player.setSurname(surname.getText().toString());
        EditText hcp = findViewById(R.id.editPersonHcp);
        player.setHcp(Float.parseFloat(hcp.getText().toString()));
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(player);
        realm.commitTransaction();
        finish();
    }

    public void deletePlayer(View view) {
        if (!getIntent().getExtras().getBoolean(CREATE_PLAYER_KEY)) {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            player = realm.copyToRealmOrUpdate(player);
            player.deleteFromRealm();
            realm.commitTransaction();
        }
        finish();
    }
}