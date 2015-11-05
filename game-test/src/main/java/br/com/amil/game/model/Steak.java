package br.com.amil.game.model;

/**
 * Identifica a maior sequência de assassinatos efetuadas por um jogador sem
 * morrer, dentro da partida.
 * 
 * @author Felipe Caparelli
 *
 */
public class Steak {

	private Jogador maiorPontuador;
	private int maiorSequenciaAssassinatos;

	public Jogador getMaiorPontuador() {
		return maiorPontuador;
	}

	public void setMaiorPontuador(Jogador maiorPontuador) {
		this.maiorPontuador = maiorPontuador;
	}

	public int getMaiorSequenciaAssassinatos() {
		return maiorSequenciaAssassinatos;
	}

	public void setMaiorSequenciaAssassinatos(int maiorSequenciaAssassinatos) {
		this.maiorSequenciaAssassinatos = maiorSequenciaAssassinatos;
	}

}
