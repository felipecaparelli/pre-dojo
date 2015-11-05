package br.com.amil.game.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ScorePartida {

	private Steak steak;
	private List<Assassinato> assassinatos;
	private Set<Jogador> assassinos;
	private Set<Jogador> mortos;
	private List<Jogador> premiadosSemMorrer;
	private List<Jogador> premiados5MortesEm1Minuto;

	public ScorePartida() {
		this.steak = new Steak();
		this.assassinatos = new ArrayList<Assassinato>();
		this.assassinos = new LinkedHashSet<Jogador>();
		this.mortos = new LinkedHashSet<Jogador>();
		this.premiadosSemMorrer = new ArrayList<Jogador>();
		this.premiados5MortesEm1Minuto = new ArrayList<Jogador>();
	}

	public void addAssassinato(Jogador assassino, Jogador assassinado, Arma armaUsada, Date dataHoraMorte) {
		this.assassinatos.add(new Assassinato(assassino, assassinado, armaUsada, dataHoraMorte));
		assassino.matou(armaUsada);		
		this.assassinos.add(assassino);
		assassinado.morreu();
		this.mortos.add(assassinado);
	}

	/**
	 * Finaliza o score da partida (premiacoes).
	 */
	public void finaliza() {
		// TODO implementar
	}
	
	public List<Jogador> getRankingAssassinos(){
		List<Jogador> listaAssassinos = new ArrayList<Jogador>(this.assassinos);
		Collections.sort(listaAssassinos);
		return listaAssassinos;
	}
	

	/* getters and setters */

	public int getQuantidadeAssassinatos() {
		return assassinatos.size();
	}

	public Steak getSteak() {
		return steak;
	}

	public List<Jogador> getPremiadosSemMorrer() {
		return premiadosSemMorrer;
	}

	public List<Assassinato> getAssassinatos() {
		return assassinatos;
	}

	public Set<Jogador> getAssassinos() {
		return assassinos;
	}

	public Set<Jogador> getMortos() {
		return mortos;
	}

	public List<Jogador> getPremiados5MortesEm1Minuto() {
		return premiados5MortesEm1Minuto;
	}

}
