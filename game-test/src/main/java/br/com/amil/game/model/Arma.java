package br.com.amil.game.model;

import java.io.Serializable;

/**
 * Arma do jogador.
 * 
 * @author Felipe Caparelli
 *
 */
public class Arma implements Serializable, Comparable<Arma> {

	private static final long serialVersionUID = 8710345518238112112L;

	private String nome;
	private int numeroUsos;

	public Arma(String nome) {
		this.nome = nome;
	}

	public void addUso() {
		++this.numeroUsos;
	}

	public String getNome() {
		return nome;
	}

	public int getNumeroUsos() {
		return numeroUsos;
	}
	public int compareTo(Arma o) {
		return this.getNome().compareTo(o.getNome());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Arma other = (Arma) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
