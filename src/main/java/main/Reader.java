package main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import processor.ProcessadorLinha;

public class Reader {
	public void executa(String arq, ProcessadorLinha processadorLinha) {		
		String linha ="";
		BufferedReader conteudoCsv = null;
		try {
			conteudoCsv = new BufferedReader (new FileReader(arq));
			while ((linha = conteudoCsv.readLine())!=null) {
				processadorLinha.processa(linha);
			}
		}catch(Exception e){
			System.out.println(e);
			System.exit(0);
		}
	}
}
