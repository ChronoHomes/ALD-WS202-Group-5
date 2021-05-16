package A05_Breitensuche;

import java.util.*;

public class Breitensuche extends BaseTree<Integer> {

	@Override
	protected int compare(Integer a, Integer b) {
		return a.compareTo(b);
	}

	/**
	 * Liefert Knoten des Baums ausgehend von Start in Reihenfolge der Breitensuche zurück
	 * @param start Startknoten für Teilbaum
	 * @return Liste der Knoten in Breitenfolge
	 */
	/** Big O notation -> O() */ //TODO - ADD O notation
	public List<Integer> getBreadthFirstOrder(Node<Integer> start) {

		List<Integer> visited = new ArrayList<>();				// visited list for result
		LinkedList<Node<Integer>> queue = new LinkedList<>();	// queue for chaching childs

		queue.add(start); 										// add start node

		while (!queue.isEmpty()){								// repeat until the queue is empty
			Node<Integer> tmpNode = queue.poll();				// cache the node

			if (tmpNode.getLeft()!=null)						// check left child is existing
				queue.add(tmpNode.getLeft());					// add child to visited list

			if (tmpNode.getRight()!=null)						// check right child is existing
				queue.add(tmpNode.getRight());					// add child to visited list

			visited.add(tmpNode.getValue());					// add the cached node to the visted list

		}

		return visited;
	}

	/**
	 * Liefert Knoten des Baums ausgehend von Start in Reihenfolge der Breitensuche zurück,
	 * allerdings nur jene Knoten, die in der angegebenen Ebene liegen (Start hat Ebene=1)
	 * @param start Startknoten für Teilbaum
	 * @param level Nur Knoten dieser Ebene ausgeben
	 * @return Liste aller Knoten
	 */
	/** Big O notation -> O() */ // TODO - ADD O notation --> O(V+E)
	public List<Integer> getBreadthFirstOrderForLevel(Node<Integer> start, int level) {

		List<Integer> levelList = new ArrayList<>();			// create list for return values
		LinkedList<Node<Integer>> queue = new LinkedList<>();	// create queue for nodes which must be checked

		start.setLevel(1);			// set level of start node to 1
		queue.add(start);			// add start node to queue (=first one to be checked)

		while (!queue.isEmpty()){						// while queue is NOT empty run through loop
			Node<Integer> tmpNode = queue.poll();		// get element (FIFO), store in temporary variable and remove from queue

			if (tmpNode.getLeft() != null)				// check if left child exists
				queue.add(tmpNode.getLeft());			// if it does add to queue

			if (tmpNode.getRight() != null)				// check if right child exits
				queue.add(tmpNode.getRight());			// if it does add to queue

			if (tmpNode.getParent() != null)							// check if current element has a parent
				tmpNode.setLevel(tmpNode.getParent().getLevel() + 1);	// if it has get level from parent add 1 to it and set it as it's own level

			if (tmpNode.getLevel() == level)			// check if level of node is target level from method parameter
				levelList.add(tmpNode.getValue());		// if it is add it to the list which is returned from the method
		}

		return levelList;	// return complete list of of level

	}

}
