package A02_Heap;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskHeapArrayList {

	/**
	 * Internes Task-Array für den Heap
	 * Ansonsten keine anderen Variablen verwenden!
	 */
	private ArrayList<Task> tasks;

	/**
	 * Konstruktor
	 */
	public TaskHeapArrayList() {
		this.tasks = new ArrayList<>();

	}

	/**
	 * Neuen Task in den Heap einfügen
	 * @param t Einzufügender Task
	 */
	public void insert(Task t) {
		tasks.add(t);
		System.out.println("tasks.size() = " + tasks.size());

		swim(tasks.size()-1);

		System.out.println("printHeap");
		printHeap();
	}

	/**
	 * Das oberste Element (mit kleinster Priorität entfernen)
	 * @return Task mit kleinster Priorität
	 */
	public Task remove() {
		// TODO: Your implementation

		Task t = null;

		if (tasks.size() != 0){
			t = tasks.get(0);
			tasks.remove(0);
		}
		System.out.println("remove: " + tasks.get(0));

		sink(0);

		return t;
	}

	private void swim(int pos) {
		// TODO: Your implementation of swim

	//	if (tasks.size() == 1)
	//		return;

	//	System.out.println("prio(pos) = " + prio(pos));
	//	System.out.println("prio(parent(prio(pos))) = " + prio(parent(pos)));


		while (prio(parent(pos)) > prio(pos)){
			exchange(parent(pos), pos);
			swim(parent(pos));
		}

	}

	private void sink(int pos) {
		// TODO: Your implementation of sink

		// if parent is larger than one or both children

		// hasChildren(pos)

		while (prio(pos) > prio(left(pos)) || prio(pos) > prio(right(pos))){
			System.out.println("sink while");
			exchange(pos, minChild(pos));
			sink(minChild(pos));


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

	//TODO - better solution?
	public void printHeap(){

		//System.out.println(tasks.toString());

		for(int i = 0; i <= tasks.size(); i++){
			for(int j = 0; j < Math.pow(2,i) && j + Math.pow(2,i) <= tasks.size(); j++){
				System.out.print(tasks.get(j + (int) Math.pow(2, i) - 1).getPriority() + "-" + tasks.get(j + (int) Math.pow(2, i) - 1).getId() + "   ");
			}
			System.out.println();
		}

	}

}
