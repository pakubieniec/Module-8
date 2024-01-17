package org.example;

import org.example.datamodel.CustomUser;
import org.example.services.CustomUserDAO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        CustomUserDAO dao = new CustomUserDAO();

        boolean flag = true;
        while (flag) {
            System.out.println("""
                                        
                    ------------------------------------
                    What do you want to do?:
                    1. Add user to database.
                    2. Remove user from database.
                    3. Finding user by email.
                    4. Finding user by id.
                    5. Show all users.
                    6. Exit the application
                    -------------------------------------
                    """);

            int answer = Integer.parseInt(scanner.nextLine());
            switch (answer) {
                case 1 -> {
                    CustomUser user = new CustomUser();
                    System.out.println("Enter your first name:");
                    String firstName = scanner.nextLine();
                    System.out.println("Enter your last name:");
                    String lastName = scanner.nextLine();
                    System.out.println("Enter your email:");
                    String email = scanner.nextLine();
                    System.out.println("Enter your age:");
                    Integer age = Integer.valueOf(scanner.nextLine());
                    user.setName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(email);
                    user.setAge(age);
                    dao.saveUser(user);
                    System.out.println("The user has been added to the database.");
                }
                case 2 -> {
                    System.out.println("Enter the ID to be deleted:");
                    int id = Integer.parseInt(scanner.nextLine());
                    dao.removeStudent(id);
                    System.out.println("The user has been deleted from the database.");
                }
                case 3 -> {
                    System.out.println("Enter the email address by which you want to search for the user:");
                    String email = scanner.nextLine();
                    System.out.println(dao.findUserByEmail(email));

                }
                case 4 -> {
                    System.out.println("Enter the ID by which you want to search for the user:");
                    int id = Integer.parseInt(scanner.nextLine());
                    dao.findUserById(id);
                }
                case 5 -> {
                    System.out.println("List of all users");
                    System.out.println(dao.findAllUsers());
                }
                case 6 -> {
                    System.out.println("Thank you for using the application.");
                    flag = false;
                }
            }
        }
    }
}