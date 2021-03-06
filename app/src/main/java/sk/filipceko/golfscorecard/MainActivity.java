package sk.filipceko.golfscorecard;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment redirect = null;
            switch (item.getItemId()){
                case R.id.games_page:
                    redirect = GamesFragment.newInstance();
                    break;
                case R.id.players_page:
                    redirect = PlayersFragment.newInstance();
                    break;
                case R.id.courses_page:
                    redirect = CoursesFragment.newInstance();
                    break;
                case R.id.new_game_page:
                    redirect = SelectCourseFragment.newInstance();
                    break;
            }
            if (redirect != null){
                navigateTo(redirect);
                return true;
            }
            return false;
        });
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_fragment_view, GamesFragment.newInstance())
                .commit();
    }

    private void navigateTo(Fragment fragment) {
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment_view, fragment);
        transaction.commit();
    }

}