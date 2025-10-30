package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    Dealership dealership;


    public UserInterface(Dealership dealership){
        this.dealership = dealership;
        System.out.println("Welcome to " + dealership.getName() + "!");
        System.out.println(dealership);
        System.out.println("-------------------------");

    }



    public void display(){
        String userInput;
        do {
            displayMenu();

            userInput = getInput("Enter corresponding character: ").toUpperCase();

            switch (userInput){
                case "1":
                    processGetByPriceRequest();
                    break;
                case "2":
                    processGetByMakeModelRequest();
                    break;
                case "3":
                    processGetByYearRequest();
                    break;
                case "4":
                    processGetByColorRequest();
                    break;
                case "5":
                    processGetByMileageRequest();
                    break;
                case "6":
                    processGetByVehicleTypeRequest();
                    break;
                case "7":
                    processGetByAllVehicleRequest();
                    break;
                case "8":
                    processAddVehicle();
                    break;
                case "9":
                    processRemoveVehicle();
                    break;
                case "L":
                    processLeaseVehicle();
                    break;
                case "B":
                    processBuyVehicle();
                    break;
                case "A":
                    new AdminUserInterface(dealership).run();
                    break;
                case "x":
                case "X":
                    new DealershipFileManager().saveDealership(dealership);
                    System.out.println("Saving data...");
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Error. Try again!");
            }


        } while (!userInput.equalsIgnoreCase("X"));

    }


    public void displayMenu(){
        String menu = """
                1) Search by price
                2) Search by Make/Model
                3) Search by year
                4) Search by color
                5) Search by mileage
                6) Search by vehicle type
                7) Show all vehicles
                8) Add vehicle
                9) Remove vehicle
                L) Lease vehicle
                B) Buy vehicle
                A) Access Admin Menu
                """;
        System.out.println(menu);
    }


    public void processGetByPriceRequest(){
        Double min = tryParseDouble(getInput("Min price: "));
        Double max = tryParseDouble(getInput("Max price: "));
        List<Vehicle> matches = dealership.getVehiclesByPrice(min, max);
        displayMatches(matches);
    }
    public void processGetByMakeModelRequest(){
        String make = getInput("Make: ");
        String model = getInput("Model: ");
        List<Vehicle> matches = dealership.getVehiclesByMakeModel(make, model);
        displayMatches(matches);

    }
    public void processGetByYearRequest(){
        Integer min = tryParseInt(getInput("Min Year: "));
        Integer max = tryParseInt(getInput("Max Year: "));
        List<Vehicle> matches = dealership.getVehiclesByYear(min, max);
        displayMatches(matches);

    }
    public void processGetByColorRequest(){
        String color = getInput("Color: ");
        List<Vehicle> matches = dealership.getVehiclesByColor(color);
        displayMatches(matches);

    }
    public void processGetByMileageRequest(){
        Integer min = tryParseInt(getInput("Min Miles: "));
        Integer max = tryParseInt(getInput("Max Miles: "));
        List<Vehicle> matches = dealership.getVehiclesByMileage(min, max);
        displayMatches(matches);
    }
    public void processGetByVehicleTypeRequest(){
        String type = getInput("Vehicle Type: ");
        List<Vehicle> matches = dealership.getVehiclesByType(type);
        displayMatches(matches);


    }
    public void processGetByAllVehicleRequest(){
        List<Vehicle> allVehicles = dealership.getInventory();
        displayMatches(allVehicles);
    }
    public void processAddVehicle(){
        int vin = Integer.parseInt(getInput("Vin: "));
        int year = Integer.parseInt(getInput("Year: "));
        String make = getInput("Make: ");
        String model = getInput("Model: ");
        String type = getInput("Vehicle Type: ");
        String color = getInput("Color: ");
        int odometer = Integer.parseInt(getInput("Miles: "));
        double price = Double.parseDouble(getInput("Price: "));

        dealership.addVehicle(vin, year, make, model, type, color, odometer, price);
        new DealershipFileManager().saveDealership(dealership);
        System.out.println("Vehicle added to inventory");
    }

    public void processRemoveVehicle(){
        int vin = Integer.parseInt(getInput("Vin: "));
        dealership.removeVehicle(vin);

        new DealershipFileManager().saveDealership(dealership);
        System.out.println("Vehicle removed from inventory");

    }

    public String getInput(String prompt){
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void displayMatches(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()){
            System.out.println("No vehicles matches found");
        } else {
            for (Vehicle v: vehicles){
                System.out.println(v);
                System.out.println("------------");
            }
        }
    }

    public void processLeaseVehicle(){
        LocalDate today = LocalDate.now();
        int vin  = Integer.parseInt(getInput("What is the VIN of the car you want you lease? "));

        Vehicle vehicleSold = dealership.getVehiclesByVin(vin);
        if (vehicleSold == null) {
            System.out.println("No vehicle with that vin number found.");
            return;
        }

        String name = getInput("What is your name? ");
        String email = getInput("What is your email? ");

        LeaseContract leaseContract = new LeaseContract(today.toString(), name, email, vehicleSold);
        System.out.println(leaseContract);

        ContractDataManager cdm = new ContractDataManager();
        cdm.saveContract(leaseContract);

        dealership.removeVehicle(vin);
        new DealershipFileManager().saveDealership(dealership);

    }

    public void processBuyVehicle(){
        boolean isFinanced;
        int vin  = Integer.parseInt(getInput("What is the VIN of the car you want you buy? "));
        LocalDate today = LocalDate.now();
        String name = getInput("What is your name? ");
        String email = getInput("What is your email? ");

        Vehicle vehicleSold = dealership.getVehiclesByVin(vin);
        if (vehicleSold == null) {
            System.out.println("No vehicle with that vin number found.");
            return;
        }

        String financePrompt = getInput("Do you want to finance this vehicle? (y/n) ");

        isFinanced = financePrompt.equalsIgnoreCase("Y") || financePrompt.equalsIgnoreCase("yes");

        SalesContract salesContract = new SalesContract(today.toString(), name, email, vehicleSold, isFinanced);
        System.out.println(salesContract);

        ContractDataManager cfm = new ContractDataManager();
        cfm.saveContract(salesContract);

        dealership.removeVehicle(vin);
        new DealershipFileManager().saveDealership(dealership);
    }

    private Double tryParseDouble(String input){
        if (input == null || input.isBlank()){
            return null;
        } try {
            return Double.parseDouble(input.trim());
        } catch (NumberFormatException e){
            System.out.println("Invalid input. Null assigned to " + input);
            return null;
        }
    }

    private Integer tryParseInt(String input){
        if (input == null || input.isBlank()){
            return null;
        } try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e){
            System.out.println("Invalid input. Null assigned to " + input);
            return null;
        }
    }
}
