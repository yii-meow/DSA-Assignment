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
public class DoubleLinkedList<T extends Comparable<T>> implements ListInterface<T>, Serializable {

    private int numberOfEntries;
    private Node firstNode;

    public DoubleLinkedList() {
        clear();
    }

    @Override
    // Add new node by compareTo for sorting
    public boolean add(T newEntry) {
        // Create new node
        Node newNode = new Node(newEntry);

        // If the list is empty, or the new entry should be inserted at the beginning
        if (isEmpty() || newEntry.compareTo(firstNode.data) <= 0) {
            newNode.next = firstNode;
            firstNode = newNode;
        } else {
            // Find the correct position for sorting
            Node currentNode = firstNode;
            while (currentNode.next != null && newEntry.compareTo(currentNode.next.data) > 0) {
                currentNode = currentNode.next;
            }

            // Insert the new node after currentNode
            newNode.next = currentNode.next;
            currentNode.next = newNode;
        }

        numberOfEntries++;
        return true;
    }

    @Override
    // remove the entry based on the programme id
    public T remove(T element) {
        if (contains(element)) {
            Node currentNode = firstNode;
            while (currentNode.next != null && currentNode.data != element) {
                currentNode = currentNode.next;
            }
            // Remove the only element
            if (getNumberOfEntries() == 1) {
                clear();
            } // Remove the first element
            else if (currentNode.prev == null) {
                firstNode = currentNode.next;
            } else {
                currentNode.prev.next = currentNode.next;
            }
            return (T) currentNode;
        }
        return null;
    }

    @Override
    public void clear() {
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public boolean contains(T element) {
        Iterator it = getIterator();

        while (it.hasNext()) {
            if (it.next() == element) {
                return true;
            }
        }
        return false;
    }

    // Initialize
    public int getNumberOfEntries() {
        return getNumberOfEntries(firstNode);
    }

//    @Override
    public int getNumberOfEntries(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + getNumberOfEntries(node.next);
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    public class Node implements Serializable {

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
        if (isEmpty()) {
            return "No programme added so far...";
        }
        return toString(firstNode, 1);
    }

    public String toString(Node node, int position) {
        if (node != null) {
            return "" + position + ") " + node.data + "\n" + toString(node.next, position + 1);
        } else {
            return "";
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
