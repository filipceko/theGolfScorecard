package sk.filipceko.golfscorecard.table;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import sk.filipceko.golfscorecard.R;

public class TextCell<T> extends ACell<T> {

    private String text;

    public TextCell(Context context, String text){
        super(context);
        this.text = text;
    }

    public void setText(String text){
        this.text = text;
    }

    @Override
    public TextView buildCellView(ViewGroup parent) {
        LayoutInflater inflater = context.getSystemService(LayoutInflater.class);
        inflater.inflate(R.layout.table_text_cell, parent);
        TextView textView = (TextView) parent.getChildAt(parent.getChildCount() - 1);
        textView.setText(text);
        if (bgColor != null){
            textView.setBackgroundColor(bgColor);
        }
        if (textColor != null) {
            textView.setTextColor(textColor);
        }
        return textView;
    }
}
