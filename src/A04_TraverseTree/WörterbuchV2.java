package A04_TraverseTree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class WörterbuchV2 {

	/**
	 * Wurzel des Baums (Startknoten)
	 */
	private Wort root;

	public Wort getRoot() {
		return root;
	}

	/**
	 * Zählt alle Wörter des Teilbaums ab einem bestimmten Wort
	 * @param w Wort
	 * @return Zahl der Wörter (=Anzahl der Elemente)
	 */
	public int countWordsInSubTree(Wort w) {

		if (w == null)  // terminate recursion
			return 0;

		return countWordsInSubTree(w.getLeft()) + countWordsInSubTree(w.getRight()) + 1;
	}

	/**
	 * Liefert die Menge aller Wörter retour, die ein spezifisches Präfix haben.
	 * @param prefix Wörter müssen diesen Präfix haben
	 * @return Menge aller zutreffenden Wörter
	 */
	public Set<String> getWordsWithPrefix(String prefix) {
		return getWordsWithPrefix(prefix, root);
	}

	public Set<String> getWordsWithPrefix(String prefix, Wort wort) { // overloading method with additional parameter -> otherwise recursive call not possible
		Set<String> set = new HashSet<>();

		if (wort == null)	// check if word "exists" if not return set and stop any further recursive calls
			return set;

		if (wort.getWort().startsWith(prefix))	// check if word has prefix
			set.add(wort.getWort());			// if it has add it to the list

																	// build set through recursive call
		set.addAll(getWordsWithPrefix(prefix, wort.getLeft()));		// addAll so the return value of type set can be added // recursive call based on left child
		set.addAll(getWordsWithPrefix(prefix, wort.getRight()));	// addAll so the return value of type set can be added // recursive call based on right child

		return set;
	}

	/**
	 * Neues Wort hinzufügen
	 * @param wort Hinzuzufügendes Wort
	 */
	public void add(String wort) {
		Wort neu = new Wort(wort);
		if (root == null) {			// Fall 1: Baum ist leer
			root = neu;
			return;
		}
		Wort w = root;				// Fall 2: Baum ist nicht leer
		while (true) {
			int vgl = wort.compareTo(w.getWort());
			if (vgl < 0) {			// Neues Wort ist lexikographisch kleiner
				if (w.getLeft() == null) {
					w.setLeft(neu);
					neu.setParent(w);
					return;
				}
				w = w.getLeft();
			}
			else if (vgl > 0) {		// Neues Wort ist lexikographisch größer
				if (w.getRight() == null) {
					w.setRight(neu);
					neu.setParent(w);
					return;
				}
				w = w.getRight();
			}
			else {					// Neues Wort ist lexikographisch gleich
				return;
			}
		}
	}

	public Wort find(String s) {
		return find(root, s);
	}
	
	private Wort find(Wort current, String s) {
		if (current == null) {
			return null;
		}
		int vgl = s.compareTo(current.getWort());
		if (vgl == 0) {		// Gefunden
			return current;
		}
		else if (vgl < 0) {	// Links
			return find(current.getLeft(), s);
		}
		else {				// Rechts
			return find(current.getRight(), s);
		}
	}
	
}
