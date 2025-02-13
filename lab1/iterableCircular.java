/*
#  Name: Mohamad Abou Helal.

# School: Royal Institute of Technology.

# Course: Algorithms & data structures,HT2021.

# This code was written as part of examination of KTH course ID1021.

#The code is written by me using the textbook (Algorithms, Robert Sedgweick).

 */

 public class iterableCircular<Item> {

    Node start;     // object
    Node end;
    int N = 0;

    class Node {     // skapa Node klass.
        Item item;
        Node next;
        Node prev;
    }

    public void addEnd(Item item) {     // Funktionen att adda Node i slutet av listan.
        if (N == 0) {
            Node newNode = new Node(); // sakpa nya Node:
            newNode.item = item;    // typ av itrm
            start = newNode;
            end = newNode;
            start.prev = end;
            end.next = start;
            N++;
            return;
        }

        Node newEnd = new Node();
        newEnd.item = item;
        Node oldEnd = end;
        oldEnd.next = newEnd;
        newEnd.prev = oldEnd;
        newEnd.next = start;
        start.prev = newEnd;
        end = newEnd;
        N++;
    }

    public void addBegin(Item item) {  // Metoden att adda Node i början av listan.

        if (N == 0) {
            Node newNode = new Node();
            newNode.item = item;
            start = newNode;
            end = newNode;
            start.prev = end;
            end.next = start;
            N++;
            return;
        }
        Node newStart = new Node();
        newStart.item = item;
        Node oldStart = start; // copy
        oldStart.prev = newStart;
        newStart.next = oldStart;
        end.next = newStart;
        newStart.prev = end;
        start = newStart;
        N++;
    }

    public Node deleteEnd() {
        if (N == 0) {
            System.out.println(" List is Empty, return value is null");
            return new Node();
        } else if (N == 1) {
            if (start != null) {
                Node temp = start;
                start = null;
                N--;
                return temp;
            } else {
                Node temp = end;
                end = null;
                N--;

                return temp;
            }
        }

        end.prev.next = start;
        start.prev = end.prev;
        Node oldEnd = new Node();
        oldEnd = end;     // copy Node
        end = end.prev; //   Nya Node blir.
        N--;
        if (N == 1) {
            start = end;
            end = start;
        }

        return oldEnd;
    }


    public Node deletStart() {
        if (N == 0) {
            System.out.println(" List is Empty, return value is null");
            return new Node();
        } else if (N == 1) {
            if (start != null) {
                Node temp = start;
                start = null;
                N--;
                return temp;
            } else {
                Node temp = end;
                end = null;
                N--;

                return temp;
            }
        }
        end.next = start.next;
        start.next.prev = end;
        Node oldStart = new Node();
        oldStart = start;
        start = start.next;
        N--;
        if (N == 1) {
            start = end;
            end = start;
        }
        return oldStart;
    }

    public Node deleteKth(int index) {
        Node temp = new Node();
        temp = start;                                   // Question 5 ( Method) !!!.
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }

        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        if (temp == start) {
            start = temp.next;
        } else if (temp == end) {

            end = temp.prev;
        }

        N--;
        return temp;
    }


    public void print() {                // Function att display metoden!.
        if (start == null || end == null) {
            System.out.println("List is empty, nothing to display!");
            return;
        }
        Node temp = start;

        System.out.println("\nTraversal in forward direction \n");
        while (temp.next != start && temp != null) {
            System.out.print(" " + temp.item.toString());
            temp = temp.next;
        }
        System.out.print(" " + temp.item.toString());
        System.out.println();

        System.out.println("\nTraversal in reverse direction \n");
        Node temp2 = end;

        while (temp2.prev != end && temp2 != null) {
            System.out.print(" " + temp2.item.toString());
            temp2 = temp2.prev;
        }
        System.out.print(" " + temp2.item.toString());
        System.out.println();
    }
    
    
    
    
    public static void main(String[] args) {
        iterableCircular list = new iterableCircular();
        list.addBegin(1);    // Adda till lista & testa hur skriven ut är !!
        list.addEnd(2);
        list.addBegin(3);
        list.addBegin(4);
        list.addEnd("Mohamad!");
        list.addBegin(5);
        list.addBegin(6);
        list.addBegin(7);
        list.addEnd("KTH");

        list.deletStart();
        list.deletStart();
        list.deletStart();
        list.deleteEnd();
        list.deleteEnd();

        list.print();

       /* list.print();
        list.deleteKth(3);   //Question 5 här !!!
        list.print();*/

        System.out.print(list.N);
    }
}

