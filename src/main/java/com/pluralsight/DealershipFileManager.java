package com.pluralsight;

import java.io.*;

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

                int vin = Integer.parseInt(parts[0].trim());
                int year = Integer.parseInt(parts[1].trim());
                String make = parts[2].trim();
                String model = parts[3].trim();
                String vehicleType = parts[4].trim();
                String color = parts[5].trim();
                int odometer = Integer.parseInt(parts[6].trim());
                double price = Double.parseDouble(parts[7].trim());

                dealership.addVehicle(vin, year, make, model, vehicleType, color, odometer, price);
            }
        } catch(IOException e){
            System.out.println("Could not read file" + e.getMessage());
        }return dealership;
    }

    public void saveDealership(Dealership dealership){
        try (BufferedWriter buffWriter = new BufferedWriter(new FileWriter("inventory.csv"))){
            buffWriter.write(String.format("%s | %s | %s", dealership.getName(), dealership.getAddress(), dealership.getPhone()));
            buffWriter.newLine();

            for (Vehicle v: dealership.getInventory()) {
                buffWriter.write(String.format("%d | %d | %s | %s | %s | %s | %d | %.2f", v.getVin(), v.getYear(), v.getMake(), v.getModel(), v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice()));
                buffWriter.newLine();
            }
            System.out.println("Inventory successfully saved");
        } catch (IOException e)
        {
            System.out.println("Error saving dealership's data" + e.getMessage());
        }
    }



}
