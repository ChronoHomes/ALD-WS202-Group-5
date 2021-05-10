package A04_TraverseTree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class WörterbuchCountWordsTestV2 {

	private WörterbuchV2 wb;
	
	@Before
	public void setUp() throws Exception {
		wb = new WörterbuchV2();
		wb.add("Homer");
		wb.add("Flanders");
		wb.add("Maggie");
		wb.add("Marge");
		wb.add("Lisa");
		wb.add("Burns");
		wb.add("Crusty");
		wb.add("Bart");		
	}

	@Test
	public void noWord() {
		assertEquals(0, wb.countWordsInSubTree(null));
	}
	
	@Test
	public void singleWord() {
		Wort w = wb.find("Bart");
		assertEquals(1, wb.countWordsInSubTree(w));
	}
	
	@Test
	public void threeWords() {
		Wort w = wb.find("Maggie");
		assertEquals(3, wb.countWordsInSubTree(w));
	}

	@Test
	public void wholeTree() {
		Wort w = wb.getRoot();
		assertEquals(8, wb.countWordsInSubTree(w));
	}
}
