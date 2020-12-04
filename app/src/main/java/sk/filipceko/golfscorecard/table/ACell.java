package sk.filipceko.golfscorecard.table;

import sk.filipceko.golfscorecard.table.interfaces.ICell;
import sk.filipceko.golfscorecard.table.interfaces.IRow;

public abstract class ACell<T> extends ATableComponent implements ICell<T> {

    protected IRow<T> parentRow = null;

    @Override
    public void setParent(IRow<T> parent) {
        parentRow = parent;
    }
}
