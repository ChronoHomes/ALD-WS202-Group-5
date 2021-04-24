package A12_Zustandsautomat;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestCSVParser_1 {

	@Test
	public void oneSimpleValue() {
		// mit CRLF
		ArrayList<String> als = CSVParser_1.parse("123\r\n").getValues();
		assertEquals(1, als.size());
		assertEquals("123", als.get(0));
		// ohne CRLF
		als = CSVParser_1.parse("234").getValues();
		assertEquals(1, als.size());
		assertEquals("234", als.get(0));
	}

	@Test
	public void threeSimpleValues() {
		// mit CRLF
		ArrayList<String> als = CSVParser_1.parse("123,abc, Space \r\n").getValues();
		assertEquals(3, als.size());
		assertEquals("123", als.get(0));
		assertEquals("abc", als.get(1));
		assertEquals(" Space ", als.get(2));
		// ohne CRLF
		als = CSVParser_1.parse("234,bcd, Place ").getValues();
		assertEquals(3, als.size());
		assertEquals("234", als.get(0));
		assertEquals("bcd", als.get(1));
		assertEquals(" Place ", als.get(2));
	}
	
	@Test
	public void oneQuotedValue() {
		// mit CRLF
		ArrayList<String> als = CSVParser_1.parse("\"123\"\r\n").getValues();
		assertEquals(1, als.size());
		assertEquals("123", als.get(0));
		// ohne CRLF
		als = CSVParser_1.parse("\"234\"").getValues();
		assertEquals(1, als.size());
		assertEquals("234", als.get(0));
	}

	@Test
	public void threeQuotedValues() {
		// mit CRLF
		ArrayList<String> als = CSVParser_1.parse("\"123\",\"abc\",\" Space \"\r\n").getValues();
		assertEquals(3, als.size());
		assertEquals("123", als.get(0));
		assertEquals("abc", als.get(1));
		assertEquals(" Space ", als.get(2));
		// ohne CRLF
		als = CSVParser_1.parse("\"234\",\"bcd\",\" Place \"").getValues();
		assertEquals(3, als.size());
		assertEquals("234", als.get(0));
		assertEquals("bcd", als.get(1));
		assertEquals(" Place ", als.get(2));
	}

	@Test
	public void quotedValueWithLineBreaks() {
		// mit CRLF
		ArrayList<String> als = CSVParser_1.parse("\"123\r\n\",\" \rab\ncd \"\r\n").getValues();
		assertEquals(2, als.size());
		assertEquals("123\r\n", als.get(0));
		assertEquals(" \rab\ncd ", als.get(1));
		// ohne CRLF
		als = CSVParser_1.parse("\"234\r\n\",\" \rbc\nde \"").getValues();
		assertEquals(2, als.size());
		assertEquals("234\r\n", als.get(0));
		assertEquals(" \rbc\nde ", als.get(1));
	}
	
	@Test
	public void quotedValueWithQuotes() {
		ArrayList<String> als = CSVParser_1.parse("\"ab\"\"cd\",12").getValues();	// Mitte
		assertEquals(2, als.size());
		assertEquals("ab\"cd", als.get(0));
		assertEquals("12", als.get(1));
		als = CSVParser_1.parse("\"\"\"abcd\",12").getValues();		// Anfang
		assertEquals(2, als.size());
		assertEquals("\"abcd", als.get(0));
		assertEquals("12", als.get(1));
		als = CSVParser_1.parse("\"abcd\"\"\",12").getValues();		// Ende
		assertEquals(2, als.size());
		assertEquals("abcd\"", als.get(0));
		assertEquals("12", als.get(1));
		als = CSVParser_1.parse("\"ab\"\"\"\"cd\",12").getValues();	// Doppelt
		assertEquals(2, als.size());
		assertEquals("ab\"\"cd", als.get(0));
		assertEquals("12", als.get(1));
		als = CSVParser_1.parse("\"\",12").getValues();	// Leer
		assertEquals(2, als.size());
		assertEquals("", als.get(0));
		assertEquals("12", als.get(1));
		als = CSVParser_1.parse("\"\"").getValues();	// Leer
		assertEquals(1, als.size());
		assertEquals("", als.get(0));
	}

	@Test
	public void emptyValues() {
		ArrayList<String> als = CSVParser_1.parse("a,,b").getValues();	// Mitte
		assertEquals(3, als.size());
		assertEquals("a", als.get(0));
		assertEquals("", als.get(1));
		assertEquals("b", als.get(2));
		als = CSVParser_1.parse(",a").getValues();	// Anfang
		assertEquals(2, als.size());
		assertEquals("", als.get(0));
		assertEquals("a", als.get(1));
		als = CSVParser_1.parse("a,").getValues();	// Ende
		assertEquals(2, als.size());
		assertEquals("a", als.get(0));
		assertEquals("", als.get(1));
		als = CSVParser_1.parse("a,\r\n").getValues();	// Ende + CRLF
		assertEquals(2, als.size());
		assertEquals("a", als.get(0));
		assertEquals("", als.get(1));
		als = CSVParser_1.parse("").getValues();	// Leer
		assertEquals(1, als.size());
		assertEquals("", als.get(0));
		als = CSVParser_1.parse("\r\n").getValues();	// Leer
		assertEquals(1, als.size());
		assertEquals("", als.get(0));
	}
	
	@Test
	public void badChars() {
		assertTrue(CSVParser_1.parse("a\tb").hasError());
		assertTrue(CSVParser_1.parse("a\rb").hasError());
		assertTrue(CSVParser_1.parse("a\nb").hasError());
		assertTrue(CSVParser_1.parse("\n").hasError());
		assertTrue(CSVParser_1.parse("\r").hasError());
		assertTrue(CSVParser_1.parse("\t").hasError());
	}
	
	@Test
	public void badQuotes() {
		assertTrue(CSVParser_1.parse("a\"b").hasError());
		assertTrue(CSVParser_1.parse("\"").hasError());
		assertTrue(CSVParser_1.parse("\"abc").hasError());
		assertTrue(CSVParser_1.parse("abc\",12").hasError());
		assertTrue(CSVParser_1.parse("\"\"\"ab").hasError());
		assertTrue(CSVParser_1.parse("\"\"\"ab\r\n").hasError());
	}
}
