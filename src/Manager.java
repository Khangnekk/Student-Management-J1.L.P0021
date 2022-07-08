
import java.util.ArrayList;
import java.util.Collections;
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
public class Manager {

    public void createStudent(int count, String msg, ArrayList<Student> stl) {
        boolean checkYN;
        // Loop use to run until add student success
        while (true) {
            System.out.println(msg);
            System.out.print("Enter ID: ");
            String ID = Validation.checkInputString();
            System.out.print("Enter name: ");
            String studentName = Validation.checkInputString();
            // Condition use to check id exist and name is not exist
            if (Validation.idExist(ID, stl) && !Validation.nameExist(studentName, stl)) {
                System.out.println("ID has exist other student, Please re-input");
                continue;
            }

            System.out.print("Enter semester: ");
            String semester = Validation.checkInputString();
            String course = Validation.checkInputCourse("Enter course: ");
            // Compare number of student greater than 10 (ask user continue or not)
            if (count > 10) {
                checkYN = Validation.checkYesNo("Do you want to continue(Y/N)");
                // Condition use to confirm user want to continue
                if (checkYN) {
                    // Condition use to check this student with this info is not exist
                    if (Validation.checkStudentExist(stl, ID, studentName, semester, course)) {
                        stl.add(new Student(ID, studentName, semester, course));
                        count++;
                        System.err.println("Successful student add");
                        return;
                    }
                }
            } else {
                // Condition use to check this student with this info is not exist
                if (Validation.checkStudentExist(stl, ID, studentName, semester, course)) {
                    stl.add(new Student(ID, studentName, semester, course));
                    count++;
                    System.err.println("Successful student add");
                    return;
                }
            }
            System.err.println("Duplicate.");
        }
    }

    public void findAndSort(String msg, ArrayList<Student> stl) {
        System.out.println(msg);
        // Condition use to check list student is empty
        if (stl.isEmpty()) {
            System.out.println("Empty List");
            return;
        }
        ArrayList<Student> listStudentFoundByName = makeListStudentFindByName(stl);
        // Condition use to check list student found by name is empty
        if (listStudentFoundByName.isEmpty()) {
            System.out.println("This student not exist");
        } else {
            Collections.sort(listStudentFoundByName);
            System.out.printf("%-20s %-15s %-15s\n", "Student name", "semester", "Course Name");
            // Loop use to run from begining of list student found by name to end of this list
            for (int i = 0; i < listStudentFoundByName.size(); i++) {
                listStudentFoundByName.get(i).print();
            }
        }
    }

    public ArrayList<Student> makeListStudentFindByName(ArrayList<Student> stl) {
        ArrayList<Student> listStudentFoundByName = new ArrayList<>();
        System.out.print("Enter name to search: ");
        String studentName = Validation.checkInputString();
        // Loop use to run from begining of list student to end of this list
        for (int i = 0; i < stl.size(); i++) {
            // Condition use to check name of student at position i in list 
            // student stl contains name of student user enter
            if (stl.get(i).getStudentName().contains(studentName)) {
                Student stFindByName = stl.get(i);
                listStudentFoundByName.add(stFindByName);
            }
        }
        return listStudentFoundByName;
    }

    public String findStudentById(ArrayList<Student> stl) {
        String id = Validation.checkID("Enter id to continue: ", "Id must be existed", 2, stl);
        // Loop use to run from begining to end of list stl
        for (int i = 0; i < stl.size(); i++) {
            // Compare id at position i in list stl equals id user enter
            if (stl.get(i).getID().equals(id)) {
                stl.get(i).toString();
            }
        }
        return id;
    }

    public void updateOrDeleteStudent(String msg, ArrayList<Student> stl) {
        Scanner sc = new Scanner(System.in);
        System.out.println(msg);
        String id = findStudentById(stl);
        System.out.println("If user chooses U, the program allows user updating. Choose D for deleting student.");
        boolean checkUD = Validation.checkUpdateDelete("Do you want to update (U) or delete (D) student: ");
        // Condition use to confirm user want to update
        if (checkUD) {
            String newstudentName = Validation.checkName("Enter name: ", "Student name must be existed", 2, stl);
            System.out.print("Enter new semester: ");
            String newSemester = sc.nextLine();
            String newCourse = Validation.checkInputCourse("Enter new course: ");
            // Loop use to run from begining to end of list stl
            for (int i = 0; i < stl.size(); i++) {
                // Condition use to check this student with this info is not exist
                if (Validation.checkStudentExist(stl, id, newstudentName, newSemester, newCourse)) {
                    stl.get(i).setStudentName(newstudentName);
                    stl.get(i).setSemester(newSemester);
                    stl.get(i).setCourseName(newCourse);
                    System.err.println("Successful student update");
                }else{
                    System.err.println("Duplicate.");
                }
            }
        } else {
            // Loop use to run from begining to end of list stl
            for (int i = 0; i < stl.size(); i++) {
                // Condition use to check id at position i in list stl equals id user enter
                if (stl.get(i).getID().equals(id)) {
                    stl.remove(i);
                    System.err.println("Successful student delete");
                }
            }
        }
    }

    public static void report(String msg, ArrayList<Student> ls) {
        // Condition use to check list student is empty
        if (ls.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        ArrayList<Report> lr = new ArrayList<>();
        // Loop use to traverse from the beginning to the end of the List ls
        for (Student student1 : ls) {
            int total = 0;
            // Loop use to traverse from the beginning to the end of the List ls
            for (Student student2 : ls) {
                // The condition used to check if there is an id when traversing the array with student1 
                // is equal to the id when traversing the array with student2 and courseName is same
                if (student1.getID().equalsIgnoreCase(student2.getID())
                        && student1.getCourseName().equalsIgnoreCase(student2.getCourseName())) {
                    total++;
                }
            }
            // Condition use to check this report is not exist in list lr 
            if (Validation.checkReportExist(lr, student1.getStudentName(),
                    student1.getCourseName(), total)) {
                Report rp = new Report(student1, total);
                lr.add(rp);
            }
            total = 0;
        }
        // Loop use to run from begining to end of list lr to print report data
        for (int i = 0; i < lr.size(); i++) {
            System.out.printf("%-20s|%-10s|%-5d\n", lr.get(i).getStudent().getStudentName(),
                    lr.get(i).getStudent().getCourseName(), lr.get(i).getTotalCourse());
        }
    }

}
