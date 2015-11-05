package br.com.amil.game.controller;

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
	
	
	public String[][] execute() throws Exception {
		
		String[][] resultados = null;
		
		//2 - executar leitura do log
		List<Partida> partidas = processar();
		
		
		if(partidas != null && !partidas.isEmpty()) {
			
			resultados = new String[partidas.size()][2];
			
			for (int i = 0; i < partidas.size(); i++) {
				
				Partida partida = partidas.get(i);
				
				// ranking de cada partida [nome, ranking]
				String [] resultadoPartida = new String[2];
				resultadoPartida[0] = "PARTIDA " + partida.getNome();
				
				System.out.println("** Ranking da partida: "+ partida.getNome() + " ** ");
				
				ScorePartida score = partida.getScorePartida();
				// quantidade assassinatos
				System.out.println("Total de assassinatos geral: " + score.getQuantidadeAssassinatos());
				
				StringBuffer sb = new StringBuffer();
				
				int posicao = 1;
				for(Jogador assassino : score.getRankingAssassinos()){
					// jogador assassino
					sb.append("Assassino: "  + posicao + " - " + assassino.getNome() + "\n")
					.append(" total de assassinatos: " + assassino.getScore().getTotalAssassinatos() + "\n")
					.append(" - Arma favorita: " + assassino.getArmaFavorita() + "\n\n");
					
					System.out.print("Assassino: "  + posicao + " - " + assassino.getNome());
					// quantidade de mortes de cada jogador
					System.out.println(" total de assassinatos: " + assassino.getScore().getTotalAssassinatos());
					//arma preferida (a que mais matou) do vencedor
					System.out.print(" - Arma favorita: " + assassino.getArmaFavorita());
					posicao++;
				}
				
				resultadoPartida[1] = sb.toString();
				/**
				 * Identificar a maior sequ�ncia de assassinatos efetuadas por um jogador (streak) sem morrer, dentro da partida;
				 * Jogadores que vencerem uma partida sem morrerem devem ganhar um "award";
				 * Jogadores que matarem 5 vezes em 1 minuto devem ganhar um "award"
				 */
				//adiciona o ranking da partida
				resultados[i] = resultadoPartida;
			}
		}
		
		
		return resultados;
	}
	
	/**
	 * Realiza o parse do log do jogo.
	 * 
	 * @throws Exception
	 */
	private static List<Partida> processar() throws Exception {

		Partida partida = null;
		List<Partida> rodadas = null;
		

		
		rodadas = new ArrayList<Partida>();
		
		try (Scanner scanner = new Scanner(LogJogoUtils.getFileStream())) {

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

		}

		return rodadas;
	}

}
