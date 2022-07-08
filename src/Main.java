
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zed25
 */
public class Main {
    
    public static void main(String[] args) {
        ArrayList<Student> stl = new ArrayList<>();
        Manager mng = new Manager();
        // Loop until user want to exit program
        while (true) {
            menu("WELCOME TO STUDENT MANAGEMENT");
            int choice = Validation.checkInt("Your choice: ", 1, 5);
            switch(choice){
                case 1:
                    mng.createStudent(3, "--- Create ---",stl);
                    break;
                case 2:
                    mng.findAndSort("--- Find and sort ---",stl);
                    break;
                case 3:
                    mng.updateOrDeleteStudent("--- Update and delete ---",stl);
                    break;
                case 4:
                    mng.report("--- Report ---",stl);
                    break;
                case 5:
                    return;
            }
        }
    }

    public static void menu(String msg) {
        System.out.println(msg);
        System.out.println("1. Create");
        System.out.println("2. Find and sort");
        System.out.println("3. Update/Delete");
        System.out.println("4. Report");
        System.out.println("5. Exit");
    }
}
