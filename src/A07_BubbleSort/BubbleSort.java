package A07_BubbleSort;


public class BubbleSort implements PersonenSort {

	/**
	 * Sortier-Funktion
	 */
	public void sort(Person[] personen) {

		// TODO - comments & refactor on naming
		Person tmpPerson;
		boolean changed = true;
		int performanceCounter = 1; // start with 1 for initial array offset (personen.length - 1) and further use as performance counter

		while (changed){
			changed = false;

			for (int i = 0; i < (personen.length - performanceCounter); i++) {
				if (personen[i].compareTo(personen[i+1]) > 0){
					tmpPerson = personen[i];
					personen[i] = personen[i+1];
					personen[i+1] = tmpPerson;
					changed = true;
				}
			}
			performanceCounter++; 	// performance -> to reduce iterations in loop
									// first iteration -> most "right" is in correct place, next iteration n-1 is in place
									// 2nd iteration -> n-2 is in place -> so no need to check position of those elements
									// which each iteration one more element is already in the "final spot" from "right" to "left"
		}


	}
	
}
