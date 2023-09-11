/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import adt.SortedDoublyLinkedList;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author yikso
 */
public class ReportSummary {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    SortedDoublyLinkedList<ActivityLog> activityLog = new SortedDoublyLinkedList<>();

    private int programmeInsertion;
    private int programmeRemoval;
    private int programmeAmended;
    private int groupInsertion;
    private int groupRemoval;

    SortedDoublyLinkedList<String> listOfSearchedProgramme = new SortedDoublyLinkedList<>();

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Duration calculateDuration() {
        Duration duration = Duration.between(getStartTime(), LocalDateTime.now());
        return duration;
    }

    public SortedDoublyLinkedList<ActivityLog> getActivityLog() {
        return activityLog;
    }

    public void setActivityLog(SortedDoublyLinkedList<ActivityLog> activityLog) {
        this.activityLog = activityLog;
    }

    public int getProgrammeInsertion() {
        return programmeInsertion;
    }

    public void setProgrammeInsertion(int programmeInsertion) {
        this.programmeInsertion = programmeInsertion;
    }

    public int getProgrammeRemoval() {
        return programmeRemoval;
    }

    public void setProgrammeRemoval(int programmeRemoval) {
        this.programmeRemoval = programmeRemoval;
    }

    public int getProgrammeAmended() {
        return programmeAmended;
    }

    public void setProgrammeAmended(int programmeAmended) {
        this.programmeAmended = programmeAmended;
    }

    public int getGroupInsertion() {
        return groupInsertion;
    }

    public void setGroupInsertion(int groupInsertion) {
        this.groupInsertion = groupInsertion;
    }

    public int getGroupRemoval() {
        return groupRemoval;
    }

    public void setGroupRemoval(int groupRemoval) {
        this.groupRemoval = groupRemoval;
    }

    public void addActivityLog(String log, String type) {
        activityLog.add(report.convertDataToLog(log, type));
    }

    public void reportAction(int option, String details) {
        String type = "";

        switch (option) {
            // programme insertion
            case 1:
                setProgrammeInsertion(getProgrammeInsertion() + 1);
                type = "AddProgramme";
                break;
            // programme removal
            case 2:
                setProgrammeRemoval(getProgrammeRemoval() + 1);
                type = "RemoveProgramme";
                break;
            // programme ammended
            case 3:
                setProgrammeAmended(getProgrammeAmended() + 1);
                type = "AmendProgramme";
                break;
            // Tutorial Group Insertion
            case 4:
                setGroupInsertion(getGroupInsertion() + 1);
                type = "AddGroupToProgramme";
                break;
            // Tutorial Group Deletion
            case 5:
                setGroupRemoval(getGroupRemoval() + 1);
                type = "RemoveGroupFromProgramme";
                break;

        }
        addActivityLog(details, type);
    }

    // Not able to reverse as my doublyLinkedList involve sorting, need to implement other adt
//    public static SortedDoublyLinkedList<String> reverse(SortedDoublyLinkedList<String> log) {
//        SortedDoublyLinkedList<String> reversed = new SortedDoublyLinkedList<>();
//        SortedDoublyLinkedList.DoubleLinkedListIterator customIterator = (SortedDoublyLinkedList.DoubleLinkedListIterator) log.getIterator();
//
//        while (customIterator.hasNext()) {
//            String res = (String) customIterator.next();
//        }
//
//        ArrayList<String> res2 = new ArrayList<>();
//
//        System.out.println("Res");
//        while (customIterator.hasPrevious()) {
//            String res = (String) customIterator.previous();
//            System.out.println(res);
//            reversed.add(res);
//            res2.add(res);
//        }
//
//        System.out.println("\n\n" + reversed.get(0));
//        System.out.println("\n\n" + res2.get(0));
//
//        return reversed;
//    }
    @Override
    public String toString() {
//        System.out.println(reverse(getActivityLog()));

        return "";
    }

    public void printReportSummary() {
        String summary = "\nReport Summary\n===============\n" + "Start Time : " + startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                + "\nTotal time spent in this program: " + calculateDuration().getSeconds() + "s.\n\nMost Favourite Programme based on your search: ?\n\n" + "- Programme Action(s) -\nProgramme Insertion (+) : " + getProgrammeInsertion()
                + "\nProgramme Deletion  (-) : " + getProgrammeRemoval() + "\nProgramme Amended   (*) : " + getProgrammeAmended() + "\n\n- Programme : Tutorial Group Action(s) -\n"
                + "Tutorial Group Insertion (+) : " + getGroupInsertion() + "\nTutorial Group Deletion  (-) : " + getGroupRemoval();

        System.out.println(summary);
    }

    public void printActivityLog() {
        System.out.println("Activity Log\n=============");

        Iterator it = getActivityLog().getIterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public void filterActivity() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Which activity you would like to filter ?\n");
        System.out.print("1. Programme Insertion\n2. Programme Deletion\n3. Programme Amendation\n4. Tutorial Group Insertion\n5. Tutorial Group Deletion\n\n> ");

        int filterChoice = scanner.nextInt();
        String type = "";

        // converrt int choice to string
        switch (filterChoice) {
            // programme insertion
            case 1:
                type = "AddProgramme";
                break;
            // programme removal
            case 2:
                type = "RemoveProgramme";
                break;
            // programme ammended
            case 3:
                type = "AmendProgramme";
                break;
            // Tutorial Group Insertion
            case 4:
                type = "AddGroupToProgramme";
                break;
            // Tutorial Group Deletion
            case 5:
                type = "RemoveGroupFromProgramme";
        }

        Iterator it = activityLog.getIterator();

        SortedDoublyLinkedList<ActivityLog> res = new SortedDoublyLinkedList<>();

        while (it.hasNext()) {
            ActivityLog activity = (ActivityLog) it.next();
            if (activity.getType().equals(type)) {
                res.add(activity);
            }
        }
        if (res.isEmpty()) {
            System.out.println("No activity.");
        } else {
            System.out.println(res);
        }
    }

    public void addListOfSearchedProgramme(String programmeCode) {
        listOfSearchedProgramme.add(programmeCode);
    }

    public SortedDoublyLinkedList<String> getListOfSearchedProgramme() {
        return listOfSearchedProgramme;
    }

    public String getFavouriteProgramme() {
        System.out.println(getListOfSearchedProgramme());

        return "";
    }
}
