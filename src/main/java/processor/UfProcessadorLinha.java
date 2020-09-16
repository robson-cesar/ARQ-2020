package processor;

import javax.persistence.EntityManager;

import dao.UfDao;
import model.Cidade;
import model.Uf;

public class UfProcessadorLinha implements ProcessadorLinha{

	private EntityManager em;
	private UfDao ufDao;

	public UfProcessadorLinha(EntityManager em) {
		this.em = em;
		this.ufDao = new UfDao(em);
	}

	public void processa(String linha) {
		UfTxt txt = new UfTxt(linha);
		try {
			em.getTransaction().begin();
			Uf ufAux = ufDao.busca(txt.getSigla());
			System.out.println(ufAux);
			if(ufAux == null) {
				Uf uf = new Uf();
				uf.setSigla(txt.getSigla());
				uf.setNome(txt.getNome());
				ufDao.inserir(uf);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		}

	}
	
	public class UfTxt { 
		
		private String campos[];
		
		public UfTxt(String linha) {  
			campos = linha.split(",");
		}
		
		public String getSigla() {
			return campos[1].trim();
		}
		
		public String getNome() {
			return campos[0];
		}
	}

}
