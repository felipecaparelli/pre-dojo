package br.com.amil.game.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LogJogoUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetFileStream() {
		assertNotNull(LogJogoUtils.getFileStream());
	}

	@Test
	public void testIsNovaPartida() {
		assertEquals(LogJogoUtils.isNovaPartida("23/04/2013 15:34:22 - New match 11348965 has started"), true);
		assertEquals(LogJogoUtils.isNovaPartida("23/04/2013 15:39:10 - <WORLD> killed Nick by DROWN"), false);
	}

	@Test
	public void testIsFimPartida() {
		assertEquals(LogJogoUtils.isFimPartida("23/04/2013 15:39:22 - Match 11348965 has ended"), true);
		assertEquals(LogJogoUtils.isFimPartida("23/04/2013 15:39:10 - <WORLD> killed Nick by DROWN"), false);
	}

	@Test
	public void testIsMorteJogador() {
		assertEquals(LogJogoUtils.isMorteJogador("23/04/2013 15:36:04 - Roman killed Nick using M16"), true);
		assertEquals(LogJogoUtils.isMorteJogador("23/04/2013 15:39:10 - <WORLD> killed Nick by DROWN"), true);
		assertEquals(LogJogoUtils.isMorteJogador("23/04/2013 15:39:22 - Match 11348965 has ended"), false);
	}

	@Test
	public void testIsMorteJogadorPorWORLD() {
		assertEquals(LogJogoUtils.isMorteJogadorPorWORLD("23/04/2013 15:39:10 - <WORLD> killed Nick by DROWN"), true);
		assertEquals(LogJogoUtils.isMorteJogadorPorWORLD("23/04/2013 15:36:04 - Roman killed Nick using M16"), false);
		assertEquals(LogJogoUtils.isMorteJogadorPorWORLD("23/04/2013 15:39:22 - Match 11348965 has ended"), false);
	}

	@Test
	public void testLerNomeDaPartida() {
		assertNotNull(LogJogoUtils.lerNomeDaPartida("23/04/2013 15:34:22 - New match 11348965 has started"));
		assertNull(LogJogoUtils.lerNomeDaPartida("23/04/2013 15:36:04 - Roman killed Nick using M16"));
		assertEquals(LogJogoUtils.lerNomeDaPartida("23/04/2013 15:34:22 - New match 11348965 has started"), "11348965");
	}

	@Test
	public void testLerDataHora() {
		assertNotNull(LogJogoUtils.lerDataHora("23/04/2013 15:34:22 - New match 11348965 has started"));
		assertEquals(LogJogoUtils.lerDataHora("23/04/2013 15:34:22 - New match 11348965 has started"), "23/04/2013 15:34:22");
	}

	@Test
	public void testLerNomeAssassino() {
		assertNotNull(LogJogoUtils.lerNomeAssassino("23/04/2013 15:36:04 - Roman killed Nick using M16"));
		assertNull(LogJogoUtils.lerNomeAssassino("23/04/2013 15:34:22 - New match 11348965 has started"));
		assertEquals(LogJogoUtils.lerNomeAssassino("23/04/2013 15:36:04 - Roman killed Nick using M16"), "Roman");
	}

	@Test
	public void testLerNomeAssassinado() {
		assertNotNull(LogJogoUtils.lerNomeAssassinado("23/04/2013 15:36:04 - Roman killed Nick using M16"));
		assertNull(LogJogoUtils.lerNomeAssassinado("23/04/2013 15:34:22 - New match 11348965 has started"));
		assertEquals(LogJogoUtils.lerNomeAssassinado("23/04/2013 15:36:04 - Roman killed Nick using M16"), "Nick");
	}

	@Test
	public void testLerNomeArma() {
		assertNotNull(LogJogoUtils.lerNomeArma("23/04/2013 15:36:04 - Roman killed Nick using M16"));
		assertNull(LogJogoUtils.lerNomeArma("23/04/2013 15:34:22 - New match 11348965 has started"));
		assertEquals(LogJogoUtils.lerNomeArma("23/04/2013 15:36:04 - Roman killed Nick using M16"), "M16");
	}

}
