package entity;

import java.io.Serializable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author yikso
 */
public class TutorialGroup implements Serializable, Comparable<TutorialGroup> {

    private String programmeCode;
    private int groupNumber;
    private int numberOfStudents;
    private Intake intake;

    public TutorialGroup() {

    }

    public TutorialGroup(String programmeCode, int groupNumber, int numberOfStudents, Intake intake) {
        this.programmeCode = programmeCode;
        this.groupNumber = groupNumber;
        this.numberOfStudents = numberOfStudents;
        this.intake = intake;
    }

    public Intake getIntake() {
        return intake;
    }

    public void setIntake(Intake intake) {
        this.intake = intake;
    }

    public String getProgrammeCode() {
        return programmeCode;
    }

    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.groupNumber;
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
        final TutorialGroup other = (TutorialGroup) obj;
        return this.groupNumber == other.groupNumber;
    }

    @Override
    public String toString() {
        return "programmeCode= " + programmeCode + ", groupNumber=" + groupNumber + ", numberOfStudents=" + numberOfStudents + ", intake=" + intake.getIntakeId() + '}';
    }
    
    // Sort Intake, then group number
    @Override
    public int compareTo(TutorialGroup o) {
        int intakeComparison = this.intake.getIntakeId().compareTo(o.intake.getIntakeId());

        if (intakeComparison == 0) {
            return Integer.compare(this.groupNumber, o.groupNumber);
        }

        return intakeComparison;
    }

}
