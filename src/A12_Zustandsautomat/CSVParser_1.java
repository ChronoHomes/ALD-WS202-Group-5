package A12_Zustandsautomat;

import java.util.Arrays;
import java.util.Scanner;

public class CSVParser_1 {

	private static final char SEPARATOR = ',';
	/**
	 * Implementierung des Automaten mit einem switch()-Statement
	 * für jeden Status des Automaten.
	 * @param str Zu parsende Eingabe
	 * @return Entweder Fehler-Objekt oder Zahl-Objekt
	 */
	public static CSVResult parse(String str) {
		
		int state = 0;

		System.out.println("Input String: " + str);


		//	System.out.println("str.length: " + str.length());
		//	String[] split = str.split(SEPARATOR);
		//	System.out.println("Arrays.toString(split): " + Arrays.toString(split));

		CSVResult result = new CSVResult();



		for (int i = 0; i < str.length(); i++) {

			char c = str.charAt(i);
			int ascii = (int) c;
			System.out.println("isTextData: " + isTextData(c));
			System.out.println((int) c);



			if (ascii == 10 || ascii == 13){
				System.out.println("do nothing"); // CRLF
			} else if (ascii == 44){
				result.addValue();
			} else if (ascii == 34){ // "

			} else {
				result.appendChar(c);
			}

			switch(state) {

			} // switch end
		}

		/*
		//System.out.println((int) SEPARATOR); // -> int 44 for seperator

		//-> cast char to int and build on that the statemachine

		// if length of string reached and last two elements are not 10 and 13
		// -> we reached the end of the string (=last element)

		 */


		result.addValue();
		return result;
	}
	
	private static boolean isTextData(char c) {
		// aus RFC TEXTDATA =  %x20-21 / %x23-2B / %x2D-7E
		// in Abweichung zum RFC werden alle Zeichen >=0x80 als legal betrachtet
		return !(c < 0x20 || c == 0x22 || c == 0x2c || c == 0x7f);
	}
}
