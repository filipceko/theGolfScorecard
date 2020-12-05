package sk.filipceko.golfscorecard.table;

import android.view.View;
import sk.filipceko.golfscorecard.table.interfaces.IDisplayable;
import sk.filipceko.golfscorecard.table.utils.colorManager.BgColorManager;
import sk.filipceko.golfscorecard.table.utils.colorManager.IColorManager;
import sk.filipceko.golfscorecard.table.utils.colorManager.TextColorManager;

public abstract class ATableComponent<T extends View> implements IDisplayable {

    private T view = null;

    protected BgColorManager bgColorManager = new BgColorManager();
    protected TextColorManager textColorManager = new TextColorManager();

    @Override
    public T getView() {
        return view;
    }

    protected void setView(T view) {
        this.view = view;
        bgColorManager.setView(view);
        textColorManager.setView(view);
    }

    @Override
    public void setBgColor(int color) {
        bgColorManager.setColor(color);
    }

    @Override
    public void setTextColor(int color) {
        textColorManager.setColor(color);
    }

    @Override
    public void refreshColors() {
        bgColorManager.setColor(IColorManager.DEFAULT_COLOR);
        textColorManager.setColor(IColorManager.DEFAULT_COLOR);
    }
}
