package adt;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Chong Yik Soon
 */
public class LinkedCircularStack<T> implements StackInterface<T> {

    private Node lastNode;
    private int numberOfEntries;

    public LinkedCircularStack() {
        clear();
    }

    @Override
    public void push(T newEntry) {
        Node newNode = new Node(newEntry);

        if (numberOfEntries == 0) {
            lastNode = newNode;
        } else if (numberOfEntries == 1) {
            newNode.next = lastNode;
            lastNode.next = newNode;
        } else {
            newNode.next = lastNode.next;
            lastNode.next = newNode;
        }
        numberOfEntries++;
    }

    @Override
    public T pop() {
        T poppedNode = null;
        if (numberOfEntries == 0) {
            lastNode = null;
        } else if (numberOfEntries == 1) {
            poppedNode = lastNode.data;
            lastNode.next = null;
        } else {
            poppedNode = lastNode.next.data;
            lastNode.next = lastNode.next.next;
        }
        numberOfEntries--;
        return poppedNode;
    }

    @Override
    public T peek() {
        if (numberOfEntries == 0) {
            return null;
        } else if (numberOfEntries == 1) {
            return lastNode.data;
        } else {
            return lastNode.next.data;
        }
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public void clear() {
        lastNode = null;
        numberOfEntries = 0;
    }

    @Override
    public String toString() {
        return "LinkedStack{" + "firstNode=" + lastNode.data + ", numberOfEntries=" + numberOfEntries + '}';
    }

    private class Node {

        private T data;
        private Node next;

        private Node(T data) {
            this.data = data;
            this.next = null;
        }

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

}
