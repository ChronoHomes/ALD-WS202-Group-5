package A03_DoubleLinkedList;

public class DoubleLinkedList<T>
{
    private Node<T> first;
    private Node<T> last;
    private Node<T> current;
    private int nodeCount;

    //TODO - check if possible to empty complete list -> check if return values are okay

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
    public void add(T element) {

        Node<T> node = new Node<>(element);     // create new node element

        if (first == null){             // check if new element is first element in list // Initialize first element
            first = node;               // assign new node as first
            last = node;                // assign new node as last
     //       node.setNext(null);         // set next from last element to null
     //       node.setPrevious(null);     // set previous from first element to null

        } else {
            Node<T> currentLastNode = last;     // assign current last node to variable
            currentLastNode.setNext(node);      // set new node as next node for the previous first
            node.setPrevious(currentLastNode);  // set current first node as previous
            last = node;                        // set new node as last
    //        node.setNext(null);                 // set next from last to null
        }
        nodeCount++;                            // increase node counter
    }

    /**
     * Internen Zeiger für next() zurücksetzen
     */
    public void reset() {
        current = first;    // reset current (list-pointer) to first element in list
    }

    /**
     * analog zur Funktion reset()
     */
    public void resetToLast() {
        current = last;     // reset current (list-pointer) to last element in list
    }

    /**
     * Liefert erste Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getFirst() {
    	return first;   // return first element list, if list is empty return null (ensured due constructor)
    }
    
    /**
     * Liefert letzte Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getLast() {
    	return last;    // return last element list, if list is empty return null (ensured due constructor)
    }
    
    /**
     * Gibt aktuelle <T> zurück und setzt internen Zeiger weiter.
     * Falls current nicht gesetzt, wird null retourniert.
     * @return <T>|null
     */
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

    public void moveNext() {

        if (current != null)
            current = current.getNext();                            // move current (list-pointer) to next element in list
    }
    
    /**
     * Analog zur Funktion moveNext()
     */

    public void movePrevious() {

        if (current != null)
            current = current.getPrevious();                        // move current (list-pointer) to previous element in list
    }
   
    /**
     * Retourniert aktuelle (current) <T>, ohne Zeiger zu ändern
     * @return <T>
     * @throws CurrentNotSetException
     */
    public T getCurrent() throws CurrentNotSetException {

        if (current == null) throw new CurrentNotSetException();    // throw exception if list-pointer is not set

    	return current.getData();                                   // return value from current element from list-pointer
    }

    /**
     * Gibt <T> an bestimmter Position zurück
     * @param pos Position, Nummerierung startet mit 1
     * @return <T>|null
     */
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
    public void remove(int pos) {

        Node<T> currentNode = first;    // start with first node in loop

        for (int i = 1; i <= size(); i++) { // another option would be -> while(currentNode != null) or only the size() in the for loop

            if (i == pos){      // check if position has been found

                if (currentNode.getPrevious() != null)  // do not try to set next on a null
                    currentNode.getPrevious().setNext(currentNode.getNext()); // set from previous element the new next "unlink" the element which should be removed
                else // first found -> if (currentNode.equals(first)){}
                    first = currentNode.getNext();

                if (currentNode.getNext() != null)      // do not try to set previous on a null
                    currentNode.getNext().setPrevious(currentNode.getPrevious());  // set from next element the new previous "unlink" the element which should be removed
                else // last found -> if (currentNode.equals(last)){}
                    last = currentNode.getPrevious();


                if (currentNode.equals(current)){   // check if current element should be deleted if yes set pointer to null
                    current = null;
                }

                nodeCount--;
                break; //stop a re-run of the loop for performance once item has been found
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
    public void removeCurrent() throws CurrentNotSetException {

        if (current == null) throw new CurrentNotSetException();

        // --------------------------------------------------------------------
        // same as remove just with a bit added current pointer handling
        // TODO - check if possible to "merge" removeCurrent() and remove since both are almost the same...
        // -------------------------------

        // TODO - check if still works if last and only element is deleted

        if (current.getPrevious() != null)  // do not try to set next on a null
            current.getPrevious().setNext(current.getNext()); // set from previous element the new next "unlink" the element which should be removed
        else // first found -> if (currentNode.equals(first)){}
            first = current.getNext(); // current should be removed therefore "next of current" is new first if current equals first

        if (current.getNext() != null) {     // do not try to set previous on a null
            current.getNext().setPrevious(current.getPrevious());  // set from next element the new previous "unlink" the element which should be removed
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
    public void insertAfterCurrentAndMove(T element) throws CurrentNotSetException { //TODO - refactor

        if (current == null)
            throw new CurrentNotSetException();

        Node<T> insertNode = new Node<>(element);
        Node<T> tmpNext = current.getNext();
        Node<T> tmpCurrent = current;

        tmpCurrent.setNext(insertNode);

        if (tmpNext != null) // not possible to assign if "next" is null
            tmpNext.setPrevious(insertNode);

        insertNode.setPrevious(tmpCurrent);
        insertNode.setNext(tmpNext);

        current = insertNode;

        nodeCount++;
    }

    public int size(){
        return nodeCount;
    }
}
