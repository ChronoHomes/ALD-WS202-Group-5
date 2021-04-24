package A12_Zustandsautomat;

import java.util.ArrayList;

public class CSVResult {
	
	private ArrayList<String> values;
	private final boolean isError;
	private StringBuffer buffer;
	
	public static final CSVResult ERROR = new CSVResult(true);
	
	public CSVResult() {
		values = new ArrayList<String>();
		isError = false;
		buffer = new StringBuffer();
	}
	
	public CSVResult(boolean isError) {
		this.isError = true;
	}
	
	public void appendChar(char c) {
		buffer.append(c);
	}
	
	public void addValue() {
		values.add(buffer.toString());
		buffer = new StringBuffer();
	}
	
	public ArrayList<String> getValues() {
		return values;
	}
	
	public boolean hasError() {
		return isError;
	}
}
