package main;
import javax.persistence.EntityManager;

import processor.CsvProcessadorLinha;
import processor.UfProcessadorLinha;

public class Main {
	public static void main(String[] args) {
		String arquivo = "C:\\Users\\robssilva\\Desktop\\RobsoN\\FSMA\\Arquitetura e Modelagem\\Programas\\ProjetoEmpresas\\src\\main\\dados\\1200-Empresas.csv";
		String arquivoUf = "C:\\Users\\robssilva\\Desktop\\RobsoN\\FSMA\\Arquitetura e Modelagem\\Programas\\ProjetoEmpresas\\src\\main\\dados\\estados.txt";
		EntityManager em = JPAUtil.getEntityManager();
		Reader leitor = new Reader();
		leitor.executa(arquivoUf, new UfProcessadorLinha(em));
		leitor.executa(arquivo, new CsvProcessadorLinha(em),true);
		System.out.println("***FIM DO PROCESSAMENTO***");
		System.exit(0);
	}
}

