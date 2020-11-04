package sk.filipceko.golfscorecard.table.interfaces;

import android.content.Context;
import android.view.View;
import android.widget.TableRow;

public interface IRow<T> {

    void setParent(ITable table);

    int getIndex();

    TableRow buildRowView();

    void addCell(ICell cell);

    void removeCell(int index);

    int getCellIndex(ICell cell);

    T getResource();

    void setResource(T resource);

    void setOnClickListener(OnClickListener<T> onClickListener);

    public interface OnClickListener<T> {
        public void onClick(View view, Context context, T value);
    }
}
