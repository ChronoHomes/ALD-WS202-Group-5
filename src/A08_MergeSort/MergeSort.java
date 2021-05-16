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
	//	System.out.println("start sort");
		// TODO: Aufteilung & Rekursion implementieren

		// to be deleted -> test with default sort
		//List<Person> list = Arrays.asList(personen);
		//Comparator<Person> nameComparator = Comparator.comparing(Person::getNachname).thenComparing(Person::getVorname);
		//list.sort(nameComparator);
		//return;


		
		int mitte = ((end - start) / 2) + start; // calculate middle

		if (start >= end) //other method? -> end - start == 1 // if 0 items or 1 item is left for split
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
	public void merge(Person[] pers1, Person[] pers2, Person[] result, int start) { // TODO - REFACTOR

		System.out.println("start merge");

		// TODO: Merge implementieren

		int pointerPers1 = 0;
		int pointerPers2 = 0;
		int pointerResult = start;
		System.out.println("pointerResult: " + start);

		System.out.println("\t\tSTART of METHOD");
		System.out.println("\tPERSONEN 1 ARRAY");
		for (int i = 0; i < pers1.length; i++) {
			System.out.println("\t\tIndex: " + i + " Name: " + pers1[i].getNachname() + " " + pers1[i].getVorname());
		}
		System.out.println("\tPERSONEN 2 ARRAY");
		for (int i = 0; i < pers2.length; i++) {
			System.out.println("\t\tIndex: " + i + " Name: " + pers2[i].getNachname() + " " + pers2[i].getVorname());
		}
		System.out.println("\tRESULT ARRAY");
		for (int i = 0; i < result.length; i++) {
			System.out.println("\t\tIndex: " + i + " Name: " + result[i].getNachname() + " " + result[i].getVorname());
		}


		// 		while ((pers1.length - 1) < pointerPers1 || (pers2.length - 1) < pointerPers2){
		while (!(pers1.length == pointerPers1 || pers2.length == pointerPers2)){

			System.out.println("pers1.length = " + pers1.length + " pointer: " + pointerPers1);
			System.out.println("pers2.length = " + pers2.length + " pointer: " + pointerPers2);


			if (pers1[pointerPers1].compareTo(pers2[pointerPers2]) > 0 ){ //return <0, wenn a<b || =0, wenn a=b || >0, wenn a>b
				System.out.println("\tIF");
				System.out.println("pers1[pointerPers1] = " + pers1[pointerPers1].getNachname());
				System.out.println("pers2[pointerPers2] = " + pers2[pointerPers2].getNachname());
				result[pointerResult] = pers2[pointerPers2];
				pointerPers2++;

			} else {
				System.out.println("\tELSE");
				System.out.println("pers1[pointerPers1] = " + pers1[pointerPers1].getNachname());
				System.out.println("pers2[pointerPers2] = " + pers2[pointerPers2].getNachname());
				result[pointerResult] = pers1[pointerPers1];
				pointerPers1++;
			}
			pointerResult++;
		}

		// TODO - refactor -> just copy remaining array to result
		if (pers1.length == pointerPers1){
			while (!(pers2.length == pointerPers2)){
				result[pointerResult] = pers2[pointerPers2];
				pointerPers2++;
				pointerResult++;
			}
		} else{
			while (!(pers1.length == pointerPers1)){
				result[pointerResult] = pers1[pointerPers1];
				pointerPers1++;
				pointerResult++;
			}
		}


		System.out.println("\t\tEND of METHOD");
		System.out.println("\tPERSONEN 1 ARRAY");
		for (int i = 0; i < pers1.length; i++) {
			System.out.println("\t\tIndex: " + i + " Name: " + pers1[i].getNachname() + " " + pers1[i].getVorname());
		}
		System.out.println("\tPERSONEN 2 ARRAY");
		for (int i = 0; i < pers2.length; i++) {
			System.out.println("\t\tIndex: " + i + " Name: " + pers2[i].getNachname() + " " + pers2[i].getVorname());
		}
		System.out.println("\tRESULT ARRAY");
		for (int i = 0; i < result.length; i++) {
			System.out.println("\t\tIndex: " + i + " Name: " + result[i].getNachname() + " " + result[i].getVorname());
		}




//		if ((pers1.length - 1) < pointerPers1){ // write rest of array to result
//			System.out.println("if entered - pers1");
//			//System.arraycopy(pers1, pointerPers1, result, result.length-1, pers1.length - 1 - pointerPers1);
//
//		} else {
//			System.out.println("else entered - pers2");
//			//System.arraycopy(pers2, pointerPers2, result, result.length-1, pers2.length - 1 - pointerPers2);
//		}




		//         int[] firstArray = {23,45,12,78,4,90,1};        //source array
		//        int[] secondArray = {77,11,45,88,32,56,3};  //destination array
		//        int fal = firstArray.length;        //determines length of firstArray
		//        int sal = secondArray.length;   //determines length of secondArray
		//        int[] result = new int[fal + sal];  //resultant array of size first array and second array
		//
		//        System.arraycopy(firstArray, 0, result, 0, fal);
		//        System.arraycopy(secondArray, 0, result, fal, sal);
		//
		//
		//        System.out.println(Arrays.toString(result));    //prints the resultant array


		// 2 arrays position für position vergleichen
		// -> kleinere Element kommt in Ziel Array
		// pointer von diesem Array wird weitergesetzt
		// solange bis eines von beiden Arrays leer ist
		// dann restliches Array einfach reinkopieren (=funktion oder element für element?)

		
	}

}
