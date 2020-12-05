package sk.filipceko.golfscorecard.table.interfaces;

import android.content.Context;
import android.view.View;
import android.widget.TableRow;

public interface IRow<T> extends IDisplayable {

    @Override
    TableRow buildView(Context context);

    @Override
    TableRow getView();

    void addCell(ICell<T> cell);

    void removeCell(ICell<?> index);

    void setParent(ITable parent);

    T getResource();

    void setResource(T resource);

    void setBgColor(int color, boolean colorCells);

    void setOnClickListener(OnClickListener<T> onClickListener);

    interface OnClickListener<T> {
        void onClick(View view, IRow<T> row);
    }
}
