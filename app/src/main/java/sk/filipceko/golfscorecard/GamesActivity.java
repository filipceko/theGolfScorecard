package sk.filipceko.golfscorecard;

import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import sk.filipceko.golfscorecard.table.Table;
import sk.filipceko.golfscorecard.table.interfaces.ITable;

public class GamesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        ITable gamesTable = new Table(findViewById(R.id.gamesTable), this);
        Resources resources = getResources();
        gamesTable.setHeader(
                resources.getString(R.string.course_resort),
                resources.getString(R.string.course_name),
                resources.getString(R.string.date_text)
        );
        gamesTable.buildTableLayout();
    }

    public void players(View view) {
        Intent startPlayers = new Intent(this, PlayersActivity.class);
        startActivity(startPlayers);
    }

    public void courses(View view) {
        Intent startCourses = new Intent(this, CoursesActivity.class);
        startActivity(startCourses);
    }
}