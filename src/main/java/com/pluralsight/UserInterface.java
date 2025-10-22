package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    Dealership dealership;
    Scanner scanner = new Scanner(System.in);

    public UserInterface(){
        this.dealership = init();

    }

    private Dealership init(){
        DealershipFileManager dfm = new DealershipFileManager();
        Dealership dealership = dfm.getDealership();
        System.out.println("Welcome to " + dealership.getName() + "!");
        System.out.println(dealership);
        System.out.println();
        return dealership;
    }

    public void display(){
        String userInput;
        do {
            displayMenu();

            userInput = getInput("Enter corresponding character: ");

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
                case "x":
                case "X":
                    DealershipFileManager dfm = new DealershipFileManager();
                    dfm.saveDealership(dealership);
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
                """;
        System.out.println(menu);
    }


    public void processGetByPriceRequest(){
        double min = Double.parseDouble(getInput("Min Price:"));
        double max = Double.parseDouble(getInput("Max price: "));
        List<Vehicle> matches = dealership.getVehiclesByPrice(min, max);
        displayMatches(matches);
    }
    public void processGetByMakeModelRequest(){
        String make = getInput("Make:");
        String model = getInput("Model: ");
        List<Vehicle> matches = dealership.getVehiclesByMakeModel(make, model);
        displayMatches(matches);

    }
    public void processGetByYearRequest(){
        int min = Integer.parseInt(getInput("Min Year:"));
        int max = Integer.parseInt(getInput("Max Year: "));
        List<Vehicle> matches = dealership.getVehiclesByYear(min, max);
        displayMatches(matches);

    }
    public void processGetByColorRequest(){
        String color = getInput("Color: ");
        List<Vehicle> matches = dealership.getVehiclesByColor(color);
        displayMatches(matches);

    }
    public void processGetByMileageRequest(){
        int min = Integer.parseInt(getInput("Min Miles:"));
        int max = Integer.parseInt(getInput("Max Miles: "));
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
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public void displayMatches(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()){
            System.out.println("No vehicles matches found");
        } else {
            for (Vehicle v: vehicles){
                System.out.println(v);
            }
        }
    }
}
