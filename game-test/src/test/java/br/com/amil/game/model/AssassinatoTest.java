package br.com.amil.game.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class AssassinatoTest {
	
	private Assassinato assassinato;

	@Before
	public void setUp() throws Exception {
		assassinato = new Assassinato(new Jogador("felipe"), new Jogador("nick"), new Arma("MK16"), new Date());
	}

	@Test
	public void testGetAssassino() {
		assertNotNull(assassinato.getAssassino());
		assertEquals(assassinato.getAssassino(), new Jogador("felipe"));
		assertEquals(assassinato.getAssassino().getNome(), "felipe");
	}

	@Test
	public void testGetAssassinado() {
		assertNotNull(assassinato.getAssassinado());
		assertEquals(assassinato.getAssassinado(), new Jogador("nick"));
		assertEquals(assassinato.getAssassinado().getNome(), "nick");
	}

	@Test
	public void testGetArmaUsada() {
		assertNotNull(assassinato.getArmaUsada());
		assertEquals(assassinato.getArmaUsada(), new Arma("MK16"));
		assertEquals(assassinato.getArmaUsada().getNome(), "MK16");
	}

	@Test
	public void testGetDataHoraMorte() {
		assertNotNull(assassinato.getDataHoraMorte());
	}

}
