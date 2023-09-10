/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author yikso
 */
public class ReportSummary {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

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
        Duration duration = Duration.between(getStartTime(), getEndTime());
        return duration;
    }

    @Override
    public String toString() {
        return "\nReport Summary\n===============\n" + "Start Time : " + startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                + "\nEnd Time : " + endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\nDuration: " + calculateDuration().getSeconds() + "s.";
    }

}
