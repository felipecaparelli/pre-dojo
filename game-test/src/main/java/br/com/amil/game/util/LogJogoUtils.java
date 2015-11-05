package br.com.amil.game.util;

import java.io.InputStream;
import java.util.regex.Pattern;

/**
 * Classe utilit�ria para leitura e identifica��o dos trechos do log.
 * 
 * @author Felipe Caparelli
 */
public class LogJogoUtils {

	private static final Pattern PATTERN_NOVA_PARTIDA = Pattern.compile("New");
	private static final Pattern PATTERN_FIM_DA_PARTIDA = Pattern.compile("ended");
	private static final Pattern PATTERN_MORTE_JOGADOR = Pattern.compile("killed");
	private static final Pattern PATTERN_MORTE_POR_WORLD = Pattern.compile("<WORLD>");
	
	public static InputStream getFileStream() {
		return LogJogoUtils.class.getResourceAsStream("/log.txt");
	}
	
	public static boolean isNovaPartida(String linha) {
		return PATTERN_NOVA_PARTIDA.matcher(linha).find();
	}
	
	public static boolean isFimPartida(String linha) {
		return PATTERN_FIM_DA_PARTIDA.matcher(linha).find();
	}
	
	public static boolean isMorteJogador(String linha) {
		return PATTERN_MORTE_JOGADOR.matcher(linha).find();
	}
	
	public static boolean isMorteJogadorPorWORLD(String linha) {
		return PATTERN_MORTE_POR_WORLD.matcher(linha).find();
	}

	public static String lerNomeDaPartida(String linha) {
		return (linha.contains("match") ? linha.split("match ")[1].split(" has")[0] : null);
	}
	
	public static String lerDataHora(String linha) {
		return linha.split(" - ")[0];
	}
	
	public static String lerNomeAssassino(String linha) {
		return (linha.contains("killed") ? linha.split(" - ")[1].split(" killed")[0] : null);
	}
	
	public static String lerNomeAssassinado(String linha) {
		return (linha.contains("killed") ? linha.split(" killed ")[1].split(" using")[0] : null);
	}
	
	public static String lerNomeArma(String linha) {
		return (linha.contains("killed") ? linha.substring(linha.indexOf("using ") + 6) : null);
	}

}
