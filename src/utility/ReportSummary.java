/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import adt.DoublyLinkedList;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

/**
 *
 * @author yikso
 */
public class ReportSummary {
    
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    
    DoublyLinkedList<String> activityLog = new DoublyLinkedList<>();
    
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
    
    public void updateActivityLog(String action) {
        String log = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ": " + action;
        activityLog.add(log);
    }
    
    @Override
    public String toString() {
        Iterator it = activityLog.getIterator();
        
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        
        return "\nReport Summary\n===============\n" + "Start Time : " + startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                + "\nTotal time spent in this program: " + calculateDuration().getSeconds() + "s.";
    }
    
}
