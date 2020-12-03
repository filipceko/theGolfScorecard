package sk.filipceko.golfscorecard.table;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import sk.filipceko.golfscorecard.R;

public class TextCell<T> extends ACell<T> {

    private String text = null;

    public TextCell(){
        super();
    }

    public TextCell(String text){
        this();
        this.text = text;
    }

    public void setText(String text){
        this.text = text;
    }

    @Override
    public void setTextColor(int color) {
        textColor = color;
        if (view != null) {
            ((TextView) view).setTextColor(color);
        }
    }

    @Override
    public TextView buildCellView(ViewGroup parent) {
        LayoutInflater inflater = parent.getContext().getSystemService(LayoutInflater.class);
        inflater.inflate(R.layout.table_text_cell, parent);
        TextView textView = (TextView) parent.getChildAt(parent.getChildCount() - 1);
        textView.setText(text);
        if (bgColor != null){
            textView.setBackgroundColor(bgColor);
        }
        if (textColor != null) {
            textView.setTextColor(textColor);
        }
        view = textView;
        return textView;
    }
}
