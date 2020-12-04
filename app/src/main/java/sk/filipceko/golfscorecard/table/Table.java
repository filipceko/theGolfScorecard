package sk.filipceko.golfscorecard.table;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import sk.filipceko.golfscorecard.R;
import sk.filipceko.golfscorecard.table.interfaces.IRow;
import sk.filipceko.golfscorecard.table.interfaces.ITable;

import java.util.Collections;
import java.util.LinkedList;

public class Table extends ATableComponent implements ITable {

    private TableLayout rootLayout;
    private final LinkedList<String> headerData = new LinkedList<>();
    private final LinkedList<IRow<?>> tableRows= new LinkedList<>();

    public Table() {
        //Nothing to do
    }

    public Table(TableLayout rootLayout) {
        this.rootLayout = rootLayout;
    }

    @Override
    public void setRoot(TableLayout root) {
        this.rootLayout = root;
    }

    @Override
    public void setHeader(String... headers) {
        headerData.clear();
        Collections.addAll(headerData, headers);
    }

    @Override
    public void addRow(IRow<?> newRow) {
        tableRows.add(newRow);
    }

    @Override
    public void deleteRow(IRow<?> row) {
        tableRows.remove(row);
    }

    @Override
    public TableLayout buildView(Context context) {
        rootLayout.addView(buildHeader(context));
        for (IRow<?> row : tableRows){
            rootLayout.addView(row.buildView(context));
        }
        return rootLayout;
    }

    @Override
    public void clearTable() {
        rootLayout.removeAllViews();
        tableRows.clear();
    }

    private TableRow buildHeader(Context context){
        TableRow headerRow = new TableRow(context);
        LayoutInflater inflater = context.getSystemService(LayoutInflater.class);
        for (String header : headerData) {
            inflater.inflate(R.layout.table_header_cell, headerRow);
            TextView headerCell = (TextView) headerRow.getChildAt(headerRow.getChildCount() - 1);
            headerCell.setText(header);
        }
        return headerRow;
    }
}
