package sk.filipceko.golfscorecard.table;

import android.view.View;
import sk.filipceko.golfscorecard.table.interfaces.ICell;
import sk.filipceko.golfscorecard.table.interfaces.IRow;

public abstract class ACell<U extends View, T> extends ATableComponent<U> implements ICell<T> {

    protected IRow<T> parentRow = null;

    @Override
    public void setParent(IRow<T> parent) {
        parentRow = parent;
    }
}
