package sk.filipceko.golfscorecard;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TableLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.button.MaterialButton;
import io.realm.Realm;
import io.realm.RealmResults;
import sk.filipceko.golfscorecard.data.Course;
import sk.filipceko.golfscorecard.table.Row;
import sk.filipceko.golfscorecard.table.Table;
import sk.filipceko.golfscorecard.table.TextCell;
import sk.filipceko.golfscorecard.table.interfaces.IRow;
import sk.filipceko.golfscorecard.table.interfaces.ITable;
import sk.filipceko.golfscorecard.table.utils.colorManager.IColorManager;

public class SelectCourseFragment extends Fragment {

    IRow<Course> selectedRow = null;
    MaterialButton startGameButton;

    public static SelectCourseFragment newInstance() {
        return new SelectCourseFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_course, container, false);
        loadTable(view.findViewById(R.id.courses_table));
        startGameButton = view.findViewById(R.id.start_game_button);
        return view;
    }

    private void loadTable(TableLayout tableView){
        ITable coursesTable = new Table(tableView);
        Resources resources = getResources();
        coursesTable.setHeader(
                resources.getString(R.string.course_resort),
                resources.getString(R.string.course_name),
                resources.getString(R.string.course_city),
                resources.getString(R.string.course_holes)
        );
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Course> courses = realm.where(Course.class).findAll();
        for (Course course : courses){
            IRow<Course> row = new Row<>();
            TextCell<Course> holesCell = new TextCell<>(Integer.toString(course.getNumberOfHoles()));
            row.addCell(holesCell);
            TextCell<Course> cityCell = new TextCell<>(course.getCity());
            row.addCell(cityCell);
            TextCell<Course> nameCell = new TextCell<>(course.getCourseName());
            row.addCell(nameCell);
            TextCell<Course> resortCell = new TextCell<>(course.getResort());
            row.addCell(resortCell);
            row.setOnClickListener(this::onSelect);
            coursesTable.addRow(row);
        }
        coursesTable.buildView(getContext());
    }

    public void onSelect(View view, IRow<Course> row){
        if (selectedRow != null) {
            selectedRow.setBgColor(IColorManager.DEFAULT_COLOR, true);
        }
        selectedRow = row;
        int color = getResources().getColor(R.color.primaryDarkColor, null);
        row.setBgColor(color, true);
    }
}