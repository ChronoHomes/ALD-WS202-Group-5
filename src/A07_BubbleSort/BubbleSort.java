package A07_BubbleSort;


public class BubbleSort implements PersonenSort {

	/**
	 * Sortier-Funktion
	 */
	public void sort(Person[] personen) {

		Person tmpPerson;
		boolean changed = true;

		// TODO - refactor nameing
		// TODO - add some sort of counter to reduce checks -> since in first loop most "right" element is already in place -> not need to check again
		// TODO - add temp variable names so it is easier to read?

		int counter = 0;

		while (changed){
			changed = false;

			for (int i = 0; i < (personen.length - 1 - counter); i++) {
				if (personen[i].compareTo(personen[i+1]) > 0){
					tmpPerson = personen[i];
					personen[i] = personen[i+1];
					personen[i+1] = tmpPerson;
					changed = true;
				}
			}
			counter++; 	// performance -> to reduce iterations in loop
						// first iteration -> most "right" is in correct place, next iteration n-1 is in place
						// 2nd iteration -> n-2 is in place
		}


	}
	
}
