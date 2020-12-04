package sk.filipceko.golfscorecard.table.utils.colorManager;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

public class BgColorManager extends AColorManager {

    @Override
    protected void setColorToView(int color) {
        view.setBackgroundColor(color);
    }

    @Override
    protected int getColorFromView() {
        Drawable drawable = view.getBackground();
        if (drawable instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) drawable;
            return colorDrawable.getColor();
        }
        return Color.TRANSPARENT;
    }
}
