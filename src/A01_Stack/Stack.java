package A01_Stack;


public class Stack<T>
{
    private Node<T> first;
    private int size;

    public Stack() {
        size = 0;   // start with zero with every new stack
        // push ++ / pop --
    }
    
    /**
     * Oberstes Element entfernen und zurückliefern.
     * Existiert kein Element, wird eine Exception ausgelöst.
     * @throws StackEmptyException
     */
    public T pop() throws StackEmptyException {

        if (size <= 0)    // guard clause - to throw StackEmptyException
            throw new StackEmptyException("There is nothing left for you :(");

        T data;                     // create generic variable for data
        data = first.getData();     // get data from top of stack
        first = first.getNext();    // update pointer to next in "line"
        size--;                     // reduce the size of stack

        return data;
    }

    /**
     * Übergebenen T auf Stack (als oberstes Element) speichern.
     * @param i data
     */
    public void push(T i) {
        Node node = new Node(i);    // create new element for stack
        Node tmp = first;           // store current "first" as temporary node
        first = node;               // assign new node as first (update it)
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
