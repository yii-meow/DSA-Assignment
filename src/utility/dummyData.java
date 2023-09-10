/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import adt.DoublyLinkedList;
import adt.ListInterface;
import entity.Intake;
import entity.Programme;
import entity.TutorialGroup;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 *
 * @author yikso
 */
public class dummyData {

    public static ListInterface<Programme> initializeProgrammeData() {
        // Load programme data
        try {
            ListInterface<Programme> programme = new DoublyLinkedList<>();
            programme = readProgrammeData("programme_data.txt");
            return programme;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ListInterface<TutorialGroup> initializeTutorialGroupData() {
        // Load Tutorial Group Data
        try {
            ListInterface<TutorialGroup> tutorialGroup = new DoublyLinkedList<>();
            tutorialGroup = readTutorialGroupData("tutorialGroupData.txt");
            return tutorialGroup;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ListInterface<Programme> readProgrammeData(String fileName) throws IOException {
        ListInterface<Programme> programmeList = new DoublyLinkedList<>();
        try ( BufferedReader reader = new BufferedReader(new FileReader("src/utility/" + fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // valid format
                Programme programme = new Programme(
                        parts[0],
                        parts[1],
                        Programme.LevelOfStudy.valueOf(parts[2]),
                        parts[3],
                        Integer.parseInt(parts[4]),
                        parts[5],
                        Double.parseDouble(parts[6]),
                        parts[7],
                        new DoublyLinkedList() // initialize tutorial group to be null first, which will add later on
                );

                for (int i = 8; i < parts.length; i += 6) {
                    TutorialGroup tutorialGroup = new TutorialGroup(
                            parts[i],
                            Integer.parseInt(parts[i + 1]),
                            Integer.parseInt(parts[i + 2]),
                            new Intake(
                                    parts[i + 3],
                                    parts[i + 4],
                                    parts[i + 5]
                            )
                    );
                    programme.addTutorialGroup(tutorialGroup);
                };
                programmeList.add(programme);
            }
        }
        return programmeList;
    }

    public static ListInterface<TutorialGroup> readTutorialGroupData(String fileName) throws IOException {
        ListInterface<TutorialGroup> tutorialGroupList = new DoublyLinkedList<>();
        try ( BufferedReader reader = new BufferedReader(new FileReader("src/utility/" + fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // valid format
                TutorialGroup tutorialGroup = new TutorialGroup(
                        parts[0],
                        Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2]),
                        new Intake(
                                parts[3],
                                parts[4],
                                parts[5])
                );
                tutorialGroupList.add(tutorialGroup);
            }
        }
        return tutorialGroupList;
    }

    // Write data to txt file
    public static void writeDataToFile(ListInterface<Programme> programmeList) {
        String programmeData = convertProgrammeDataToString(programmeList);

        // Get the current working directory
        String currentDirectory = System.getProperty("user.dir");

        // Construct the absolute path to the src/utility directory
        String outputPath = currentDirectory + "/src/utility/";

        // Create a File object for the destination file
        File outputFile = new File(outputPath + "programme_data.txt");

        try {
            // Create parent directories if they don't exist
            outputFile.getParentFile().mkdirs();

            // Create a FileWriter to write data to the file
            FileWriter fileWriter = new FileWriter(outputFile);

            // Write the data to the file
            fileWriter.write(programmeData);

            // Close the FileWriter
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving the file.");
        }
    }

    // For converting programme list into String
    public static String convertProgrammeDataToString(ListInterface<Programme> programmeList) {
        Iterator it = programmeList.getIterator();
        StringBuilder formattedString = new StringBuilder();

        while (it.hasNext()) {
            Programme programme = (Programme) it.next();
            // Append the fields in the desired order, separated by commas

            formattedString.append(programme.getProgrammeCode()).append(",");
            formattedString.append(programme.getProgrammeName()).append(",");
            formattedString.append(programme.getProgrammeLevel().toString()).append(",");
            formattedString.append(programme.getDepartment()).append(",");
            formattedString.append(programme.getDuration()).append(",");
            formattedString.append(programme.getIntake()).append(",");
            formattedString.append(programme.getFee()).append(",");
            formattedString.append(programme.getDescription());

            if (programme.getTutorialGroup() != null) {
                Iterator tutorialGroupIt = programme.getTutorialGroup().getIterator();

                while (tutorialGroupIt.hasNext()) {
                    TutorialGroup tutorialGroup = (TutorialGroup) tutorialGroupIt.next();
                    formattedString.append(",").append(tutorialGroup.getProgrammeCode());
                    formattedString.append(",").append(tutorialGroup.getGroupNumber());
                    formattedString.append(",").append(tutorialGroup.getNumberOfStudents());
                    formattedString.append(",").append(tutorialGroup.getIntake().getIntakeId());
                    formattedString.append(",").append(tutorialGroup.getIntake().getDate());
                    formattedString.append(",").append(tutorialGroup.getIntake().getYear());
                }
            }
            formattedString.append("\n");
        }
        return formattedString.toString();
    }
}
