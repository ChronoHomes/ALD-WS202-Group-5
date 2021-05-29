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
		// to be deleted -> test with default sort
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

		int pointerPers1 = 0;
		int pointerPers2 = 0;
		int pointerResult = start;

		while (!(pers1.length == pointerPers1 || pers2.length == pointerPers2)){
			if (pers1[pointerPers1].compareTo(pers2[pointerPers2]) > 0 ){
				result[pointerResult] = pers2[pointerPers2];
				pointerPers2++;

			} else {
				result[pointerResult] = pers1[pointerPers1];
				pointerPers1++;
			}
			pointerResult++;
		}

		if (pers1.length == pointerPers1)
			System.arraycopy(pers2, pointerPers2, result, pointerResult, (pers2.length - pointerPers2));
		else
			System.arraycopy(pers1, pointerPers1, result, pointerResult, (pers1.length - pointerPers1));
	}

}
