package com.pluralsight;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        DealershipFileManager dfm = new DealershipFileManager();
        Dealership dealership = dfm.getDealership();

        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while(running){
            String menu = """
                    Welcome!
                    1) User Menu
                    2) Admin Menu
                    3) Exit
                    """;
            System.out.println(menu);
            System.out.print("Enter corresponding number: ");
            String input = scanner.nextLine();

            switch (input){
                case "1" -> new UserInterface(dealership).display();
                case "2" -> {
                    System.out.println("Launching admin menu...");
                    new AdminUserInterface(dealership).run();
                }
                case "3" ->{
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Error.");
            }
        }
        scanner.close();
    }
}
