package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DealershipFileManager {

    public Dealership getDealership() {
        Dealership dealership = null;
        try (BufferedReader bufReader = new BufferedReader(new FileReader("inventory.csv"))) {
            String line;

            String dealershipInfo = bufReader.readLine();
            if (dealershipInfo == null || dealershipInfo.isEmpty()){
                System.out.println("No dealership information provided.");
                return null;
            }
            String[] info = dealershipInfo.split("\\|");
            dealership = new Dealership(info[0], info[1], info[2]);

            while ((line = bufReader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length < 8) continue;

                int vin = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[1]);
                String make = parts[2];
                String model = parts[3];
                String vehicleType = parts[4];
                String color = parts[5];
                int odometer = Integer.parseInt(parts[6]);
                double price = Double.parseDouble(parts[7]);

                dealership.addVehicle(vin, year, make, model, vehicleType, color, odometer, price);
            }
        } catch(IOException e){
            System.out.println("Could not read file" + e.getMessage());
        }return dealership;
    }

    public void saveDealership(){

    }



}
