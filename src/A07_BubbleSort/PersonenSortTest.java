package A07_BubbleSort;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PersonenSortTest {

	protected PersonenSort ps;
	
	protected Person[] personen;
	
	@Before
	public void setUpPersons() {
		personen = new Person[9];
		personen[0] = new Person("Homer", "Simpson");
		personen[1] = new Person("Marge", "Simpson");
		personen[2] = new Person("Lisa", "Simpson");
		personen[3] = new Person("Bart", "Simpson");
		personen[4] = new Person("Maggie", "Simpson");
		personen[5] = new Person("Albert", "Einstein");
		personen[6] = new Person("Max", "Planck");
		personen[7] = new Person("Edwin", "Hubble");
		personen[8] = new Person("Richard", "Feynman");
	}
	
	@Test
	public void emptyArray() {
		Person[] p = new Person[0];
		ps.sort(p);
		assertEquals(0, p.length);
	}

	@Test
	public void oneElementArray() {
		Person[] p = new Person[1];
		p[0] = personen[8];
		ps.sort(p);
		assertEquals(1, p.length);
		assertEquals("Feynman", p[0].getNachname());
	}
	
	@Test
	public void twoElementArray() {
		Person[] p = new Person[2];
		// reverse order
		p[0] = personen[7];
		p[1] = personen[8];
		ps.sort(p);
		assertEquals("Feynman", p[0].getNachname());
		assertEquals("Hubble", p[1].getNachname());
		// sorted order
		ps.sort(p);
		assertEquals("Feynman", p[0].getNachname());
		assertEquals("Hubble", p[1].getNachname());
	}
	
	@Test
	public void fiveElementArray() {
		Person[] p = new Person[5];
		// reverse order
		p[0] = personen[2];
		p[1] = personen[5];
		p[2] = personen[6];
		p[3] = personen[7];
		p[4] = personen[8];
		// unsorted order
		ps.sort(p);
		assertEquals("Einstein", p[0].getNachname());
		assertEquals("Feynman", p[1].getNachname());
		assertEquals("Hubble", p[2].getNachname());
		assertEquals("Planck", p[3].getNachname());
		assertEquals("Simpson", p[4].getNachname());
		// sorted order
		ps.sort(p);
		assertEquals("Einstein", p[0].getNachname());
		assertEquals("Feynman", p[1].getNachname());
		assertEquals("Hubble", p[2].getNachname());
		assertEquals("Planck", p[3].getNachname());
		assertEquals("Simpson", p[4].getNachname());
	}
	
	
	@Test
	public void sameSurnameArray() {
		Person[] p = new Person[3];
		// reverse order
		p[0] = personen[1];
		p[1] = personen[0];
		p[2] = personen[2];
		// unsorted order
		ps.sort(p);
		assertEquals("Homer", p[0].getVorname());
		assertEquals("Lisa", p[1].getVorname());
		assertEquals("Marge", p[2].getVorname());
		// sorted order
		ps.sort(p);
		assertEquals("Homer", p[0].getVorname());
		assertEquals("Lisa", p[1].getVorname());
		assertEquals("Marge", p[2].getVorname());
	}
	
	@Test
	public void mixedArray() {
		Person[] p = new Person[9];
		for (int i = 0; i < 9; i++) {
			p[i] = personen[i];
		}
		// unsorted order
		ps.sort(p);
		assertEquals("Einstein", p[0].getNachname());
		assertEquals("Feynman", p[1].getNachname());
		assertEquals("Hubble", p[2].getNachname());
		assertEquals("Planck", p[3].getNachname());
		assertEquals("Bart", p[4].getVorname());
		assertEquals("Homer", p[5].getVorname());
		assertEquals("Lisa", p[6].getVorname());
		assertEquals("Maggie", p[7].getVorname());
		assertEquals("Marge", p[8].getVorname());
		// sorted order
		ps.sort(p);
		assertEquals("Einstein", p[0].getNachname());
		assertEquals("Feynman", p[1].getNachname());
		assertEquals("Hubble", p[2].getNachname());
		assertEquals("Planck", p[3].getNachname());
		assertEquals("Bart", p[4].getVorname());
		assertEquals("Homer", p[5].getVorname());
		assertEquals("Lisa", p[6].getVorname());
		assertEquals("Maggie", p[7].getVorname());
		assertEquals("Marge", p[8].getVorname());
	}
	
}
