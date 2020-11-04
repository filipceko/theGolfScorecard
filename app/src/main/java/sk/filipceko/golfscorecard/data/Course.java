package sk.filipceko.golfscorecard.data;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
import org.bson.types.ObjectId;

public class Course extends RealmObject {
    @PrimaryKey
    private ObjectId courseId = new ObjectId();
    @Required
    private String resort;
    @Required
    private String courseName;
    private String City;
    private String Country;
    private RealmList<Tee> tees;
    private RealmList<Hole> holes;


    public RealmList<Tee> getTees() {
        return tees;
    }

    public void setTees(RealmList<Tee> tees) {
        this.tees = tees;
    }

    public RealmList<Hole> getHoles() {
        return holes;
    }

    public void setHoles(RealmList<Hole> holes) {
        this.holes = holes;
    }

    public Hole getHole(int holeNumber){
        return holes.get(holeNumber);
    }

    public int getNumberOfHoles(){
        return holes.size();
    }

    public ObjectId getCourseId() {
        return courseId;
    }

    public void setCourseId(ObjectId courseId) {
        this.courseId = courseId;
    }

    public String getResort() {
        return resort;
    }

    public void setResort(String resort) {
        this.resort = resort;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }
}
