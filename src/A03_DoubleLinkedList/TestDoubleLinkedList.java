package A03_DoubleLinkedList;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDoubleLinkedList {

	@Test
	public void next() {
		DoubleLinkedList<Ausrede> dll = new DoubleLinkedList<>();
		dll.add(new Ausrede("eins"));
		dll.add(new Ausrede("zwei"));
		dll.add(new Ausrede("drei"));
		dll.add(new Ausrede("vier"));
		dll.reset();
		assertEquals("eins", dll.next().getAusrede());
		assertEquals("zwei", dll.next().getAusrede());
		assertEquals("drei", dll.next().getAusrede());
		assertEquals("vier", dll.next().getAusrede());
		assertNull(dll.next());
	}


	@Test
	public void previous() {
		DoubleLinkedList<Ausrede> dll = new DoubleLinkedList<>();
		dll.add(new Ausrede("eins"));
		dll.add(new Ausrede("zwei"));
		dll.add(new Ausrede("drei"));
		dll.add(new Ausrede("vier"));
		dll.resetToLast();
		assertEquals("vier", dll.previous().getAusrede());
		assertEquals("drei", dll.previous().getAusrede());
		assertEquals("zwei", dll.previous().getAusrede());
		assertEquals("eins", dll.previous().getAusrede());
		assertNull(dll.previous());
	}

	@Test
	public void getFirst() {
		DoubleLinkedList<Ausrede> dll = new DoubleLinkedList<>();
		assertNull(dll.getFirst());
		dll.add(new Ausrede("eins"));
		assertEquals("eins", dll.getFirst().getData().getAusrede());
		dll.add(new Ausrede("zwei"));
		assertEquals("eins", dll.getFirst().getData().getAusrede());
	}

	@Test
	public void getLast() {
		DoubleLinkedList<Ausrede> dll = new DoubleLinkedList<>();
		assertNull(dll.getLast());
		dll.add(new Ausrede("eins"));
		assertEquals("eins", dll.getLast().getData().getAusrede());
		dll.add(new Ausrede("zwei"));
		assertEquals("zwei", dll.getLast().getData().getAusrede());
	}
	
	@Test(expected=CurrentNotSetException.class)
	public void getCurrentException() throws CurrentNotSetException {
		DoubleLinkedList<Ausrede> dll = new DoubleLinkedList<>();
		dll.getCurrent();
	}	

	@Test(expected=CurrentNotSetException.class)
	public void getCurrentException2() throws CurrentNotSetException {
		DoubleLinkedList<Ausrede> dll = new DoubleLinkedList<>();
		dll.add(new Ausrede("eins"));
		dll.add(new Ausrede("zwei"));
		dll.getCurrent();
	}	

	@Test(expected=CurrentNotSetException.class)
	public void getCurrentException3() throws CurrentNotSetException {
		DoubleLinkedList<Ausrede> dll = new DoubleLinkedList<>();
		dll.add(new Ausrede("eins"));
		dll.add(new Ausrede("zwei"));
		dll.moveNext();
		dll.getCurrent();
	}
	
	@Test(expected=CurrentNotSetException.class)
	public void getCurrentException4() throws CurrentNotSetException {
		DoubleLinkedList<Ausrede> dll = new DoubleLinkedList<>();
		dll.add(new Ausrede("eins"));
		dll.add(new Ausrede("zwei"));
		dll.movePrevious();
		dll.getCurrent();
	}

	@Test(expected=CurrentNotSetException.class)
	public void getCurrentException5() throws CurrentNotSetException {
		DoubleLinkedList<Ausrede> dll = new DoubleLinkedList<>();
		dll.add(new Ausrede("eins"));
		dll.add(new Ausrede("zwei"));
		dll.reset();
		dll.movePrevious();
		dll.getCurrent();
	}

	@Test(expected=CurrentNotSetException.class)
	public void getCurrentException6() throws CurrentNotSetException {
		DoubleLinkedList<Ausrede> dll = new DoubleLinkedList<>();
		dll.add(new Ausrede("eins"));
		dll.add(new Ausrede("zwei"));
		dll.resetToLast();
		dll.moveNext();
		dll.getCurrent();
	}	

	@Test
	public void moveAndGetCurrent() throws CurrentNotSetException {
		DoubleLinkedList<Ausrede> dll = new DoubleLinkedList<>();
		dll.add(new Ausrede("eins"));
		dll.add(new Ausrede("zwei"));
		dll.add(new Ausrede("drei"));
		dll.add(new Ausrede("vier"));
		dll.reset();
		dll.moveNext();
		dll.moveNext();
		assertEquals("drei", dll.getCurrent().getAusrede());
		dll.moveNext();
		dll.movePrevious();
		assertEquals("drei", dll.getCurrent().getAusrede());
		dll.movePrevious();
		dll.movePrevious();
		assertEquals("eins", dll.getCurrent().getAusrede());
		dll.moveNext();
		dll.moveNext();
		dll.moveNext();
		assertEquals("vier", dll.getCurrent().getAusrede());
		dll.movePrevious();
		dll.movePrevious();
		assertEquals("zwei", dll.getCurrent().getAusrede());
	}

	
	@Test(expected=CurrentNotSetException.class)
	public void removeException() throws CurrentNotSetException {
		DoubleLinkedList<Ausrede> dll = new DoubleLinkedList<>();
		dll.add(new Ausrede("eins"));
		dll.add(new Ausrede("zwei"));
		dll.add(new Ausrede("drei"));
		dll.reset();
		dll.moveNext();
		dll.moveNext();
		dll.remove(2);
		assertEquals("drei", dll.getCurrent().getAusrede());
		dll.remove(2);
		dll.getCurrent();
	}
	
	@Test
	public void remove() throws CurrentNotSetException {
		DoubleLinkedList<Ausrede> dll = new DoubleLinkedList<>();
		dll.remove(1);
		dll.remove(10);
		dll.add(new Ausrede("eins"));
		dll.add(new Ausrede("zwei"));
		dll.add(new Ausrede("drei"));
		dll.add(new Ausrede("vier"));
		dll.remove(1);
		assertEquals("zwei", dll.getFirst().getData().getAusrede());
		dll.remove(3);
		assertEquals("drei", dll.getLast().getData().getAusrede());
		dll.add(new Ausrede("fünf"));
		dll.add(new Ausrede("sechs"));
		dll.add(new Ausrede("sieben"));
		assertEquals("zwei", dll.getFirst().getData().getAusrede());
		assertEquals("sieben", dll.getLast().getData().getAusrede());
		dll.reset();
		dll.moveNext();
		dll.moveNext();
		dll.moveNext();
		dll.moveNext();
		assertEquals("sieben", dll.getCurrent().getAusrede());
		dll.movePrevious();
		dll.movePrevious();
		dll.movePrevious();
		dll.movePrevious();
		assertEquals("zwei", dll.getCurrent().getAusrede());
		dll.remove(2);
		dll.moveNext();
		assertEquals("fünf", dll.getCurrent().getAusrede());
		dll.remove(2);
		dll.resetToLast();
		dll.movePrevious();
		dll.movePrevious();
		assertEquals("zwei", dll.getCurrent().getAusrede());
	}
	
	@Test(expected=CurrentNotSetException.class)
	public void removeCurrentException() throws CurrentNotSetException {
		DoubleLinkedList<Ausrede> dll = new DoubleLinkedList<>();
		dll.removeCurrent();
	}

	@Test(expected=CurrentNotSetException.class)
	public void removeCurrentException2() throws CurrentNotSetException {
		DoubleLinkedList<Ausrede> dll = new DoubleLinkedList<>();
		dll.add(new Ausrede("eins"));
		dll.add(new Ausrede("zwei"));
		dll.removeCurrent();
	}

	@Test(expected=CurrentNotSetException.class)
	public void removeCurrentException3() throws CurrentNotSetException {
		DoubleLinkedList<Ausrede> dll = new DoubleLinkedList<>();
		dll.add(new Ausrede("eins"));
		dll.add(new Ausrede("zwei"));
		dll.reset();
		dll.removeCurrent();
		dll.removeCurrent();
		dll.removeCurrent();
	}

	@Test
	public void removeCurrent() throws CurrentNotSetException {
		DoubleLinkedList<Ausrede> dll = new DoubleLinkedList<>();
		dll.add(new Ausrede("eins"));
		dll.add(new Ausrede("zwei"));
		dll.add(new Ausrede("drei"));
		dll.add(new Ausrede("vier"));
		assertEquals("eins", dll.getFirst().getData().getAusrede());
		assertEquals("vier", dll.getLast().getData().getAusrede());
		dll.reset();
		dll.removeCurrent();
		assertEquals("zwei", dll.getFirst().getData().getAusrede());
		assertNull(dll.getFirst().getPrevious());
		dll.resetToLast();
		dll.removeCurrent();
		assertEquals("drei", dll.getLast().getData().getAusrede());
		assertNull(dll.getLast().getNext());
		dll.add(new Ausrede("fünf"));
		dll.add(new Ausrede("sechs"));
		dll.add(new Ausrede("sieben"));
		assertEquals("zwei", dll.getFirst().getData().getAusrede());
		assertEquals("sieben", dll.getLast().getData().getAusrede());
		dll.reset();
		dll.moveNext();
		dll.moveNext();
		dll.removeCurrent();
		dll.movePrevious();
		dll.moveNext();
		dll.moveNext();
		assertEquals("sieben", dll.getCurrent().getAusrede());
		dll.movePrevious();
		dll.movePrevious();
		dll.movePrevious();
		assertEquals("zwei", dll.getCurrent().getAusrede());
		dll.moveNext();
		dll.removeCurrent();
		assertEquals("sechs", dll.getCurrent().getAusrede());
		dll.removeCurrent();
		assertEquals("sieben", dll.getCurrent().getAusrede());
		dll.removeCurrent();
		assertEquals("zwei", dll.getCurrent().getAusrede());
	}
	
	@Test(expected=CurrentNotSetException.class)
	public void insertAfterCurrentAndMoveException() throws CurrentNotSetException {
		DoubleLinkedList<Ausrede> dll = new DoubleLinkedList<>();
		dll.insertAfterCurrentAndMove(new Ausrede("neu"));
	}
	
	@Test
	public void insertAfterCurrentAndMove() throws CurrentNotSetException {
		DoubleLinkedList<Ausrede> dll = new DoubleLinkedList<>();
		dll.add(new Ausrede("eins"));
		dll.reset();
		dll.insertAfterCurrentAndMove(new Ausrede("zwei"));
		assertEquals("zwei", dll.getCurrent().getAusrede());
		dll.insertAfterCurrentAndMove(new Ausrede("drei"));
		assertEquals("drei", dll.getCurrent().getAusrede());
		dll.movePrevious();
		assertEquals("zwei", dll.getCurrent().getAusrede());
		dll.insertAfterCurrentAndMove(new Ausrede("vier"));
		assertEquals("vier", dll.getCurrent().getAusrede());
		dll.movePrevious();
		assertEquals("zwei", dll.getCurrent().getAusrede());
		dll.moveNext();
		dll.moveNext();
		assertEquals("drei", dll.getCurrent().getAusrede());
	}
}