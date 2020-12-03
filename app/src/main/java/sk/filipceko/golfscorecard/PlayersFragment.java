package sk.filipceko.golfscorecard;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.button.MaterialButton;
import io.realm.Realm;
import io.realm.RealmResults;
import sk.filipceko.golfscorecard.data.Player;
import sk.filipceko.golfscorecard.table.Row;
import sk.filipceko.golfscorecard.table.Table;
import sk.filipceko.golfscorecard.table.TextCell;
import sk.filipceko.golfscorecard.table.interfaces.IRow;
import sk.filipceko.golfscorecard.table.interfaces.ITable;

public class PlayersFragment extends Fragment implements ICreateEditDeleteView.OnResultListener<Player> {

    ITable table;

    public static PlayersFragment newInstance(){
        return new PlayersFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_players, container, false);
        Resources resources = getResources();
        table = new Table(view.findViewById(R.id.players_table));
        table.setHeader(
                resources.getString(R.string.player_name),
                resources.getString(R.string.player_surname),
                resources.getString(R.string.hcp)
        );
        refreshPlayersTable();
        MaterialButton createPlayerButton = view.findViewById(R.id.create_player_button);
        createPlayerButton.setOnClickListener(this::createPlayer);
        return view;
    }

    private void refreshPlayersTable(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Player> players = realm.where(Player.class).findAll();
        table.clearTable();
        for (Player player : players){
            Row<Player> newRow = new Row<>(player);
            TextCell<Player> hcpCell = new TextCell<>(player.getHcpString());
            newRow.addCell(hcpCell);
            TextCell<Player> surnameCell = new TextCell<>(player.getSurname());
            newRow.addCell(surnameCell);
            TextCell<Player> nameCell = new TextCell<>(player.getName());
            newRow.addCell(nameCell);
            table.addRow(newRow);
            newRow.setOnClickListener(this::editPlayer);
        }
        table.buildTableLayout();
    }

    public void editPlayer(View view, IRow<Player> row) {
        EditPlayerFragment editPlayerFragment = EditPlayerFragment.newInstance(row.getResource());
        getParentFragmentManager().beginTransaction()
                .add(R.id.main_fragment_view, editPlayerFragment, null)
                .addToBackStack(null)
                .commit();
        editPlayerFragment.setOnResultListener(this);
    }

    public void createPlayer(View view) {
        EditPlayerFragment editPlayerFragment = EditPlayerFragment.newInstance(null);
        getParentFragmentManager().beginTransaction()
                .add(R.id.main_fragment_view, editPlayerFragment, null)
                .addToBackStack(null)
                .commit();
        editPlayerFragment.setOnResultListener(this);
    }

    @Override
    public void onSave(Player item) {
        refreshPlayersTable();
    }

    @Override
    public void onDelete(Player item) {
        refreshPlayersTable();
    }

    @Override
    public void onClose() {
        //Nothing to do
    }
}