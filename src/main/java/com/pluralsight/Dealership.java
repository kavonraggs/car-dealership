package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dealership {
    private final String name;
    private final String address;
    private final String phone;
    private final ArrayList<Vehicle> inventory = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public List<Vehicle> getVehiclesByPrice(double minPrice, double maxPrice){
        ArrayList<Vehicle> priceSearchList = new ArrayList<>();
        for (Vehicle v: inventory){
            if (v.getPrice() >= minPrice && v.getPrice() <= maxPrice){
                priceSearchList.add(v);
            }
        }
        return priceSearchList;
    }


    public List<Vehicle> getVehiclesByMakeModel(String make, String model){
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
            if (v.getYear() >= minYear && v.getYear() <= maxYear){
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
            if (v.getOdometer() >= minMiles && v.getOdometer() <= maxMiles){
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

    public void addVehicle(int vin, int year, String make, String model, String vehicleType, String color, int odometer, double price){
        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        inventory.add(vehicle);

    }

    public void removeVehicle(int vin){
        inventory.removeIf(v -> v.getVin() == vin);
        //(Found cleaner code via IntelliJ)
//        for (Vehicle v: inventory) {
//            if (v.getVin() == vin) {
//                inventory.remove(v);
//            }
//        }
    }

    public String getInput(String prompt){
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public ArrayList<Vehicle> getInventory() {
        return inventory;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %d vehicles", name, address, phone, inventory.size());
    }
}
