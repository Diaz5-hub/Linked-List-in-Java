package edu.csc220.linkedlist;

import java.util.*;

public class Program {
    /**
     * insertInSortedOrder assumes that the singly linked list starting at head is already arranged in increasing sorted
     * order, alphabetically. It modifies the list by inserting a new node containing newElement in the correct place so
     * that the list remains in sorted order. The method should return the head node of the modified list.
     *
     * See the assignment handout for more details and tips on how to approach the problem.
     */
    public SingleNode insertInSortedOrder(SingleNode head, String newElement) {
        // TODO: Implement.
        SingleNode prev = null;
        SingleNode curr = head;             //set curr = head
        SingleNode newNode = new SingleNode(newElement);        //create new node with new element
        while(curr != null){                                        //traverse linked list
            if(curr.getElement().compareTo(newNode.getElement()) > 0){  //if new node appears before curr
                if(prev == null){
                    newNode.setNext(head);
                    head = newNode;
                    break;
                }
                else{
                    newNode.setNext(curr);
                    prev.setNext(newNode);
                    break;
                }
            }

            prev = curr;
            curr = curr.getNext();
        }
        if(curr == null) {
            prev.setNext(newNode);
        }
        return head;
    }

    /**
     * removeAllWithLength should modify the singly linked list starting at head by removing all Strings whose length
     * is equal to the length parameter. The method should return the head node of the modified list.
     *
     * See the assignment handout for more details and tips on how to approach the problem.
     */
    public SingleNode removeAllWithLength(SingleNode head, int length) {
        // TODO: Implement.
        SingleNode prev = null;
        SingleNode curr = head;
        while(curr != null){
            if(curr.getElement().length() == length){       //if length is found in list
                if(prev == null){               //if at the head of list
                    head = head.getNext();
                }
                else{                                   //otherwise
                    prev.setNext(curr.getNext());
                }
            }
            prev = curr;
            curr = curr.getNext();
        }
        if(curr == null){                               //if removing last node
            prev.setNext(null);
        }
        return head;
    }

    /**
     * insertInSortedOrder assumes that the doubly linked list starting at head is already arranged in increasing sorted
     * order, alphabetically. It modifies the list by inserting a new node containing newElement in the correct place so
     * that the list remains in sorted order.
     *
     * See the assignment handout for more details and tips on how to approach the problem.
     */
    public DoubleNode insertInSortedOrder(DoubleNode head, String newElement) {
        // TODO: Implement.
        DoubleNode curr = head;     //set curr to head
        DoubleNode previous = curr.getPrev();
        DoubleNode newNode = new DoubleNode(newElement);
        while(curr != null){
            if(curr.getElement().compareTo(newNode.getElement()) > 0){     //if newNode appears before curr
                newNode.setNext(curr);      // newNode next to bob
                newNode.setPrev(previous);     //newNode prev to Alice
                curr.setPrev(newNode);//bob prev to newNode

                if(previous == null){       //if at head of the list
                    head = newNode;
                }
                else{           //if not at head of list
                    previous.setNext(newNode);
                }
                break;
            }                               //if at end, put outside while loop
            previous = curr;
            curr = curr.getNext();          //fine rn
        }
        if(curr == null){                   //if at the end of list
            previous.setNext(newNode);
            newNode.setPrev(previous);
        }

        return head;
    }


    public static void main(String[] args) {
        Program program = new Program();

        System.out.print("Checking insertInSortedOrder for singly linked list... ");
        SingleNode ll1 = createSinglyLinkedList("Alice", "Bob", "Carlos");
        ll1 = program.insertInSortedOrder(ll1, "Amelia");
        verify(ll1, "Alice", "Amelia", "Bob", "Carlos");
        System.out.println("Done!");

        System.out.print("Checking removeAllWithLength for singly linked list... ");
        SingleNode ll2 = createSinglyLinkedList("salad", "carrot", "apple", "celery", "crouton", "banana");
        ll2 = program.removeAllWithLength(ll2, 6);
        verify(ll2, "salad", "apple", "crouton");
        System.out.println("Done!");


        System.out.print("Checking insertInSortedOrder for doubly linked list... ");
        DoubleNode ll3 = createDoublyLinkedList("Alice", "Bob", "Carlos");
        ll3 = program.insertInSortedOrder(ll3, "Amelia");
        verify(ll3, "Alice", "Amelia", "Bob", "Carlos");
        System.out.println("Done!");
    }

    /** You SHOULD NOT call this method in your implementation. */
    private static SingleNode createSinglyLinkedList(String... elements) {
        SingleNode head = null;
        for (int i = elements.length - 1; i >= 0; i--) {
            SingleNode node = new SingleNode(elements[i]);
            node.setNext(head);
            head = node;
        }
        return head;
    }

    /** You SHOULD NOT call this method in your implementation. */
    private static DoubleNode createDoublyLinkedList(String... elements) {
        DoubleNode head = null;
        for (int i = elements.length - 1; i >= 0; i--) {
            DoubleNode node = new DoubleNode(elements[i]);
            if (head != null) {
                node.setNext(head);
                head.setPrev(node);
            }
            head = node;
        }
        return head;
    }

    private static void verify(SingleNode head, String... expected) {
        HashSet<SingleNode> nodesSeen = new HashSet<>();
        ArrayList<String> elements = new ArrayList<>();
        while (head != null) {
            if (nodesSeen.contains(head)) {
                throw new RuntimeException(
                        String.format(
                                "Found a cycle in the linked list! We've already seen '%s' before.",
                                head.getElement()));
            }
            nodesSeen.add(head);
            elements.add(head.getElement());
            head = head.getNext();
        }

        List<String> expectedElements = Arrays.asList(expected);
        if (!expectedElements.equals(elements)) {
            throw new RuntimeException(
                    String.format("\nExpected: %s\nActual:   %s", expectedElements, elements));
        }
    }

    private static void verify(DoubleNode head, String... expected) {
        HashSet<DoubleNode> nodesSeen = new HashSet<>();
        ArrayList<String> elements = new ArrayList<>();

        DoubleNode tail = null;
        while (head != null) {
            if (nodesSeen.contains(head)) {
                throw new RuntimeException(
                        String.format(
                                "Found a cycle in the linked list (forward direction)! We've already seen '%s' before.",
                                head.getElement()));
            }
            nodesSeen.add(head);
            elements.add(head.getElement());
            tail = head;
            head = head.getNext();
        }

        nodesSeen.clear();
        ArrayList<String> reverseElements = new ArrayList<>();
        while (tail != null) {
            if (nodesSeen.contains(tail)) {
                throw new RuntimeException(
                        String.format(
                                "Found a cycle in the linked list (reverse direction)! We've already seen '%s' before.",
                                tail.getElement()));
            }
            nodesSeen.add(tail);
            reverseElements.add(tail.getElement());
            tail = tail.getPrev();
        }

        List<String> expectedElements = Arrays.asList(expected);
        if (!expectedElements.equals(elements)) {
            throw new RuntimeException(
                    String.format("\nExpected: %s\nActual:   %s", expectedElements, elements));
        }

        Collections.reverse(expectedElements);
        if (!expectedElements.equals(reverseElements)) {
            throw new RuntimeException(
                    String.format(
                            "\nExpected (reversed): %s\nActual (reversed):   %s", expectedElements, reverseElements));
        }
    }
}
