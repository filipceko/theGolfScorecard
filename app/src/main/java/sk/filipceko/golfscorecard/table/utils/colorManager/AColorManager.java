package sk.filipceko.golfscorecard.table.utils.colorManager;

import android.view.View;

public abstract class AColorManager implements IColorManager {

    protected View view;

    private int defaultColor = IColorManager.DEFAULT_COLOR;

    protected abstract void setColorToView(int color);

    protected abstract int getColorFromView();

    public void setView(View view) {
        this.view = view;
        if (defaultColor != IColorManager.DEFAULT_COLOR){
            setColorToView(defaultColor);
        } else {
            defaultColor = getColorFromView();
        }
    }

    @Override
    public void setColor(int color) {
        if (view == null) {
            defaultColor = color;
            return;
        }
        if (color == IColorManager.DEFAULT_COLOR){
            setColorToView(defaultColor);
        } else {
            setColorToView(color);
        }
    }

    @Override
    public int getColor() {
        if (view == null) {
            return defaultColor;
        }
        return getColorFromView();
    }
}
