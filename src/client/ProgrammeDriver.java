/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import adt.SortedDoublyLinkedList;
import adt.ListInterface;
import entity.Programme;
import entity.TutorialGroup;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import utility.ReportSummary;
import utility.dummyData;
import utility.report;

/**
 *
 * @author yikso
 */
public class ProgrammeDriver {

    private ListInterface<Programme> programmeList = new SortedDoublyLinkedList<>();
    private ListInterface<TutorialGroup> tutorialGroupList = new SortedDoublyLinkedList<>();
    Scanner scanner = new Scanner(System.in);
    private ReportSummary reportSummary = new ReportSummary();

    public static void main(String[] args) {
        ProgrammeDriver driver = new ProgrammeDriver();
        driver.initializeData();
        driver.menu();
        driver.writeNewData();
    }

    public void initializeData() {
        reportSummary.setStartTime(LocalDateTime.now());

        // retrieve existing data
        programmeList = dummyData.initializeProgrammeData();
        tutorialGroupList = dummyData.initializeTutorialGroupData();
    }

    public void writeNewData() {
        dummyData.writeDataToFile(programmeList);
        reportSummary.setEndTime(LocalDateTime.now());
        scanner.close();
    }

    private void menu() {
        int choice = 0;

        do {
            System.out.println("\n==========================================\n\tProgramme Management\n==========================================");
            System.out.println("1. Add a new programme");
            System.out.println("2. Remove a programme");
            System.out.println("3. Find programme");
            System.out.println("4. Amend programme details");
            System.out.println("5. List all programmes");
            System.out.println("6. Add a tutorial group to a programme");
            System.out.println("7. Remove a tutorial group from a programme");
            System.out.println("8. List all tutorial groups from a programme");
            System.out.println("9. Generate relevant reports\n");
            System.out.print("Choice (0 to exit): ");

            String option = scanner.next();

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
                        System.out.println(findProgramme(null));
                        break;
                    case 4:
                        amendProgramme(null);
                        break;
                    case 5:
                        listProgramme();
                        break;
                    case 6:
                        addGroupToProgramme(null);
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
                    case 0:
                        return;
                    default:
                        break;
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

    private void addProgramme() {
        // variable for validation
        boolean isValid = false;

        System.out.println("\n------------------\nAdd Programme Form\n------------------");

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
            }
            return;
        }

        // Programme Name is not allowed to have duplicated entry
        System.out.print("\nProgramme Name: ");
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
                System.out.println("Please ensure the duration is a positive whole number.");
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

        // Description
        System.out.print("Programme Description: ");
        scanner.nextLine();
        String description = scanner.nextLine();
        System.out.println("");

        // initialize to be null tutorial group
        SortedDoublyLinkedList<TutorialGroup> tutorialGroup = new SortedDoublyLinkedList<>();

        Programme programme = new Programme(
                programmeCode,
                programmeName,
                Programme.LevelOfStudy.valueOf(programmeLevel),
                programmeDepartment,
                programmeDuration,
                programmeIntake,
                programmeFee,
                description,
                tutorialGroup
        );

        if (programmeList.add(programme)) {
            System.out.println("Successfully added the programme - " + programmeCode + " !\n");
            System.out.println(programme);
            reportSummary.reportAction(1, "Added programme - " + programmeCode);
        } else {
            System.out.println("Invalid entry. Please try again !");
        }
    }

    private void amendProgramme(String programmeCode) {
        programmeIdList();

        Programme res = null;
        if (programmeCode == null) {
            res = findProgramme(programmeCode);
        } else {
            res = programmeDetails(programmeCode, 1);
        }
        System.out.println("\n" + res);

        if (res != null) {
            System.out.println("Which details to amend ? (-1 to exit)\n*Programme Code and Programme Name is not allowed to be amended*\n");
            System.out.println("1. Programme Level");
            System.out.println("2. Department");
            System.out.println("3. Programme Duration");
            System.out.println("4. Programme Intake");
            System.out.println("5. Programme Fee");
            System.out.println("6. Programme Description");
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
            String reportDetails = "Amended ";

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
                    reportDetails += "programme level : " + res.getProgrammeLevel() + " -> " + newProgrammeLevel;
                    res.setProgrammeLevel(Programme.LevelOfStudy.valueOf(newProgrammeLevel));
                    break;
                case 2:
                    System.out.println(res.getDepartment());
                    System.out.print("Amended Department > ");
                    scanner.nextLine();
                    String newDepartment = scanner.nextLine();
                    System.out.println(res.getDepartment() + " -> " + newDepartment);
                    reportDetails += "department : " + res.getDepartment() + " -> " + newDepartment;
                    res.setDepartment(newDepartment);
                    break;
                case 3:
                    System.out.println(res.getDuration());
                    System.out.print("Amended Programme Duration > ");
                    int newDuration = scanner.nextInt();
                    System.out.println(res.getDuration() + " -> " + newDuration);
                    reportDetails += "duration : " + res.getDuration() + " -> " + newDuration;
                    res.setDuration(newDuration);
                    break;
                case 4:
                    System.out.println(res.getIntake());
                    System.out.print("Amended Programme Intake > ");
                    scanner.nextLine();
                    String newIntake = scanner.next();
                    System.out.println(res.getIntake() + " -> " + newIntake);
                    reportDetails += "intake : " + res.getIntake() + " -> " + newIntake;
                    res.setIntake(newIntake);
                    break;
                case 5:
                    System.out.println(res.getFee());
                    System.out.print("Amended Programme Fee > ");
                    Double newFee = scanner.nextDouble();
                    System.out.println(res.getFee() + " -> " + newFee);
                    reportDetails += "fee : " + res.getFee() + " -> " + newFee;
                    res.setFee(newFee);
                    break;
                case 6:
                    System.out.println(res.getDescription());
                    System.out.print("Amended Programme Description > ");
                    scanner.nextLine();
                    String newDescription = scanner.nextLine();
                    System.out.println(res.getDescription() + " -> " + newDescription);
                    reportDetails += "description: " + res.getDescription() + " -> " + newDescription;
                    res.setDescription(newDescription);
            }
            reportSummary.reportAction(3, reportDetails + " for programme " + res.getProgrammeCode());
        } else {
            System.out.println("Programme not found!");
        }
    }

    // return true if successfully removed the programme
    private boolean removeProgramme() {
        programmeIdList();

        System.out.print("Please enter the programme code : ");
        String programmeCode = scanner.next();

        Programme programmeToRemove = programmeDetails(programmeCode, 1);

        if (programmeToRemove != null) {
            System.out.print("Are you sure to remove the program : " + programmeCode + " (Y/n) ? ");
            if (scanner.next().toUpperCase().charAt(0) == 'Y') {
                if (programmeList.remove(programmeToRemove) != null) {
                    System.out.println("\nSuccessfully removed \"" + programmeToRemove.getProgrammeCode() + "\" program !");
                    reportSummary.reportAction(2, "Removed " + programmeToRemove.getProgrammeCode());
                    return true;
                }
            }
        } else {
            System.out.println("The program code - " + programmeCode + " does not exists.");
        }

        return false;
    }

    // Find the programme, and return the result
    private Programme findProgramme(String programmeCode) {
        if (programmeCode == null) {
            System.out.print("Please enter the programme code : ");
            programmeCode = scanner.next();
        }

        Programme res = programmeDetails(programmeCode, 1);

        if (res != null) {
            return res;
        } else {
            return null;
        }
    }

    // Return the found programme based on programme code or programme name
    // Option : 1 = Programme Code, 2 = Programme Name 
    private Programme programmeDetails(String details, int option) {
        details = details.toUpperCase();
        Programme result = null;
        boolean found = false;

        Iterator it = programmeList.getIterator();

        while (it.hasNext() && !found) {
            result = (Programme) it.next();
            // compare programme code
            if (option == 1) {
                if (result.getProgrammeCode().equals(details)) {
                    return result;
                }
            } else {
                // compare programme name
                if (result.getProgrammeName().equals(details)) {
                    return result;
                }
            }
        }
        return null;
    }

    // Display all the progrmame Id
    private void programmeIdList() {
        System.out.println("\nProgramme List\n===============");
        Iterator it = programmeList.getIterator();
        while (it.hasNext()) {
            Programme programme = (Programme) it.next();
            System.out.println(programme.getProgrammeCode());
        }
        System.out.println();
    }

    // List Program - 1. Overview, 2. Specific, 3. One-By-One (can go to previous & next)
    private void listProgramme() {
        int option = 0;

        do {
            System.out.println("\nListing way");
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
                programmeIdList();

                System.out.print("\nProgramme code > ");
                String programmeCode = scanner.next();
                System.out.println("");

                Programme programme = findProgramme(programmeCode);

                if (programme != null) {
                    System.out.println(programme);
                } else {
                    System.out.println("Programme not found!");
                }
            } else if (option == 3) {
                // One by one, allow checking previous and next programme
                SortedDoublyLinkedList.DoubleLinkedListIterator customIterator = (SortedDoublyLinkedList.DoubleLinkedListIterator) programmeList.getIterator();
                Programme programme = (Programme) customIterator.next();
                // If there is no programme, quit listing
                if (programme == null) {
                    System.out.println("There is no programme available to show");
                    return;
                }

                Character lastChoice = 'N'; // variable to check whether it need to do two step forward or backward
                Character choice = 'N';
                boolean firstTime = true; // to avoid 'previous' option for the first data while entering

                while (true) {
                    lastChoice = choice;

                    // If there is no programme, quit listing
                    if (programme == null) {
                        System.out.println("There is no programme available to show");
                        return;
                    }

                    System.out.println(programme);

                    if (customIterator.hasPrevious() && !firstTime) {
                        System.out.print("Previous Programme (P) / ");
                    }
                    firstTime = false;

                    if (customIterator.hasNext()) {
                        System.out.print("Next Programme (N) / ");
                    }

                    System.out.print("Next (E) > ");

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
            System.out.print("Do you want to continue with the listing program? (Y/n) : ");
        } while (scanner.next().toUpperCase().charAt(0) == 'Y');
    }

    private void addGroupToProgramme(String programmeCode) {
        System.out.println("Adding Tutorial Group to A Programme\n====================================\n");
        Programme targetProgramme = null;

        if (programmeCode == null) {
            programmeIdList();
            System.out.print("Which programme you are looking for ? \nProgramme Code : ");
            String programmeToAddGroup = scanner.next();
            targetProgramme = programmeDetails(programmeToAddGroup, 1);
        } else {
            targetProgramme = programmeDetails(programmeCode, 1);
        }

        // if programme exists
        if (targetProgramme != null) {
            SortedDoublyLinkedList<TutorialGroup> updatedGroup = targetProgramme.getTutorialGroup();
            System.out.println("\nCurrent Tutorial Group\n=====================");

            if (updatedGroup.getNumberOfEntries() != 0) {
                System.out.println(updatedGroup);
            } else {
                System.out.println("No tutorial group has been added so far.\n");
            }

            Iterator tutorialGroupIt = tutorialGroupList.getIterator();

            // temporary storing available groups
            SortedDoublyLinkedList availableTutorialGroup = new SortedDoublyLinkedList<>();

            int option = 0;

            // List out the tutorial groups by retrieving all the tutorial groups using utility function and store available groups into a temporary list 
            // and does not contains in the prrogramme
            while (tutorialGroupIt.hasNext()) {
                TutorialGroup tutorialGroup = (TutorialGroup) tutorialGroupIt.next();
                if (tutorialGroup.getProgrammeCode().equals(targetProgramme.getProgrammeCode()) && !targetProgramme.getTutorialGroup().contains(tutorialGroup)) {
                    availableTutorialGroup.add(tutorialGroup);
                }
            }

            do {
                if (availableTutorialGroup.getNumberOfEntries() == 0) {
                    System.out.println("There is no tutorial group available to add...");
                    return;
                }

                System.out.println("Which tutorial group you would like to add to the programme ?\n");
                System.out.println(availableTutorialGroup);

                boolean validGroup = false;
                // Choose a tutorial group and do validation
                do {
                    System.out.print("Option (0 to cancel) > ");
                    option = scanner.nextInt();

                    if (option > 0 && option <= availableTutorialGroup.getNumberOfEntries()) {
                        validGroup = true;
                    } else if (option == 0) {
                        System.out.println("Back to the menu...");
                        return;
                    } else {
                        System.out.println("This option is not available! Please choose again.");
                    }
                } while (!validGroup);

                // Add the tutorial group to programme (index minus by one)
                TutorialGroup targetTutorialGroup = (TutorialGroup) availableTutorialGroup.get(option - 1);

                updatedGroup.add(targetTutorialGroup);

                System.out.println("\nAdded tutorial group successfully!\nUpdated Tutorial Group\n");
                System.out.println(updatedGroup);
                reportSummary.reportAction(4, "Added tutorial group " + targetTutorialGroup.getGroupNumber() + " to " + targetTutorialGroup.getProgrammeCode() + " (" + targetTutorialGroup.getIntake().getIntakeId() + ")");

                System.out.print("Continue adding tutorial group? (Y/n) : ");
                availableTutorialGroup.remove(targetTutorialGroup);
            } while (scanner.next().toUpperCase().charAt(0) == 'Y');
        }
    }

    private void removeGroupFromProgramme() {
        System.out.println("Remove Tutorial Group from A Programme\n====================================");

        Iterator it = programmeList.getIterator();
        while (it.hasNext()) {
            Programme programme = (Programme) it.next();
            System.out.println(programme.getProgrammeCode());
        }

        System.out.print("\nWhich programme you are looking for ? \nProgramme Code : ");
        String programmeToAddGroup = scanner.next();
        Programme targetProgramme = programmeDetails(programmeToAddGroup, 1);

        // if programme exists
        if (targetProgramme != null) {
            SortedDoublyLinkedList<TutorialGroup> updatedGroup = targetProgramme.getTutorialGroup();

            int option = 0;

            do {
                if (updatedGroup.getNumberOfEntries() == 0) {
                    System.out.println("There is no tutorial group available to remove...");
                    return;
                }
                System.out.println("\nCurrent Tutorial Group\n===================\n" + updatedGroup);

                System.out.println("Which tutorial group you would like to remove from the programme ?\n");

                boolean validGroup = false;
                // Choose a tutorial group and do validation
                do {
                    System.out.print("Option (0 to cancel) > ");
                    option = scanner.nextInt();

                    if (option > 0 && option <= updatedGroup.getNumberOfEntries()) {
                        validGroup = true;
                    } else if (option == 0) {
                        System.out.println("Back to the menu...");
                        return;
                    } else {
                        System.out.println("This option is not available! Please choose again.");
                    }
                } while (!validGroup);

                // Add the tutorial group to programme (index minus by one)
                TutorialGroup targetTutorialGroup = (TutorialGroup) updatedGroup.get(option - 1);

                if (updatedGroup.remove(targetTutorialGroup) != null) {
                    System.out.println("Removed successfully!\n");
                    System.out.println(updatedGroup);
                    System.out.print("Continue removing tutorial group? (Y/n) : ");
                } else {
                    System.out.println("Removed unsuccessfully. Please try again.");
                }

            } while (scanner.next().toUpperCase().charAt(0) == 'Y');
        }
    }

    private void listGroupFromProgramme() {
        programmeIdList();

        System.out.print("Which programme you are looking for ? \nProgramme Code : ");
        String programmeForListingGroup = scanner.next();
        Programme targetProgramme = programmeDetails(programmeForListingGroup, 1);
        System.out.println("");

        if (targetProgramme != null) {
            SortedDoublyLinkedList<TutorialGroup> group = targetProgramme.getTutorialGroup();
            if (!group.isEmpty()) {
                System.out.println(group);
            } else {
                System.out.print("There are no tutorial groups in this programme.\nWould you like to add tutorial group to this programme? (Y/n) > ");
                if (scanner.next().toUpperCase().charAt(0) == 'Y') {
                    System.out.println("");
                    addGroupToProgramme(targetProgramme.getProgrammeCode());
                }
            }
        } else {
            System.out.println("Programme not found!");
        }
    }

    private void generateReport() {
        reportSummary.addActivityLog("Generate Report.");

        do {
            System.out.println("\n1. Report Summary");
            System.out.println("2. Filter activity");
            System.out.print("3. Activity Log\n\n> ");

            int option = scanner.nextInt();
            System.out.println("");

            switch (option) {
                // report summary
                case 1:
                    reportSummary.printReportSummary();
                    break;
                // filter acitities
                case 2:
                    System.out.println("Which activity you would like to filter ?\n");
                    System.out.println("1. Programme Insertion\n2. Programme Deletion\n3. Programme Amendation\n4. Tutorial Group Insertion\n5. Tutorial Group Deletion\n\n> ");
                    int filterChoice = scanner.nextInt();

                    switch (filterChoice) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        default:
                            break;
                    }

                    break;
                // activity logs
                case 3:
                    System.out.println("Activity Log\n=============");
                    reportSummary.printActivityLog();
                    break;
                default:
                    break;
            }
            System.out.print("\nContinue viewing report ? (Y/n) : ");
        } while (scanner.next().toUpperCase().charAt(0) == 'Y');
    }
}
