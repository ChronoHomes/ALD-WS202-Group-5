package A12_Zustandsautomat;

public class CSVParser_1 {

	private static final int COMMA = ',';		// comma ',' - 44
	private static final int CR = '\r'; 		// carriage return '\r' - 13
	private static final int LF = '\n';			// line feed '\n' - 10
	private static final int QUOTE = '\"';		// quotation marks '\"' - 34
	private static final int TAB = '\t';		// tab '\t' - 9
	/**
	 * Implementierung des Automaten mit einem switch()-Statement
	 * für jeden Status des Automaten.
	 * @param str Zu parsende Eingabe
	 * @return Entweder Fehler-Objekt oder Zahl-Objekt
	 */
	public static CSVResult parse(String str) {

		//TODO - handle states with enum !?
		int state = 0;

		System.out.println(COMMA + " " + LF + " " + CR + " " + QUOTE + " " + TAB);

		System.out.println("Input String: " + str + " length: " + str.length());

		CSVResult result = new CSVResult();

		boolean quote = false;


		int quoteCount = 0;
		int badCharCount = 0;

		for (int i = 0; i < str.length(); i++) {

			char c = str.charAt(i);
			System.out.println("char: " + c + " int: " + (int) c + " isTextData: " + isTextData(c));


			if ((int) c == COMMA) {

				if(!(badCharCount % 2 == 0)) {
					result = CSVResult.ERROR; // set error -> last two are passed then
					break;
				}

				result.addValue();
				badCharCount = 0;

				quote = false;

			} else if ((int) c == CR || (int) c == LF || (int) c == TAB){

				System.out.println("le dot");
				badCharCount++;
				if (((int) c == LF || (int) c == CR) && (str.length()-1 == i || str.length()-2 == i)) {
					quote = false;
				} else {
					result.appendChar(c);
				}

		//	} else if (((int) c == LF || (int) c == CR) && (str.length()-1 == i || str.length()-2 == i)){
		//		quote = false;

			} else if ((int) c == QUOTE){
				quoteCount++;
				//System.out.println("i: " + i + " char: " + c);

				if (quote){
					if (!(i == str.length()-1)){
						if (str.charAt(i+1) != COMMA)
							result.appendChar(c);
					}
					quote = false;
				} else {
					quote = true;
				}


			} else {
				result.appendChar(c);
				quote = false;
			}

			switch(state) {

			} // switch end

		}

		System.out.println("badCharCount: " + badCharCount);

		if (!result.hasError())
			result.addValue();

		if(!(badCharCount % 2 == 0)) {
			result = CSVResult.ERROR;
		}
		if(!(quoteCount % 2 == 0)) {
			result = CSVResult.ERROR;
		}

		return result;
	}
	
	private static boolean isTextData(char c) {
		// aus RFC TEXTDATA =  %x20-21 / %x23-2B / %x2D-7E
		// in Abweichung zum RFC werden alle Zeichen >=0x80 als legal betrachtet
		return !(c < 0x20 || c == 0x22 || c == 0x2c || c == 0x7f);
	}
}
