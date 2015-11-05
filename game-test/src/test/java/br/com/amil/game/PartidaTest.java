package br.com.amil.game;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import br.com.amil.game.model.Jogador;
import br.com.amil.game.model.Partida;

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
	public void testTerminoPartida() {
		Partida partida = new Partida("4535435", new Date());
		assertNotNull(partida.getInicio());
		assertNull(partida.getTermino());
		partida.finaliza(new Date());
		assertNotNull(partida.getTermino());
	}
	
	@Test
	public void testJogadoresPartida() {
		Partida partida = new Partida("4535435", new Date());
		assertNotNull(partida.getJogadores());
		assertNotNull(partida.getJogador("samuel"));
		assertNotEquals(new Jogador("samuel"), partida.getJogador("samuel"));
	}

}
