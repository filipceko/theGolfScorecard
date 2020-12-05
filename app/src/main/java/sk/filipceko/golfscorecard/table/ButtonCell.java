package sk.filipceko.golfscorecard.table;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.google.android.material.button.MaterialButton;
import sk.filipceko.golfscorecard.R;

public class ButtonCell<T> extends ACell<MaterialButton, T> {

    String buttonText = null;
    ICellButtonListener<T> cellButtonListener = null;

    public ButtonCell(){
        //Nothing to do
    }

    public ButtonCell(String buttonText) {
        this.buttonText = buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
        if (getView() != null) {
            getView().setText(buttonText);
        }
    }

    public void setOnClickListener(ICellButtonListener<T> listener){
        cellButtonListener = listener;
    }

    @Override
    public MaterialButton buildView(Context context) {
        LayoutInflater inflater = context.getSystemService(LayoutInflater.class);
        ViewGroup parent = parentRow.getView();
        inflater.inflate(R.layout.table_button_cell, parent);
        MaterialButton button = (MaterialButton) parent.getChildAt(parent.getChildCount() - 1);
        button.setText(buttonText);
        if (cellButtonListener != null) {
            button.setOnClickListener(view -> cellButtonListener.onClick(view,
                    (parentRow == null) ? null : parentRow.getResource()));
        }
        setView(button);
        return button;
    }
}
