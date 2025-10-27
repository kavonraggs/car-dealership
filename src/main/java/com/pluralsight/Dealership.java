package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private final String name;
    private final String address;
    private final String phone;
    private final ArrayList<Vehicle> inventory = new ArrayList<>();

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public List<Vehicle> getVehiclesByPrice(Double minPrice, Double maxPrice) {
        ArrayList<Vehicle> priceSearchList = new ArrayList<>();

        for (Vehicle v : inventory) {
            boolean minPriceMatch = (minPrice == null) || (v.getPrice() >= minPrice);
            boolean maxPriceMatch = (maxPrice == null) || (v.getPrice() <= maxPrice);

            if (minPriceMatch && maxPriceMatch) {
                priceSearchList.add(v);
            }
        }
        return priceSearchList;
    }


    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        ArrayList<Vehicle> makeModelSearch = new ArrayList<>();
        if (make == null) {
            make = "";
        }
        if (model == null) {
            model = "";
        }

        for (Vehicle v : inventory) {
            boolean makeMatch = make.isBlank() || v.getMake().equalsIgnoreCase(make);
            boolean modelMatch = model.isBlank() || v.getModel().equalsIgnoreCase(model);

            if (makeMatch && modelMatch) {
                makeModelSearch.add(v);
            }
        }
        return makeModelSearch;
    }

    public List<Vehicle> getVehiclesByYear(Integer minYear, Integer maxYear) {
        ArrayList<Vehicle> yearSearchList = new ArrayList<>();
        for (Vehicle v : inventory) {
            boolean minYearMatch = (minYear == null) || (v.getYear() >= minYear);
            boolean maxYearMatch = (maxYear == null) || (v.getYear() <= maxYear);

            if (minYearMatch && maxYearMatch) {
                yearSearchList.add(v);
            }
        }
        return yearSearchList;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        ArrayList<Vehicle> colorSearchList = new ArrayList<>();
        if (color == null || color.isBlank()) {
            colorSearchList.addAll(inventory);
        } else {
            for (Vehicle v : inventory) {
                if (v.getColor().equalsIgnoreCase(color)) {
                    colorSearchList.add(v);
                }
            }
        }
        return colorSearchList;
    }

    public Vehicle getVehiclesByVin(int vin) {
            for (Vehicle v : inventory) {
                if (v.getVin() == vin) return v;
            }
        return null;
    }


    public List<Vehicle> getVehiclesByMileage(Integer minMiles, Integer maxMiles) {
        ArrayList<Vehicle> mileSearchList = new ArrayList<>();
        for (Vehicle v : inventory) {
            boolean minMileMatch = (minMiles == null) || (v.getOdometer() >= minMiles);
            boolean maxMileMatch = (maxMiles == null) || (v.getOdometer() <= maxMiles);

            if (minMileMatch && maxMileMatch) {
                mileSearchList.add(v);
            }
        }
        return mileSearchList;
    }

    public List<Vehicle> getVehiclesByType(String type) {
        ArrayList<Vehicle> typeSearchList = new ArrayList<>();
        if (type == null || type.isBlank()) {
            typeSearchList.addAll(inventory);
        } else {
            for (Vehicle v : inventory) {
                if (v.getVehicleType().equalsIgnoreCase(type)) {
                    typeSearchList.add(v);
                }
            }
        }
        return typeSearchList;
    }

    public void addVehicle(int vin, int year, String make, String model, String vehicleType, String color, int odometer, double price) {
        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        inventory.add(vehicle);

    }

    public void removeVehicle(int vin) {
        inventory.removeIf(v -> v.getVin() == vin);
        //(Found cleaner code via IntelliJ)
//        for (Vehicle v: inventory) {
//            if (v.getVin() == vin) {
//                inventory.remove(v);
//            }
//        }
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

    public List<Vehicle> getInventory() {
        return inventory;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %d vehicles", name, address, phone, inventory.size());
    }
}
