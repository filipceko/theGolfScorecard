package sk.filipceko.golfscorecard;

import android.os.Bundle;
import android.view.View;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import dev.sasikanth.colorsheet.ColorSheet;
import io.realm.Realm;
import kotlin.Unit;
import org.bson.types.ObjectId;
import sk.filipceko.golfscorecard.data.Tee;
import sk.filipceko.golfscorecard.data.TeeColor;

public class EditTeeFragment extends ACreateEditDeleteFragment<Tee> {

    private MaterialButton teeColorButton;
    private TeeColor selectedColor;

    public static EditTeeFragment newInstance(Tee teeToEdit){
        EditTeeFragment editTeeFragment = new EditTeeFragment();
        if (teeToEdit != null) {
            Bundle arguments = new Bundle();
            arguments.putSerializable(TO_EDIT_KEY, teeToEdit.getTeeId());
            editTeeFragment.setArguments(arguments);
        }
        return editTeeFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_edit_tee;
    }

    @Override
    protected Tee realmQuery(ObjectId objectId) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Tee.class).equalTo("teeId", objectId).findFirst();
    }

    @Override
    protected void onCreateViewLoad(){
        teeColorButton = mainView.findViewById(R.id.tee_color_button);
        teeColorButton.setOnClickListener(this::launchColorPicker);
        if (itemToEdit == null) {
            itemToEdit = new Tee();
        }
        selectedColor = itemToEdit.getTeeColor();
        if (selectedColor == null) {
            selectedColor = TeeColor.Black;
            setupColorButton(false);
        } else {
            setupColorButton(true);
        }
        TextInputEditText nameEditText = mainView.findViewById(R.id.tee_name_edit_text);
        nameEditText.setText(itemToEdit.getTeeName());
        TextInputEditText crEditText = mainView.findViewById(R.id.tee_cr_edit_text);
        crEditText.setText(String.valueOf(itemToEdit.getCr()));
        TextInputEditText srEditText = mainView.findViewById(R.id.tee_sr_edit_text);
        srEditText.setText(String.valueOf(itemToEdit.getSr()));
    }

    @Override
    protected void save(View view) {
        //TODO Data validation
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        TextInputEditText nameEditText = mainView.findViewById(R.id.tee_name_edit_text);
        itemToEdit.setTeeName(nameEditText.getText().toString());
        TextInputEditText crEditText = mainView.findViewById(R.id.tee_cr_edit_text);
        itemToEdit.setCr(Float.parseFloat(crEditText.getText().toString()));
        TextInputEditText srEditText = mainView.findViewById(R.id.tee_sr_edit_text);
        itemToEdit.setSr(Integer.parseInt(srEditText.getText().toString()));
        itemToEdit.setTeeColor(selectedColor);
        realm.insertOrUpdate(itemToEdit);
        realm.commitTransaction();
        fireSaveEvent(itemToEdit);
        close(true);
    }

    private void setupColorButton(boolean displayColorName){
        teeColorButton.setBackgroundColor(selectedColor.getColor(getResources()));
        if (selectedColor.secondaryTextColor()) {
            teeColorButton.setTextColor(getResources().getColor(R.color.secondaryTextColor, null));
        } else {
            teeColorButton.setTextColor(getResources().getColor(R.color.primaryTextColor, null));
        }
        if (displayColorName) {
            teeColorButton.setText(selectedColor.getUiColorName(getResources()));
        } else {
            teeColorButton.setText(getResources().getString(R.string.pick_color_label));
        }

    }

    private void launchColorPicker(View view) {
        ColorSheet colorSheet = new ColorSheet();
        colorSheet.colorPicker(
                TeeColor.getAllCodes(getResources()),
                (selectedColor == null) ? null : selectedColor.getColor(getResources()),
                false, this::setColor)
                .show(getParentFragmentManager());
    }

    private Unit setColor (int color) {
        selectedColor = TeeColor.parse(color, getResources());
        setupColorButton(true);
        return Unit.INSTANCE;
    }
}