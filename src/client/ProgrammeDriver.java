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

    public static void main(String[] args) {
        ListInterface<Programme> programme = new DoubleLinkedList<>();
        programme.add(new Programme(""));
    }

}
