
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author zed25
 */
public class Validation {

//    private static Scanner sc = new Scanner(System.in);
    public static int checkInt(String msg, int min, int max) {
        Scanner sc = new Scanner(System.in);
        String input;
        double output;
        // Loop use to util output in corect format
        do {
            System.out.print(msg);
            input = sc.nextLine();
            // Check the existence of Input
            if (input.isEmpty()) {
                System.out.println("Input could not be Empty. Please enter positive integer");
                continue;
            }
            try {
                output = Double.parseDouble(input);
                // compare value of input with output
                if ((int) output != output) {
                    throw new Error();
                }
                // Compare output less than min or output greater than max
                if (output < min || output > max) {
                    System.out.println("Please enter in range " + min + " to " + max);
                    continue;
                }
                break;
            } catch (NumberFormatException exception) {
                System.out.println("Input could not be a string. Please enter positive integer");
                continue;
            } catch (Error RealNum) {
                System.out.println("Input could not be a real number. Please enter positive integer");
                continue;
            }
        } while (true);
        return (int) output;
    }

    public static String checkInputCourse(String msg) {
        Scanner sc = new Scanner(System.in);
        String course;
        // Loop until user enter correct course name
        while (true) {
            System.out.print(msg);
            course = sc.nextLine();
            // Condition use to check course is empty
            if (course.isEmpty()) {
                System.out.println("Input could not be Empty.");
            } else {
                // Condition use to check course equls Java or course equals .Net or course equals C/C++
                if (course.equalsIgnoreCase("Java")
                        || course.equalsIgnoreCase(".Net")
                        || course.equalsIgnoreCase("C/C++")
                        || course.equalsIgnoreCase("java")
                        || course.equalsIgnoreCase(".net")
                        || course.equalsIgnoreCase("c/c++")) {
                    return course;
                }
                System.err.println("There are only three courses: Java, .Net, C/C++");
            }
        }

    }

    public static boolean checkYesNo(String msg) {
        Scanner sc = new Scanner(System.in);
        String input;
        System.out.println(msg);
        input = sc.nextLine();
        // Loop until user enter correct 'y' or 'n'
        while (true) {
            // Condition use to check input is empty
            if (input.isEmpty()) {
                System.out.println("Input could not be empty");
            } else {
                // Condition use to check input equals 'Y' or 'y'
                if (input.equals("Y") || input.equals("y")) {
                    return true;
                }
                // Condition use to check input equals 'N' or 'n'
                else if (input.equals("N") || input.equals("n")) {
                    return false;
                } else {
                    System.out.println("Please enter Yes(Y) or No(N)");
                }
            }
        }
    }

    public static boolean checkUpdateDelete(String msg) {
        Scanner sc = new Scanner(System.in);
        String input;
        System.out.print(msg);
        input = sc.nextLine();
        // Loop run until user enter 'u' or 'd'
        while (true) {
            // Condition use to check input is empty
            if (input.isEmpty()) {
                System.out.println("Input could not be empty");              
            } else {
                // Condition use to check input equals 'U' or 'u'
                if (input.equals("U") || input.equals("u")) {
                    return true;
                } 
                // Condition use to check input equals 'D' or 'd'
                else if (input.equals("D") || input.equals("d")) {
                    return false;
                } else {
                    System.out.println("Please enter (U) to update or (D) to delete");
                }
            }

        }
    }

    public static String checkInputString() {
        Scanner sc = new Scanner(System.in);
        //loop until user input correct
        while (true) {
            String result = sc.nextLine().trim();
            // Condition use to check input is empty
            if (result.isEmpty()) {
                System.err.println("Not empty");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    public static boolean checkStudentExist(ArrayList<Student> ls, String id,
            String studentName, String semester, String courseName) {
        // Loop use  to traverse list ls with student
        for (Student student : ls) {
            // Condition use to check id at parameter equals id when traverse list ls with student
            // studentName,semester,courseName are same for id
            if (    id.equalsIgnoreCase(student.getID())
                    && studentName.equalsIgnoreCase(student.getStudentName())
                    && semester.equalsIgnoreCase(student.getSemester())
                    && courseName.equalsIgnoreCase(student.getCourseName())) {
                return false;
            }
        }
        return true;
    }

    public static String checkID(String msg, String err, int mode, List<Student> st) {
        Scanner sc = new Scanner(System.in);
        String ID;
        // Loop use to run until 
        // (mode equals 1 and code is not exist in List Student) or
        //  (mode equals 2 and code is exist in List Student)
        while (true) {
            System.out.print(msg);
            ID = sc.nextLine();
            // Check the existence of Input
            if (ID.isEmpty()) {
                System.out.println("Input could not be Empty.");
                continue;
            }
            // Compare mode equals 1 and code is not exist in List Student or
            // mode equals 2 and code is exist in List Student
            if ((mode == 1 && !idExist(ID, st)) || (mode == 2 && idExist(ID, st))) {
                break;
            } else {
                System.out.println(err);
            }
        }
        return ID;
    }

    public static String checkName(String msg, String err, int mode, List<Student> st) {
        Scanner sc = new Scanner(System.in);
        String name;
        // Loop use to run until 
        // (mode equals 1 and code is not exist in List Student) or
        //  (mode equals 2 and code is exist in List Student)
        while (true) {
            System.out.print(msg);
            name = sc.nextLine();
            // Check the existence of Input
            if (name.isEmpty()) {
                System.out.println("Input could not be Empty.");
                continue;
            }
            // Compare mode equals 1 and name is not exist in List Student or
            // mode equals 2 and name is exist in List Student
            if ((mode == 1 && !nameExist(name, st)) || (mode == 2 && nameExist(name, st))) {
                break;
            } else {
                System.out.println(err);
                continue;
            }
        }
        return name;
    }

    public static boolean idExist(String ID, List<Student> st) {
        // Loop use to run from begining of List st to end of this List
        for (int i = 0; i < st.size(); i++) {
            // Compare id at position i in List st equals code
            if (st.get(i).getID().equals(ID)) {
                return true;
            }
        }
        return false;
    }

    public static boolean nameExist(String ID, List<Student> st) {
        // Loop use to run from begining of List st to end of this List
        for (int i = 0; i < st.size(); i++) {
            // Compare id at position i in List st equals code
            if (st.get(i).getStudentName().equals(ID)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkReportExist(ArrayList<Report> lr, String name,
            String course, int total) {
        // traverse list lr
        for (Report report : lr) {
            // Condition use to check name equals name of student at list report when traverse with report
            // is equals parameter, total and course are same for name
            if (name.equalsIgnoreCase(report.getStudent().getStudentName())
                    && total == report.getTotalCourse()
                    && course.equals(report.getStudent().getCourseName())) {
                return false;
            }
        }
        return true;
    }
}
