package sk.filipceko.golfscorecard.table;

import android.view.View;
import sk.filipceko.golfscorecard.table.interfaces.ICell;
import sk.filipceko.golfscorecard.table.interfaces.IRow;

public abstract class ACell<T> implements ICell<T> {

    protected Integer bgColor = null;
    protected Integer textColor = null;
    protected IRow<T> parentRow = null;
    protected View view = null;

    @Override
    public void setParent(IRow<T> parent) {
        parentRow = parent;
    }

    @Override
    public void setBgColor(int color) {
        bgColor = color;
        if (view != null) {
            view.setBackgroundColor(color);
        }
    }
}
