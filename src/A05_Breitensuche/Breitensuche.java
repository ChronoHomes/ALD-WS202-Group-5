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
	public List<Integer> getBreadthFirstOrder(Node<Integer> start) {

		List<Integer> visited = new ArrayList<>();				// visited list for result
		LinkedList<Node<Integer>> queue = new LinkedList<>();	// queue for chaching childs

		queue.add(start); 										// add start node

		while (!queue.isEmpty()){								// repeat until the queue is empty
			Node<Integer> tmpnode = queue.poll();				// cache the node


			if (tmpnode.getLeft()!=null){						// check left child is existing
				queue.add(tmpnode.getLeft());					// add child to visited list
			}
			if (tmpnode.getRight()!=null){						// check right child is existing
				queue.add(tmpnode.getRight());					// add child to visited list
			}

			visited.add(tmpnode.getValue());					// add the cached node to the visted list

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
	public List<Integer> getBreadthFirstOrderForLevel(Node<Integer> start, int level) {

		List<Integer> levelList = new ArrayList<>();
		LinkedList<Node<Integer>> queue = new LinkedList<>();

		start.setLevel(1);
		queue.add(start);

		while (!queue.isEmpty()){
			Node<Integer> tmpNode = queue.poll();


			if (tmpNode.getLeft() != null) {
				queue.add(tmpNode.getLeft());
			}

			if (tmpNode.getRight() != null) {
				queue.add(tmpNode.getRight());
			}

			if (tmpNode.getParent() != null) {
				tmpNode.setLevel(tmpNode.getParent().getLevel() + 1);
			}

			if (tmpNode.getLevel() == level) {
				levelList.add(tmpNode.getValue());
			}

		}

		return levelList;

	}

}
