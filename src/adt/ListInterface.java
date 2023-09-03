package adt;

import adt.DoubleLinkedList.Node;
import java.util.Iterator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
/**
 *
 * @author yikso
 */
public interface ListInterface<T> {

    public boolean add(T newEntry);

    public T remove(T element);

    public void clear();

    public boolean contains(T element);

//    public int getNumberOfEntries(Node node);

    public boolean isEmpty();

    public Iterator<T> getIterator();
}
