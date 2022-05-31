package edu.csc220.linkedlist;

/** Represents a node in a singly linked list. */
public class SingleNode {
    private String element;
    private SingleNode next;

    public SingleNode(String element) {
        this.element = element;
    }

    public String getElement() {
        return element;
    }

    public SingleNode getNext() {
        return next;
    }

    public void setNext(SingleNode next) {
        this.next = next;
    }
}
