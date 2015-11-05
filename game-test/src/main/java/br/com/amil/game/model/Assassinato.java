package br.com.amil.game.model;

import java.util.Date;

/**
 * Representa a ocorrencia de uma morte/assassinato na partida.
 * 
 * @author Felipe Caparelli
 *
 */
public class Assassinato {

	private Jogador assassino;
	private Jogador assassinado;
	private Arma armaUsada;
	private Date dataHoraMorte;

	public Assassinato(Jogador assassino, Jogador assassinado, Arma armaUsada, Date dataHoraMorte) {
		this.assassino = assassino;
		this.assassinado = assassinado;
		this.armaUsada = armaUsada;
		this.dataHoraMorte = dataHoraMorte;
	}

	public Jogador getAssassino() {
		return assassino;
	}

	public Jogador getAssassinado() {
		return assassinado;
	}

	public Arma getArmaUsada() {
		return armaUsada;
	}

	public Date getDataHoraMorte() {
		return dataHoraMorte;
	}

}
