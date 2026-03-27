package Service;

import entity.Course;
import entity.Enrollment;
import entity.Student;
import exception.EntityNotFoundException;
import exception.InvalidInputException;
import util.IdGenerator;

import java.time.LocalDate;
import java.util.ArrayList;

public class EnrollmentService {
    private ArrayList<Enrollment> enrollments = new ArrayList<>();

    public void enrollStudent(Student student, Course course) {
        if (!student.isActive()) {
            throw new InvalidInputException("Cannot enroll inactive student.");
        }

        if (!course.isActive()) {
            throw new InvalidInputException("Cannot enroll in inactive course.");
        }

        Enrollment enrollment = new Enrollment(
                IdGenerator.getNextEnrollmentId(),
                student.getId(),
                course.getId(),
                LocalDate.now().toString(),
                "ENROLLED"
        );

        enrollments.add(enrollment);
    }

    public ArrayList<Enrollment> getAllEnrollments() {
        return enrollments;
    }

    public ArrayList<Enrollment> getEnrollmentsByStudentId(int studentId) {
        ArrayList<Enrollment> result = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId) {
                result.add(enrollment);
            }
        }
        return result;
    }

    public Enrollment findEnrollmentById(int id) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId() == id) {
                return enrollment;
            }
        }
        throw new EntityNotFoundException("Enrollment not found with id: " + id);
    }

    public void updateEnrollmentStatus(int enrollmentId, String status) {
        Enrollment enrollment = findEnrollmentById(enrollmentId);
        enrollment.updateStatus(status);
    }
}