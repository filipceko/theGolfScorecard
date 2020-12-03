package sk.filipceko.golfscorecard;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import sk.filipceko.golfscorecard.table.Table;
import sk.filipceko.golfscorecard.table.interfaces.ITable;

public class GamesFragment extends Fragment {

    public static GamesFragment newInstance() {
        return new GamesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_games, container, false);
        ITable gamesTable = new Table(view.findViewById(R.id.games_table), getContext());
        Resources resources = getResources();
        gamesTable.setHeader(
                resources.getString(R.string.course_resort),
                resources.getString(R.string.course_name),
                resources.getString(R.string.game_date)
        );
        //TODO
        gamesTable.buildTableLayout();
        return view;
    }
}