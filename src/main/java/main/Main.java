package main;
import javax.persistence.EntityManager;

import processor.CsvProcessadorLinha;

public class Main {
	public static void main(String[] args) {
		String arquivo = "C:\\Users\\robssilva\\Desktop\\RobsoN\\FSMA\\Arquitetura e Modelagem\\Programas\\ProjetoEmpresas\\src\\main\\dados\\1200-Empresas.csv";
		EntityManager em = JPAUtil.getEntityManager();
		Reader leitor = new Reader();
		leitor.executa(arquivo, new CsvProcessadorLinha(em));
		System.exit(0);
	}
}

