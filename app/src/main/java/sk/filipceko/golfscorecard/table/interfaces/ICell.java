package sk.filipceko.golfscorecard.table.interfaces;

import android.view.View;
import android.view.ViewGroup;

public interface ICell<T> {

    View buildCellView(ViewGroup parent);

    void setParent(IRow<T> parent);

    void setBgColor(int color);

    void setTextColor(int color);

    interface ICellButtonListener<T> {
        void onClick(View view, T value);
    }
}
