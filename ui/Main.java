package ui;

import entity.Course;
import entity.Enrollment;
import entity.Student;
import exception.EntityNotFoundException;
import exception.InvalidInputException;
import Service.CourseService;
import Service.EnrollmentService;
import Service.StudentService;
import util.IdGenerator;
import util.InputValidator;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            showMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        viewAllStudents();
                        break;
                    case 3:
                        searchStudentById();
                        break;
                    case 4:
                        deactivateStudent();
                        break;
                    case 5:
                        addCourse();
                        break;
                    case 6:
                        viewAllCourses();
                        break;
                    case 7:
                        toggleCourseStatus();
                        break;
                    case 8:
                        enrollStudentInCourse();
                        break;
                    case 9:
                        viewEnrollmentsByStudent();
                        break;
                    case 10:
                        updateEnrollmentStatus();
                        break;
                    case 0:
                        running = false;
                        System.out.println("Exiting application. Thank you!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid numeric option.");
            } catch (InvalidInputException | EntityNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n========== LearnTrack Menu ==========");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student By ID");
        System.out.println("4. Deactivate Student");
        System.out.println("5. Add Course");
        System.out.println("6. View All Courses");
        System.out.println("7. Activate/Deactivate Course");
        System.out.println("8. Enroll Student in Course");
        System.out.println("9. View Enrollments By Student");
        System.out.println("10. Update Enrollment Status");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addStudent() {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        InputValidator.validateString(firstName, "First name");

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        InputValidator.validateString(lastName, "Last name");

        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        InputValidator.validateEmail(email);

        System.out.print("Enter batch: ");
        String batch = scanner.nextLine();
        InputValidator.validateString(batch, "Batch");

        Student student = new Student(
                IdGenerator.getNextStudentId(),
                firstName,
                lastName,
                email,
                batch,
                true
        );

        studentService.addStudent(student);
        System.out.println("Student added successfully: " + student);
    }

    private static void viewAllStudents() {
        ArrayList<Student> students = studentService.listStudents();
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void searchStudentById() {
        System.out.print("Enter student ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Student student = studentService.findStudentById(id);
        System.out.println(student);
    }

    private static void deactivateStudent() {
        System.out.print("Enter student ID to deactivate: ");
        int id = Integer.parseInt(scanner.nextLine());
        studentService.deactivateStudent(id);
        System.out.println("Student deactivated successfully.");
    }

    private static void addCourse() {
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        InputValidator.validateString(courseName, "Course name");

        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        InputValidator.validateString(description, "Description");

        System.out.print("Enter duration in weeks: ");
        int duration = Integer.parseInt(scanner.nextLine());
        InputValidator.validatePositiveNumber(duration, "Duration");

        Course course = new Course(
                IdGenerator.getNextCourseId(),
                courseName,
                description,
                duration,
                true
        );

        courseService.addCourse(course);
        System.out.println("Course added successfully: " + course);
    }

    private static void viewAllCourses() {
        ArrayList<Course> courses = courseService.listCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }

        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private static void toggleCourseStatus() {
        System.out.print("Enter course ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        courseService.toggleCourseStatus(id);
        System.out.println("Course status updated successfully.");
    }

    private static void enrollStudentInCourse() {
        System.out.print("Enter student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter course ID: ");
        int courseId = Integer.parseInt(scanner.nextLine());

        Student student = studentService.findStudentById(studentId);
        Course course = courseService.findCourseById(courseId);

        enrollmentService.enrollStudent(student, course);
        System.out.println("Student enrolled successfully.");
    }

    private static void viewEnrollmentsByStudent() {
        System.out.print("Enter student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        ArrayList<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudentId(studentId);

        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found for this student.");
            return;
        }

        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }
    }

    private static void updateEnrollmentStatus() {
        System.out.print("Enter enrollment ID: ");
        int enrollmentId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter new status (COMPLETED/CANCELLED/ENROLLED): ");
        String status = scanner.nextLine().trim().toUpperCase();
        InputValidator.validateString(status, "Status");

        enrollmentService.updateEnrollmentStatus(enrollmentId, status);
        System.out.println("Enrollment status updated successfully.");
    }
}