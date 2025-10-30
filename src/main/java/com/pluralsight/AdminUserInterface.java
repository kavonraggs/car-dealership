package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminUserInterface {
    Scanner scanner = new Scanner(System.in);
    ContractDataManager cdm = new ContractDataManager();


    public AdminUserInterface(Dealership dealership) {

    }


    public void run(){
        if (!login()) {
            return;
        }
        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Enter Corresponding Number: ");
            String input = scanner.nextLine();


            switch (input) {
                case "1" -> displayContracts(getAllContracts(cdm));
                case "2" -> displayContracts(getLastTenContracts(cdm));
                case "3" -> {
                    System.out.println("Returning to Home Menu...");
                    running = false;
                }

                default -> System.out.println("Error. Try again.");
            }
        }
    }

    public boolean login() {
        System.out.println("Enter admin password: ");
        String input = scanner.nextLine();

        String adminPassword = "P@ssword";

        if (!input.equals(adminPassword)) {
            System.out.println("Access denied. Returning to main menu...");
            return false;
        } else {
            System.out.println("Access granted.");
            return true;
        }

    }


    public void displayMenu() {
        String menu = """
                Admin Menu
                1) View all contracts
                2) View last 10 contracts
                3) Return to User Menu
                """;

        System.out.println(menu);
    }


    public ArrayList<Contract> getAllContracts(ContractDataManager cdm) {
        ArrayList<Contract> allContracts = new ArrayList<>();

        try {
            BufferedReader bufReader = new BufferedReader(new FileReader("contracts.csv"));

            String line;
            while ((line = bufReader.readLine()) != null) {
                if (line.trim().isBlank()) {
                    continue;
                }
                String[] parts = line.split("\\|");
                Contract contract = parseContract(parts);
                if (contract != null) {
                    allContracts.add(contract);
                }
            }

            bufReader.close();

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return allContracts;
    }

    public void displayContracts(ArrayList<Contract> contracts) {
        for (Contract contract : contracts) {
            System.out.println(contract);
            System.out.println("-----------------------------");
        }
    }


    private Contract parseContract(String[] parts) {
        String contractType = parts[0];
        String date = parts[1];
        String name = parts[2];
        String email = parts[3];
        Vehicle vehicleSold = new Vehicle(Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), parts[6], parts[7], parts[8], parts[9], Integer.parseInt(parts[10]), Double.parseDouble(parts[11]));


        if (contractType.equalsIgnoreCase("lease")) {
            return new LeaseContract(date, name, email, vehicleSold);

        } else if (contractType.equalsIgnoreCase("sale")) {
            boolean isFinanced = Boolean.parseBoolean(parts[12]);

            return new SalesContract(date, name, email, vehicleSold, isFinanced);
        } else {
            return null;
        }
    }


    public ArrayList<Contract> getLastTenContracts(ContractDataManager cdm) {
        ArrayList<Contract> allContacts = getAllContracts(cdm);

        ArrayList<Contract> lastTenContracts = new ArrayList<>();
        int i;
        if (allContacts.size() > 10) {
            for (i = allContacts.size() - 1; i >= allContacts.size() - 10; i--) {
                Contract contract = allContacts.get(i);
                lastTenContracts.add(contract);
            }
        } else {
            for (i = 0; i < allContacts.size(); i++) {
                Contract contract = allContacts.get(i);
                lastTenContracts.add(contract);
            }
        }
        return lastTenContracts;
    }
}
