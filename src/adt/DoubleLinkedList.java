/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import java.io.Serializable;

/**
 *
 * @author yikso
 */
public class DoubleLinkedList<T> implements ListInterface<T>, Serializable {

    private int numberOfEntries;
    private Node firstNode;

    public DoubleLinkedList() {
        clear();
    }

    @Override
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);

        if (numberOfEntries == 0) {
            firstNode = newNode;
        } else {
            Node currentNode = null;
            while (firstNode.next != null) {
                currentNode = firstNode.next;
            }
            currentNode.next = newNode;
        }

        return true;
    }

    @Override
    public boolean add(int position, T newEntry) {
        if (position >= 1 || position < numberOfEntries + 1) {

        }

        return true;
    }

    @Override
    public T remove(T element) {
        return null;
    }

    @Override
    public void clear() {
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public boolean contains(T element) {
        return true;
    }

    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public boolean isFull() {
        return true;
    }

    private class Node {

        private T data;
        private Node next;
        private Node prev;

        private Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        return "";
    }
}
