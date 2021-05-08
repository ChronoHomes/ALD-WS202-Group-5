package A04_TraverseTree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Wörterbuch {

	/**
	 * Wurzel des Baums (Startknoten)
	 */
	private Wort root;

	private ArrayList<Wort> list = new ArrayList<>(); // Helper for createList() - is required because of the recursion

	public Wort getRoot() {
		return root;
	}

	/**
	 * Zählt alle Wörter des Teilbaums ab einem bestimmten Wort
	 * @param w Wort
	 * @return Zahl der Wörter (=Anzahl der Elemente)
	 */
	public int countWordsInSubTree(Wort w) {

		ArrayList<Wort> words = createList(w); // returns an Arraylist

		if (words == null)  // In case of a NullPointerException
			return 0;

		return words.size(); // return the number of elements
	}

	/**
	 * Liefert die Menge aller Wörter retour, die ein spezifisches Präfix haben.
	 * @param prefix Wörter müssen diesen Präfix haben
	 * @return Menge aller zutreffenden Wörter
	 */
	public Set<String> getWordsWithPrefix(String prefix) {

		Set<String> prefixset = new HashSet<>(); // Creating return attribute

		ArrayList<Wort> words = createList(root); // returns an Arraylist

		for (Wort word : words) {
			if (word.getWort().startsWith(prefix)){
				prefixset.add(word.getWort());    // words with prefix will be added to the list

				// For Testing:
				// System.out.println("getWordsWithPrefix TEST - " + word.getWort());
			}
		}

		return prefixset;
	}

	// create list for further processing :)
	public ArrayList<Wort> createList(Wort wort){

		// list not created in method
		if (wort == null)
			return null;

		list.add(wort);
		// System.out.println("createList TEST - " + wort);
		createList(wort.getLeft());
		createList(wort.getRight());

		// System.out.println("size: " + list.size());
		return list;

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
