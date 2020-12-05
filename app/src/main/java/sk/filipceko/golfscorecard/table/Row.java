package sk.filipceko.golfscorecard.table;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TableRow;
import sk.filipceko.golfscorecard.R;
import sk.filipceko.golfscorecard.table.interfaces.ICell;
import sk.filipceko.golfscorecard.table.interfaces.IRow;
import sk.filipceko.golfscorecard.table.interfaces.ITable;

import java.util.LinkedList;

public class Row<T> extends ATableComponent<TableRow> implements IRow<T> {

    private ITable parent;
    private final LinkedList<ICell<?>> cells = new LinkedList<>();
    private T resource = null;
    private IRow.OnClickListener<T> onClickListener;

    public Row() {
        //Nothing to do
    }

    public Row(T resource) {
        this.resource = resource;
    }

    @Override
    public TableRow buildView(Context context) {
        LayoutInflater inflater = context.getSystemService(LayoutInflater.class);
        TableLayout parentView = (TableLayout) inflater.inflate(R.layout.table_row_layout, parent.getView());
        TableRow row = (TableRow) parentView.getChildAt(parentView.getChildCount() - 1);
        if (onClickListener != null) {
            row.setOnClickListener((view) -> onClickListener.onClick(view, this));
        }
        setView(row);
        for (ICell<?> cell : cells) {
            cell.buildView(context);
        }
        return row;
    }


    @Override
    public void addCell(ICell<T> cell) {
        cells.add(cell);
        cell.setParent(this);
    }

    @Override
    public void removeCell(ICell<?> cell) {
        cells.remove(cell);
    }

    @Override
    public void setParent(ITable parent) {
        this.parent = parent;
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
    public void setBgColor(int color, boolean colorCells) {
        setBgColor(color);
        if(!colorCells){
            return;
        }
        for (ICell<?> cell : cells) {
            cell.setBgColor(color);
        }
    }

    @Override
    public void setOnClickListener(IRow.OnClickListener<T> onClickListener) {
        this.onClickListener = onClickListener;
    }
}
