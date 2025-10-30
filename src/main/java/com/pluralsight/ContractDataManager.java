package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ContractDataManager {

    public static final String File_Name = "contracts.csv";

    public void saveContract(Contract contract){
        try (PrintWriter writer = new PrintWriter(
                new BufferedWriter(new FileWriter(File_Name, true)))){
                writer.println(formatContract(contract));
            System.out.println("File saved");
        } catch (IOException e) {
            System.out.println("Error saving file");
        }
    }

    private String formatContract(Contract contract) {
        StringBuilder stringBuilder = new StringBuilder();
        Vehicle v = contract.getVehicleSold();

        if (contract instanceof LeaseContract leaseContract){
            stringBuilder.append("LEASE|");
            stringBuilder.append(contract.getDate()).append("|");
            stringBuilder.append(contract.getName()).append("|");
            stringBuilder.append(contract.getEmail()).append("|");
            stringBuilder.append(v.getVin()).append("|");
            stringBuilder.append(v.getYear()).append("|");
            stringBuilder.append(v.getMake()).append("|");
            stringBuilder.append(v.getModel()).append("|");
            stringBuilder.append(v.getVehicleType()).append("|");
            stringBuilder.append(v.getColor()).append("|");
            stringBuilder.append(v.getOdometer()).append("|");
            stringBuilder.append(String.format("%.2f",v.getPrice())).append("|");

            stringBuilder.append(String.format("%.2f", leaseContract.getLeaseFee())).append("|");
            stringBuilder.append(String.format("%.2f", leaseContract.getExpectedEndingValue())).append("|");
            stringBuilder.append(String.format("%.2f", leaseContract.getTotalPrice())).append("|");
            stringBuilder.append(String.format("%.2f", leaseContract.getMonthlyPayment()));

        }


        else if (contract instanceof SalesContract salesContract){
            stringBuilder.append("SALE|");
            stringBuilder.append(contract.getDate()).append("|");
            stringBuilder.append(contract.getName()).append("|");
            stringBuilder.append(contract.getEmail()).append("|");
            stringBuilder.append(v.getVin()).append("|");
            stringBuilder.append(v.getYear()).append("|");
            stringBuilder.append(v.getMake()).append("|");
            stringBuilder.append(v.getModel()).append("|");
            stringBuilder.append(v.getVehicleType()).append("|");
            stringBuilder.append(v.getColor()).append("|");
            stringBuilder.append(v.getOdometer()).append("|");
            stringBuilder.append(String.format("%.2f",v.getPrice())).append("|");

            stringBuilder.append(String.format("%.2f", salesContract.getSalesTax())).append("|");
            stringBuilder.append(String.format("%.2f", salesContract.getRecordingFee())).append("|");
            stringBuilder.append(String.format("%.2f", salesContract.getProcessingFee())).append("|");
            stringBuilder.append(String.format("%.2f", salesContract.getTotalPrice())).append("|");
            stringBuilder.append(String.format("%.2f", salesContract.getMonthlyPayment())).append("|");
            stringBuilder.append(salesContract.isFinanced());
        }

        return stringBuilder.toString();
    }


}
