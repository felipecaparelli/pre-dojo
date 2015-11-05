package br.com.amil.game.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import br.com.amil.game.controller.PartidaController;

public class JogoView {

	/**
	 * Entrada do teste do jogo.
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
					gameStart();
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        });
    }
	
	/**
	 * Constrio a view de exibicao do jogo
	 * @throws Exception
	 */
    private static void gameStart() throws Exception {
    	
        //Criando a tela de exibicao...
        JFrame frame = new JFrame("Jogo Teste");        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.BLACK);
        
        //executa a leitura do arquivo
        String dados[][] = new PartidaController().execute();
        
        JPanel layout = new JPanel(new BorderLayout());
        layout.setBackground(Color.RED);
        layout.setForeground(Color.GRAY);
        layout.setBorder(BorderFactory.createEmptyBorder());
        layout.add(criarTitulo("RANKING GERAL"), BorderLayout.NORTH);
        
        
        JPanel painel = new JPanel(new GridLayout());
        painel.setBackground(Color.BLACK);
        
        layout.add(painel, BorderLayout.CENTER);
        
        for (int i = 0; i < dados.length; i++) {
        	
			painel.add(criarSubTitulo(dados[i][0]));
	        painel.add(criarTexto(dados[i][1]));	        
		}
        
        frame.add(layout, BorderLayout.CENTER);
        
        //seta o tamanho da janela
        frame.setSize(300, 150);
        
        //abre a janela
        frame.pack();
        frame.setVisible(true);
    }

	private static JLabel criarTitulo(String texto) {
		
		JLabel label = new JLabel(texto, SwingConstants.CENTER);
		
		label.setFont(new Font("Monospaced", Font.BOLD, 28));
		label.setBackground(Color.BLACK);
		label.setForeground(Color.BLACK);
		
		return label;
	}

	private static JLabel criarSubTitulo(String texto) {
		
		JLabel label = new JLabel(texto, SwingConstants.CENTER);
		
		label.setFont(new Font("Monospaced", Font.BOLD, 20));
		label.setBackground(Color.BLACK);
		label.setForeground(Color.GREEN);
		
		return label;
	}

	private static JTextPane criarTexto(String texto) {
		
		JTextPane text = new JTextPane();
        text.setText(texto);
        text.setEditable(false);
        text.setFocusable(false);
        text.setBackground(Color.BLACK);
        text.setFont(new Font("Monospaced", Font.PLAIN, 14));
        text.setForeground(Color.GREEN);
		
		return text;
	}

}
