package sk.filipceko.golfscorecard.table.interfaces;

import android.content.Context;
import android.view.View;

public interface ICell<T> extends IDisplayable {

    @Override
    View buildView(Context context);

    void setParent(IRow<T> parent);

    interface ICellButtonListener<T> {
        void onClick(View view, T value);
    }
}
