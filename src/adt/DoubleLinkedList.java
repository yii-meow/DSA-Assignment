/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

/**
 *
 * @author yikso
 */
public class DoubleLinkedList<T> implements ListInterface<T> {

    @Override
    public boolean add(T newEntry) {
        return true;
    }

    @Override
    public boolean add(int position, T newEntry) {
        return true;
    }

    @Override
    public T remove(T element) {
        return null;
    }

    @Override
    public void clear() {
        
    }

    @Override
    public boolean contains(T element) {
        return true;
    }

    @Override
    public int getNumberOfEntries() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isFull() {
        return true;
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
