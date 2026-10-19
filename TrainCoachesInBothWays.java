Here is the Java code for **Problem 4: Train Coaches Both Ways** without comments, matching the example and requirements from your assignment:

```java
class Node {
    String name;
    Node prev;
    Node next;

    Node(String name) {
        this.name = name;
        this.prev = null;
        this.next = null;
    }
}

public class TrainCoachesBothWays {
    private Node head;
    private Node tail;

    public TrainCoachesBothWays() {
        this.head = null;
        this.tail = null;
    }

    public void addLast(String name) {
        Node newNode = new Node(name);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public void remove(String name) {
        Node current = head;
        while (current != null) {
            if (current.name.equals(name)) {
                if (current == head) {
                    head = head.next;
                    if (head != null) {
                        head.prev = null;
                    } else {
                        tail = null;
                    }
                } else if (current == tail) {
                    tail = tail.prev;
                    if (tail != null) {
                        tail.next = null;
                    } else {
                        head = null;
                    }
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                break;
            }
            current = current.next;
        }
    }

    public void printForward() {
        System.out.print("Forward: ");
        Node current = head;
        while (current != null) {
            System.out.print(current.name);
            if (current.next != null) {
                System.out.print(" <-> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    public void printBackward() {
        System.out.print("Backward: ");
        Node current = tail;
        while (current != null) {
            System.out.print(current.name);
            if (current.prev != null) {
                System.out.print(" <-> ");
            }
            current = current.prev;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TrainCoachesBothWays train = new TrainCoachesBothWays();

        train.addLast("Engine");
        train.addLast("A1");
        train.addLast("B1");
        train.addLast("B2");
        train.addLast("Guard");

        train.remove("B1");

        train.printForward();
        train.printBackward();
    }
}



Complexity Analysis

Time to find the coach:O(N), where N is the number of coaches in the train (linear search from the head).
Time to unlink once found:O(1) constant time, as unlinking only requires updating the next and prev pointers of adjacent nodes.
Overall Time Complexity:O(N)
Auxiliary Space Complexity:O(1) constant space.