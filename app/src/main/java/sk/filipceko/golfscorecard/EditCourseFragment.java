package sk.filipceko.golfscorecard;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.view.View;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import io.realm.Realm;
import io.realm.RealmList;
import org.bson.types.ObjectId;
import sk.filipceko.golfscorecard.data.Course;
import sk.filipceko.golfscorecard.data.Hole;
import sk.filipceko.golfscorecard.data.Tee;
import sk.filipceko.golfscorecard.table.ButtonCell;
import sk.filipceko.golfscorecard.table.Row;
import sk.filipceko.golfscorecard.table.Table;
import sk.filipceko.golfscorecard.table.TextCell;
import sk.filipceko.golfscorecard.table.interfaces.ICell;
import sk.filipceko.golfscorecard.table.interfaces.IRow;
import sk.filipceko.golfscorecard.table.interfaces.ITable;

public class EditCourseFragment extends ACreateEditDeleteFragment<Course>
        implements ICreateEditDeleteView.OnResultListener<Tee> {

    ITable teesTable;
    TableLayout parTable;
    TableLayout hcpTable;

    public static EditCourseFragment newInstance(Course courseToEdit){
        EditCourseFragment editCourseFragment = new EditCourseFragment();
        if (courseToEdit != null) {
            Bundle arguments = new Bundle();
            arguments.putSerializable(TO_EDIT_KEY, courseToEdit.getCourseId());
            editCourseFragment.setArguments(arguments);
        }
        return editCourseFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_edit_course;
    }

    @Override
    protected Course realmQuery(ObjectId objectId) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Course.class).equalTo("courseId", objectId).findFirst();
    }

    @Override
    protected void onCreateViewLoad(){
        if (itemToEdit == null) {
            itemToEdit = new Course();
        }
        hcpTable = mainView.findViewById(R.id.course_hcp_table);
        parTable = mainView.findViewById(R.id.course_par_table);
        //Prepare tee section
        teesTable = new Table(mainView.findViewById(R.id.course_tee_table));
        Resources resources = getResources();
        teesTable.setHeader(
                resources.getString(R.string.tee_name),
                resources.getString(R.string.tee_color),
                resources.getString(R.string.tee_cr),
                resources.getString(R.string.tee_sr),
                "" //Button header - no text
        );
        MaterialButton createTeeButton = mainView.findViewById(R.id.create_tee_button);
        createTeeButton.setOnClickListener(this::createTee);
        //Load course
        TextInputEditText resortEditText = mainView.findViewById(R.id.course_resort_edit_text);
        resortEditText.setText(itemToEdit.getResort());
        TextInputEditText nameEditText = mainView.findViewById(R.id.course_name_editText);
        nameEditText.setText(itemToEdit.getCourseName());
        TextInputEditText cityEditText = mainView.findViewById(R.id.course_city_edit_text);
        cityEditText.setText(itemToEdit.getCity());
        TextInputEditText countryEditText = mainView.findViewById(R.id.course_country_edit_text);
        countryEditText.setText(itemToEdit.getCountry());
        for (Hole hole : itemToEdit.getHoles()){
            getHoleEdit(hole.getNumber(), parTable).setText(String.valueOf(hole.getPar()));
            getHoleEdit(hole.getNumber(), hcpTable).setText(String.valueOf(hole.getHcp()));
        }
        loadTeesTable(itemToEdit.getTees());
        setOnEditListenersToHoleTables();
    }

    @Override
    public void save(View view) {
        //TODO Data validation
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        TextInputEditText resortEditText = mainView.findViewById(R.id.course_resort_edit_text);
        itemToEdit.setResort(resortEditText.getText().toString());
        TextInputEditText nameEditText = mainView.findViewById(R.id.course_name_editText);
        itemToEdit.setCourseName(nameEditText.getText().toString());
        TextInputEditText cityEditText = mainView.findViewById(R.id.course_city_edit_text);
        itemToEdit.setCity(cityEditText.getText().toString());
        TextInputEditText countryEditText = mainView.findViewById(R.id.course_country_edit_text);
        itemToEdit.setCountry(countryEditText.getText().toString());
        for (int i = 1; i < 19; ++i) {
            String hcpString = getHoleEdit(i, hcpTable).getText().toString();
            String parString = getHoleEdit(i, parTable).getText().toString();
            if (hcpString.isEmpty() || parString.isEmpty()) {
                itemToEdit.removeHole(i);
            } else {
                Hole hole = itemToEdit.getHole(i);
                if (hole == null) {
                    hole = new Hole();
                }
                hole.setNumber(i);
                hole.setPar(Integer.parseInt(parString));
                hole.setHcp(Integer.parseInt(hcpString));
                itemToEdit.addHole(hole);
            }
        }
        realm.insertOrUpdate(itemToEdit);
        realm.commitTransaction();
        close(true);
        fireSaveEvent(itemToEdit);
    }

    private TextInputEditText getHoleEdit(int hole, TableLayout table){
        int row_index = hole > 9 ? 3 : 1;
        TableRow row = (TableRow) table.getChildAt(row_index);
        return (TextInputEditText) row.getChildAt((hole - 1) % 9);
    }

    private void loadTeesTable(RealmList<Tee> tees){
        Resources resources = getResources();
        teesTable.clearTable();
        if (tees.isEmpty()) {
            return;
        }
        for (Tee tee : tees){
            IRow<Tee> row = new Row<>(tee);
            ButtonCell<Tee> editButtonCell = new ButtonCell<>(resources.getString(R.string.edit_button_label));
            editButtonCell.setOnClickListener(this::editTee);
            row.addCell(editButtonCell);
            ICell<Tee> srCell = new TextCell<>(String.valueOf(tee.getSr()));
            row.addCell(srCell);
            ICell<Tee> crCell = new TextCell<>(String.valueOf(tee.getCr()));
            row.addCell(crCell);
            ICell<Tee> colorCell = new TextCell<>(tee.getTeeColor().getUiColorName(resources));
            colorCell.setBgColor(tee.getTeeColor().getColor(resources));
            colorCell.setTextColor(resources.getColor(
                    (tee.getTeeColor().secondaryTextColor())? R.color.secondaryTextColor : R.color.primaryTextColor,
                    null));
            row.addCell(colorCell);
            ICell<Tee> nameCell = new TextCell<>(tee.getTeeName());
            row.addCell(nameCell);
            teesTable.addRow(row);
        }
        teesTable.buildTableLayout();
    }

    //Listeners
    public void editTee(View view, Tee tee) {
        EditTeeFragment teeFragment = EditTeeFragment.newInstance(tee);
        getParentFragmentManager().beginTransaction()
                .add(R.id.main_fragment_view,teeFragment)
                .addToBackStack(null)
                .commit();
        teeFragment.setOnResultListener(this);
    }

    public void createTee(View view) {
        EditTeeFragment teeFragment = EditTeeFragment.newInstance(null);
        getParentFragmentManager().beginTransaction()
                .add(R.id.main_fragment_view, teeFragment)
                .addToBackStack(null)
                .commit();
        teeFragment.setOnResultListener(this);
    }

    @Override
    public void onSave(Tee item) {
        if(itemToEdit.isManaged()) {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            itemToEdit.addTee(item);
            realm.commitTransaction();
        } else {
            itemToEdit.addTee(item);
        }
        loadTeesTable(itemToEdit.getTees());
    }

    @Override
    public void onDelete(Tee item) {
        if (!itemToEdit.isManaged()) {
            itemToEdit.removeTee(item);
        }
        loadTeesTable(itemToEdit.getTees());
    }

    @Override
    public void onClose() {
        InputMethodManager imm = (InputMethodManager) getActivity()
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void setOnEditListenersToHoleTables(){
        for (int i = 1; i < 18; ++i) {
            TextInputEditText editText = getHoleEdit(i, parTable);
            int finalI = i;
            editText.setOnEditorActionListener((v, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    getHoleEdit(finalI + 1, parTable).requestFocus();
                    return true;
                }
                return false;
            });
            editText = getHoleEdit(i, hcpTable);
            editText.setOnEditorActionListener((v, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    getHoleEdit(finalI + 1, hcpTable).requestFocus();
                    return true;
                }
                return false;
            });
        }
        TextInputEditText editText = getHoleEdit(18, parTable);
        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                getHoleEdit(1, hcpTable).requestFocus();
                return true;
            }
            return false;
        });
        editText = getHoleEdit(18, hcpTable);
        editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
    }
}