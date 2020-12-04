package sk.filipceko.golfscorecard.table.interfaces;

import android.content.Context;
import android.widget.TableLayout;

public interface ITable extends IDisplayable {

    void setRoot(TableLayout root);

    void setHeader(String ... headers);

    void addRow(IRow<?> newRow);

    void deleteRow(IRow<?> row);

    @Override
    TableLayout buildView(Context context);

    void clearTable();
}
