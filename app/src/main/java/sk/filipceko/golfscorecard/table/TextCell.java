package sk.filipceko.golfscorecard.table;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import sk.filipceko.golfscorecard.R;

public class TextCell<T> extends ACell<TextView, T> {

    private String text = null;
    private Integer layoutId = R.layout.table_text_cell;

    public TextCell(){
        //Nothing to do
    }

    public TextCell(String text){
        this.text = text;
    }

    public void setText(String text){
        this.text = text;
    }

    public void setLayout(int layoutId) {
        this.layoutId = layoutId;
    }

    @Override
    public TextView buildView(Context context) {
        ViewGroup parent = parentRow.getView();
        LayoutInflater inflater = context.getSystemService(LayoutInflater.class);
        inflater.inflate(layoutId, parent);
        TextView textView = (TextView) parent.getChildAt(parent.getChildCount() - 1);
        textView.setText(text);
        setView(textView);
        return textView;
    }
}
