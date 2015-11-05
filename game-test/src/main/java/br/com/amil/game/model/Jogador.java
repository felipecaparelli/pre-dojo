package br.com.amil.game.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Jogador implements Comparable<Jogador> {

	private String nome;
	private StatusJogador status;
	private ScoreJogador score;
	private List<Arma> armasUsadas;

	/**
	 * Construindo o jogador.
	 * 
	 * @param nome
	 *            - nome do jogador
	 */
	public Jogador(String nome) {
		this.nome = nome;
		this.status = StatusJogador.JOGANDO;
		this.score = new ScoreJogador();
		this.armasUsadas = new ArrayList<Arma>();
	}

	public void morreu() {
		this.status = StatusJogador.MORREU;
		this.score.morreu();
	}

	public void matou(Arma armaUsada) {
		this.status = StatusJogador.MATOU;
		this.score.addTotalAssassinatos();
		this.score.addMaiorSequenciaAssassinatoSemMorrer();
		this.registraArma(armaUsada);
	}

	private void registraArma(Arma arma) {
		if (this.armasUsadas.contains(arma)) {
			for (Arma armaUsada : armasUsadas) {
				if (armaUsada.equals(arma)) {
					armaUsada.addUso();
				}
			}
		} else {
			arma.addUso();
			this.armasUsadas.add(arma);
		}
	}

	public String getArmaFavorita() {

		if (this.armasUsadas.isEmpty())
			return null;

		Collections.sort(this.armasUsadas, new Comparator<Arma>() {

			public int compare(Arma o1, Arma o2) {

				if (o1.getNumeroUsos() > o2.getNumeroUsos()) {
					return -1;
				} else if (o1.getNumeroUsos() < o2.getNumeroUsos()) {
					return 1;
				}

				return 0;
			}

		});

		return this.armasUsadas.get(0).getNome();
	}

	/* getters and setters */

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public StatusJogador getStatus() {
		return status;
	}

	public void setStatus(StatusJogador status) {
		this.status = status;
	}

	public ScoreJogador getScore() {
		return score;
	}

	public void setScore(ScoreJogador score) {
		this.score = score;
	}

	public List<Arma> getArmasUsadas() {
		return armasUsadas;
	}

	public int compareTo(Jogador o) {
		if (this.getScore().getTotalAssassinatos() > o.getScore().getTotalAssassinatos()) {
			return -1;
		} else if (this.getScore().getTotalAssassinatos() < o.getScore().getTotalAssassinatos()) {
			return 1;
		}

		return 0;
	}

	public enum StatusJogador {
		JOGANDO, MATOU, MORREU;
	}

	/**
	 * Score do jogador em uma partida.
	 * 
	 * @author Felipe Caparelli
	 *
	 */
	public class ScoreJogador {

		private int totalAssassinatos;
		private int totalAssassinatosSeguidos;
		private boolean morreuNaPartida;
		private int maiorSequenciaAssassinatosSemMorrer;
		private Date ultimoAssassinato;

		public void addTotalAssassinatos() {
			this.totalAssassinatos++;
		}

		public void addMaiorSequenciaAssassinatoSemMorrer() {
			if (!this.morreuNaPartida)
				this.maiorSequenciaAssassinatosSemMorrer++;
		}

		public void morreu() {
			this.morreuNaPartida = true;
			this.maiorSequenciaAssassinatosSemMorrer = 0;
		}

		/* getter and setters */

		public int getTotalAssassinatos() {
			return totalAssassinatos;
		}

		public void setTotalAssassinatos(int totalAssassinatos) {
			this.totalAssassinatos = totalAssassinatos;
		}

		public int getTotalAssassinatosSeguidos() {
			return totalAssassinatosSeguidos;
		}

		public void setTotalAssassinatosSeguidos(int total) {
			this.totalAssassinatosSeguidos = total;
		}

		public boolean isMorreuNaPartida() {
			return morreuNaPartida;
		}

		public void setMorreuNaPartida(boolean morreuNaPartida) {
			this.morreuNaPartida = morreuNaPartida;
		}

		public int getMaiorSequenciaAssassinatosSemMorrer() {
			return maiorSequenciaAssassinatosSemMorrer;
		}

		public void setMaiorSequenciaAssassinatosSemMorrer(int maiorSequenciaAssassinatosSemMorrer) {
			this.maiorSequenciaAssassinatosSemMorrer = maiorSequenciaAssassinatosSemMorrer;
		}

		public Date getUltimoAssassinato() {
			return ultimoAssassinato;
		}

		public void setUltimoAssassinato(Date ultimoAssassinato) {
			this.ultimoAssassinato = ultimoAssassinato;
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jogador other = (Jogador) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

}
