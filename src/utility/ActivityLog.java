/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Chong Yik Soon
 */
public class ActivityLog implements Comparable<ActivityLog> {

    private String log;
    private String type;
    private LocalDateTime time;

    public ActivityLog(String log, String type, LocalDateTime time) {
        this.log = log;
        this.type = type;
        this.time = time;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "" + getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " : " + getLog();
    }

    @Override
    public int compareTo(ActivityLog o) {
        return this.getTime().compareTo(o.getTime());
    }

}
