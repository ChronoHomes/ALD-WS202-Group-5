package A08_MergeSort;

import java.util.*;


public class MergeSort implements PersonenSort {
	
	/**
	 * Sortier-Funktion
	 */
	public void sort(Person[] personen) {
		// Start des rekursiven Aufrufs
		sort(personen, 0, personen.length-1);

	}

	/**
	 * Rekursive Funktion zum Sortieren eines Teils des Arrays
	 * @param personen Zu sortierendes Array
	 * @param start    Startpunkt im Array
	 * @param end      Endpunkt im Array
	 */
	public void sort(Person[] personen, int start, int end)
	{
		/*
		// test
		List<Person> list = Arrays.asList(personen);
		Comparator<Person> nameComparator = Comparator.comparing(Person::getNachname).thenComparing(Person::getVorname);
		list.sort(nameComparator);
		return;
		*/

		int middle = ((end - start) / 2) + start; // calculate middle

		if (start >= end)
			return;

		sort(personen, start, middle);
		sort(personen, middle+1, end);

		Person[] part1 = Arrays.copyOfRange(personen, start, middle+1);
		Person[] part2 = Arrays.copyOfRange(personen, middle+1, end+1);

		merge(part1, part2, personen, start);

	}

	/**
	 * Merge zweier Arrays in ein Ergebnis-Array
	 * @param pers1 Erstes Array
	 * @param pers2 Zweites Array
	 * @param result Ergebnisarray
	 * @param start  Position für Speicherung in Ergebnisarray
	 */
	public void merge(Person[] pers1, Person[] pers2, Person[] result, int start) {

		int pointerP1 = 0;
		int pointerP2 = 0;
		int pointerResult = start;

		while (!(pers1.length == pointerP1 || pers2.length == pointerP2)){	// loop while non of the pointer has reached the final element of it's array
			if (pers1[pointerP1].compareTo(pers2[pointerP2]) > 0 ){			// check if current (pointerP1) element from pers1 array is greater than the current element from pers2 array against > 0
				result[pointerResult] = pers2[pointerP2];					// put element from pers2 array in the result array
				pointerP2++;												// move pointer of the 2nd array to the next element

			} else {
				result[pointerResult] = pers1[pointerP1];					// put element from pers1 array in the result array
				pointerP1++;												// move pointer of the 1st array to the next element
			}
			pointerResult++;												// update result pointer for next iteration
		}

		if (pers1.length == pointerP1)		// check if array pers1 or pers2 has still remaining elements, which must be moved to result array
			System.arraycopy(pers2, pointerP2, result, pointerResult, (pers2.length - pointerP2)); 	// copy rest of array to result array // optional add rest of array via loop
		else
			System.arraycopy(pers1, pointerP1, result, pointerResult, (pers1.length - pointerP1));	// copy rest of array to result array
	}

}
