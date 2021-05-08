package A02_Heap;

import java.util.ArrayList;

public class TaskHeapArrayList_withDebugMessages {

	/**
	 * Internes Task-Array für den Heap
	 * Ansonsten keine anderen Variablen verwenden!
	 */
	private ArrayList<Task> tasks;

	/**
	 * Konstruktor
	 */
	public TaskHeapArrayList_withDebugMessages() {
		this.tasks = new ArrayList<>();
		//TODO - better solution?
		tasks.add(new Task(Integer.MIN_VALUE, Integer.MIN_VALUE)); //add dummy element for index 0, otherwise calculation (parent, left, right) does not work
	}

	/**
	 * Neuen Task in den Heap einfügen
	 * @param t Einzufügender Task
	 */
	public void insert(Task t) {
		tasks.add(t);
		swim(tasks.size()-1); // "swim up" new added element // new elements are initially always added in the last position
	}

	/**
	 * Das oberste Element (mit kleinster Priorität entfernen)
	 * @return Task mit kleinster Priorität
	 */
	public Task remove() {

		if (tasks.size() == 0 || tasks.size() == 1) // return null if no element is in stack // == 1 to return null if only dummy element is there
			return null;

		Task t = tasks.get(1);				// save first element in variable before it is removed
		//System.out.println("remove: " + tasks.get(1));
		exchange(1, tasks.size()-1); 		// exchange element which should be removed with last element and then remove it
		tasks.remove(tasks.size()-1);	// remove element from heap (first and last where exchanged then "new" last is removed)
		sink(1);						// check / restore heap property

		return t;							// return element which was removed from heap
	}

	private void swim(int pos) {

		if (prio(parent(pos)) < prio(pos))	// check if "swim up" is required if not return from function // termination for recursive call
			return;

		exchange(parent(pos), pos);			// position change with parent element
		//System.out.println("\t\t\tRecursive Call - SWIM");
		swim(parent(pos));					// recursive function call

	}

	private void sink(int pos) {

		//System.out.println("SINK");

		if (exists(left(pos)) && exists(right(pos))) {
			if (prio(pos) > prio(left(pos)) || prio(pos) > prio(right(pos))) {	// check if parent is greater than any child
				exchange(pos, minChild(pos));									// exchange parent with smallest child
				//System.out.println("\t\t\tRecursive Call - SINK");
				sink(minChild(pos));											// recursive function call
			}
		} else if (exists(left(pos))){					// check if child exists // hasChildren() function another option (=harder to read for me)
			if (prio(pos) > prio(left(pos)))			// check if parent is greater than child
				exchange(pos, left(pos));				// exchange parent with child if it is greater
														// recursive function call not needed
		}
		// else if (exists(right(pos))){		// Should not be needed if both child's do not exist only left exists
		// 	if (prio(pos) > prio(right(pos)))
		// 		exchange(pos, right(pos));
		// }

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
		return (pos < tasks.size() && pos > 0);
	}

	private int prio(int pos) {
		return tasks.get(pos).getPriority();
	}

	private void exchange(int pos1, int pos2) {
		Task temp;
		temp = tasks.get(pos1);
		tasks.set(pos1, tasks.get(pos2));
		tasks.set(pos2, temp);
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

	public void printHeap(){
		System.out.println(tasks.toString());	// print heap for debugging

	//	double calc = Math.log(tasks.size()) / Math.log(2);
	//	int rows = (int) calc + 1;


}

}
