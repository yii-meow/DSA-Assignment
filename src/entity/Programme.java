/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.SortedDoublyLinkedList;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Chong Yik Soon
 */
public class Programme implements Comparable<Programme>, Serializable {

    public enum LevelOfStudy {
        DIPLOMA,
        BACHELOR_DEGREE,
        MASTER
    }

    private String programmeCode;
    private String programmeName;
    private LevelOfStudy programmeLevel;
    private String department;
    private int duration;
    private String intake;
    private double fee;
    private String description;
    private SortedDoublyLinkedList<TutorialGroup> tutorialGroup;

    public Programme(String programmeCode, String programmeName, LevelOfStudy programmeLevel, String department, int duration, String intake, double fee, String description, SortedDoublyLinkedList<TutorialGroup> tutorialGroup) {
        this.programmeCode = programmeCode;
        this.programmeName = programmeName;
        this.programmeLevel = programmeLevel;
        this.department = department;
        this.duration = duration;
        this.intake = intake;
        this.fee = fee;
        this.tutorialGroup = tutorialGroup;
        this.description = description;
    }

    public String getProgrammeCode() {
        return programmeCode;
    }

    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
    }

    public String getProgrammeName() {
        return programmeName;
    }

    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    public LevelOfStudy getProgrammeLevel() {
        return programmeLevel;
    }

    public void setProgrammeLevel(LevelOfStudy programmeLevel) {
        this.programmeLevel = programmeLevel;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getIntake() {
        return intake;
    }

    public void setIntake(String intake) {
        this.intake = intake;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public SortedDoublyLinkedList<TutorialGroup> getTutorialGroup() {
        return tutorialGroup;
    }

    public void setTutorialGroup(SortedDoublyLinkedList<TutorialGroup> tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean addTutorialGroup(TutorialGroup tutorialGroup) {
        if (this.tutorialGroup.add(tutorialGroup)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.programmeCode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Programme other = (Programme) obj;
        return Objects.equals(this.programmeCode, other.programmeCode);
    }

    @Override
    public int compareTo(Programme other) {
        return this.programmeCode.compareTo(other.programmeCode);
    }

    @Override
    public String toString() {
        String tutorialGroupDetails = "No Details";

        if (!tutorialGroup.isEmpty()) {
            tutorialGroupDetails = tutorialGroup.toString();
        }

        return "Programme Code : " + programmeCode + "\nProgramme Name : " + programmeName
                + "\nProgramme Level : " + programmeLevel + "\nDepartment : " + department
                + "\nDuration : " + duration + "\nIntake : " + intake + "\nProgramme Fee : RM " + String.format("%.2f", fee)
                + "\nProgramme Description : " + description + "\n\nTutorial Group\n" + tutorialGroupDetails;

    }
}
