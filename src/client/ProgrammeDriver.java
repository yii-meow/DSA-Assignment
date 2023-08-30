/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import adt.DoubleLinkedList;
import adt.ListInterface;
import entity.Programme;
import entity.TutorialGroup;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author yikso
 */
public class ProgrammeDriver {

    private static ListInterface<Programme> programme = new DoubleLinkedList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {

        int choice = 0;

        do {
            System.out.println("======================\n Programme Management\n======================");
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

            String option = scanner.nextLine();
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
        // Temporary dummy data
        programme.add(
                new Programme(
                        "RSD",
                        "Bachelor in Software System Development",
                        Programme.LevelOfStudy.BACHELOR_DEGREE,
                        "FOCS",
                        36,
                        "2023-06",
                        20000,
                        new TutorialGroup(
                                1,
                                24,
                                1,
                                1
                        ),
                        "Software Engineering"
                ));

        programme.add(
                new Programme(
                        "DFT",
                        "Diploma in Information Technology",
                        Programme.LevelOfStudy.DIPLOMA,
                        "FOCS",
                        24,
                        "2023-06",
                        18000,
                        new TutorialGroup(
                                1,
                                24,
                                1,
                                1
                        ),
                        "Information Technology"
                ));

//        System.out.println("------------------\nAdd Programme Form\n------------------");
//        System.out.print("Programme Code: ");
//        int programmeCode = scanner.nextInt();
//
//        System.out.print("Programme Name: ");
//        scanner.next();
//        String programmeName = scanner.nextLine();
//        System.out.println("");
//
//        System.out.print("Programme Level: \n1. Diploma\n2. Bachelor Degree\n3. Master");
//        int programmeLevel = scanner.nextInt();
//        System.out.println("");
//
//        System.out.print("Department: ");
//        scanner.next();
//        String programmeDepartment = scanner.nextLine();
//
//        System.out.print("Duration (in year): ");
//        int programmeDuration = scanner.nextInt();
//        System.out.println("");
//
//        System.out.print("Intake (e.g 2023-06): ");
//        scanner.next();
//        String programmeIntake = scanner.nextLine();
//        System.out.println("");
//
//        System.out.print("Programme Fee: ");
//        Double programmeFee = scanner.nextDouble();
//        System.out.println("");
//
//        System.out.print("Tutorial Group: ");
//        System.out.println("");
//
//        System.out.print("Programme Description: ");
//        scanner.next();
//        String description = scanner.nextLine();
//        System.out.println("");
    }

    private static void removeProgramme() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void findProgramme() {
        Programme result = null;
        boolean found = false;

        System.out.print("Please enter the programme code: ");
        String programmeCode = scanner.next();

        Iterator it = programme.getIterator();

        while (it.hasNext() && !found) {
            result = (Programme) it.next();
            if (String.valueOf(result).equals(programmeCode)) {
                found = true;
            }
        }
        System.out.println(result);
    }

    private static void amendProgramme() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void listProgramme() {
        System.out.println("-----------------\nProgramme Listing\n-----------------");
        System.out.println(programme);
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
