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
    private int yearOfStudy;
    private int semesterOfStudy;

    public TutorialGroup() {

    }

    public TutorialGroup(String programmeCode,int groupNumber, int numberOfStudents, int yearOfStudy, int semesterOfStudy) {
        this.programmeCode = programmeCode;
        this.groupNumber = groupNumber;
        this.numberOfStudents = numberOfStudents;
        this.yearOfStudy = yearOfStudy;
        this.semesterOfStudy = semesterOfStudy;
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

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public int getSemesterOfStudy() {
        return semesterOfStudy;
    }

    public void setSemesterOfStudy(int semesterOfStudy) {
        this.semesterOfStudy = semesterOfStudy;
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
        return "TutorialGroup{" + "groupNumber=" + groupNumber + ", numberOfStudents=" + numberOfStudents + ", yearOfStudy=" + yearOfStudy + ", semesterOfStudy=" + semesterOfStudy + '}';
    }

    @Override
    public int compareTo(TutorialGroup o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
