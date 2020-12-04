package sk.filipceko.golfscorecard.table.utils.colorManager;

import android.view.View;
import android.widget.TextView;

public class TextColorManager extends AColorManager {

    @Override
    public void setView(View view) {
        if (view instanceof TextView) {
            super.setView(view);
        }
    }

    @Override
    protected void setColorToView(int color) {
        TextView textView = (TextView) view;
        textView.setTextColor(color);
    }

    @Override
    protected int getColorFromView() {
        TextView textView = (TextView) view;
        return textView.getCurrentTextColor();
    }
}
