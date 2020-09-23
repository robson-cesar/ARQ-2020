package processor;

import javax.persistence.EntityManager;

import dao.RamoDao;
import model.Ramo;
import validador.RamoValidador;

public class RamoProcessadorLinha implements ProcessadorLinha{
	
	private RamoDao ramoDao;
	private RamoValidador ramoValidador = new RamoValidador();
	
	public RamoProcessadorLinha(EntityManager em) {
		this.ramoDao = new RamoDao(em);
	}
	
	public void processa(String linha) {
		EmpresaCsv csv = new EmpresaCsv(linha);

		Ramo ramoAux = ramoDao.busca(csv.getRamo());
		if(ramoAux == null) {
			Ramo ramo = new Ramo();
			ramo.setNome(csv.getRamo());
			if (ramoValidador.isNaoValido(ramo)) {
				System.out.println(ramoValidador.getMensagem());
				return;
			}
			ramoDao.inserir(ramo);
		}
	}
}
