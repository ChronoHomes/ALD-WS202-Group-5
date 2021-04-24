package A05_Breitensuche;

public class Node<Type> {

	/**
	 * Linkes Kind
	 */
	protected Node<Type> left;
	
	/**
	 * Rechtes Kind
	 */
	protected Node<Type> right;
	
	/**
	 * Elternelement
	 */
	protected Node<Type> parent;
	
	/**
	 * Wert des Knotens, hier: String, der Wort enthält
	 */
	protected final Type value;


	/**
	 * Konstruktor
	 * @param value Zu speichernder Wert
	 */
	public Node(Type value) {
		this.value = value;
	}

	public Node<Type> getLeft() {
		return left;
	}

	public void setLeft(Node<Type> left) {
		this.left = left;
	}

	public Node<Type> getRight() {
		return right;
	}

	public void setRight(Node<Type> right) {
		this.right = right;
	}

	public Node<Type> getParent() {
		return parent;
	}

	public void setParent(Node<Type> parent) {
		this.parent = parent;
	}

	public Type getValue() {
		return value;
	}
	
}
