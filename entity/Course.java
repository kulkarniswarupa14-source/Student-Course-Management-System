package entity;

public class Course {
    private int id;
    private String courseName;
    private String description;
    private int durationInWeeks;
    private boolean active;

    public Course() {
        this.active = true;
    }

    public Course(int id, String courseName, String description, int durationInWeeks, boolean active) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
        this.durationInWeeks = durationInWeeks;
        this.active = active;
    }

    // Constructor overloading
    public Course(int id, String courseName, int durationInWeeks) {
        this.id = id;
        this.courseName = courseName;
        this.description = "";
        this.durationInWeeks = durationInWeeks;
        this.active = true;
    }

    public int getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public int getDurationInWeeks() {
        return durationInWeeks;
    }

    public boolean isActive() {
        return active;
    }

    public void toggleStatus() {
        this.active = !this.active;
    }

    @Override
    public String toString() {
        return "Course{id=" + id +
                ", courseName='" + courseName + '\'' +
                ", description='" + description + '\'' +
                ", durationInWeeks=" + durationInWeeks +
                ", active=" + active +
                '}';
    }
}