package sk.filipceko.golfscorecard;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class LoadingActivity extends AppCompatActivity {

    private TextView message;
    private int tapCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        message = findViewById(R.id.welcomeText);
        ImageView pic = findViewById(R.id.golfLogo);
    }

    public void tapDrop(View view) {
        tapCounter++;
        String counterText;
        counterText = String.format("%d times", tapCounter);
        message.setText(String.format("You tapped the picture %s", counterText));
    }

    public void toGamesView(View view){
        Intent intent = new Intent(this, GamesActivity.class);
        startActivity(intent);
    }
}