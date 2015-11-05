package br.com.amil.game.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.com.amil.game.model.Jogador;
import br.com.amil.game.model.Partida;
import br.com.amil.game.model.ScorePartida;
import br.com.amil.game.util.LogJogoUtils;

public class PartidaController {
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	
	public static void main(String[] args) throws Exception {
		
		//1 - recebe/carrega arquivo
		ClassLoader classLoader = PartidaController.class.getClassLoader();
		File arquivo = new File(classLoader.getResource("pre-dojo-log.txt").getFile());
		
		//2 - executar leitura do log
		List<Partida> partidas = processar(arquivo);
		
		for (Partida partida : partidas) {
			System.out.println("** Ranking da partida: "+ partida.getNome() + " ** ");
			ScorePartida score = partida.getScorePartida();
			// quantidade assassinatos
			System.out.println("Total de assassinatos geral: " + score.getQuantidadeAssassinatos());
			// quantidade de mortes de cada jogador;
			
			int posicao = 1;
			for(Jogador assassino : score.getRankingAssassinos()){
				System.out.println("Assassino: "  + posicao + " - " + assassino.getNome() + " - Arma favorita: " + assassino.getArmaFavorita() + " total de assassinatos: " + assassino.getScore().getTotalAssassinatos());
				// + "The best streak:" + p.getStreak().getBestNumKillsSequence() + " He wins :" + p.getStreak().getAwardForMinute() +" minute kills award and " + awardGame+ " award for the match! "
				posicao++;
			}
			/**
			 * Montar o ranking de cada partida, com a quantidade assassinatos e a quantidade de mortes de cada jogador;
			-----
			* Descobrir a arma preferida (a que mais matou) do vencedor;
			* Identificar a maior sequência de assassinatos efetuadas por um jogador (streak) sem morrer, dentro da partida;
			* Jogadores que vencerem uma partida sem morrerem devem ganhar um "award";
			* Jogadores que matarem 5 vezes em 1 minuto devem ganhar um "award"
			 */
		}
	}
	
	/**
	 * Realiza o parse do log do jogo.
	 * 
	 * @throws Exception
	 */
	public static List<Partida> processar(File arquivo) throws Exception {

		Partida partida = null;
		List<Partida> rodadas = null;
		
		if(arquivo.exists()) {
			
			rodadas = new ArrayList<Partida>();
			
			try (Scanner scanner = new Scanner(arquivo)) {

				while (scanner.hasNextLine()) {
					
					//inicia leitura (linha a linha)
					String linha = scanner.nextLine();
					
					//extrai elementos da linha
					
					// se inicio de partida...
					if (LogJogoUtils.isNovaPartida(linha)) {
						
						//gera partida
						partida = new Partida(LogJogoUtils.lerNomeDaPartida(linha), sdf.parse(LogJogoUtils.lerDataHora(linha)));
						
					// se assassinato...
					} else if (LogJogoUtils.isMorteJogador(linha) && !LogJogoUtils.isMorteJogadorPorWORLD(linha)) {
						
						String assassino = (LogJogoUtils.lerNomeAssassino(linha));
						String assassinado = (LogJogoUtils.lerNomeAssassinado(linha));
						String armaUsada = (LogJogoUtils.lerNomeArma(linha));
						Date dataHoraMorte = sdf.parse(LogJogoUtils.lerDataHora(linha));
						
						//registra o assassinato
						partida.addAssassinato(assassino, assassinado, armaUsada, dataHoraMorte);
						
					//se termino da partida...
					} else if(LogJogoUtils.isFimPartida(linha)) {
						
						//finaliza a partida
						partida.finaliza(sdf.parse(LogJogoUtils.lerDataHora(linha)));
						
						//e adiciona a mesma a lista de rodadas.
						rodadas.add(partida);
					}
				}

				scanner.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return rodadas;
	}

}
