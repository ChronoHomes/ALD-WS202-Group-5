package A01_Stack;


public class Stack<T>
{
    private Node<T> first;
    private int size;

    public Stack() {
        first = null;   // top of stack to null for every new stack
        size = 0;       // start with zero with every new stack | push ++ / pop --
    }
    
    /**
     * Oberstes Element entfernen und zurückliefern.
     * Existiert kein Element, wird eine Exception ausgelöst.
     * @throws StackEmptyException
     */
    public T pop() throws StackEmptyException {

        if (size <= 0)    // guard clause - to throw StackEmptyException
            throw new StackEmptyException("There is nothing left for you :(");

        T data = null;              // create generic variable for data
        data = first.getData();     // store data in variable from top of the stack
        first = first.getNext();    // update pointer to -> "next in line"
        size--;                     // reduce size of stack

        return data;
    }

    /**
     * Übergebenen T auf Stack (als oberstes Element) speichern.
     * @param i data
     */
    public void push(T i) {
        Node node = new Node(i);    // create new element for stack
        Node tmp = first;           // store current top of stack as temporary node
        first = node;               // assign new node as first -> update it
        first.setNext(tmp);         // set reference to previous top -> next to pop
        size++;                     // increase stack size
    }

    /**
     * Liefert die Anzahl der Elemente im Stack
     * @return
     */
    public int getCount() {
        return size;
    }
}
