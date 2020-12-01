package sk.filipceko.golfscorecard;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.button.MaterialButton;
import io.realm.Realm;
import io.realm.RealmResults;
import sk.filipceko.golfscorecard.data.Course;
import sk.filipceko.golfscorecard.table.Row;
import sk.filipceko.golfscorecard.table.Table;
import sk.filipceko.golfscorecard.table.TextCell;
import sk.filipceko.golfscorecard.table.interfaces.ITable;

public class CoursesFragment extends Fragment implements ICreateEditDeleteView.OnResultListener<Course> {

    ITable coursesTable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_courses, container, false);
        coursesTable = new Table(view.findViewById(R.id.courses_table), getContext());
        Resources resources = getResources();
        coursesTable.setHeader(
                resources.getString(R.string.course_resort),
                resources.getString(R.string.course_name),
                resources.getString(R.string.course_city),
                resources.getString(R.string.course_country),
                resources.getString(R.string.course_holes),
                resources.getString(R.string.course_tees_size)
        );
        refreshTable();
        MaterialButton createCourseButton = view.findViewById(R.id.create_course_button);
        createCourseButton.setOnClickListener(this::createCourse);
        return view;
    }

    private void refreshTable() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Course> courses = realm.where(Course.class).findAll();
        coursesTable.clearTable();
        for (Course course : courses) {
            Row<Course> courseRow = new Row<>(getContext(), course);
            TextCell<Course> teesCell = new TextCell<>(getContext(), Integer.toString(course.getTees().size()));
            courseRow.addCell(teesCell);
            TextCell<Course> holesCell = new TextCell<>(getContext(), Integer.toString(course.getNumberOfHoles()));
            courseRow.addCell(holesCell);
            TextCell<Course> countryCell = new TextCell<>(getContext(), course.getCountry());
            courseRow.addCell(countryCell);
            TextCell<Course> cityCell = new TextCell<>(getContext(), course.getCity());
            courseRow.addCell(cityCell);
            TextCell<Course> nameCell = new TextCell<>(getContext(), course.getCourseName());
            courseRow.addCell(nameCell);
            TextCell<Course> resortCell = new TextCell<>(getContext(), course.getResort());
            courseRow.addCell(resortCell);
            courseRow.setOnClickListener(this::editCourse);
            coursesTable.addRow(courseRow);
        }
        coursesTable.buildTableLayout();
    }

    public void editCourse(View view, Course course) {
        EditCourseFragment editCourseFragment = EditCourseFragment.newInstance(course);
        getParentFragmentManager().beginTransaction()
                .add(R.id.main_fragment_view, editCourseFragment)
                .addToBackStack(null)
                .commit();
        editCourseFragment.setOnResultListener(this);
    }

    public void createCourse(View view) {
        EditCourseFragment editCourseFragment = EditCourseFragment.newInstance(null);
        getParentFragmentManager().beginTransaction()
                .add(R.id.main_fragment_view, editCourseFragment)
                .addToBackStack(null)
                .commit();
        editCourseFragment.setOnResultListener(this);
    }

    @Override
    public void onSave(Course item) {
        refreshTable();
    }

    @Override
    public void onDelete(Course item) {
        refreshTable();
    }

    @Override
    public void onClose() {
        //Nothing to do
    }
}