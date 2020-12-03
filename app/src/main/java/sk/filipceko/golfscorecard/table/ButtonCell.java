package sk.filipceko.golfscorecard.table;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.google.android.material.button.MaterialButton;
import sk.filipceko.golfscorecard.R;

public class ButtonCell<T> extends ACell<T> {

    String buttonText = null;
    ICellButtonListener<T> cellButtonListener = null;

    public ButtonCell(){
        super();
    }

    public ButtonCell(String buttonText) {
        this();
        this.buttonText = buttonText;
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
        if (view != null) {
            ((MaterialButton) view).setText(buttonText);
        }
    }

    @Override
    public void setTextColor(int color) {
        textColor = color;
        if (view != null) {
            ((MaterialButton) view).setTextColor(color);
        }
    }

    public void setOnClickListener(ICellButtonListener<T> listener){
        cellButtonListener = listener;
    }

    @Override
    public MaterialButton buildCellView(ViewGroup parent) {
        LayoutInflater inflater = parent.getContext().getSystemService(LayoutInflater.class);
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
        view = button;
        return button;
    }
}
