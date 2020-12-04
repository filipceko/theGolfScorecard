package sk.filipceko.golfscorecard.table;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TableRow;
import sk.filipceko.golfscorecard.table.interfaces.ICell;
import sk.filipceko.golfscorecard.table.interfaces.IRow;

import java.util.LinkedList;

public class Row<T> extends ATableComponent implements IRow<T> {

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
    public ViewGroup getView() {
        return (ViewGroup) super.getView();
    }

    @Override
    public TableRow buildView(Context context) {
        TableRow row = new TableRow(context);
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
    public void addCell(ICell<T> cell) {
        cells.push(cell);
        cell.setParent(this);
    }

    @Override
    public void removeCell(ICell<?> cell) {
        cells.remove(cell);
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
