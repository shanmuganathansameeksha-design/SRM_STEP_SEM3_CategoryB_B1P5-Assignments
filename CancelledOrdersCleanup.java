class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class CancelledOrdersCleanup {

    public static Node removeAll(Node head, int code) {
        while (head != null && head.data == code) {
            head = head.next;
        }

        Node current = head;
        while (current != null && current.next != null) {
            if (current.next.data == code) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return head;
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
        Node head1 = new Node(5);
        head1.next = new Node(3);
        head1.next.next = new Node(5);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);

        System.out.print("Sample 1 Before: ");
        printList(head1);
        head1 = removeAll(head1, 5);
        System.out.print("Sample 1 After:  ");
        printList(head1);

        System.out.println();

        Node head2 = new Node(5);
        head2.next = new Node(5);
        head2.next.next = new Node(5);

        System.out.print("Sample 2 Before: ");
        printList(head2);
        head2 = removeAll(head2, 5);
        System.out.print("Sample 2 After:  ");
        printList(head2);
    }
}