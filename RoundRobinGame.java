Here is the Java code for **Problem 5: Round-Robin Game Turns** without comments, matching the example and requirements:

```java
class Node {
    String name;
    Node next;

    Node(String name) {
        this.name = name;
        this.next = null;
    }
}

public class RoundRobinGameTurns {
    private Node tail;

    public RoundRobinGameTurns() {
        this.tail = null;
    }

    public void addLast(String name) {
        Node newNode = new Node(name);
        if (tail == null) {
            tail = newNode;
            tail.next = tail;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void printTurns(int turns) {
        if (tail == null || turns <= 0) {
            return;
        }

        Node current = tail.next;
        for (int i = 0; i < turns; i++) {
            System.out.print(current.name);
            if (i < turns - 1) {
                System.out.print(" ");
            }
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        RoundRobinGameTurns game = new RoundRobinGameTurns();

        game.addLast("Asha");
        game.addLast("Ravi");
        game.addLast("Neha");

        game.printTurns(7);
    }
}



 Complexity Analysis

Time Complexity:
addLast(String name): O(1) constant time, as we maintain a tail pointer and insert directly after it without iterating.
printTurns(int turns): O(K), where K is the number of turns requested.


Auxiliary Space Complexity:O(1) constant space, as no additional data structures are created during iteration.