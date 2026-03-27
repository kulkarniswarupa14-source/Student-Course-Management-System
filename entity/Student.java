package entity;

public class Student extends Person {
    private String batch;
    private boolean active;

    public Student() {
        this.active = true;
    }

    public Student(int id, String firstName, String lastName, String email, String batch, boolean active) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = active;
    }

    // Constructor overloading
    public Student(int id, String firstName, String lastName, String batch) {
        super(id, firstName, lastName, "");
        this.batch = batch;
        this.active = true;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        this.active = false;
    }

    @Override
    public String getDisplayName() {
        return "Student: " + firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Student{id=" + id +
                ", name='" + getDisplayName() + '\'' +
                ", email='" + email + '\'' +
                ", batch='" + batch + '\'' +
                ", active=" + active +
                '}';
    }
}