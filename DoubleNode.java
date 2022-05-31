package edu.csc220.linkedlist;

/** Represents a node in a doubly linked list. */
public class DoubleNode {
    private String element;
    private DoubleNode next;
    private DoubleNode prev;

    public DoubleNode(String element) {
        this.element = element;
    }

    public String getElement() {
        return element;
    }

    public DoubleNode getNext() {
        return next;
    }

    public DoubleNode getPrev() {
        return prev;
    }

    public void setNext(DoubleNode next) {
        this.next = next;
    }

    public void setPrev(DoubleNode prev) {
        this.prev = prev;
    }
}
