/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author yikso
 */
// Associative Entity
public class ProgrammeIntake {

    private String programmeCode;
    private String intakeId;

    public ProgrammeIntake(String programmeCode, String intakeId) {
        this.programmeCode = programmeCode;
        this.intakeId = intakeId;
    }

    public String getProgrammeCode() {
        return programmeCode;
    }

    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
    }

    public String getIntakeId() {
        return intakeId;
    }

    public void setIntakeId(String intakeId) {
        this.intakeId = intakeId;
    }

    @Override
    public String toString() {
        return "ProgrammeIntake{" + "programmeCode=" + programmeCode + ", intakeId=" + intakeId + '}';
    }
}
