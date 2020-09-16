package processor;

import javax.persistence.EntityManager;

import dao.CidadeDao;
import dao.UfDao;
import model.Cidade;
import model.Uf;

public class CidadeProcessadorLinha implements ProcessadorLinha{
	
	private EntityManager em;
	private UfDao ufDao;
	private CidadeDao cidadeDao;
	
	public CidadeProcessadorLinha(EntityManager em) {
		this.em = em;
		this.ufDao = new UfDao(em);
		this.cidadeDao = new CidadeDao(em);
	}
	
	public void processa(String linha) {
		EmpresaCsv csv = new EmpresaCsv(linha);
		
		try {
			em.getTransaction().begin();
			Uf uf = ufDao.busca(csv.getSiglaUf());
			
			if(uf == null) {
				em.getTransaction().rollback();
				return;
			}
			
			Cidade cidadeAux = cidadeDao.busca(uf, csv.getCidade());
			if(cidadeAux == null) {
				Cidade cidade = new Cidade();
				cidade.setNome(csv.getCidade());
				cidade.setUf(uf);
				cidadeDao.inserir(cidade);
			}
			em.getTransaction().commit();
			
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		}
	}
}
