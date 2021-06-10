package A12_Zustandsautomat;

public class CSVParser_1 {

	enum States { //TODO check if this is the right way to define it....
		TEXT,
		COMMA,
		CR,
		LF,
		QUOTE,
		TAB,
		ERROR,
		SUCCESSFUL,
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
		States state;

		boolean lastCharQuote = false;
		boolean deletetLast = false;

		int countQuote = 0;
		int countLf = 0;
		int countCr = 0;
		int lastChar = -1;

		parseLoop: for (int i = 0; i < str.length(); i++) {

			char c = str.charAt(i);
			state = getStateLoop(result, c);

			//System.out.println("char: " + c + " int: " + (int) c + " isTextData: " + isTextData(c));

			switch(state) {

				case ERROR:		// +++++++++++++++++++++++++++++++ ERROR +++++++++++++++++++++++++++++++
					break parseLoop;

				case TEXT:		// +++++++++++++++++++++++++++++++ TEXT +++++++++++++++++++++++++++++++
					result.appendChar(c);
					break;

				case COMMA:		// +++++++++++++++++++++++++++++++ COMMA +++++++++++++++++++++++++++++++
					/*
					// more test cases if a more detailed evaluation is needed for each element before added to array
						if (!(countCr == countLf)){
							result = CSVResult.ERROR;
							break parseLoop;
						}
						countLf = 0;
						countCr = 0;
					*/

					result.addValue();
					break;

				case QUOTE:		// +++++++++++++++++++++++++++++++ QUOTE +++++++++++++++++++++++++++++++
					countQuote++;

					if (lastChar != QUOTE) lastCharQuote = false;

					if (lastCharQuote) {
						if ((i != str.length() - 1) && (str.charAt(i + 1) != COMMA)) // allowed to check next char within state machine?
							result.appendChar(c);
						lastCharQuote = false;
					} else
						lastCharQuote = true;

					break;

				case CR:		// +++++++++++++++++++++++++++++++ CR +++++++++++++++++++++++++++++++
					countCr++;
					if (!(str.length()-2 == i))	// check if carriage return is NOT 2nd to last element in string
						result.appendChar(c);
					break;

				case LF:		// +++++++++++++++++++++++++++++++ LF +++++++++++++++++++++++++++++++
					countLf++;
					if (!(str.length()-1 == i))	// check if line feed is NOT last element in string
						result.appendChar(c);
					break;

				case TAB:		// +++++++++++++++++++++++++++++++ TAB +++++++++++++++++++++++++++++++
					result = CSVResult.ERROR;	// always error if TAB within string (?) -> test cases to be improved if not true :)
					break;

				default:
					System.out.println("Input could not be parsed - set to ERROR");
					result = CSVResult.ERROR;
			}

			lastChar = c;

		}


		// check if string parsing was successful based on result state and counter variables
		state = getStatePost(result, countCr, countLf, countQuote);

		switch(state) {

			case ERROR:
				result = CSVResult.ERROR;
				break;

			case SUCCESSFUL:
				result.addValue(); // add last element to array list
				break;

			default:
				System.out.println("Result could be evaluated - set to ERROR");
				result = CSVResult.ERROR;
		}


		return result;
	}

	private static boolean isTextData(char c) {
		// aus RFC TEXTDATA =  %x20-21 / %x23-2B / %x2D-7E
		// in Abweichung zum RFC werden alle Zeichen >=0x80 als legal betrachtet
		return !(c < 0x20 || c == 0x22 || c == 0x2c || c == 0x7f);
	}

	private static States getStateLoop(CSVResult result, int c) {

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

		if (result.hasError())
			state = States.ERROR;

		return state;
	}

	private static States getStatePost(CSVResult result, int countCr, int countLf, int countQuote) {

		States state;

		if (result.hasError())		// no error present from loop
			state = States.ERROR;
		else
			state = States.SUCCESSFUL;


		if (!(countCr == countLf))	// same count of CR and LF (?) -> test cases to be improved if not true :)
			state = States.ERROR;

		if(!(countQuote % 2 == 0))	// Quotation Marks must be even counted over the whole input
			state = States.ERROR;


		return state;
	}

}