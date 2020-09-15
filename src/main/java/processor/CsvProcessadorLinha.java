package processor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import dao.EmpresaDao;
import model.Bairro;
import model.Cidade;
import model.Empresa;
import model.Produto;
import model.Uf;

public class CsvProcessadorLinha implements ProcessadorLinha{

	private EntityManager em;
	private List<ProcessadorLinha> processadoresLinha = new ArrayList<>();

	public CsvProcessadorLinha(EntityManager em) {
		processadoresLinha.add(new UfProcessadorLinha(em));
//		processadoresLinha.add(new CidadeProcessadorLinha(em));
//		processadoresLinha.add(new BairroProcessadorLinha(em));
//		processadoresLinha.add(new EmpresaProcessadorLinha(em));
//		processadoresLinha.add(new ProdutoProcessadorLinha(em));
	}
 
	public void processa(String linha) {
		for (ProcessadorLinha processador : processadoresLinha) {
			processador.processa(linha);
		}
	}	
}
