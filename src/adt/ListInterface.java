package adt;

import java.util.Iterator;
import java.util.ListIterator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
/**
 *
 * @author Chong Yik Soon
 */
public interface ListInterface<T extends Comparable<T>> {

    public T get(int index);

    public boolean add(T newEntry);

    public T remove(T element);

    public void clear();

    public boolean contains(T element);

    public int getNumberOfEntries();
    
    public boolean isEmpty();

    public Iterator<T> getIterator();
}
