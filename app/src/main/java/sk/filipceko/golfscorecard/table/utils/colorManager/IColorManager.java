package sk.filipceko.golfscorecard.table.utils.colorManager;

import android.view.View;

public interface IColorManager {
    int DEFAULT_COLOR = -1;

    void setView(View view);

    void setColor(int color);

    int getColor();
}
