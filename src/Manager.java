
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
        while (true) {
            System.out.println(msg);
            System.out.print("Enter ID: ");
            String ID = Validation.checkInputString();
            System.out.print("Enter name: ");
            String studentName = Validation.checkInputString();
            if (Validation.idExist(ID, stl) && !Validation.nameExist(studentName, stl)) {
                System.out.println("ID has exist student, Please re-input");
                continue;
            }

            System.out.print("Enter semester: ");
            String semester = Validation.checkInputString();
            String course = Validation.checkInputCourse("Enter course: ");

            if (count > 10) {
                checkYN = Validation.checkYesNo("Do you want to continue(Y/N)");
                if (checkYN) {
                    if (Validation.checkStudentExist(stl, ID, studentName, semester, course)) {
                        stl.add(new Student(ID, studentName, semester, course));
                        count++;
                        System.err.println("Successful student add");
                        return;
                    }
                }
            } else {
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
        if (stl.isEmpty()) {
            System.out.println("Empty List");
            return;
        }
        ArrayList<Student> listStudentFoundByName = makeListStudentFindByName(stl);
        if (listStudentFoundByName.isEmpty()) {
            System.out.println("This student not exist");
        } else {
            Collections.sort(listStudentFoundByName);
            System.out.printf("%-20s %-15s %-15s\n", "Student name", "semester", "Course Name");
            for (int i = 0; i < listStudentFoundByName.size(); i++) {
                listStudentFoundByName.get(i).print();
            }
        }
    }

    public ArrayList<Student> makeListStudentFindByName(ArrayList<Student> stl) {
        ArrayList<Student> listStudentFoundByName = new ArrayList<>();
        System.out.print("Enter name to search: ");
        String studentName = Validation.checkInputString();
        for (int i = 0; i < stl.size(); i++) {
            if (stl.get(i).getStudentName().contains(studentName)) {
                Student stFindByName = stl.get(i);
                listStudentFoundByName.add(stFindByName);
            }
        }
        return listStudentFoundByName;
    }

    public String findStudentById(ArrayList<Student> stl) {
        String id = Validation.checkID("Enter id to continue: ", "Id must be existed", 2, stl);
        for (int i = 0; i < stl.size(); i++) {
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
        if (checkUD) {
            String newstudentName = Validation.checkName("Enter name: ", "Student name must be existed", 2, stl);
            System.out.print("Enter new semester: ");
            String newSemester = sc.nextLine();
            String newCourse = Validation.checkInputCourse("Enter new course: ");
            for (int i = 0; i < stl.size(); i++) {
                if (Validation.checkStudentExist(stl, id, newstudentName, newSemester, newCourse)) {
                    stl.get(i).setStudentName(newstudentName);
                    stl.get(i).setSemester(newSemester);
                    stl.get(i).setCourseName(newCourse);
                    System.err.println("Successful student update");
                }
            }
        } else {
            for (int i = 0; i < stl.size(); i++) {
                if (stl.get(i).getID().equals(id)) {
                    stl.remove(i);
                    System.err.println("Successful student delete");
                }
            }
        }
    }

    public static void report(String msg, ArrayList<Student> ls) {
        if (ls.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        ArrayList<Report> lr = new ArrayList<>();
        for (Student student1 : ls) {
            int total = 0;
            for (Student student2 : ls) {
                if (student1.getID().equalsIgnoreCase(student2.getID())
                        && student1.getCourseName().equalsIgnoreCase(student2.getCourseName())) {
                    total++;
                }
            }
            if (Validation.checkReportExist(lr, student1.getStudentName(),
                    student1.getCourseName(), total)) {
                Report rp = new Report(student1, total);
                lr.add(rp);
            }
            total = 0;
        }
        for (int i = 0; i < lr.size(); i++) {
            System.out.printf("%-20s|%-10s|%-5d\n", lr.get(i).getStudent().getStudentName(),
                    lr.get(i).getStudent().getCourseName(), lr.get(i).getTotalCourse());
        }
    }

}
