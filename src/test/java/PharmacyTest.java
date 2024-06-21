package src.test.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.java.Medicines;
import src.main.java.Pharmacy;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class PharmacyTest {
    private Pharmacy pharmacy;

    @BeforeEach
    public void setUp() {
        pharmacy = new Pharmacy("Healthy", "27 Wall str.", 7);
    }

    @Test
    public void testOutputMedicines() {
        pharmacy.addMedicine("Ibuprofen", "Capsule", 30, "Canada");
        pharmacy.addMedicine("Aspirin", "Tablet", 20, "UK");
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream("Ibuprofen\n".getBytes()));
            pharmacy.outputMedicines();
        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    public void testSearchNameNotFound() {
        pharmacy.addMedicine("Ibuprofen", "Capsule", 30, "Canada");
        pharmacy.addMedicine("Aspirin", "Tablet", 20, "UK");

        // Simulate user input for search
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream("Paracetamol\n".getBytes()));
            pharmacy.searchName("Paracetamol");
        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    public void testAddDuplicateMedicine() {
        pharmacy.addMedicine("Paracetamol", "Tablet", 50, "USA");
        pharmacy.addMedicine("Paracetamol", "Capsule", 30, "UK");
        assertEquals(1, pharmacy.getMedicineNames().size());
        Medicines updatedMedicine = pharmacy.getMedicineByName("Paracetamol");
        assertEquals("Capsule", updatedMedicine.releaseForm);
        assertEquals(30, updatedMedicine.quantity);
        assertEquals("UK", updatedMedicine.country);
    }

    @Test
    public void testUpdateNonExistingMedicine() {
        pharmacy.updateMedicine("Paracetamol", "Capsule", 30, "UK");
        assertTrue(pharmacy.getMedicineNames().isEmpty());
    }

    @Test
    public void testOutputMedicinesEmpty() {
        pharmacy.outputMedicines();

        assertTrue(systemOut().endsWith("No medicines available\n"));
    }

    private String systemOut() {
        return systemOut.toString();
    }
}
