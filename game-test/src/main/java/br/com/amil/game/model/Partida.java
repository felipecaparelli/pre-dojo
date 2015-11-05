package br.com.amil.game.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Representa uma partida (rodada) do jogo.
 * 
 * @author Felipe Caparelli
 *
 */
public class Partida {

	private String nome;
	private List<Jogador> jogadores;
	private StatusPartida statusPartida;
	private ScorePartida scorePartida;
	private Date inicio;
	private Date termino;

	public Partida(String partida, Date inicio) {
		this.nome = partida;
		this.inicio = inicio;
		this.statusPartida = StatusPartida.INICIADA;
		this.scorePartida = new ScorePartida();
		this.jogadores = new ArrayList<Jogador>();
	}

	/**
	 * Obtem o jogador a partir do nome.
	 * @param nome
	 * @return
	 */
	public Jogador getJogador(String nome) {
		
		for (Jogador jogador : this.jogadores) {
			if (jogador.getNome().equals(nome))
				return jogador;
		}
		
		Jogador jogador = new Jogador(nome);
		//adiciona o novo jogador a lista da partida
		this.jogadores.add(jogador);
		
		return jogador;
	}

	/**
	 * Registra um assassinato na partida.
	 * 
	 * @param assassino
	 * @param assassinado
	 * @param armaUsada
	 * @param dataHoraMorte
	 */
	public void addAssassinato(String assassino, String assassinado, String armaUsada, Date dataHoraMorte) {		
		this.scorePartida.addAssassinato(getJogador(assassino), getJogador(assassinado), new Arma(armaUsada), dataHoraMorte);
	}
	
	/**
	 * Registra o termino da partida.
	 * 
	 * @param termino
	 */
	public void finaliza(Date termino) {
		this.termino = termino;
		this.statusPartida = StatusPartida.FINALIZADA;
		//resumir score (premiacoes)
		this.scorePartida.finaliza();
	}

	/* getters and setters */

	public String getNome() {
		return nome;
	}

	public List<Jogador> getJogadores() {
		return jogadores;
	}

	public StatusPartida getStatusPartida() {
		return statusPartida;
	}

	public ScorePartida getScorePartida() {
		return scorePartida;
	}

	public Date getInicio() {
		return inicio;
	}

	public Date getTermino() {
		return termino;
	}

	enum StatusPartida {
		INICIADA, FINALIZADA;
	}
}
