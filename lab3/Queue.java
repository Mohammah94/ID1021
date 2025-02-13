/*
#  Name: Mohamad Abou Helal.

# School: Royal Institute of Technology.

# Course: Algorithms & data structures, HT2021

# This code was written as part of examination of KTH course ID1021.

#The code is written by me using the textbook (Algorithms, Robert Sedgweick).

 */

 public class Queue <Item> {

    private Node first;            // link to least recently added node // att ladda node till  i början
    private Node last;             // link to most recently added node // att ladda Node i slutet:
    private int N;                 // length

    private class Node { // nested class to define nodes
        Item item;
        Node next;
        Node prev;
    }

    public boolean isEmpty() {
        return first == null;
    }
    // Or: N == 0.

    public int size() {
        return N;
    }

    public void enqueue(Item item) { // For adding the first node to the queue

       /* Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        N++; */

        if (isEmpty()) {

            Node newNode = new Node(); // skapa Node.
            newNode.item = item;
            last = newNode;                   //  if satatment går genom koden for En Node
            first = newNode;
        } else {
            Node newNode = new Node();
            newNode.item = item;
            last.next = newNode;               //  Annars går här
            newNode.prev = last;
            last = newNode;
        }
        N++;
        printQueue();
    }

    public Item dequeue() {      // Remove item from the beginning of the list.
        Item item = first.item; //***********
        first = first.next;
        if (isEmpty()) last = null;
        N--;
        printQueue();
        return item;
    }

    public void printQueue() {
        Node example = new Node();
        example = first;
        while (example != null) {
            System.out.print(" <=> " + example.item);
            example = example.next;
        }
        System.out.println();
        System.out.println("Queue ends here!!");

    }

    public static void main(String[] args) {         // Create a queue and enqueue/dequeue strings.
        //Scanner scan = new Scanner(System.in);

        Queue<String> q = new Queue<String>();
        q.enqueue("Hi");
        q.enqueue("world");
        q.enqueue("I");
        q.enqueue("am");
        q.enqueue("studying");
        q.enqueue("al-gorithm");

        System.out.println();

        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();

        System.out.println("(" + q.size() + " left on queue)");
    }
}

