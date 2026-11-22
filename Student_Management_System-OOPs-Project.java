import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private int roll;
    private String name;
    private String course;
    private double marks;

    public Student(int roll, String name, String course, double marks) {
        this.roll = roll;
        this.name = name;
        this.course = course;
        this.marks = marks;
    }

    public int getRoll() {
        return roll;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public double getMarks() {
        return marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public String grade() {
        if (marks >= 90) return "A";
        else if (marks >= 80) return "B";
        else if (marks >= 60) return "C";
        else if (marks >= 40) return "D";
        else return "Fail";
    }

    public void display() {
        System.out.println("----------------------------");
        System.out.println("Roll   : " + roll);
        System.out.println("Name   : " + name);
        System.out.println("Course : " + course);
        System.out.println("Marks  : " + marks);
        System.out.println("Grade  : " + grade());
        System.out.println("----------------------------");
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();

    public static void addStudent() {
        System.out.print("Enter roll number: ");
        int roll = sc.nextInt();
        sc.nextLine();

        for (Student s : students) {
            if (s.getRoll() == roll) {
                System.out.println("Roll number already exists!");
                return;
            }
        }

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter course: ");
        String course = sc.nextLine();

        System.out.print("Enter marks (0-100): ");
        double marks = sc.nextDouble();
        sc.nextLine();

        if (marks < 0 || marks > 100) {
            System.out.println("Marks must be between 0 and 100!");
            return;
        }

        students.add(new Student(roll, name, course, marks));
        System.out.println("Student added successfully!");
    }

    public static void deleteStudent() {
        System.out.print("Enter roll number to delete: ");
        int roll = sc.nextInt();
        sc.nextLine();

        for (Student s : students) {
            if (s.getRoll() == roll) {
                students.remove(s);
                System.out.println("Student deleted successfully!");
                return;
            }
        }

        System.out.println("Student not found!");
    }

    public static void updateStudent() {
        System.out.print("Enter roll number to update: ");
        int roll = sc.nextInt();
        sc.nextLine();

        for (Student s : students) {
            if (s.getRoll() == roll) {
                System.out.print("Enter new name: ");
                s.setName(sc.nextLine());

                System.out.print("Enter new course: ");
                s.setCourse(sc.nextLine());

                System.out.print("Enter new marks (0-100): ");
                double marks = sc.nextDouble();
                sc.nextLine();

                if (marks < 0 || marks > 100) {
                    System.out.println("Invalid marks! Update cancelled for marks.");
                    return;
                }

                s.setMarks(marks);
                System.out.println("Student updated successfully!");
                return;
            }
        }

        System.out.println("Student not found!");
    }

    public static void searchStudent() {
        System.out.print("Enter roll number to search: ");
        int roll = sc.nextInt();
        sc.nextLine();

        for (Student s : students) {
            if (s.getRoll() == roll) {
                s.display();
                return;
            }
        }

        System.out.println("Student not found!");
    }

    public static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records available!");
            return;
        }

        for (Student s : students) {
            s.display();
        }
    }

    public static void main(String[] args) {
        int option;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Update Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Enter your option: ");

            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    deleteStudent();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    searchStudent();
                    break;
                case 5:
                    displayAllStudents();
                    break;
                case 6:
                    System.out.println("Thank you for using SMS!");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        } while (option != 6);

        sc.close();
    }
}