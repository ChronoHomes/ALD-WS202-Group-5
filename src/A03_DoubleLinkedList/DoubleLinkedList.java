package A03_DoubleLinkedList;

public class DoubleLinkedList<T>
{
    private Node<T> first;
    private Node<T> last;
    private Node<T> current;
    private int nodeCount;

    public DoubleLinkedList() {
        first = null;
        last = null;
        current = null;
        nodeCount = 0;
    }

    /**
     * Einfügen einer neuen <T>
     * @param element <T>
     */
    public void add(T element) {

        Node<T> node = new Node<>(element);

        if (first == null){ //empty list
            first = node;
            last = node;
            node.setNext(null);
            node.setPrevious(null);

        } else {

            Node<T> tmpCurrLast = last;

            tmpCurrLast.setNext(node); // set new node as next node for the previous first

            node.setPrevious(tmpCurrLast); // set current first node as previous
            node.setNext(null);

            last = node;

        }

        System.out.println("nodeCount = " + nodeCount);
        nodeCount++;
    }

    /**
     * Internen Zeiger für next() zurücksetzen
     */
    //TODO - check if cast makes sense
    public void reset() {
        current = first;
    }

    /**
     * analog zur Funktion reset()
     */
    //TODO - check if cast makes sense
    public void resetToLast() {
        current = last;
    }

    /**
     * Liefert erste Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getFirst() {
    	return first;
    }
    
    /**
     * Liefert letzte Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getLast() {
    	return last;
    }
    
    /**
     * Gibt aktuelle <T> zurück und setzt internen Zeiger weiter.
     * Falls current nicht gesetzt, wird null retourniert.
     * @return <T>|null
     */
    public T next() {

        if (current == null)
            return null;

        Node<T> tmp = current;
        current = current.getNext();
    	return tmp.getData();
    }

    /**
     * analog zur Funktion next()
     * @return <T>|null
     */
    public T previous() {

        if (current == null)
            return null;

        Node<T> tmp = current;
        current = current.getPrevious();
        return tmp.getData();

    }
    
    /**
     * Current-Pointer auf nächste <T> setzen (aber nicht auslesen).
     * Ignoriert still, dass current nicht gesetzt ist.
     */
    //TODO - check - added CurrentNotSetException to signature - correct?
    public void moveNext() throws CurrentNotSetException {

        if (current == null)
            throw new CurrentNotSetException();

        System.out.println("move next");
        current = current.getNext();
    }
    
    /**
     * Analog zur Funktion moveNext()
     */
    //TODO - check - added CurrentNotSetException to signature - correct?
    public void movePrevious() throws CurrentNotSetException {

        if (current == null)
            throw new CurrentNotSetException();

        current = current.getPrevious();
    }
   
    /**
     * Retourniert aktuelle (current) <T>, ohne Zeiger zu ändern
     * @return <T>
     * @throws CurrentNotSetException
     */
    public T getCurrent() throws CurrentNotSetException {

        if (current == null)
            throw new CurrentNotSetException("current list pointer not set");

    	return current.getData();
    }

    /**
     * Gibt <T> an bestimmter Position zurück
     * @param pos Position, Nummerierung startet mit 1
     * @return <T>|null
     */
    //TODO - get() never used?
    public T get(int pos) {
        if (first == null)
            throw new IllegalStateException("list is empty no elements to get");

        Node<T> currentNode = first;

        for (int i = 1; i < size() && currentNode != null; i++) {
            if (i == pos){
                return currentNode.getData();
            }

            currentNode = currentNode.getNext();
        }

        //TODO - throw Exception here?
        return null; // not found :(
    }

    /**
     * Entfernen des Elements an der angegebenen Position.
     * Falls das entfernte Element das aktuelle Element ist, wird current auf null gesetzt.
     * @param pos
     */
    public void remove(int pos) {


        System.out.println("remove called // size = " + size() + " pos to delete = " + pos);

        Node<T> currentNode = first;

        for (int i = 1; i <= size() && currentNode != null; i++) {
            System.out.println("i = " + i);
            if (i == pos){
                boolean skip = false;
                System.out.println("position found");

                if (currentNode.equals(current)){
                    current = null;
                    System.out.println("current set to null");
                }

                if (currentNode.equals(first)){
                    first = currentNode.getNext();
                    first.setPrevious(null);
                    skip = true;
                    System.out.println("delete FIRST");
                }

                if (currentNode.equals(last)){
                    last = currentNode.getPrevious();
                    last.setNext(null);
                    skip = true;
                    System.out.println("delete LAST");
                }

                if (!skip){ //TODO - needed??
                    Node<T> tmp = currentNode.getPrevious();
                    if (tmp != null)
                        tmp.setNext(currentNode.getNext());

                    tmp = currentNode.getNext();
                    if (tmp != null)
                        tmp.setPrevious(currentNode.getPrevious());
                }


                nodeCount--;
                break; //stop a re-run of the loop for performance once item has been found
                //TODO - return; ???? or already within the IF???
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

        if (current == null)
            throw new CurrentNotSetException();

        System.out.println("size() " + size());

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
            current = last.getPrevious();
            nodeCount--;
            return;
        }

        Node<T> tmp;
        tmp = current.getPrevious();
        tmp.setNext(current.getNext());

        tmp = current.getNext();
        tmp.setPrevious(current.getPrevious());

        current = current.getNext();
        if (current.getNext() == null)
            current = current.getPrevious();

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

        nodeCount++;
    }

    public int size(){
        return nodeCount;
    }
}
