package processor;

import javax.persistence.EntityManager;

import dao.CidadeDao;
import dao.UfDao;
import model.Cidade;
import model.Uf;
import validador.CidadeValidador;

public class CidadeProcessadorLinha implements ProcessadorLinha{
	
	private UfDao ufDao;
	private CidadeDao cidadeDao;
	private CidadeValidador cidadeValidador = new CidadeValidador();
	
	public CidadeProcessadorLinha(EntityManager em) {
		this.ufDao = new UfDao(em);
		this.cidadeDao = new CidadeDao(em);
	}
	
	public void processa(String linha) {
		EmpresaCsv csv = new EmpresaCsv(linha);

		Uf uf = ufDao.busca(csv.getSiglaUf());
		if(uf == null) {
			return;
		}

		Cidade cidadeAux = cidadeDao.busca(uf, csv.getCidade());
		if(cidadeAux == null) {
			Cidade cidade = new Cidade();
			cidade.setNome(csv.getCidade());
			cidade.setUf(uf);
			if (cidadeValidador.isNaoValido(cidade)) {
				System.out.println(cidadeValidador.getMensagem());
				return;
			}
			cidadeDao.inserir(cidade);
		}
	}
}
