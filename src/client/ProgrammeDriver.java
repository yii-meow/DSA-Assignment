/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import adt.DoubleLinkedList;
import adt.ListInterface;
import entity.Programme;
import entity.TutorialGroup;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;
import utility.dummyData;

/**
 *
 * @author yikso
 */
public class ProgrammeDriver {

    private static ListInterface<Programme> programmeList = new DoubleLinkedList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // retrieve existing data
        programmeList = dummyData.initializeProgrammeData();
        menu();
    }

    private static void menu() {

        int choice = 0;

        do {
            System.out.println("==========================================\n\tProgramme Management\n==========================================");
            System.out.println("1. Add a new programme");
            System.out.println("2. Remove a programme");
            System.out.println("3. Find programme");
            System.out.println("4. Amend programme details");
            System.out.println("5. List all programmes");
            System.out.println("6. Add a tutorial group to a programme");
            System.out.println("7. Remove a tutorial group from a programme");
            System.out.println("8. List all tutorial groups from a programme");
            System.out.println("9. Generate relevant reports\n");
            System.out.print("Choice: ");

            String option = scanner.next();
            System.out.println("\n");

            try {
                choice = Integer.parseInt(option);

                switch (choice) {
                    case 1:
                        addProgramme();
                        break;
                    case 2:
                        removeProgramme();
                        break;
                    case 3:
                        findProgramme();
                        break;
                    case 4:
                        amendProgramme();
                        break;
                    case 5:
                        listProgramme();
                        break;
                    case 6:
                        addGroupToProgramme();
                        break;
                    case 7:
                        removeGroupFromProgramme();
                        break;
                    case 8:
                        listGroupFromProgramme();
                        break;
                    case 9:
                        generateReport();
                        break;
                    default:
                        System.out.println("This choice is not yet been supported. Do you want to change it to another choice ? (Y/n)");

                }
                System.out.println();
            } catch (NumberFormatException e) {
                choice = 0;
            }
        } while (choice > 0 && choice <= 9);

        System.out.println("\nQuiting...\nThank you for using this system.");
        scanner.close();
    }

    private static void addProgramme() {
        // variable for validation
        boolean isValid = false;

        System.out.println("------------------\nAdd Programme Form\n------------------");

        // Program Code is primary key. User can only amend details
        System.out.print("Programme Code: ");
        String programmeCode = scanner.next();
        while (!programmeCode.matches("^[A-Za-z]{3}")) {
            System.out.println("\nInvalid programme code. Programme code should contains three alphabets.\n");
            System.out.print("Programme Code: ");
            programmeCode = scanner.next();
        }

        // Duplicated entry - programme code , ask for amend details?
        if (programmeDetails(programmeCode) != null) {
            System.out.println("The programme code " + programmeCode + " is existed.\nDo you want to amend details? (Y/n) : ");
            if (scanner.next().equals("Y") || scanner.next().equals('y')) {
                amendProgramme();
            } else {
                return;
            }
        }
        System.out.println("Programme Code is valid!\n");

        // Programme Name is not allowed to have duplicated entry
        System.out.print("Programme Name: ");
        scanner.next();
        String programmeName = scanner.nextLine();
        System.out.println("");

        // Programme Level
        int option = 0;

        while (!isValid || (option < 1 || option > 3)) {
            try {
                System.out.print("\nProgramme Level: \n1. Diploma\n2. Bachelor Degree\n3. Master\n\n> ");
                option = scanner.nextInt();
                if (option < 1 || option > 3) {
                    System.out.println("Invalid option. Please choose from 1-3 ONLY");
                }
                isValid = true;
            } catch (InputMismatchException ex) {
                System.out.println("Invalid option. Please choose from 1-3 ONLY");
                scanner.nextLine();
            }
        }

        String programmeLevel = "";

        // Convert programme level (number) to enum
        switch (option) {
            case 1:
                programmeLevel = "DIPLOMA";
                break;
            case 2:
                programmeLevel = "BACHELOR_DEGREE";
                break;
            case 3:
                programmeLevel = "MASTER";
        }
        System.out.println("");

        // Department
        System.out.print("Department: ");
        scanner.nextLine();
        String programmeDepartment = scanner.nextLine();
        System.out.println("");

        // Duration
        int programmeDuration = 0;
        isValid = false;

        while (!isValid) {
            try {
                System.out.print("Duration (in year): ");
                programmeDuration = scanner.nextInt();
                isValid = true;
            } catch (InputMismatchException ex) {
                System.out.println("Please ensure the duration is a positive whole number");
                scanner.nextLine();
            }
        }

        System.out.println("");

        // Intake
        System.out.print("Intake (e.g 2023-06): ");
        scanner.nextLine();
        String programmeIntake = scanner.nextLine();

        while (!programmeIntake.matches("^\\d{4}-(0[1-9]|1[0-2])$")) {
            System.out.println("\nInvalid programme intake format. Please format this format (e.g 2023-06).\n");
            programmeIntake = scanner.nextLine();
        }

        System.out.println("");

        // Programme Fee
        Double programmeFee = 0.0;
        isValid = false;

        while (!isValid) {
            try {
                System.out.print("Programme Fee (RM) : ");
                programmeFee = scanner.nextDouble();
                isValid = true;
            } catch (InputMismatchException ex) {
                System.out.println("Invalid data. Please ensure the fee is a positive value");
                scanner.nextLine();
            }
        }

        System.out.println("");

        // Let user choose from a list
        System.out.print("Tutorial Group: ");
        TutorialGroup tutorialGroup = null;
        System.out.println("");

        // Description
        System.out.print("Programme Description: ");
        scanner.nextLine();
        String description = scanner.nextLine();
        System.out.println("");

        Programme programme = new Programme(
                programmeCode,
                programmeName,
                // to change
                Programme.LevelOfStudy.valueOf(programmeLevel),
                programmeDepartment,
                programmeDuration,
                programmeIntake,
                programmeFee,
                new TutorialGroup(),
                description
        );

        if (programmeList.add(programme)) {
            System.out.println("Successfully added the programme - " + programmeCode + " !");
            System.out.println(programme);
            System.out.println(programmeList);
        } else {
            System.out.println("Invalid entry. Please try again !");
        }
    }

    private static void removeProgramme() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Find the programme, and provide the details of it
    private static Programme findProgramme() {
        System.out.print("Please enter the programme code: ");
        String programmeCode = scanner.next();
        programmeCode = programmeCode.toUpperCase();

        Programme res = programmeDetails(programmeCode);

        if (res != null) {
            System.out.println("Here's the result...\n\n" + res);
            return res;
        } else {
            System.out.println("Programme not found!");
            return null;
        }
    }

    // Return the found programme
    private static Programme programmeDetails(String programmeCode) {
        Programme result = null;
        boolean found = false;

        Iterator it = programmeList.getIterator();

        System.out.println("\nFinding the programme....\n");

        while (it.hasNext() && !found) {
            result = (Programme) it.next();
            if (result.getProgrammeCode().equals(programmeCode)) {
                return result;
            }
        }

        return null;
    }

    private static void amendProgramme() {
        Programme res = findProgramme();

        if (res != null) {
            System.out.println("Which details to amemd ? (-1 to exit) *Programme Code is not allowed to be amend*");
            System.out.println("1. Programme Name");
            System.out.println("2. Programme Level");
            System.out.println("3. Department");
            System.out.println("4. Programme Duration");
            System.out.println("5. Programme Intake");
            System.out.println("6. Programme Fee");
            System.out.println("7. Tutorial Group");
            System.out.println("8. Programme Description");

            int amendOption = scanner.nextInt();

            while ((amendOption < 1 || amendOption > 8) && amendOption != -1) {
                System.out.println("Please enter a valid choice! (choose from 1-8 or -1 to exit)");
                amendOption = scanner.nextInt();
            }

            System.out.println("Previous Details :");

            switch (amendOption) {
                case 1:
                    System.out.println(res.getProgrammeName());
                    System.out.println("Amended Programme Name > ");
                    break;
                case 2:
                    System.out.println(res.getProgrammeLevel());
                    System.out.println("Amended Programme Level > ");
                    break;
                case 3:
                    System.out.println(res.getDepartment());
                    System.out.println("Amended Department > ");
                    break;
                case 4:
                    System.out.println(res.getDuration());
                    System.out.println("Amended Programme Duration > ");
                    break;
                case 5:
                    System.out.println(res.getIntake());
                    System.out.println("Amended Programme Intake > ");
                    break;
                case 6:
                    System.out.println(res.getFee());
                    System.out.println("Amended Programme Fee > ");
                    break;
                case 7:
                    System.out.println(res.getTutorialGroup());
                    System.out.println("Amended Tutorial Group > ");
                    break;
                case 8:
                    System.out.println(res.getDescription());
                    System.out.println("Amended Programme Description > ");
            }
            System.out.println("\n\nxxx -> yyy \nConfirm ? (Y/n) : _\b");
            char option = scanner.next().charAt(0);
            if (option == 'Y') {
                System.out.println("Amended.");
            } else {
                System.out.println("Cancelled operation.");
            }
        } else {
            System.out.println("Programme not found!");
        }

    }

    private static void listProgramme() {
        System.out.println("-----------------\nProgramme Listing\n-----------------");
        System.out.println(programmeList);

        Iterator it = programmeList.getIterator();
        Programme res = (Programme) it.next();
        System.out.println(res.getProgrammeCode());
//        while(it.hasNext()){
//            System.out.println(it.next().);
//        }
    }

    private static void addGroupToProgramme() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void removeGroupFromProgramme() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void listGroupFromProgramme() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void generateReport() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
