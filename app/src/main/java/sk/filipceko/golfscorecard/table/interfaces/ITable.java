package sk.filipceko.golfscorecard.table.interfaces;

import android.content.Context;
import android.widget.TableLayout;

public interface ITable extends IDisplayable {

    @Override
    TableLayout buildView(Context context);

    @Override
    TableLayout getView();

    void setHeader(String ... headers);

    void addRow(IRow<?> newRow);

    void deleteRow(IRow<?> row);

    void clearTable();
}
