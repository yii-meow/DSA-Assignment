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
public class DoubleLinkedList<T> implements ListInterface<T> {

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
            newNode.prev = currentNode;
        }
        numberOfEntries++;
        return true;
    }

    // @Override
    // Not working due to using Comparator / Sorting
    // add new entry to any possible position
//    public boolean add(int position, T newEntry) {
//        Node newNode = new Node(newEntry);
//
//        if (position >= 1 && position <= numberOfEntries + 1) {
//            
//            return true;
//        } else {
//            System.out.println("Insuccessfully added the new entry due to invalid position");
//            return false;
//        }
//
//    }
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

    public String toString(Node node, int position) {
        if (node.next != null) {
            return "" + position + ") " + node.data + "\n" + toString(node.next, position + 1);
        } else {
            return "" + position + ") " + node.data;
        }
    }

    public Iterator<T> getIterator() {
        return new DoubleLinkedListIterator();
    }

    public class DoubleLinkedListIterator implements Iterator<T> {

        private Node currentNode;

        DoubleLinkedListIterator() {
            currentNode = firstNode;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T returnNode = currentNode.data;
                currentNode = currentNode.next;
                return returnNode;
            } else {
                return null;
            }
        }

    }
}
