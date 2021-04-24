package A05_Breitensuche;

public abstract class BaseTree<Type> {

	/**
	 * Wurzel des Baums (Startknoten)
	 */
	protected Node<Type> root;

	/**
	 * Wurzel auslesen
	 * @return
	 */
	public Node<Type> getRoot() {
		return root;
	}

	/** 
	 * Methode zum Vergleich zweier Elemente für die innere Ordnung des Baums
	 * @param a Erstes Element
	 * @param b Zweites Element
	 * @return <0, wenn a<b | >0, wenn a>b | 0, wenn a=b
	 */
	protected abstract int compare(Type a, Type b);

	/**
	 * Neues Element hinzufügen
	 * @param elem Hinzuzufügendes Element
	 */
	public void add(Type elem) {
		Node<Type> neu = new Node<Type>(elem);
		if (root == null) {			// Fall 1: Baum ist leer
			root = neu;
			return;
		}
		Node<Type> node = root;				// Fall 2: Baum ist nicht leer
		while (true) {
			int vgl = compare(elem, node.getValue());
			if (vgl < 0) {					// kleiner
				if (node.getLeft() == null) {
					node.setLeft(neu);
					neu.setParent(node);
					return;
				}
				node = node.getLeft();
			}
			else if (vgl > 0) {				// größer
				if (node.getRight() == null) {
					node.setRight(neu);
					neu.setParent(node);
					return;
				}
				node = node.getRight();
			}
			else {							// gleich (nicht nochmal einfügen)
				return;
			}
		}
	}

	/**
	 * Element im Baum finden (startet bei Root-Node)
	 * @param needle Zu suchendes Element
	 * @return Knoten des Elements
	 */
	public Node<Type> find(Type needle) {
		return find(root, needle);
	}
	
	/**
	 * Element in Teilbaum finden (startet bei current-Node)
	 * @param current Startknoten
	 * @param needle  Zu suchendes Element
	 * @return Knoten des Elements
	 */
	public Node<Type> find(Node<Type> current, Type needle) {
		if (current == null) {
			return null;
		}
		int vgl = compare(needle, current.getValue());
		if (vgl == 0) {		// Gefunden
			return current;
		}
		else if (vgl < 0) {	// Links
			return find(current.getLeft(), needle);
		}
		else {				// Rechts
			return find(current.getRight(), needle);
		}
	}
	
	/**
	 * Funktion zur Ausgabe des gesamten Baums.
	 */
	public void printTree() {
		printTree(root, "");
	}
	
	/**
	 * Funktion zur Ausgabe des Baums unterhalb eines Knotens
	 * @param current Knoten, dessen Teilbaum ausgegeben werden soll
	 * @param prefix  Zur Einrückung
	 */
	public void printTree(Node<Type> current, String prefix) {
		if (current == null) {
			return;
		}
		System.out.println(prefix + current.getValue());
		printTree(current.getLeft(), prefix + " L ");
		printTree(current.getRight(), prefix + " R ");
	}

}
