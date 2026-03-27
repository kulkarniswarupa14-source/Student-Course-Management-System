package util;

public class IdGenerator {
    private static int studentIdCounter = 1001;
    private static int courseIdCounter = 2001;
    private static int enrollmentIdCounter = 3001;

    public static int getNextStudentId() {
        return studentIdCounter++;
    }

    public static int getNextCourseId() {
        return courseIdCounter++;
    }

    public static int getNextEnrollmentId() {
        return enrollmentIdCounter++;
    }
}