package processor;

import javax.persistence.EntityManager;

import dao.BairroDao;
import dao.CidadeDao;
import dao.UfDao;
import model.Bairro;
import model.Cidade;
import model.Uf;

public class BairroProcessadorLinha implements ProcessadorLinha{
	
	private EntityManager em;
	private UfDao ufDao;
	private CidadeDao cidadeDao;
	private BairroDao bairroDao;
	
	public BairroProcessadorLinha(EntityManager em) {
		this.em = em;
		this.ufDao = new UfDao(em);
		this.cidadeDao = new CidadeDao(em);
		this.bairroDao = new BairroDao(em);
	}
	
	public void processa(String linha) {
		DadosCsv csv = new DadosCsv(linha);
		
		try {
			em.getTransaction().begin();
			Uf uf = ufDao.busca(csv.getSiglaUf());
			
			if(uf == null) {
				em.getTransaction().rollback();
				return;
			}
			
			Cidade cidade = cidadeDao.busca(uf, csv.getCidade());
			
			if(cidade == null) {
				em.getTransaction().rollback();
				return;
			}
			
			Bairro bairroAux = bairroDao.busca(cidade, csv.getBairro());
			if(bairroAux == null) {
				Bairro bairro = new Bairro();
				bairro.setCidade(cidade);
				bairro.setNome(csv.getBairro());
				bairroDao.inserir(bairro);
			}
			
			em.getTransaction().commit();
			
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		}
	}
}
