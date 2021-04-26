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

        if (first == null){             // check if new element is first element
            first = node;               // assign new node as first
            last = node;                // assign new node as last
            node.setNext(null);         // set next from last element to null
            node.setPrevious(null);     // set previous from first element to null

        } else {
            Node<T> currentLastNode = last;     // assign current last node to variable
            currentLastNode.setNext(node);      // set new node as next node for the previous first
            node.setPrevious(currentLastNode);  // set current first node as previous
            last = node;                        // set new node as last
            node.setNext(null);                 // set next from last to null
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
    public T next() {   //TODO - what if "next element" is null -> if next() is called when current is at the last element?

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
    public T previous() {   //TODO - same as for next just with first element

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
    //TODO - check - added CurrentNotSetException to signature - correct -> SK?
    public void moveNext() throws CurrentNotSetException {

        if (current == null) throw new CurrentNotSetException();    // throw exception if list-pointer is not set

        current = current.getNext();                                // move current (list-pointer) to next element in list
    }
    
    /**
     * Analog zur Funktion moveNext()
     */
    //TODO - check - added CurrentNotSetException to signature - correct?
    public void movePrevious() throws CurrentNotSetException {

        if (current == null) throw new CurrentNotSetException();    // throw exception if list-pointer is not set

        current = current.getPrevious();                            // move current (list-pointer) to previous element in list
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
    public T get(int pos) {     //TODO - write Unit Test to proof get is working
        if (first == null) throw new IllegalStateException();       // throw Exception if list is empty -> to be confirmed if this should work like this

        Node<T> currentNode = first;                                // assign first node as current for element iteration through loop

        for (int i = 1; i < size() && currentNode != null; i++) {   // for loop while i smaller than size() (=nodeCount) and iteration node not null (safety..)
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

        Node<T> currentNode = first;

        for (int i = 1; i <= size() && currentNode != null; i++) {
            if (i == pos){
                boolean skip = false;
                if (currentNode.equals(current)){
                    current = null;
                }

                if (currentNode.equals(first)){
                    first = currentNode.getNext();
                    first.setPrevious(null);
                    skip = true;
                }

                if (currentNode.equals(last)){
                    last = currentNode.getPrevious();
                    last.setNext(null);
                    skip = true;
                }

                if (!skip){ //TODO - check if neeeded -> option to directly return in above statements
                    Node<T> tmp = currentNode.getPrevious();
                    if (tmp != null)
                        tmp.setNext(currentNode.getNext());

                    tmp = currentNode.getNext();
                    if (tmp != null)
                        tmp.setPrevious(currentNode.getPrevious());
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


        if (current.equals(first)){
            first = current.getNext();
            first.setPrevious(null);
            current = first.getNext();
            nodeCount--;
            return;
        }
        if (current.equals(last)){
            last = current.getPrevious();
            last.setNext(null);

            if (last.getPrevious() == null)
                current = current;
            else
                current = last.getPrevious();

            //TODO - DOES THIS MAKES SENSE????
            current = current.getPrevious();
            nodeCount--;
            return;
        }

        Node<T> tmp;
        tmp = current.getPrevious();
        tmp.setNext(current.getNext());

        tmp = current.getNext();
        tmp.setPrevious(current.getPrevious());

        if (current.getNext() == null)
            current = current.getPrevious();
        else
            current = current.getNext();

        nodeCount--;
    }
    
    /**
     * Die Methode fügt die übergebene <T> nach der aktuellen (current) ein
     * und setzt dann die neu eingefügte <T> als aktuelle (current) <T>.
     * @throws CurrentNotSetException 
     */
    public void insertAfterCurrentAndMove(T element) throws CurrentNotSetException {

        if (current == null)
            throw new CurrentNotSetException();

        //TODO - change variable names -> quite confusing currently
        Node<T> newNode = new Node<>(element);
        Node<T> tmpNext = current.getNext();
        Node<T> tmpCurrent = current;
        current = newNode;

        tmpCurrent.setNext(newNode);

        if (tmpNext != null) // not possible to assign if "next" is null
            tmpNext.setPrevious(newNode);

        newNode.setPrevious(tmpCurrent);
        newNode.setNext(tmpNext);

        nodeCount++;
    }

    public int size(){
        return nodeCount;
    }
}
