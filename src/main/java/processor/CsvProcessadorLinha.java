package processor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

public class CsvProcessadorLinha implements ProcessadorLinha{

	private List<ProcessadorLinha> processadoresLinha = new ArrayList<>();
	private EntityManager em = null;

	public CsvProcessadorLinha(EntityManager em) {
		this.em = em;
		processadoresLinha.add(new CidadeProcessadorLinha(em));
		processadoresLinha.add(new BairroProcessadorLinha(em));
		processadoresLinha.add(new RamoProcessadorLinha(em));
		processadoresLinha.add(new EmpresaProcessadorLinha(em));
		processadoresLinha.add(new ProdutoProcessadorLinha(em));
	}

	public void processa(String linha) {
		try {
			em.getTransaction().begin();
			for (ProcessadorLinha processador : processadoresLinha) {
				processador.processa(linha);
			}
			em.getTransaction().commit();
		} catch(Exception e) {
			em.getTransaction().rollback();
		}
	}	
}
