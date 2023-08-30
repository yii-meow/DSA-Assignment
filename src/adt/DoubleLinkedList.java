/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import java.io.Serializable;
import java.util.Iterator;

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
    // Add new node to the rear by default
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);

        if (numberOfEntries == 0) {
            firstNode = newNode;
        } else {
            Node currentNode = firstNode;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
        }
        numberOfEntries++;
        return true;
    }

    @Override
    // add new entry to any possible position
    public boolean add(int position, T newEntry) {
        if (position >= 1 || position < numberOfEntries + 1) {

        }

        return true;
    }

    @Override
    // remove the entry based on the programme id
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
        return toString(firstNode, 1);
    }

    public String toString(Node node, int it) {
        if (node.next != null) {
            return "" + it + ") " + node.data + "\n" + toString(node.next, it + 1);
        } else {
            return "" + it + ") " + node.data;
        }
    }

    public class DoubleLinkedListIterator implements Iterator<T> {

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

    }
}
