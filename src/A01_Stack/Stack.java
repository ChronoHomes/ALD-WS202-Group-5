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
    /** Big O notation -> O(1) */
    public T pop() throws StackEmptyException {

        if (size == 0)    // guard clause - to throw StackEmptyException
            throw new StackEmptyException("There is nothing left for you :(");

        T data = first.getData();   // store dat in generic variable
        first = first.getNext();    // update pointer to -> "next in line"
        size--;                     // reduce size of stack

        return data;
    }

    /**
     * Übergebenen T auf Stack (als oberstes Element) speichern.
     * @param element data
     */
    /** Big O notation -> O(1) */
    public void push(T element) {
        Node<T> tmp = first;                // store current top of stack as temporary node
        first = new Node<>(element);        // create new element for top of stack
        first.setNext(tmp);                 // set reference to previous top -> next to pop
        size++;                             // increase stack size
    }

    /**
     * Liefert die Anzahl der Elemente im Stack
     * @return
     */
    /** Big O notation -> O(1) */
    public int getCount() {
        return size;
    }
}
