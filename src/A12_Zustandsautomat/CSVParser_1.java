package A12_Zustandsautomat;

public class CSVParser_1 {


	enum States { //TODO check if this is the right way to define it....
		TEXT,
		COMMA,
		CR,
		LF,
		CRLF, // TODO - needed?
		QUOTE,
		TAB,
		ERROR,
		UNDEFINED
	}

	// TODO - good practice to have same name for constants as for enum???
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

		System.out.println("Input String: " + str + " length: " + str.length());

		CSVResult result = new CSVResult();

		boolean quote = false;
		int quotationMarkCount = 0;
		int specialCharCount = 0;
		int lastAscii = -1;

		parseLoop: for (int i = 0; i < str.length(); i++) {

			char c = str.charAt(i);
			States state = getState(c, result);
			//States state = getState(c, result, str, i);

			System.out.println("char: " + c + " int: " + (int) c + " isTextData: " + isTextData(c));


			if ((int) c != QUOTE) // solved if previous char is stored?
				quote = false;


			switch(state) {

				case ERROR:		// +++++++++++++++++++++++++++++++ ERROR +++++++++++++++++++++++++++++++
					break parseLoop;

				case TEXT:		// +++++++++++++++++++++++++++++++ TEXT +++++++++++++++++++++++++++++++
					result.appendChar(c);
					break;

				case COMMA:		// +++++++++++++++++++++++++++++++ COMMA +++++++++++++++++++++++++++++++
					result.addValue();
					specialCharCount = 0;
					break;

				case QUOTE:		// +++++++++++++++++++++++++++++++ QUOTE +++++++++++++++++++++++++++++++
					quotationMarkCount++;

					//if (lastAscii == COMMA){

					//}

					if (quote) {

						if (!(i == str.length() - 1)) {
							if (str.charAt(i + 1) != COMMA)
								result.appendChar(c);
						}
						quote = false;

					} else {
						quote = true;
					}
					break;
				//Version 1 - working
				case CR:		// +++++++++++++++++++++++++++++++ CR +++++++++++++++++++++++++++++++
					specialCharCount++;

					if (!(str.length()-2 == i))
						result.appendChar(c);

					break;

				case LF:		// +++++++++++++++++++++++++++++++ LF +++++++++++++++++++++++++++++++
					specialCharCount++;

					if (!(str.length()-1 == i))
						result.appendChar(c);

					break;


/*
				//Version 2 - TEST
				case CRLF:
					specialCharCount++;
					char prev = c;

					if (lastAscii != CR || str.length() - 1 != i) {
						result.appendChar(c);
					}

					break;
*/
				case TAB:		// +++++++++++++++++++++++++++++++ TAB +++++++++++++++++++++++++++++++
					result = CSVResult.ERROR;	// TODO: tbc if TAB always results in an ERROR
					break;

				default:
					System.out.println("Input could not be parsed!");
					result = CSVResult.ERROR;
			}

			lastAscii = c;


		}


		System.out.println("specialCharCount: " + specialCharCount);


		// TODO - CREATE another switch/case

		if (!result.hasError())
			result.addValue();

//		if(!(specialCharCount % 2 == 0) || specialCharCount == str.length() && str.length() > 0) {
		if(!(specialCharCount % 2 == 0)) {
			result = CSVResult.ERROR;
		}

		if(!(quotationMarkCount % 2 == 0)) // Quotation Marks must be even counted over the whole input
			result = CSVResult.ERROR;

		return result;
	}

	private static boolean isTextData(char c) {
		// aus RFC TEXTDATA =  %x20-21 / %x23-2B / %x2D-7E
		// in Abweichung zum RFC werden alle Zeichen >=0x80 als legal betrachtet
		return !(c < 0x20 || c == 0x22 || c == 0x2c || c == 0x7f);
	}

	private static States getState(int c, CSVResult result) {

		States state;

		// convert char to ascii and assign value to states for readability

		if (isTextData((char) c))
			state = States.TEXT;
		else if (c == COMMA)
			state = States.COMMA;
		else if (c == QUOTE)
			state = States.QUOTE;
		else if (c == LF)
			state = States.LF;
		else if (c == CR)
			state = States.CR;
		else if (c == TAB)
			state = States.TAB;
		else
			state = States.UNDEFINED;


	//	if (c == CR || c == LF)
	//		state = States.CRLF;

		if (result.hasError())
			state = States.ERROR;


		return state;


	}
	/*
	private static States getState(int c, CSVResult result, String str, int i){

		States state;

		// convert char to ascii and assign value to states for readability

		if (isTextData((char) c))
			state = States.TEXT;
		else if (c == COMMA)
			state = States.COMMA;
		else if (c == QUOTE)
			state = States.QUOTE;
		else if (c == LF)
			state = States.LF;
		else if (c == CR)
			state = States.CR;
		else if (c == TAB)
			state = States.TAB;
		else
			state = States.UNDEFINED;


		// detect CRLF ????


		if (result.hasError())
			state = States.ERROR;


		return state;

	}

	 */

}
