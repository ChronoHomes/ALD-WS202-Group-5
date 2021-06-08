package A12_Zustandsautomat;

public class CSVParser_1 {


	enum States { //TODO check if this is the right way to define it....
		TEXT,
		COMMA,
		CR,
		LF,
		QUOTE,
		TAB,
		UNDEFINED
	}

	// TODO - good practice to have same name as enum???
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
		//int state = 0;

		States state = States.UNDEFINED;

		System.out.println(COMMA + " " + LF + " " + CR + " " + QUOTE + " " + TAB);
		System.out.println("Input String: " + str + " length: " + str.length());

		CSVResult result = new CSVResult();

		boolean quote = false;


		int quoteCount = 0;
		int badCharCount = 0;
		int lastAscii = -1;

		for (int i = 0; i < str.length(); i++) {

			char c = str.charAt(i);
			System.out.println("char: " + c + " int: " + (int) c + " isTextData: " + isTextData(c));


			// convert char to ascii and assign value to states for readability
			int ascii = c;
			if (isTextData(c))
				state = States.TEXT;
			else if (ascii == COMMA)
				state = States.COMMA;
			else if (ascii == QUOTE)
				state = States.QUOTE;
			else if (ascii == LF)
				state = States.LF;
			else if (ascii == CR)
				state = States.CR;
			else if (ascii == TAB)
				state = States.TAB;


			if (ascii != QUOTE) // solved if previous char is stored?
				quote = false;



			switch(state) {

				case TEXT: // +++++++++++++++++++++++++++++++ TEXT +++++++++++++++++++++++++++++++
					result.appendChar(c);
					break;

				case COMMA: // +++++++++++++++++++++++++++++++ COMMA +++++++++++++++++++++++++++++++
					if(!(badCharCount % 2 == 0))
						result = CSVResult.ERROR;

					result.addValue();
					badCharCount = 0;
					break;

				case QUOTE: // +++++++++++++++++++++++++++++++ QUOTE +++++++++++++++++++++++++++++++
					quoteCount++;
					//			if (lastAscii == COMMA){
					//			}
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

				case CR: // +++++++++++++++++++++++++++++++ CR +++++++++++++++++++++++++++++++
					badCharCount++;

					if (!(str.length()-2 == i))
						result.appendChar(c);

					break;

				case LF: // +++++++++++++++++++++++++++++++ LF +++++++++++++++++++++++++++++++
					badCharCount++;

					if (!(str.length()-1 == i))
						result.appendChar(c);

					break;

				case TAB:	// +++++++++++++++++++++++++++++++ TAB +++++++++++++++++++++++++++++++
					badCharCount++;
					result.appendChar(c);
					break;

				default:
					System.out.println("Input could not be parsed!");
					result = CSVResult.ERROR; //???
			}

			lastAscii = ascii;

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
