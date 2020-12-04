package sk.filipceko.golfscorecard.table;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import sk.filipceko.golfscorecard.R;

public class TextCell<T> extends ACell<T> {

    private String text = null;

    public TextCell(){
        //Nothing to do
    }

    public TextCell(String text){
        this.text = text;
    }

    public void setText(String text){
        this.text = text;
    }

    @Override
    public TextView buildView(Context context) {
        ViewGroup parent = parentRow.getView();
        LayoutInflater inflater = context.getSystemService(LayoutInflater.class);
        inflater.inflate(R.layout.table_text_cell, parent);
        TextView textView = (TextView) parent.getChildAt(parent.getChildCount() - 1);
        textView.setText(text);
        setView(textView);
        return textView;
    }
}
