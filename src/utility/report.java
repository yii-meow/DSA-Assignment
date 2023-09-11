/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;
import java.time.LocalDateTime;

/**
 *
 * @author Chong Yik Soon
 */
public class report {

    public static ActivityLog convertDataToLog(String action, String type) {
        ActivityLog log = new ActivityLog(action, type, LocalDateTime.now());
        return log;
    }
}
