package sk.filipceko.golfscorecard.table;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import sk.filipceko.golfscorecard.R;
import sk.filipceko.golfscorecard.table.interfaces.ICell;

public class TextCell implements ICell {

    private final Context context;
    private String text;

    public TextCell(Context context, String text){
        this.context = context;
        this.text = text;
    }

    public void setText(String text){
        this.text = text;
    }

    @Override
    public View buildCellView(ViewGroup parent) {
        LayoutInflater inflater = context.getSystemService(LayoutInflater.class);
        ViewGroup parentView = (ViewGroup) inflater.inflate(R.layout.table_cell, parent);
        TextView textView = (TextView) parentView.getChildAt(parentView.getChildCount() - 1);
        textView.setText(text);
        return parentView;
    }
}
