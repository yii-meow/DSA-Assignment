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

        programme.add(
                new Programme(
                        "RIS",
                        "Bachelor in Information Security",
                        Programme.LevelOfStudy.BACHELOR_DEGREE,
                        "FOCS",
                        36,
                        "2023-06",
                        22000,
                        new TutorialGroup(
                                1,
                                24,
                                1,
                                1
                        ),
                        "This program is designed to arm students with the technical skills and know-how in information security, covering areas like Internet Security, Vulnerability Scans, Penetration Testing, and Systems Security. Plus, you'll get to dive into computer networking and software development. The program offers a range of elective courses, including but not limited to Digital Forensics, AI, Mobile App Development, and Blockchain.\n"
                        + "\n"
                        + "You'll also get hands-on experience through a 6-month industry placement, where you'll tackle real-world infosec projects. This not only boosts your resume but also opens up more job opportunities for you.\n"
                ));

        programme.add(
                new Programme(
                        "RDS",
                        "Bachelor in Data Science",
                        Programme.LevelOfStudy.BACHELOR_DEGREE,
                        "FOCS",
                        36,
                        "2023-06",
                        24000,
                        new TutorialGroup(
                                1,
                                24,
                                1,
                                1
                        ),
                        "This program aims to equip students with skills in both computer science and data science, setting them up for a thriving career as data pros or data scientists. In today's data-centric world, grads from this program are hot commodities. They'll be the go-to people for crunching big data to enhance business operations, boost profits, improve customer experiences, and more.\n"
                        + "\n"
                        + "The curriculum isn't just basic computer science stuff like Programming and Database Management. It's spiced up with specialized courses in AI, Machine Learning, IoT, and Cloud Computing, among others.\n"
                        + "\n"
                        + "Students also get a taste of the real world with a 6-month industry stint, working on actual projects in data and computer science. This experience is a resume booster and a ticket to more job offers.\n"
                        + "\n"
                        + "And here's the cherry on top: completing the program earns you a joint SAS Certificate in Data Science and Machine Learning.\n"
                        + "\n"
                        + "Need more rephrasing? Just let me know!"
                ));
                
          // User input
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
