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
        programmeList = dummyData.initializeData();
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
                        amendProgramme(null);
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
            System.out.print("Do you want to continue using this program? (Y/n) : ");
        } while ((choice > 0 && choice <= 9) && scanner.next().toUpperCase().charAt(0) == 'Y');

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
        if (programmeDetails(programmeCode, 1) != null) {
            System.out.print("The programme code " + programmeCode + " is existed.\nDo you want to amend details? (Y/n) : ");
            if (scanner.next().toUpperCase().charAt(0) == 'Y') {
                amendProgramme(programmeCode);
            } else {
                return;
            }
        }

        // Programme Name is not allowed to have duplicated entry
        System.out.print("Programme Name: ");
        scanner.nextLine();
        String programmeName = scanner.nextLine();
        System.out.println("");

        // Duplicated entry - programme name , ask for amend details
        if (programmeDetails(programmeName, 2) != null) {
            System.out.print("The programme name " + programmeName + " is existed.\nDo you want to amend details? (Y/n) : ");
            if (scanner.next().toUpperCase().charAt(0) == 'Y') {
                amendProgramme(programmeDetails(programmeName, 2).getProgrammeCode());
            } else {
                return;
            }
        }

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
                Programme.LevelOfStudy.valueOf(programmeLevel),
                programmeDepartment,
                programmeDuration,
                programmeIntake,
                programmeFee,
                //                new TutorialGroup(),
                description,
                new DoubleLinkedList()
        );

        if (programmeList.add(programme)) {
            System.out.println("Successfully added the programme - " + programmeCode + " !");
            System.out.println(programme);
            System.out.println(programmeList);
        } else {
            System.out.println("Invalid entry. Please try again !");
        }
    }

    private static boolean removeProgramme() {
        System.out.print("Please enter the programme code : ");
        String programmeCode = scanner.next();
        programmeCode = programmeCode.toUpperCase();

        Programme programmeToRemove = programmeDetails(programmeCode, 1);

        if (programmeToRemove != null) {
            System.out.println("Are you sure to remove the program : " + programmeCode + " (Y/n) ? ");
            if (scanner.next().toUpperCase().charAt(0) == 'Y') {
                if (programmeList.remove(programmeToRemove) != null) {
                    System.out.println("Successfully removed the program.");
                    return true;
                }
            }
        } else {
            System.out.println("The program code - " + programmeCode + " does not exists.");
        }

        return false;
    }

    // Find the programme, and provide the details of it
    private static Programme findProgramme() {
        System.out.print("Please enter the programme code : ");
        String programmeCode = scanner.next();
        programmeCode = programmeCode.toUpperCase();

        Programme res = programmeDetails(programmeCode, 1);

        if (res != null) {
            System.out.println("Here's the result...\n\n" + res);
            return res;
        } else {
            System.out.println("Programme not found!");
            return null;
        }
    }

    // Return the found programme
    // Option : 1 = Programme Code, 2 = Programme Name 
    private static Programme programmeDetails(String details, int option) {
        Programme result = null;
        boolean found = false;

        Iterator it = programmeList.getIterator();

//        System.out.println("\nFinding the programme....\n");
        while (it.hasNext() && !found) {
            result = (Programme) it.next();
            if (option == 1) {
                if (result.getProgrammeCode().equals(details)) {
                    return result;
                }
            } else {
                if (result.getProgrammeName().equals(details)) {
                    return result;
                }
            }
        }
        return null;
    }

    private static void amendProgramme(String programmeCode) {
        Programme res = null;
        if (programmeCode == null) {
            res = findProgramme();
        } else {
            res = programmeDetails(programmeCode, 1);
            System.out.println(res);
        }

        if (res != null) {
            System.out.println("Which details to amemd ? (-1 to exit)\n*Programme Code and Name is not allowed to be amended*\n");
            System.out.println("1. Programme Level");
            System.out.println("2. Department");
            System.out.println("3. Programme Duration");
            System.out.println("4. Programme Intake");
            System.out.println("5. Programme Fee");
            System.out.println("6. Tutorial Group");
            System.out.println("7. Programme Description");
            System.out.print("\n> ");

            int amendOption = scanner.nextInt();

            while ((amendOption < 1 || amendOption > 7) && amendOption != -1) {
                System.out.println("Please enter a valid choice! (choose from 1-7 or -1 to exit)");
                amendOption = scanner.nextInt();
            }

            if (amendOption == -1) {
                return;
            }

            System.out.print("\nPrevious Details\n===============\nOld: ");

            switch (amendOption) {
                case 1:
                    System.out.print(res.getProgrammeLevel());
                    System.out.println("\n\nProgramme Level (1-3) : \n1. Diploma\n2. Bachelor Degree\n3. Master\n");
                    System.out.print("Amended Programme Level > ");
                    int option = scanner.nextInt();
                    String newProgrammeLevel = "";
                    switch (option) {
                        case 1:
                            newProgrammeLevel = "DIPLOMA";
                            break;
                        case 2:
                            newProgrammeLevel = "BACHELOR_DEGREE";
                            break;
                        case 3:
                            newProgrammeLevel = "MASTER";
                    }
                    System.out.println(res.getProgrammeLevel() + " -> " + newProgrammeLevel);
                    res.setProgrammeLevel(Programme.LevelOfStudy.valueOf(newProgrammeLevel));
                    break;
                case 2:
                    System.out.println(res.getDepartment());
                    System.out.print("Amended Department > ");
                    scanner.nextLine();
                    String newDepartment = scanner.nextLine();
                    System.out.println(res.getDepartment() + " -> " + newDepartment);
                    res.setDepartment(newDepartment);
                    break;
                case 3:
                    System.out.println(res.getDuration());
                    System.out.print("Amended Programme Duration > ");
                    int newDuration = scanner.nextInt();
                    System.out.println(res.getDuration() + " -> " + newDuration);
                    res.setDuration(newDuration);
                    break;
                case 4:
                    System.out.println(res.getIntake());
                    System.out.print("Amended Programme Intake > ");
                    scanner.nextLine();
                    String newIntake = scanner.next();
                    System.out.println(res.getIntake() + " -> " + newIntake);
                    res.setIntake(newIntake);
                    break;
                case 5:
                    System.out.println(res.getFee());
                    System.out.print("Amended Programme Fee > ");
                    Double newFee = scanner.nextDouble();
                    System.out.println(res.getFee() + " -> " + newFee);
                    res.setFee(newFee);
                    break;
                case 6:
                    System.out.println(res.getTutorialGroup());
                    System.out.print("Amended Tutorial Group > ");
                    // Choose from list
                    break;
                case 7:
                    System.out.println(res.getDescription());
                    System.out.print("Amended Programme Description > ");
                    scanner.nextLine();
                    String newDescription = scanner.nextLine();
                    System.out.println(res.getDescription() + " -> " + newDescription);
                    res.setDescription(newDescription);
            }
//            System.out.print("Confirm ? (Y/n) : ");
//            char option = scanner.next().toUpperCase().charAt(0);
//            if (option == 'Y') {
//                System.out.println("Amended.");
//            } else {
//                System.out.println("Cancelled operation.");
//            }
        } else {
            System.out.println("Programme not found!");
        }
    }

    private static void listProgramme() {
        int option = 0;

        do {
            System.out.println("Listing way");
            System.out.println("1. Overview of Programme");
            System.out.println("2. Specific Programme");
            System.out.print("3. One-by-One\n\n> ");

            option = scanner.nextInt();
            System.out.println("\n-----------------\nProgramme Listing\n-----------------");

            if (option == 1) {
                // First way : Overview
                Iterator it = programmeList.getIterator();

                int index = 1;
                while (it.hasNext()) {
                    Programme programme = (Programme) it.next();
                    System.out.println(index + ")");
                    System.out.println(programme.getProgrammeCode());
                    System.out.println(programme.getProgrammeName());
                    System.out.println(programme.getProgrammeLevel());
                    System.out.println(programme.getDepartment() + "\n");

                    index += 1;
                }

                // Specific Program Details
            } else if (option == 2) {
                Iterator it = programmeList.getIterator();
                while (it.hasNext()) {
                    Programme programme = (Programme) it.next();
                    System.out.println(programme.getProgrammeCode());
                }

                System.out.print("\nProgramme code > ");
                String programmeCode = scanner.next();
                System.out.println("");
                programmeCode = programmeCode.toUpperCase();

                Programme programme = programmeDetails(programmeCode, 1);

                if (programme != null) {
                    System.out.println(programme);
                } else {
                    System.out.println("Programme not found!");
                }
            } else if (option == 3) {
                DoubleLinkedList.DoubleLinkedListIterator customIterator = (DoubleLinkedList.DoubleLinkedListIterator) programmeList.getIterator();
                Programme programme = (Programme) customIterator.next();

                // If there is no programme, quit listing
                if (programme == null) {
                    System.out.println("There is no programme available to show");
                    return;
                }

                Character lastChoice = 'N'; // variable to check whether it need to do two step forward or backward
                Character choice = 'N';

                while (true) {
                    lastChoice = choice;
                    System.out.println(programme);
                    System.out.print("Next Programme (N) / Previous Programme (P) / Exit (E) > ");
                    choice = scanner.next().toUpperCase().charAt(0);

                    System.out.println("\n--------------------------------------------\n");

                    if (choice == 'N') {
                        if (customIterator.hasNext()) {
                            programme = (Programme) customIterator.next();
                        }
                        if (lastChoice == 'P') {
                            // Two step backward for pointer
                            programme = (Programme) customIterator.next();
                        }
                    } else if (choice == 'P') {
                        // previous programme
                        if (customIterator.hasPrevious()) {
                            programme = (Programme) customIterator.previous();
                        }
                        if (lastChoice == 'N') {
                            // Two step forward for pointer
                            programme = (Programme) customIterator.previous();
                        }
                    } else {
                        break;
                    }
                }
            }
            System.out.print("Do you want to continue listing program? (Y/n) : ");
        } while (scanner.next().toUpperCase().charAt(0) == 'Y');
    }

    private static void addGroupToProgramme() {
        System.out.print("\nWhich programme you are looking for ? \n Programme Code : ");
        String programmeToAddGroup = scanner.next();
        Programme targetProgramme = programmeDetails(programmeToAddGroup, 1);

        // if programme exists
        if (targetProgramme != null) {
            DoubleLinkedList<TutorialGroup> updatedGroup = targetProgramme.getTutorialGroup();

            // List out the tutorial groups by retrieving all the tutorial groups using utility function
            // Choose a tutorial group
            // Add the tutorial group to programme
            updatedGroup.add(null);

            targetProgramme.setTutorialGroup(updatedGroup);
        }
    }

    private static void removeGroupFromProgramme() {
        System.out.print("\nWhich programme you are looking for ? \n Programme Code : ");
        String programmeToRemoveGroup = scanner.next();
        Programme targetProgramme = programmeDetails(programmeToRemoveGroup, 1);

        if (targetProgramme != null) {
            DoubleLinkedList<TutorialGroup> updatedGroup = targetProgramme.getTutorialGroup();

            // List out the tutorial groups using the listGroupFromProgramme()
            // Choose a tutorial group
            // remove the tutorial group to programme
            updatedGroup.remove(null);
        }
    }

    private static void listGroupFromProgramme() {
        System.out.print("\nWhich programme you are looking for ? \n Programme Code : ");
        String programmeForListingGroup = scanner.next();
        Programme targetProgramme = programmeDetails(programmeForListingGroup, 1);

        if (targetProgramme != null) {
            DoubleLinkedList<TutorialGroup> group = targetProgramme.getTutorialGroup();
            System.out.println(group);
        }
    }

    private static void generateReport() {

    }
}
