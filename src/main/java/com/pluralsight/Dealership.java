package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dealership {
    private final String name;
    private final String address;
    private final String phone;
    ArrayList<Vehicle> inventory = new ArrayList<>();

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public List<Vehicle> getVehiclesByPrice(double minPrice, double maxPrice){
        ArrayList<Vehicle> priceSearchList = new ArrayList<>();
        for (Vehicle v: inventory){
            if (v.getPrice() > minPrice && v.getPrice() < maxPrice){
                priceSearchList.add(v);
            }
        }
        return priceSearchList;
    }


    public List<Vehicle> getVehicleByMakeModel(String make, String model){
        ArrayList<Vehicle> makeModelSearch = new ArrayList<>();
        for (Vehicle v: inventory){
            if (v.getMake().equalsIgnoreCase(make) && v.getModel().equalsIgnoreCase(model)){
                makeModelSearch.add(v);
            }
        }
        return makeModelSearch;
    }

    public List<Vehicle> getVehiclesByYear(int minYear, int maxYear){
        ArrayList<Vehicle> yearSearchList = new ArrayList<>();
        for (Vehicle v: inventory){
            if (v.getYear() > minYear && v.getYear() < maxYear){
                yearSearchList.add(v);
            }
        }
        return yearSearchList;
    }
    public List<Vehicle> getVehiclesByColor(String color) {
        ArrayList<Vehicle> colorSearchList = new ArrayList<>();
        for (com.pluralsight.Vehicle v: inventory){
            if (v.getColor().equalsIgnoreCase(color)){
                colorSearchList.add(v);
            }
        }
        return colorSearchList;
    }

    public List<Vehicle> getVehicleByMileage(int minMiles, int maxMiles) {
        ArrayList<Vehicle> mileSearchList = new ArrayList<>();
        for (Vehicle v: inventory){
            if (v.getOdometer() > minMiles && v.getOdometer() < maxMiles){
                mileSearchList.add(v);
            }
        }
        return mileSearchList;
    }

    public List<Vehicle> getVehicleByType(String type) {
        ArrayList<Vehicle> typeSearchList = new ArrayList<>();
        for (Vehicle v: inventory) {
            if (v.getVehicleType().equalsIgnoreCase(type)){
                typeSearchList.add(v);
            }
        }
        return typeSearchList;
    }

    public List<Vehicle> getAllVehicles(){
        return new ArrayList<>(inventory);
    }

    public void addVehicle(){
        System.out.println("Please enter the following vehicle details: ");
        int vin = Integer.parseInt(getInput("Vin: "));
        int year = Integer.parseInt(getInput("Year: "));
        String make = getInput("Make: ");
        String model = getInput("Model: ");
        String vehicleType = getInput("Vehicle Type:");
        String color = getInput("Color: ");
        int odometer = Integer.parseInt(getInput("Odometer: "));
        double price = Double.parseDouble(getInput("Price"));

        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        inventory.add(vehicle);
        System.out.println("New Vehicle Added.");

    }

    public void removeVehicle(){
        int vin = Integer.parseInt(getInput("Please enter the vin number of the vehicle you want you delete: "));
        //inventory.removeIf(v -> v.getVin() == vin); (Found cleaner code via IntelliJ)
        for (Vehicle v: inventory) {
            if (v.getVin() == vin) {
                inventory.remove(v);
            }
        }
    }

    public String getInput(String prompt){
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        return scanner.nextLine();
    }
}
