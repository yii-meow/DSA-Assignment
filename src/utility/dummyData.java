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
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author yikso
 */
public class dummyData {

    public static ListInterface<Programme> initializeProgrammeData() {
        // Load programme data
        try {
            ListInterface<Programme> programme = new DoublyLinkedList<>();
            programme = readProgrammeData("programmeData.txt");
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
}
