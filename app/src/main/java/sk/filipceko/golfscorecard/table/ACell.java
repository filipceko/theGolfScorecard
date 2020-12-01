package sk.filipceko.golfscorecard.table;

import android.content.Context;
import sk.filipceko.golfscorecard.table.interfaces.ICell;
import sk.filipceko.golfscorecard.table.interfaces.IRow;

public abstract class ACell<T> implements ICell<T> {

    protected Context context;
    protected Integer bgColor = null;
    protected Integer textColor = null;
    protected IRow<T> parentRow = null;

    public ACell(Context context) {
        this.context = context;
    }

    @Override
    public void setParent(IRow<T> parent) {
        parentRow = parent;
    }

    @Override
    public void setBgColor(int color) {
        bgColor = color;
    }

    @Override
    public void setTextColor(int color) {
        textColor = color;
    }
}
