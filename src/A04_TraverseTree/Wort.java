package A04_TraverseTree;

public class Wort {

	/**
	 * Linkes Kind
	 */
	private Wort left;
	
	/**
	 * Rechtes Kind
	 */
	private Wort right;
	
	/**
	 * Elternelement
	 */
	private Wort parent;
	
	/**
	 * Wert des Knotens, hier: String, der Wort enthält
	 */
	private final String wort;
	

	public Wort(String s) {
		wort = s;
	}

	public Wort getLeft() {
		return left;
	}

	public void setLeft(Wort left) {
		this.left = left;
	}

	public Wort getRight() {
		return right;
	}

	public void setRight(Wort right) {
		this.right = right;
	}

	public Wort getParent() {
		return parent;
	}

	public void setParent(Wort parent) {
		this.parent = parent;
	}

	public String getWort() {
		return wort;
	}
}
