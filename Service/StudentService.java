package Service;

import entity.Student;
import exception.EntityNotFoundException;

import java.util.ArrayList;

public class StudentService {
    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public ArrayList<Student> listStudents() {
        return students;
    }

    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        throw new EntityNotFoundException("Student not found with id: " + id);
    }

    public void deactivateStudent(int id) {
        Student student = findStudentById(id);
        student.deactivate();
    }
}