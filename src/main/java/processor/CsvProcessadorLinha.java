package processor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

public class CsvProcessadorLinha implements ProcessadorLinha{

	private List<ProcessadorLinha> processadoresLinha = new ArrayList<>();

	public CsvProcessadorLinha(EntityManager em) {
		processadoresLinha.add(new CidadeProcessadorLinha(em));
		processadoresLinha.add(new BairroProcessadorLinha(em));
//		processadoresLinha.add(new EmpresaProcessadorLinha(em));
//		processadoresLinha.add(new ProdutoProcessadorLinha(em));
	}
 
	public void processa(String linha) {
		for (ProcessadorLinha processador : processadoresLinha) {
			processador.processa(linha);
		}
	}	
}
