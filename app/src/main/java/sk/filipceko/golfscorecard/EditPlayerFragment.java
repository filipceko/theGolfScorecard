package sk.filipceko.golfscorecard;

import android.os.Bundle;
import android.view.View;
import com.google.android.material.textfield.TextInputEditText;
import io.realm.Realm;
import org.bson.types.ObjectId;
import sk.filipceko.golfscorecard.data.Player;

public class EditPlayerFragment extends ACreateEditDeleteFragment<Player> {

    public static EditPlayerFragment newInstance(Player playerToEdit){
        EditPlayerFragment editPlayerFragment = new EditPlayerFragment();
        if (playerToEdit != null) {
            Bundle arguments = new Bundle();
            arguments.putSerializable(TO_EDIT_KEY, playerToEdit.getPlayerID());
            editPlayerFragment.setArguments(arguments);
        }
        return editPlayerFragment;
    }

    @Override
    protected Player realmQuery(ObjectId objectId) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Player.class).equalTo("playerId", objectId).findFirst();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_edit_player;
    }

    @Override
    protected void onCreateViewLoad() {
        if (itemToEdit == null) {
            itemToEdit = new Player();
        }
        TextInputEditText nameEditText = mainView.findViewById(R.id.name_edit_text);
        nameEditText.setText(itemToEdit.getName());
        TextInputEditText surnameEditText = mainView.findViewById(R.id.surname_edit_text);
        surnameEditText.setText(itemToEdit.getSurname());
        TextInputEditText hcpEditText = mainView.findViewById(R.id.hcp_edit_text);
        hcpEditText.setText(String.valueOf(itemToEdit.getHcp()));
    }

    @Override
    public void save(View view) {
        //TODO Data validation
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        TextInputEditText nameEditText = mainView.findViewById(R.id.name_edit_text);
        itemToEdit.setName(nameEditText.getText().toString());
        TextInputEditText surnameEditText = mainView.findViewById(R.id.surname_edit_text);
        itemToEdit.setSurname(surnameEditText.getText().toString());
        TextInputEditText hcpEditText = mainView.findViewById(R.id.hcp_edit_text);
        itemToEdit.setHcp(Float.parseFloat(hcpEditText.getText().toString()));
        realm.insertOrUpdate(itemToEdit);
        realm.commitTransaction();
        fireSaveEvent(itemToEdit);
        close(true);
    }
}