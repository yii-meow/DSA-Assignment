package entity;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author yikso
 */
public class TutorialGroup {

    private int groupNumber;
    private int numberOfStudent;
    private int yearOfStudy;
    private int semesterOfStudy;

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public int getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(int numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
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
        int hash = 7;
        hash = 89 * hash + this.groupNumber;
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
        return "TutorialGroup{" + "groupNumber=" + groupNumber + ", numberOfStudent=" + numberOfStudent + ", yearOfStudy=" + yearOfStudy + ", semesterOfStudy=" + semesterOfStudy + '}';
    }

}
