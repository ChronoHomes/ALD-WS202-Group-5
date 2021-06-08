package A12_Zustandsautomat;

public class CSVParser_1 {

	private static final int COMMA = ',';			// comma ',' - 44
	private static final int LF = '\n';				// line feed '\n' - 10
	private static final int CR = '\r'; 			// carriage return '\r' - 13
	private static final int QUOTATION_MARK = '\"';	// quotation marks '\"' - 34
	/**
	 * Implementierung des Automaten mit einem switch()-Statement
	 * für jeden Status des Automaten.
	 * @param str Zu parsende Eingabe
	 * @return Entweder Fehler-Objekt oder Zahl-Objekt
	 */
	public static CSVResult parse(String str) {
		
		int state = 0;

		System.out.println(COMMA + " " + LF + " " + CR + " " + QUOTATION_MARK);

		System.out.println("Input String: " + str + " length: " + str.length());


		//	System.out.println("str.length: " + str.length());
		//	String[] split = str.split(SEPARATOR);
		//	System.out.println("Arrays.toString(split): " + Arrays.toString(split));

		CSVResult result = new CSVResult();


		boolean quote = false;

		for (int i = 0; i < str.length(); i++) {

			char c = str.charAt(i);
			System.out.println("char: " + c + " int: " + (int) c + " isTextData: " + isTextData(c));


			if ((int) c == COMMA) {
				result.addValue();
				quote = false;
			} else if (((int) c == LF || (int) c == CR) && (str.length()-1 == i || str.length()-2 == i)){
				quote = false;

			} else if ((int) c == QUOTATION_MARK){

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

		//	} else if (!isTextData(c)){
		//		result = CSVResult.ERROR;
		//		break;

			} else if ((int) c == '\t'){ // tryout
				System.out.println("here?");
				result = CSVResult.ERROR;
				break;
			} else {
				result.appendChar(c);
				quote = false;
			}

			switch(state) {

			} // switch end

		}

		if (!result.hasError())
			result.addValue();

		//result = CSVResult.ERROR; // set error -> last two are passed then

		return result;
	}
	
	private static boolean isTextData(char c) {
		// aus RFC TEXTDATA =  %x20-21 / %x23-2B / %x2D-7E
		// in Abweichung zum RFC werden alle Zeichen >=0x80 als legal betrachtet
		return !(c < 0x20 || c == 0x22 || c == 0x2c || c == 0x7f);
	}
}
