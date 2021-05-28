package A06_Tiefensuche;

import java.util.ArrayList;
import java.util.List;

import A05_Breitensuche.BaseTree;
import A05_Breitensuche.Node;

public class Tiefensuche extends BaseTree<Film> {

	@Override
	/**
	 * Sortierkriterium im Baum: Länge des Films
	 */
	protected int compare(Film a, Film b) {

		return Double.compare(a.getLänge(), b.getLänge());
	}

	/**
	 * Retourniert die Titelliste der Film-Knoten des Teilbaums in symmetrischer Folge (engl. in-order, d.h. links-Knoten-rechts)
	 * @param node Wurzelknoten des Teilbaums
	 * @return Liste der Titel in symmetrischer Reihenfolge
	 */
	/** Big O notation -> O(V+E) */
	public List<String> getNodesInOrder(Node<Film> node) {

		List<String> list = new ArrayList<>();		// create new list to store film title

		if (node == null)		// termination of recursion
			return list;

		list.addAll(getNodesInOrder(node.getLeft()));	// call recursive method on left child leave
		list.add(node.getValue().getTitel()); 			// in the middle due the symmetry for in-order
		list.addAll(getNodesInOrder(node.getRight()));	// call recursive method on right child leave

		return list;
	}
	
	/**
	 * Retourniert Titelliste jener Filme, deren Länge zwischen min und max liegt, in Hauptreihenfolge (engl. pre-order, d.h. Knoten-links-rechts)
	 * @param min Minimale Länge des Spielfilms
	 * @param max Maximale Länge des Spielfilms
	 * @return Liste der Filmtitel in Hauptreihenfolge
	 */
	/** Big O notation -> O(V+E) */
	public List<String> getMinMaxPreOrder(double min, double max) {
		return getMinMaxPreOrder(min, max, root);	// call method recursive with min & max from method parameter and the root
	}

	public List<String> getMinMaxPreOrder(double min, double max, Node<Film> node) {	// overload method to call it recursive

		List<String> list = new ArrayList<>();		// create new list to store film title

		if (node == null)		// termination of recursion
			return list;

		if (node.getValue().getLänge() > min && node.getValue().getLänge() < max)    // check if duration is within min & max parameter
			list.add(node.getValue().getTitel());                                    // if it is add to list

			list.addAll(getMinMaxPreOrder(min, max, node.getLeft()));                    // call method recursive on left leave
			list.addAll(getMinMaxPreOrder(min, max, node.getRight()));                   // call method recursive on right leave


		//TODO: Optimierung -> for better performance check if "FILM" is within length

		return list;
	}



}
