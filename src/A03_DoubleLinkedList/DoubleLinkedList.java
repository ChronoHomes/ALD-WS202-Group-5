package A03_DoubleLinkedList;

public class DoubleLinkedList<T>
{
    private Node<T> first;
    private Node<T> last;
    private Node<T> current;
    private int nodeCount;

    public DoubleLinkedList() {     // constructor -> called when new dll is created
        first = null;               // initialise first element as null
        last = null;                // initialise last element as null
        current = null;             // initialise current element as null
        nodeCount = 0;              // initialise nodeCount with 0
    }

    /**
     * Einfügen einer neuen <T>
     * @param element <T>
     */
    /** Big O notation -> O(1) */
    public void add(T element) {

        Node<T> node = new Node<>(element);     // create new node element

        if (first == null){             // check if new element is first element in list // Initialize first element
            first = node;               // assign new node as first
            last = node;                // assign new node as last
        } else {
            Node<T> tmpNode = last;     // assign current last node to variable
            tmpNode.setNext(node);      // set new node as next node for the previous first
            node.setPrevious(tmpNode);  // set current first node as previous
            last = node;                // set new node as last
        }
        nodeCount++;                    // increase node counter
    }

    /**
     * Internen Zeiger für next() zurücksetzen
     */
    /** Big O notation -> O(1) */
    public void reset() {
        current = first;    // reset current (list-pointer) to first element in list
    }

    /**
     * analog zur Funktion reset()
     */
    /** Big O notation -> O(1) */
    public void resetToLast() {
        current = last;     // reset current (list-pointer) to last element in list
    }

    /**
     * Liefert erste Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    /** Big O notation -> O(1) */
    public Node<T> getFirst() {
    	return first;   // return first element list, if list is empty return null (ensured due constructor)
    }
    
    /**
     * Liefert letzte Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    /** Big O notation -> O(1) */
    public Node<T> getLast() {
    	return last;    // return last element list, if list is empty return null (ensured due constructor)
    }
    
    /**
     * Gibt aktuelle <T> zurück und setzt internen Zeiger weiter.
     * Falls current nicht gesetzt, wird null retourniert.
     * @return <T>|null
     */
    /** Big O notation -> O(1) */
    public T next() {

        if (current == null)            // return null if current (list-pointer) is not set
            return null;

        Node<T> tmp = current;          // assign value from current element in list to temporary variable
        current = current.getNext();    // move current (list-pointer) to next element in list
    	return tmp.getData();           // return value from the current element -> when method was called
    }

    /**
     * analog zur Funktion next()
     * @return <T>|null
     */
    /** Big O notation -> O(1) */
    public T previous() {

        if (current == null)                // return null if current (list-pointer) is not set
            return null;

        Node<T> tmp = current;              // assign value from current element in list to temporary variable
        current = current.getPrevious();    // move current (list-pointer) to previous element in list
        return tmp.getData();               // return value from the current element -> when method was called
    }
    
    /**
     * Current-Pointer auf nächste <T> setzen (aber nicht auslesen).
     * Ignoriert still, dass current nicht gesetzt ist.
     */
    /** Big O notation -> O(1) */
    public void moveNext() {

        if (current != null)
            current = current.getNext();        // move current (list-pointer) to next element in list
    }
    
    /**
     * Analog zur Funktion moveNext()
     */
    /** Big O notation -> O(1) */
    public void movePrevious() {

        if (current != null)
            current = current.getPrevious();    // move current (list-pointer) to previous element in list
    }
   
    /**
     * Retourniert aktuelle (current) <T>, ohne Zeiger zu ändern
     * @return <T>
     * @throws CurrentNotSetException
     */
    /** Big O notation -> O(1) */
    public T getCurrent() throws CurrentNotSetException {

        if (current == null) throw new CurrentNotSetException();    // throw exception if list-pointer is not set

    	return current.getData();                                   // return value from current element from list-pointer
    }

    /**
     * Gibt <T> an bestimmter Position zurück
     * @param pos Position, Nummerierung startet mit 1
     * @return <T>|null
     */
    /** Big O notation -> O(n) */
    public T get(int pos) {     //TODO - write Unit Test to proof get() is working
        if (first == null) throw new IllegalStateException();       // throw Exception if list is empty -> to be confirmed if this should work like this

        Node<T> currentNode = first;                                // assign first node as current for element iteration through loop

        for (int i = 1; i < size() && currentNode != null; i++) {   // for loop while i smaller than size() (=nodeCount) and iteration node not null
            if (i == pos){                                          // check if current element (=currentNode) is same as desired position
                return currentNode.getData();                       // in case if statement is true return data and exit function
            }
            currentNode = currentNode.getNext();                    // get next node for next loop iteration
        }

        return null;                                                // return null if element was not found for some reason (=should not reach this point)
    }

    /**
     * Entfernen des Elements an der angegebenen Position.
     * Falls das entfernte Element das aktuelle Element ist, wird current auf null gesetzt.
     * @param pos
     */
    /** Big O notation -> O(n) */
    public void remove(int pos) {

        Node<T> currentNode = first;    // start with first node in loop

        for (int i = 1; i <= size() && currentNode != null; i++) { // loop to iterate through complete dll

            if (i == pos){      // check if position has been found

                if (currentNode.getPrevious() != null)  // check if previous is not a null
                    currentNode.getPrevious().setNext(currentNode.getNext()); // set from the previous element the new next which is the next element of the one which should be removed
                else // first found -> if (currentNode.equals(first)){}
                    first = currentNode.getNext();

                if (currentNode.getNext() != null)      // check if next is not a null
                    currentNode.getNext().setPrevious(currentNode.getPrevious());  // set from the next element the new previous which is the previous element of the one which should be removed
                else // last found -> if (currentNode.equals(last)){}
                    last = currentNode.getPrevious();


                if (currentNode.equals(current)){   // check if current element should be deleted if yes set pointer to null
                    current = null;
                }

                nodeCount--;
                break; //stop a re-run of the loop
            }

            currentNode = currentNode.getNext();
        }



    }
    
    /**
     * Entfernt das aktuelle Element.
     * Als neues aktuelles Element wird der Nachfolger gesetzt oder
     * (falls kein Nachfolger) das vorhergehende Element 
     * @throws CurrentNotSetException
     */
    /** Big O notation -> O(1) */
    public void removeCurrent() throws CurrentNotSetException {     // TODO - merge remove() & removeCurrent() to since both are almost the same or create helper function

        if (current == null) throw new CurrentNotSetException();

        if (current.getPrevious() != null)  // do not try to set next on a null
            current.getPrevious().setNext(current.getNext()); // set from the previous element the new next which is the next element of the one which should be removed
        else // first found -> if (currentNode.equals(first)){}
            first = current.getNext(); // current should be removed therefore "next of current" is new first if current equals first

        if (current.getNext() != null) {     // do not try to set previous on a null
            current.getNext().setPrevious(current.getPrevious());  // set from the next element the new previous which is the previous element of the one which should be removed
            current = current.getNext(); //current pointer handling
        } else { // last found -> if (currentNode.equals(last)){}
            last = current.getPrevious();
            current = current.getPrevious(); //current pointer handling
        }

        nodeCount--;

    }
    
    /**
     * Die Methode fügt die übergebene <T> nach der aktuellen (current) ein
     * und setzt dann die neu eingefügte <T> als aktuelle (current) <T>.
     * @throws CurrentNotSetException 
     */
    /** Big O notation -> O(1) */
    public void insertAfterCurrentAndMove(T element) throws CurrentNotSetException {

        if (current == null)
            throw new CurrentNotSetException();

        Node<T> insertNode = new Node<>(element);
        Node<T> tmpNext = current.getNext();
        Node<T> tmpCurrent = current;

        tmpCurrent.setNext(insertNode);

        if (tmpNext != null)
            tmpNext.setPrevious(insertNode);

        insertNode.setPrevious(tmpCurrent);
        insertNode.setNext(tmpNext);

        current = insertNode;

        nodeCount++;
    }

    /** Big O notation -> O(1) */
    public int size(){
        return nodeCount;
    }
}
