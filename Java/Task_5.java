import java.util.*;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private int enrolledStudents;
    private String schedule;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = 0;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public String getSchedule() {
        return schedule;
    }

    public boolean isAvailable() {
        return enrolledStudents < capacity;
    }

    public void enrollStudent() {
        if (isAvailable()) {
            enrolledStudents++;
        }
    }

    public void dropStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
        }
    }
}

class Student {
    private String studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        if (course.isAvailable() && !registeredCourses.contains(course)) {
            registeredCourses.add(course);
            course.enrollStudent();
            System.out.println("Successfully registered for: " + course.getTitle());
        } else {
            System.out.println("Cannot register for the course. It may be full or already registered.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.dropStudent();
            System.out.println("Successfully dropped: " + course.getTitle());
        } else {
            System.out.println("You are not registered for this course.");
        }
    }
}

public class Task_5 {
    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int option;
        do {
            System.out.println("\n--- Course Management System ---");
            System.out.println("1. Add Course");
            System.out.println("2. Add Student");
            System.out.println("3. List Available Courses");
            System.out.println("4. Register Student for Course");
            System.out.println("5. Drop Course for Student");
            System.out.println("6. View Student Courses");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    addCourse(scanner);
                    break;
                case 2:
                    addStudent(scanner);
                    break;
                case 3:
                    listAvailableCourses();
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    String regStudentID = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    String regCourseCode = scanner.nextLine();
                    registerStudentForCourse(regStudentID, regCourseCode);
                    break;
                case 5:
                    System.out.print("Enter Student ID: ");
                    String dropStudentID = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    String dropCourseCode = scanner.nextLine();
                    dropCourseForStudent(dropStudentID, dropCourseCode);
                    break;
                case 6:
                    System.out.print("Enter Student ID: ");
                    String viewStudentID = scanner.nextLine();
                    viewStudentCourses(viewStudentID);
                    break;
                case 7:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 7);

        scanner.close();
    }

    private static void addCourse(Scanner scanner) {
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Schedule: ");
        String schedule = scanner.nextLine();

        courses.add(new Course(courseCode, title, description, capacity, schedule));
        System.out.println("Course added successfully!");
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        students.add(new Student(studentID, name));
        System.out.println("Student added successfully!");
    }

    private static void listAvailableCourses() {
        System.out.println("\n--- Available Courses ---");
        for (Course course : courses) {
            System.out.println("Course Code: " + course.getCourseCode());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println("Capacity: " + course.getEnrolledStudents() + "/" + course.getCapacity());
            System.out.println("-------------------------");
        }
    }

    private static void registerStudentForCourse(String studentID, String courseCode) {
        Student student = findStudentByID(studentID);
        Course course = findCourseByCode(courseCode);

        if (student != null && course != null) {
            student.registerCourse(course);
        } else {
            System.out.println("Invalid student ID or course code.");
        }
    }

    private static void dropCourseForStudent(String studentID, String courseCode) {
        Student student = findStudentByID(studentID);
        Course course = findCourseByCode(courseCode);

        if (student != null && course != null) {
            student.dropCourse(course);
        } else {
            System.out.println("Invalid student ID or course code.");
        }
    }

    private static void viewStudentCourses(String studentID) {
        Student student = findStudentByID(studentID);

        if (student != null) {
            System.out.println("\n--- Courses for " + student.getName() + " ---");
            for (Course course : student.getRegisteredCourses()) {
                System.out.println("Course Code: " + course.getCourseCode() + ", Title: " + course.getTitle());
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private static Student findStudentByID(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
}
