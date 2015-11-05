package br.com.amil.game.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.amil.game.model.Jogador.StatusJogador;

public class JogadorTest {
	
	private Jogador jogador;

	@Before
	public void setUp() throws Exception {
		this.jogador = new Jogador("felipe");
	}

	@Test
	public void testStatus() {
		assertNotNull(this.jogador.getStatus());
	}

	@Test
	public void testScore() {
		assertNotNull(this.jogador.getScore());
	}

	@Test
	public void testMorreu() {
		this.jogador.morreu();
		assertEquals(this.jogador.getStatus(), StatusJogador.MORREU);
	}

	@Test
	public void testMatou() {
		this.jogador.matou(new Arma("MK16"));
		assertEquals(this.jogador.getStatus(), StatusJogador.MATOU);
		assertEquals(this.jogador.getScore().getTotalAssassinatos(), 1);
	}

	@Test
	public void testGetArmaFavorita() {
		this.jogador.matou(new Arma("MK16"));
		this.jogador.matou(new Arma("MK16"));
		this.jogador.matou(new Arma("MK15"));
		assertEquals(this.jogador.getArmaFavorita(), "MK16");
	}

	@Test
	public void testGetNome() {
		assertNotNull(this.jogador.getNome());
		assertEquals(this.jogador.getNome(), "felipe");
	}

	@Test
	public void testGetStatus() {
		assertEquals(this.jogador.getStatus(), StatusJogador.JOGANDO);
		this.jogador.morreu();
		assertEquals(this.jogador.getStatus(), StatusJogador.MORREU);
		this.jogador.matou(new Arma("MK16"));
		assertEquals(this.jogador.getStatus(), StatusJogador.MATOU);
	}

	@Test
	public void testGetArmasUsadas() {
		assertNotNull(this.jogador.getArmasUsadas());
		this.jogador.matou(new Arma("MK16"));
		assertEquals(this.jogador.getArmasUsadas().size(), 1);
		this.jogador.matou(new Arma("MK15"));
		assertEquals(this.jogador.getArmasUsadas().size(), 2);
	}

}
