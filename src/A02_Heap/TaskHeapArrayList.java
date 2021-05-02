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

		//Test for Offset Calc
		//tasks.add(new Task(0, Integer.MIN_VALUE));
		tasks.add(new Task(0, 0));
	}

	/**
	 * Neuen Task in den Heap einfügen
	 * @param t Einzufügender Task
	 */
	public void insert(Task t) {
		tasks.add(t);
		System.out.println(tasks.size());
		System.out.println("add - prio: " + t.getPriority() + " - id: " + t.getId());

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

		if (tasks.size() == 0)
			return null;
		if (tasks.size() == 1)
			return null;


		Task t = tasks.get(1);

		System.out.println("remove: " + tasks.get(1));
		exchange(1, tasks.size()-1); //exchange element which should be removed with last elemtn and then remove it
		tasks.remove(tasks.size()-1);

		sink(1);

		return t;
	}

	private void swim(int pos) {
		// TODO: Your implementation of swim

	//	if (tasks.size() == 1)
	//		return;

	//	System.out.println("prio(pos) = " + prio(pos));
	//	System.out.println("prio(parent(prio(pos))) = " + prio(parent(pos)));

		if (prio(parent(pos)) < prio(pos))
			return;

	//	while (prio(parent(pos)) > prio(pos)){

			System.out.println("\tpos: " + pos + " prio: " + prio(pos));
			System.out.println("\tparent: " + parent(pos) + " prio: " + prio(parent(pos)));

		exchange(parent(pos), pos);
		swim(parent(pos));
	//	}

/*
		if (prio(parent(pos)) < prio(pos))
			return;

		exchange(parent(pos), pos);
		swim(parent(pos));
 */

	}

	private void sink(int pos) {
		// TODO: Your implementation of sink

		// if parent is larger than one or both children

		System.out.println("SINK");
	//	System.out.println("prio(pos) = " + prio(pos));
	//	System.out.println("prio(left(pos)) = " + prio(left(pos)));
	//	System.out.println("prio(right(pos)) = " + prio(right(pos)));



		if (hasChildren(pos)){

			if (exists(left(pos)) && exists(right(pos))) {
				if (prio(pos) > prio(left(pos)) || prio(pos) > prio(right(pos))) {
					exchange(pos, minChild(pos));

					sink(minChild(pos)); //????
				}
			} else if (exists(left(pos))){
				if (prio(pos) > prio(left(pos))){
					exchange(pos, left(pos));
				}

			} else if (exists(right(pos))){
				if (prio(pos) > prio(right(pos))){
					exchange(pos, right(pos));
				}
			}

		}


/*		if (hasChildren(pos)) {
			while (prio(pos) > prio(left(pos)) || prio(pos) > prio(right(pos))) {
				System.out.println("sink while");
				exchange(pos, minChild(pos));

				if (hasChildren(minChild(pos)))
					sink(minChild(pos));


			}
		}*/

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

		System.out.println("pos: " + pos1 + " exch " + pos2);
		System.out.println("prio: " + prio(pos1) + " exch " + prio(pos2));

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

		System.out.println(tasks.toString());

/*		for(int i = 0; i <= tasks.size(); i++){
			for(int j = 0; j < Math.pow(2,i) && j + Math.pow(2,i) <= tasks.size(); j++){
				System.out.print(tasks.get(j + (int) Math.pow(2, i) - 1).getPriority() + "-" + tasks.get(j + (int) Math.pow(2, i) - 1).getId() + "   ");
			}
			System.out.println();
		}*/

	}

}
