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
    private RealmList<Tee> tees = new RealmList<>();
    private RealmList<Hole> holes = new RealmList<>();


    public RealmList<Tee> getTees() {
        return tees;
    }

    public void addTee(Tee newTee){
        for (Tee oldTee : tees) {
            if (oldTee.equals(newTee)) {
                oldTee.setTeeName(newTee.getTeeName());
                oldTee.setTeeColor(newTee.getTeeColor());
                oldTee.setCr(newTee.getCr());
                oldTee.setSr(newTee.getSr());
                return;
            }
        }
        tees.add(newTee);
    }

    public void removeTee(Tee tee) {
        tees.remove(tee);
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
        for (Hole hole : holes) {
            if (hole.getNumber() == holeNumber){
                return hole;
            }
        }
        return null;
    }

    public void addHole(Hole hole) {
        Hole oldHole = getHole(hole.getNumber());
        if (oldHole ==null) {
            holes.add(hole);
            return;
        }
        oldHole.setPar(hole.getPar());
        oldHole.setHcp(hole.getHcp());
    }

    public void removeHole(int holeNumber) {
        Hole hole = getHole(holeNumber);
        if (hole != null) {
            holes.remove(hole);
            hole.deleteFromRealm();
        }
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
