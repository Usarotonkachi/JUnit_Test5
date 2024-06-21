package src.main.java;
import java.util.ArrayList;
import java.util.Scanner;

class Medicines {
    String name;
    String releaseForm;
    Integer quantity;
    String country;

    Medicines(String name, String releaseForm, Integer quantity, String country) {
        this.name = name;
        this.releaseForm = releaseForm;
        this.quantity = quantity;
        this.country = country;
    }
}

class PrescriptionMedicines extends Medicines {
    String prescriptionType;

    PrescriptionMedicines(String name, String releaseForm, Integer quantity, String country, String prescriptionType) {
        super(name, releaseForm, quantity, country);
        this.prescriptionType = prescriptionType;
    }
}

class Business {
    private String name;
    private String address;
    private int employeeCount;

    public Business(String name, String address, int employeeCount) {
        this.name = name;
        this.address = address;
        this.employeeCount = employeeCount;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void printInfo() {
        System.out.println("Business Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Number of Employees: " + employeeCount);
    }
}

class Pharmacy extends Business {
    private ArrayList<Medicines> medicineNames;

    public Pharmacy(String name, String address, int employeeCount) {
        super(name, address, employeeCount);
        this.medicineNames = new ArrayList<Medicines>();
    }

    public void addMedicine(String name, String releaseForm, Integer quantity, String country) {
        medicineNames.add(new Medicines(name, releaseForm, quantity, country));
    }

    public void searchName(String name) {
        boolean found = false;
        for (Medicines medicine : medicineNames) {
            if (medicine.name.equals(name)) {
                System.out.println(medicine.name);
                System.out.println(medicine.releaseForm);
                System.out.println(medicine.quantity);
                System.out.println(medicine.country);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Medicine not found.");
        }
    }

    public void outputMedicines() {
        if (medicineNames.isEmpty()) {
            System.out.println("No medicines available");
            return;
        }

        for (Medicines medicine : medicineNames) {
            System.out.println(
                    medicine.name + "  " + medicine.releaseForm + "  " + medicine.quantity + "  " + medicine.country);
        }
    }

    public void outputPharmacyName() {
        System.out.println(getName());
    }

    public void outputMedicinesNames() {
        if (medicineNames.isEmpty()) {
            System.out.println("No medicines available");
            return;
        }

        for (Medicines medicine : medicineNames) {
            System.out.print(medicine.name + "  ");
        }
        System.out.println();
    }

    public void updateMedicine(String name, String releaseForm, Integer quantity, String country) {
        boolean found = false;
        if (medicineNames.isEmpty()) {
            System.out.println("No medicines available");
            return;
        }

        for (Medicines medicine : medicineNames) {
            if (medicine.name.equals(name)) {
                medicine.name = name;
                medicine.releaseForm = releaseForm;
                medicine.quantity = quantity;
                medicine.country = country;
                found = true;
            }
        }
        if (!found) {
            System.out.println("Medicine not found.");
        }
    }

    public void removeMedicine(String name) {
        boolean found = false;
        if (medicineNames.isEmpty()) {
            System.out.println("No medicines available");
            return;
        }

        for (int i = 0; i < medicineNames.size(); i++) {
            Medicines medicine = medicineNames.get(i);
            if (medicine.name.equals(name)) {
                medicineNames.remove(i);
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println("Medicine removed successfully.");
        } else {
            System.out.println("Medicine not found.");
        }
    }

    public void pharmacyInterface() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("Next activity? 1: yes  0: no");
            int activity = input.nextInt();
            if (activity == 0) {
                break;
            }

            clearConsole();

            System.out.println("Choose the option");
            System.out.println("1: Add new medicine info");
            System.out.println("2: Search medicine info using Name of medicine");
            System.out.println("3: Output all medicines info");
            System.out.println("4: Output all medicines name");
            System.out.println("5: Update medicine info using name");
            System.out.println("6: Remove medicine info using name");
            System.out.println("7: Output pharmacy name");
            System.out.println("8: Output pharmacy all info");
            System.out.println();

            int number = input.nextInt();

            switch (number) {
                case 1:
                    System.out.println("Input new medicine name");
                    String newMedicineName = input.next();
                    System.out.println("Input new medicine releaseForm");
                    String releaseForm = input.next();
                    System.out.println("Input new medicine quantity");
                    int quantity = input.nextInt();
                    System.out.println("Input new medicine country");
                    String country = input.next();
                    addMedicine(newMedicineName, releaseForm, quantity, country);
                    break;
                case 2:
                    System.out.println("Input medicine name");
                    String searchMedicineName = input.next();
                    searchName(searchMedicineName);
                    break;
                case 3:
                    outputMedicines();
                    break;
                case 4:
                    outputMedicinesNames();
                    break;

                case 5:
                    System.out.println("Input medicine name");
                    String nameUpdate = input.next();
                    System.out.println("Input medicine releaseForm");
                    String releaseFormUpdate = input.next();
                    System.out.println("Input medicine quantity");
                    int quantityUpdate = input.nextInt();
                    System.out.println("Input medicine country");
                    String countryUpdate = input.next();
                    updateMedicine(nameUpdate, releaseFormUpdate, quantityUpdate, countryUpdate);
                    break;
                case 6:
                    System.out.println("Input medicine name");
                    String nameRemoveMedicine = input.next();
                    removeMedicine(nameRemoveMedicine);
                    break;

                case 7:
                    outputPharmacyName();
                    break;

                case 8:
                    printInfo();
                    break;

                default:
                    System.out.println("Finish of the program");
                    input.close();
                    return;
            }
        }
        input.close();
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (Exception ex) {
            System.out.println("Error clearing the console.");
        }
    }

    public static void main(String[] args) {
        System.out.println("||||||||   Pharmacy 'Healthy' DataBase   ||||||||");
        Pharmacy pharmacy = new Pharmacy("Healthy", "27 Wall str.", 7);
        pharmacy.pharmacyInterface();
    }
}

