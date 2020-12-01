package sk.filipceko.golfscorecard.table;

import android.content.Context;
import android.content.res.Resources;
import android.widget.TableRow;
import sk.filipceko.golfscorecard.R;
import sk.filipceko.golfscorecard.table.interfaces.ICell;
import sk.filipceko.golfscorecard.table.interfaces.IRow;
import sk.filipceko.golfscorecard.table.interfaces.ITable;

import java.util.LinkedList;

public class Row<T> implements IRow<T> {

    private final Context context;
    private final LinkedList<ICell> cells = new LinkedList<>();
    private T resource = null;
    private ITable parentTable;
    private IRow.OnClickListener<T> onClickListener;

    public Row(Context context){
        this.context = context;
    }

    public Row(Context context, T resource){
        this(context);
        this.resource = resource;
    }

    @Override
    public void setParent(ITable table) {
        this.parentTable = table;
    }

    @Override
    public int getIndex() {
        return parentTable.getIndexOf(this);
    }

    @Override
    public TableRow buildRowView() {
        TableRow row = new TableRow(context);
        for (ICell cell : cells) {
            cell.buildCellView(row);
        }
        if (onClickListener != null) {
            row.setOnClickListener((view) -> onClickListener.onClick(view, resource));
        }
        return row;
    }

    @Override
    public void addCell(ICell<T> cell) {
        cells.push(cell);
        cell.setParent(this);
    }

    @Override
    public void removeCell(int index) {
        cells.remove(index);
    }

    @Override
    public int getCellIndex(ICell<T> cell) {
        return cells.indexOf(cell);
    }

    @Override
    public T getResource() {
        return resource;
    }

    @Override
    public void setResource(T resource) {
        this.resource = resource;
    }

    @Override
    public void setOnClickListener(IRow.OnClickListener<T> onClickListener) {
        this.onClickListener = onClickListener;
    }
}
