package sk.filipceko.golfscorecard.table;

import android.content.Context;
import android.widget.TableLayout;
import sk.filipceko.golfscorecard.R;
import sk.filipceko.golfscorecard.table.interfaces.IRow;
import sk.filipceko.golfscorecard.table.interfaces.ITable;

import java.util.LinkedList;

public class Table extends ATableComponent<TableLayout> implements ITable {

    private IRow<Object> headerRow = null;
    private final LinkedList<IRow<?>> tableRows= new LinkedList<>();

    public Table() {
        //Nothing to do
    }

    public Table(TableLayout rootLayout) {
        setView(rootLayout);
    }

    @Override
    public void setHeader(String... headers) {
        headerRow = new Row<>();
        headerRow.setParent(this);
        for (String header : headers) {
            TextCell<Object> textCell = new TextCell<>();
            textCell.setLayout(R.layout.table_header_cell);
            textCell.setText(header);
            headerRow.addCell(textCell);
        }
    }

    @Override
    public void addRow(IRow<?> newRow) {
        tableRows.add(newRow);
        newRow.setParent(this);
    }

    @Override
    public void deleteRow(IRow<?> row) {
        tableRows.remove(row);
    }

    @Override
    public TableLayout buildView(Context context) {
        TableLayout rootLayout = getView();
        headerRow.buildView(context);
        for (IRow<?> row : tableRows){
            row.buildView(context);
        }
        return rootLayout;
    }

    @Override
    public void clearTable() {
        getView().removeAllViews();
        tableRows.clear();
    }
}
