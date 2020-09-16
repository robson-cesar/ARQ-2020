package main;
import java.io.BufferedReader;
import java.io.FileReader;

import processor.ProcessadorLinha;

public class Reader {
	
	public void executa(String arq, ProcessadorLinha processadorLinha) {	
		executa(arq, processadorLinha, false);
	}
	
	public void executa(String arq, ProcessadorLinha processadorLinha, boolean pulaPrimeiraLinha) {	
		
		BufferedReader conteudoCsv = null;
		try {
			conteudoCsv = new BufferedReader (new FileReader(arq));
			String linha = conteudoCsv.readLine();
			if (pulaPrimeiraLinha) { 
				linha = conteudoCsv.readLine();
			}
			while (linha != null) {
				processadorLinha.processa(linha);
				linha = conteudoCsv.readLine();
			}
			conteudoCsv.close();
			
		} catch(Exception e){
			System.out.println(e);
			System.exit(0);
		}
	}
}
