package sk.filipceko.golfscorecard.table.interfaces;

import android.widget.TableLayout;

public interface ITable {

    void setRoot(TableLayout root);

    void setHeader(String ... headers);

    void addRow(IRow<?> newRow);

    void deleteRow(int index);

    int getLastRowIndex();

    int getIndexOf(IRow<?> row);

    void addRow(int index, IRow<?> newRow);

    TableLayout buildTableLayout();

    void clearTable();
}
