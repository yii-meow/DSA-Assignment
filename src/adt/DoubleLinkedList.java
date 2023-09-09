/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import entity.Programme;
import java.io.Serializable;
import java.util.Iterator;

/**
 *
 * @author yikso
 */
public class DoubleLinkedList<T extends Comparable<T>> implements ListInterface<T>, Serializable {

    private Node firstNode;

    public DoubleLinkedList() {
        clear();
    }

    @Override
    // Add new node by compareTo for sorting
    public boolean add(T newEntry) {
        // Create new node
        Node newNode = new Node(newEntry);

        // If the list is empty 
        if (isEmpty()) {
            firstNode = newNode;
        } // if the new entry should be inserted at the beginning
        else if (newEntry.compareTo(firstNode.data) <= 0) {
            firstNode.prev = newNode;
            newNode.next = firstNode;
            newNode.prev = null;
            firstNode = newNode;
        } else {
            // Find the correct position for sorting
            Node currentNode = firstNode;
            while (currentNode.next != null && newEntry.compareTo(currentNode.next.data) > 0) {
                currentNode = currentNode.next;
            }
            // Insert the new node after currentNode
            newNode.next = currentNode.next;
            newNode.prev = currentNode;

            if (currentNode.next != null) {
                currentNode.next.prev = newNode; // Update the prev pointer of the next node
            }

            currentNode.next = newNode;
        }
        return true;
    }

    @Override
    // remove the entry 
    public T remove(T element) {
        if (contains(element) && getNumberOfEntries() != 0) {
            Node currentNode = firstNode;

            // remove first element
            if (currentNode != null && currentNode.data.equals(element)) {
                firstNode = currentNode.next;

                if (firstNode != null) {
                    firstNode.prev = null;
                }
                return currentNode.data;
            }

            while (currentNode != null && !currentNode.data.equals(element)) {
                currentNode = currentNode.next;
            }

            if (currentNode != null) {
                if (currentNode.prev != null) {
                    currentNode.prev.next = currentNode.next;
                }
                if (currentNode.next != null) {
                    currentNode.next.prev = currentNode.prev;
                }
            }
            return currentNode.data;
        }
        return null;
    }

    @Override
    public void clear() {
        firstNode = null;
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
    // Recursively loop through every node and get the total number of nodes
    public int getNumberOfEntries(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + getNumberOfEntries(node.next);
    }

    @Override
    public boolean isEmpty() {
        return getNumberOfEntries() == 0;
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

        public Node getNext() {
            return currentNode.next;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        public boolean hasPrevious() {
            return currentNode.prev != null;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T returnNode = currentNode.data;
                if (currentNode.next != null) {
                    currentNode = currentNode.next;
                }
                return returnNode;
            } else {
                return null;
            }
        }

        public T previous() {
            if (hasPrevious()) {
                T returnNode = currentNode.prev.data;
                currentNode = currentNode.prev;
                return returnNode;
            } else {
                return null;
            }
        }
    }
}
