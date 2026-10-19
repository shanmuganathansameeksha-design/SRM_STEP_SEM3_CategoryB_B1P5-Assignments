Here is the Java code for **Problem 3: Reverse the Queue** without comments:

```java
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class ReverseTheQueue {

    public static Node reverse(Node head) {
        Node prev = null;
        Node current = head;
        Node next = null;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    public static void printList(Node head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);

        System.out.print("Sample 1 Before: ");
        printList(head1);
        head1 = reverse(head1);
        System.out.print("Sample 1 After:  ");
        printList(head1);

        System.out.println();

        Node head2 = new Node(9);

        System.out.print("Sample 2 Before: ");
        printList(head2);
        head2 = reverse(head2);
        System.out.print("Sample 2 After:  ");
        printList(head2);
    }
}



Complexity Analysis

Time Complexity:O(N), where N is the number of nodes in the queue, as it traverses the list once.
Auxiliary Space Complexity:O(1) constant space, as pointers are modified in place without extra memory allocation.