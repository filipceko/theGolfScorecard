package sk.filipceko.golfscorecard.table;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.google.android.material.button.MaterialButton;
import sk.filipceko.golfscorecard.R;

public class ButtonCell<T> extends ACell<T> {

    String buttonText = null;
    ICellButtonListener<T> cellButtonListener = null;

    //TODO allow to set style

    public ButtonCell(Context context) {
        super(context);
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public void setOnClickListener(ICellButtonListener<T> listener){
        cellButtonListener = listener;
    }

    @Override
    public MaterialButton buildCellView(ViewGroup parent) {
        LayoutInflater inflater = context.getSystemService(LayoutInflater.class);
        inflater.inflate(R.layout.table_button_cell, parent);
        MaterialButton button = (MaterialButton) parent.getChildAt(parent.getChildCount() - 1);
        button.setText(buttonText);
        if (bgColor != null){
            button.setBackgroundColor(bgColor);
        }
        if (textColor != null) {
            button.setTextColor(textColor);
        }
        if (cellButtonListener != null) {
            button.setOnClickListener(view -> cellButtonListener.onClick(view,
                    (parentRow == null) ? null : parentRow.getResource()));
        }
        return button;
    }
}
