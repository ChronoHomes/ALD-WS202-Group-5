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
	/** Big O notation -> O() */ //TODO - ADD O notation
	public List<String> getNodesInOrder(Node<Film> node) {

		List<String> list = new ArrayList<>();

		if (node == null)
			return list;

		list.addAll(getNodesInOrder(node.getLeft()));
		list.add(node.getValue().getTitel()); // in the middle due the symmetry for in-order
		list.addAll(getNodesInOrder(node.getRight()));

		return list;
	}
	
	/**
	 * Retourniert Titelliste jener Filme, deren Länge zwischen min und max liegt, in Hauptreihenfolge (engl. pre-order, d.h. Knoten-links-rechts)
	 * @param min Minimale Länge des Spielfilms
	 * @param max Maximale Länge des Spielfilms
	 * @return Liste der Filmtitel in Hauptreihenfolge
	 */
	/** Big O notation -> O() */ //TODO - ADD O notation
	public List<String> getMinMaxPreOrder(double min, double max) {
		//printTree();
		return getMinMaxPreOrder(min, max, root);
	}

	//Method Overloading for Recursive call
	public List<String> getMinMaxPreOrder(double min, double max, Node<Film> node) {

		List<String> list = new ArrayList<>();

		if (node == null)
			return list;

		if (node.getValue().getLänge() > min && node.getValue().getLänge() < max)
			list.add(node.getValue().getTitel());

		list.addAll(getMinMaxPreOrder(min, max, node.getLeft()));
		list.addAll(getMinMaxPreOrder(min, max, node.getRight()));

		return list;
	}



}
