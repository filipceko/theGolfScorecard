package sk.filipceko.golfscorecard;

import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import io.realm.Realm;
import io.realm.RealmResults;
import sk.filipceko.golfscorecard.data.Course;
import sk.filipceko.golfscorecard.table.Row;
import sk.filipceko.golfscorecard.table.Table;
import sk.filipceko.golfscorecard.table.TextCell;

public class CoursesActivity extends AppCompatActivity {

    Table coursesTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        fillCoursesTable();
    }

    private void fillCoursesTable(){

        coursesTable = new Table(findViewById(R.id.coursesTable), this);
        Resources resources = getResources();
        coursesTable.setHeader(
                resources.getString(R.string.course_resort),
                resources.getString(R.string.course_name),
                resources.getString(R.string.course_city),
                resources.getString(R.string.course_country),
                resources.getString(R.string.course_holes)
        );
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Course> courses = realm.where(Course.class).findAll();
        courses.addChangeListener(this::refreshCoursesTable);
        refreshCoursesTable(courses);
    }

    private void refreshCoursesTable(RealmResults<Course> courses){
        coursesTable.clearTable();
        for (Course course : courses) {
            Row<Course> courseRow = new Row<>(this);
            TextCell resortCell = new TextCell(this, course.getResort());
            courseRow.addCell(resortCell);
            TextCell nameCell = new TextCell(this, course.getCourseName());
            courseRow.addCell(nameCell);
            TextCell cityCell = new TextCell(this, course.getCity());
            courseRow.addCell(cityCell);
            TextCell countryCell = new TextCell(this, course.getCountry());
            courseRow.addCell(countryCell);
            TextCell holesCell = new TextCell(this, Integer.toString(course.getNumberOfHoles()));
            courseRow.addCell(holesCell);
            coursesTable.addRow(courseRow);
        }
        coursesTable.buildTableLayout();
    }

    public void createCourse(View view) {
        Intent intent = new Intent(this, CreateCourseActivity.class);
        startActivity(intent);

    }
}