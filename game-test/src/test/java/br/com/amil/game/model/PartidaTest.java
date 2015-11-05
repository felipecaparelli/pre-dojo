package br.com.amil.game.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import br.com.amil.game.model.Partida.StatusPartida;

public class PartidaTest {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testInicioPartida() {
		Partida partida = new Partida("323424", null);
		assertNotNull(partida.getNome());
		assertNull(partida.getInicio());
		partida = new Partida("323424", new Date());
		assertNotNull(partida.getInicio());
	}

	@Test
	public void testGetJogador() {
		Partida partida = new Partida("4535435", new Date());
		assertNotNull(partida.getJogador("samuel"));
	}

	@Test
	public void testAddAssassinato() {
		Partida partida = new Partida("4535435", new Date());
		partida.addAssassinato("felipe", "nick", "HK45", new Date());
		assertEquals(partida.getJogador("felipe").getScore().getTotalAssassinatos(), 1);
	}

	@Test
	public void testFinaliza() {
		Partida partida = new Partida("4535435", new Date());
		assertNotNull(partida.getInicio());
		assertNull(partida.getTermino());
		partida.finaliza(new Date());
		assertNotNull(partida.getTermino());
	}

	@Test
	public void testGetNome() {
		Partida partida = new Partida(null, new Date());
		assertNull(partida.getNome());
		partida = new Partida("4535435", new Date());
		assertNotNull(partida.getNome());
	}

	@Test
	public void testGetJogadores() {
		Partida partida = new Partida("4535435", new Date());
		assertNotNull(partida.getJogadores());
		assertNotEquals(new Jogador("samuel"), partida.getJogador("samuel"));
	}

	@Test
	public void testGetStatusPartida() {
		Partida partida = new Partida("4535435", new Date());
		assertEquals(partida.getStatusPartida(), StatusPartida.INICIADA);
		partida.finaliza(new Date());
		assertEquals(partida.getStatusPartida(), StatusPartida.FINALIZADA);
	}

	@Test
	public void testGetScorePartida() {
		Partida partida = new Partida("4535435", new Date());
		assertNotNull(partida.getScorePartida());
		assertEquals(partida.getScorePartida().getQuantidadeAssassinatos(), 0);
		partida.addAssassinato("felipe", "nick", "HK45", new Date());
		assertEquals(partida.getScorePartida().getQuantidadeAssassinatos(), 1);
	}

}
