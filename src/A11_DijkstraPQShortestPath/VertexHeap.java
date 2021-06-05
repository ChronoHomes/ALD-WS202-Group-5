package A11_DijkstraPQShortestPath;



import java.util.ArrayList;

public class VertexHeap {

	private ArrayList<Vertex> vertexHeap;

	public VertexHeap() {
		this.vertexHeap = new ArrayList<>();								// create new ArrayList
		vertexHeap.add(new Vertex(Integer.MIN_VALUE, Integer.MIN_VALUE));	// add dummy element for index 0, otherwise calculation (parent, left, right) does not work
	}

	public void insert(Vertex v) {
		vertexHeap.add(v);					// add new element to ArrayList
		swim(vertexHeap.size()-1);		// "swim up" new added element
											// new elements are initially always added at the last position and then checked where they actually "belong"
	}

	public Vertex remove() {

		if (vertexHeap.size() == 0 || vertexHeap.size() == 1) // return null if no element is in stack // "== 1" to return null if only dummy element is there
			return null;

		Vertex tmp = vertexHeap.get(1);					// save first element in temp variable before it is removed
		exchange(1, vertexHeap.size()-1); 				// exchange element which should be removed (=top), with last element
		vertexHeap.remove(vertexHeap.size()-1);	// remove last element from heap (first and last where exchanged then "new" last is removed)
		sink(1);									// check / restore heap property with sink for new "top" element (= previous last element)

		return tmp;										// return element top element from heap (which was removed)
	}

	private void swim(int pos) {

		if (prio(parent(pos)) < prio(pos))	// check if "swim up" is required if not return from function // termination for recursive call
			return;

		exchange(parent(pos), pos);			// position change with parent element
		swim(parent(pos));					// recursive function call
	}

	private void sink(int pos) {

		if (exists(left(pos)) && exists(right(pos))) {							// check if both child's exist
			if (prio(pos) > prio(left(pos)) || prio(pos) > prio(right(pos))) {	// check if parent is greater than any child
				exchange(pos, minChild(pos));									// exchange parent with smallest child
				sink(minChild(pos));											// recursive function call
			}
		} else if (exists(left(pos))){					// check if child exists (due heap property only left) // hasChildren() function another option (=harder to read for me)
			if (prio(pos) > prio(left(pos)))			// check if parent is greater than child
				exchange(pos, left(pos));				// exchange parent with child if it is greater
														// recursive function call not needed since "end of tree" -> due single child -> heap property
		}

	}

	private int parent(int pos) {
		return pos / 2;
	}

	private int left(int pos) {
		return pos * 2;
	}

	private int right(int pos) {
		return (pos * 2) + 1;
	}

	private boolean exists(int pos) {
		return (pos < vertexHeap.size() && pos > 0);
	}

	private int prio(int pos) {
		return vertexHeap.get(pos).getCost(); // priority based on cost
	}

	private void exchange(int pos1, int pos2) {
		Vertex temp;
		temp = vertexHeap.get(pos1);
		vertexHeap.set(pos1, vertexHeap.get(pos2));
		vertexHeap.set(pos2, temp);
	}

	private boolean hasChildren(int pos) {
		return exists(left(pos));
	}

	private int minChild(int pos) {
		int min, l, r;
		l = left(pos);
		r = right(pos);
		min = l;
		if (exists(r) && prio(r) < prio(l)) {
			min = r;
		}
		return min;
	}

	public boolean isEmpty() {
		//return vertexList.isEmpty(); // does not work due dummy element
		return vertexHeap.size() <= 1;
	}

	public void setCost(int vertex, int cost) {

		for (int i = 1; i <= vertexHeap.size()-1; i++) {	// loop through all heap elements

			if (vertexHeap.get(i).getVertex() == vertex) {	// check if vertex has been found where the cost should be set
				int oldCost = vertexHeap.get(i).getCost();	// store current cost/priority in variable for comparison
				vertexHeap.get(i).setCost(cost);			// set new cost/priority for vertex

				if (cost < oldCost)		// check if cost increased or decreased and call swim or sink depending ont he result
					swim(i);			// if new cost is smaller than the previous value "swim up" the element in the heap
				else
					sink(i);			// if new cost is greater than the previous value "sink down" the element in the heap

				return;
			}
		}


	}

	public void printHeap(){
		System.out.println("Print Heap");
		for (Vertex vertex : vertexHeap) {
			System.out.println("vertex: " + vertex.getVertex() + " cost: " + vertex.getCost());
		}
	}


}


