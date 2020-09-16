package processor;

import javax.persistence.EntityManager;

import dao.RamoDao;
import model.Ramo;
import validador.RamoValidador;

public class RamoProcessadorLinha implements ProcessadorLinha{
	
	private EntityManager em;
	private RamoDao ramoDao;
	private RamoValidador ramoValidador = new RamoValidador();
	
	public RamoProcessadorLinha(EntityManager em) {
		this.em = em;
		this.ramoDao = new RamoDao(em);
	}
	
	public void processa(String linha) {
		EmpresaCsv csv = new EmpresaCsv(linha);
		
		if (ramoValidador.naoPodeIncluir(csv.getRamo())) { 
			return;
		}
		try {
			em.getTransaction().begin();
//					
			Ramo ramoAux = ramoDao.busca(csv.getRamo());
			if(ramoAux == null) {
				Ramo ramo = new Ramo();
				ramo.setNome(csv.getRamo());
				ramoDao.inserir(ramo);
			}
			em.getTransaction().commit();
			
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		}
	}
}
