/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import adt.DoubleLinkedList;
import adt.ListInterface;
import entity.Programme;
import entity.TutorialGroup;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author yikso
 */
public class dummyData {

    public static ListInterface<Programme> initializeProgrammeData() {
        ListInterface<Programme> programme = new DoubleLinkedList<>();
        
        programme.add(
                new Programme(
                        "RSD",
                        "Bachelor in Software System Development",
                        Programme.LevelOfStudy.BACHELOR_DEGREE,
                        "FOCS",
                        36,
                        "2023-06",
                        20000,
                        new TutorialGroup(
                                1,
                                24,
                                1,
                                1
                        ),
                        "Software Engineering"
                ));

        programme.add(
                new Programme(
                        "DFT",
                        "Diploma in Information Technology",
                        Programme.LevelOfStudy.DIPLOMA,
                        "FOCS",
                        24,
                        "2023-06",
                        18000,
                        new TutorialGroup(
                                1,
                                24,
                                1,
                                1
                        ),
                        "Information Technology"
                ));

        programme.add(
                new Programme(
                        "RIS",
                        "Bachelor in Information Security",
                        Programme.LevelOfStudy.BACHELOR_DEGREE,
                        "FOCS",
                        36,
                        "2023-06",
                        22000,
                        new TutorialGroup(
                                1,
                                24,
                                1,
                                1
                        ),
                        "This program is designed to arm students with the technical skills and know-how in information security, covering areas like Internet Security, Vulnerability Scans, Penetration Testing, and Systems Security. Plus, you'll get to dive into computer networking and software development. The program offers a range of elective courses, including but not limited to Digital Forensics, AI, Mobile App Development, and Blockchain.\n"
                        + "\n"
                        + "You'll also get hands-on experience through a 6-month industry placement, where you'll tackle real-world infosec projects. This not only boosts your resume but also opens up more job opportunities for you.\n"
                ));

        programme.add(
                new Programme(
                        "RDS",
                        "Bachelor in Data Science",
                        Programme.LevelOfStudy.BACHELOR_DEGREE,
                        "FOCS",
                        36,
                        "2023-06",
                        24000,
                        new TutorialGroup(
                                1,
                                24,
                                1,
                                1
                        ),
                        "This program aims to equip students with skills in both computer science and data science, setting them up for a thriving career as data pros or data scientists. In today's data-centric world, grads from this program are hot commodities. They'll be the go-to people for crunching big data to enhance business operations, boost profits, improve customer experiences, and more.\n"
                        + "\n"
                        + "The curriculum isn't just basic computer science stuff like Programming and Database Management. It's spiced up with specialized courses in AI, Machine Learning, IoT, and Cloud Computing, among others.\n"
                        + "\n"
                        + "Students also get a taste of the real world with a 6-month industry stint, working on actual projects in data and computer science. This experience is a resume booster and a ticket to more job offers.\n"
                        + "\n"
                        + "And here's the cherry on top: completing the program earns you a joint SAS Certificate in Data Science and Machine Learning.\n"
                        + "\n"
                        + "Need more rephrasing? Just let me know!"
                ));

        try {
            File file = new File("programmes.dat");
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(programme);
            return programme;
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException ex) {
            System.out.println("Cannot save to file");
        }
        return null;
    }
}
