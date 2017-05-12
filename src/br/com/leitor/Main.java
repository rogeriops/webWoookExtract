package br.com.leitor;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
	private static final Logger LOGGER = Logger.getLogger(Leitor.class.getName());

	public static void main(String[] args) {
	
		LOGGER.info("Iniciando a leitura do arquivo de log....");
		
		Scanner ler = new Scanner(System.in);
		System.out.println("Informe o caminho do arquivo de log :");
		String path  = ler.nextLine();
		
		Leitor leitor = new Leitor();
		leitor.imprimeDados(leitor.extrairDados(path));

		LOGGER.info("Finalizado a leitura do arquivo de log.");
		
	}
	
	
}


