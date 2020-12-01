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

public class Table implements ITable {

    private final Context context;
    private TableLayout rootLayout;
    private final LinkedList<String> headerData = new LinkedList<>();
    private final LinkedList<IRow<?>> tableRows= new LinkedList<>();

    private boolean build = false;

    public Table(TableLayout rootLayout, Context context) {
        this.context = context;
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
        newRow.setParent(this);
        if (build){
            rootLayout.addView(newRow.buildRowView());
        }
    }

    @Override
    public void addRow(int index, IRow<?> newRow) {
        tableRows.add(index, newRow);
        newRow.setParent(this);
        if (build){
            rootLayout.addView(newRow.buildRowView(), index);
        }
    }

    @Override
    public void deleteRow(int index) {
        tableRows.remove(index);
    }

    @Override
    public int getLastRowIndex() {
        return tableRows.size() - 1;
    }

    @Override
    public int getIndexOf(IRow<?> row) {
        return tableRows.indexOf(row);
    }

    @Override
    public TableLayout buildTableLayout() {
        rootLayout.addView(buildHeader());
        for (IRow row : tableRows){
            rootLayout.addView(row.buildRowView());
        }
        build = true;
        return rootLayout;
    }

    @Override
    public void clearTable() {
        rootLayout.removeAllViews();
        tableRows.clear();
        build = false;
    }

    private TableRow buildHeader(){
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
