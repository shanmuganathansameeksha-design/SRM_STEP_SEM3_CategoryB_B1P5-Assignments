Here is the Java code for **Problem 1: Priority Token Insertion** without comments, matching the example and specifications from your assignment page:

```java
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class PriorityTokenInsertion {
    private Node head;

    public PriorityTokenInsertion() {
        this.head = null;
    }

    public void addFirst(int token) {
        Node newNode = new Node(token);
        newNode.next = head;
        head = newNode;
    }

    public void addLast(int token) {
        Node newNode = new Node(token);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    public void insertAt(int index, int token) {
        if (index == 0) {
            addFirst(token);
            return;
        }
        Node newNode = new Node(token);
        Node current = head;
        for (int i = 0; i < index - 1 && current != null; i++) {
            current = current.next;
        }
        if (current != null) {
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        PriorityTokenInsertion list = new PriorityTokenInsertion();

        list.addLast(101);
        list.addLast(102);
        list.addLast(103);

        list.addFirst(100);
        list.addLast(104);
        list.insertAt(2, 150);

        list.printList();
    }
}


 Time Complexities

addFirst(int token): O(1)
addLast(int token): O(N)
insertAt(int index, int token): O(N)