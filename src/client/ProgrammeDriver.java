/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import adt.DoubleLinkedList;
import adt.ListInterface;
import entity.Programme;

/**
 *
 * @author yikso
 */
public class ProgrammeDriver {

    ListInterface<Programme> programme = new DoubleLinkedList<>();

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        System.out.println("======================\n Programme Management\n======================");
        System.out.println("1. Add a new programme");
        System.out.println("2. Remove a new programme");
        System.out.println("3. Find programme");
        System.out.println("4. Amend programme details");
        System.out.println("5. List all programmes");
        System.out.println("6. Add a tutorial group to a programme");
        System.out.println("7. Remove a tutorial group from a programme");
        System.out.println("8. List all tutorial groups from a programme");
        System.out.println("9. Generate relevant reports");
    }

}
