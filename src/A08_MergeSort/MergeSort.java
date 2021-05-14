package A08_MergeSort;

import java.util.Arrays;


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
		// TODO: Aufteilung & Rekursion implementieren
		
		int mitte = ((end - start) / 2) + start; // + start as offset compensation

		if (start >= end) //other method? -> end - start == 1
			return;

		sort(personen, start, mitte);
		sort(personen, mitte+1, end);
		
		// Für Merge: Hälften in eigene Arrays kopieren
		// Hinweis: bei copyOfRange ist Obergrenze exklusiv, deshalb "+ 1"
		Person[] part1 = Arrays.copyOfRange(personen, start, mitte+1);
		Person[] part2 = Arrays.copyOfRange(personen, mitte+1, end+1);
		// Beide Hälften zusammenfügen und in data-Array schreiben
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

		// TODO: Merge implementieren



		
	}

}
