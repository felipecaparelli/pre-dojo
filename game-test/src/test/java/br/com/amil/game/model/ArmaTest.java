package br.com.amil.game.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArmaTest {
	
	private Arma arma;

	@Before
	public void setUp() throws Exception {
		arma = new Arma("MK16");
	}

	@Test
	public void testAddUso() {
		assertEquals(arma.getNumeroUsos(), 0);
		arma.addUso();
		assertEquals(arma.getNumeroUsos(), 1);
	}

	@Test
	public void testGetNome() {
		assertNotNull(arma.getNome());
		assertEquals(arma.getNome(), "MK16");
	}

	@Test
	public void testGetNumeroUsos() {
		assertEquals(arma.getNumeroUsos(), 0);
	}

}
