/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author yiyimeow
 */
public class Node<T> {

    private T data;
    private Node next;
    private Node prev;

    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public Node(T data, Node next) {
        this.data = data;
        this.next = next;
    }
    
    public T getData(){
        return data;
    }
    
    public void setData(T data){
        this.data = data;
    }
    
    public Node getNext(){
        return next;
    }
    
    public void setNext(Node next){
        this.next = next;
    }
    
    public Node getPrev(){
        return prev;
    }
    
    public void setPrev(Node prev){
        this.prev = prev;
    }
}
