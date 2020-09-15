package processor;

import javax.persistence.EntityManager;

import dao.UfDao;
import model.Cidade;
import model.Uf;

public class UfProcessadorLinha implements ProcessadorLinha{

	private EntityManager em;
	private UfDao ufDao;

	public UfProcessadorLinha(EntityManager em) {
		this.ufDao = new UfDao(em);
	}

	public void processa(String linha) {
		DadosCsv csv = new DadosCsv(linha);

		try {
			em.getTransaction().begin();
			Uf ufAux = ufDao.busca(csv.getSiglaUf());
			if(ufAux == null) {
				Uf uf = new Uf();
				//Cidade cidade = new Cidade();
				uf.setSigla(csv.getSiglaUf());
				//cidade.setNome(csv.getCidade());
				//cidade.setUf(uf);
				//uf.setCidades(cidade);
				//uf.setEmpresas(csv.getRazaoSocial());
				ufDao.inserir(uf);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		}

	}

}
