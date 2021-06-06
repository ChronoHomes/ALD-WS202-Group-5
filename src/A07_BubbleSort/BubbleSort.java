package A07_BubbleSort;


public class BubbleSort implements PersonenSort {

	/**
	 * Sortier-Funktion
	 */
	/** Big O notation -> O(n²) */
	public void sort(Person[] personen) {

		boolean changed = true;		// termination variable for while loop
		int perfCount = 1; 			// counter to reduce loop iterations // start with 1 for initial offset due .length

		while (changed){
			changed = false;		// set termination condition always to false, to terminate in case we do not enter IF

			for (int i = 0; i < (personen.length - perfCount); i++) {	//
				if (personen[i].compareTo(personen[i+1]) > 0){			// compare two elements which are next to each other and check if they must be exchanged // > 0 and not >= 0 to reduce operations
					Person tmpPerson = personen[i];						// store element in helper variable
					personen[i] = personen[i+1];						// overwrite element with next element next to it (smaller one)
					personen[i+1] = tmpPerson;							// write on 2nd element the content of the helper
					changed = true;										// set changed flag to to true to ensure another iteration (=while loop)
				}
			}
			perfCount++; 		// increase count of performance counter, with each iteration of the for loop the largest unsorted
								// element is already in the right position
								// therefore the for loop does not need to check it again
		}


	}
	
}
