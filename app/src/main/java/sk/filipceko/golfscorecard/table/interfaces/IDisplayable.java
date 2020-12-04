package sk.filipceko.golfscorecard.table.interfaces;

import android.content.Context;
import android.view.View;

public interface IDisplayable {

    View getView();

    void setBgColor(int color);

    void setTextColor(int color);

    void refreshColors();

    View buildView(Context context);
}
