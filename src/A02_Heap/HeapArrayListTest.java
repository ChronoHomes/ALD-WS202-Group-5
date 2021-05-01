package A02_Heap;

import static org.junit.Assert.*;

import org.junit.Test;

public class HeapArrayListTest {
	@Test
	public void emptyHeap() {
		TaskHeapArrayList th = new TaskHeapArrayList();
		assertNull(th.remove());
	}

	@Test
	public void removeTest() {
		TaskHeapArrayList th = new TaskHeapArrayList();
		th.insert(new Task(1, 12));
		th.insert(new Task(2, 2));
		th.insert(new Task(3, 14));
		th.insert(new Task(4, 3));
		th.insert(new Task(5, 5));
		th.insert(new Task(6, 6));
		assertEquals(2, th.remove().getId());
		th.printHeap();
		assertEquals(4, th.remove().getId());
		th.printHeap();
		assertEquals(5, th.remove().getId());
		th.printHeap();
		assertEquals(6, th.remove().getId());
		th.printHeap();
		assertEquals(1, th.remove().getId());
		assertEquals(3, th.remove().getId());
		assertNull(th.remove());
	}


/*	@Test
	public void insertTest() {
		TaskHeapArrayList th = new TaskHeapArrayList();
		th.insert(new Task(1, 12));
		th.insert(new Task(2, 2));
		th.insert(new Task(3, 14));
		th.insert(new Task(4, 3));
		th.insert(new Task(5, 5));
		th.insert(new Task(6, 6));
		th.insert(new Task(7, 1));
		th.insert(new Task(8, 9));
		th.insert(new Task(9, 30));
		th.insert(new Task(10, 8));
		th.insert(new Task(11, 13));
		th.insert(new Task(12, 1));

	}*/

	
	@Test
	public void removeTest2() {
		TaskHeapArrayList th = new TaskHeapArrayList();
		th.insert(new Task(1, 7));
		th.insert(new Task(2, 2));
		th.insert(new Task(3, 14));
		assertEquals(2, th.remove().getId());
		th.insert(new Task(4, 9));
		th.insert(new Task(5, 1));
		assertEquals(5, th.remove().getId());
		th.insert(new Task(6, 8));
		assertEquals(1, th.remove().getId());
		assertEquals(6, th.remove().getId());
		assertEquals(4, th.remove().getId());
		assertEquals(3, th.remove().getId());
		assertNull(th.remove());
	}
}
