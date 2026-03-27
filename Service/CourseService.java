package Service;

import entity.Course;
import exception.EntityNotFoundException;

import java.util.ArrayList;

public class CourseService {
    private ArrayList<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public ArrayList<Course> listCourses() {
        return courses;
    }

    public Course findCourseById(int id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        throw new EntityNotFoundException("Course not found with id: " + id);
    }

    public void toggleCourseStatus(int id) {
        Course course = findCourseById(id);
        course.toggleStatus();
    }
}