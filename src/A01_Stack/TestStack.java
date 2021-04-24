package A01_Stack;

import static org.junit.Assert.*;

import org.junit.Test;


public class TestStack {

	@Test
	public void getCount() throws StackEmptyException {
		Stack<Integer> s = new Stack<>();
		assertEquals("Leerer Stack", 0, s.getCount());
		s.push(1);
		assertEquals("Ein Element", 1, s.getCount());
		s.push(2);
		assertEquals("Zwei Elemente", 2, s.getCount());
		s.push(3);
		assertEquals("Drei Elemente", 3, s.getCount());
		s.pop();
		assertEquals("Zwei Elemente", 2, s.getCount());
		s.pop();
		assertEquals("Ein Element", 1, s.getCount());
		s.push(4);
		assertEquals("Zwei Elemente", 2, s.getCount());
		s.pop();
		s.pop();
		assertEquals("Leerer Stack", 0, s.getCount());
	}

	@Test(expected=StackEmptyException.class)
	public void stackEmptyException() throws StackEmptyException {
		Stack<Integer> s = new Stack<>();
		s.pop();
	}

	@Test(expected=StackEmptyException.class)
	public void stackEmptyAgainException() throws StackEmptyException {
		Stack<Integer> s = new Stack<>();
		s.push(1);
		s.push(2);
		s.pop();
		s.pop();
		s.pop();
	}

	@Test
	public void pushPop() throws StackEmptyException {
		Stack<Integer> s = new Stack<>();
		s.push(1);
		s.push(2);
		s.push(3);
		assertEquals("Drei", 3, (int)s.pop());
		s.push(4);
		assertEquals("Vier", 4, (int)s.pop());
		s.push(5);
		s.push(6);
		assertEquals("Sechs", 6, (int)s.pop());
		assertEquals("Fünf", 5,(int) s.pop());
		assertEquals("Zwei", 2,(int) s.pop());
		assertEquals("Eins", 1,(int) s.pop());
		s.push(7);
		assertEquals("Sieben", 7,(int) s.pop());
		s.push(8);
		s.push(9);
		assertEquals("Neun", 9,(int) s.pop());
		assertEquals("Acht", 8,(int) s.pop());
	}

}