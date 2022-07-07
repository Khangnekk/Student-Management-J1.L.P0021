
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

//    private static Scanner sc = new Scanner(System.in);
    

    public void createStudent(int count, String msg, ArrayList<Student> stl) {
        Scanner sc = new Scanner(System.in);
        boolean checkYN;
        System.out.println(msg);
        String ID = Validation.checkID("Enter ID: ", "ID must be not null and unique", 1, stl);
        String studentName = Validation.checkName("Enter name: ", "Student name must be not null and unique", 1, stl);
        System.out.print("Enter semester: ");
        String semester = sc.nextLine();
        String course = Validation.checkInputCourse("Enter course: ");

        if (count > 10) {
            checkYN = Validation.checkYesNo("Do you want to continue(Y/N)");
            if (checkYN) {
                stl.add(new Student(ID, studentName, semester, course));
                count++;
                System.err.println("Successful student add");
            }
        } else {
            stl.add(new Student(ID, studentName, semester, course));
            count++;
            System.err.println("Successful student add");
        }
    }

    public void findAndSort(String msg,ArrayList<Student> stl) {
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
        String studentName = Validation.checkName("Enter name to search: ", "Student name must be existed", 2, stl);
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

    public void updateOrDeleteStudent(String msg,ArrayList<Student> stl) {
        Scanner sc = new Scanner(System.in);
        System.out.println(msg);
        String id = findStudentById(stl);
        System.out.println("If user chooses U, the program allows user updating. Choose D for deleting student.");
        boolean checkUD = Validation.checkUpdateDelete("Do you want to update (U) or delete (D) student: ");
        if (checkUD) {
            String newstudentName = Validation.checkName("Enter new name: ", "Student name must not be existed", 1, stl);
            System.out.print("Enter new semester: ");
            String newSemester = sc.nextLine();
            String newCourse = Validation.checkInputCourse("Enter new course: ");
            for (int i = 0; i < stl.size(); i++) {
                if (stl.get(i).getID().equals(id)) {
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

    public void report(String msg) {

    }

}
