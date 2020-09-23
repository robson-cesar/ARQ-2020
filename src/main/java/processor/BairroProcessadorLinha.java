package processor;

import javax.persistence.EntityManager;

import dao.BairroDao;
import dao.CidadeDao;
import dao.UfDao;
import model.Bairro;
import model.Cidade;
import model.Uf;
import validador.BairroValidador;

public class BairroProcessadorLinha implements ProcessadorLinha{
	
	private UfDao ufDao;
	private CidadeDao cidadeDao;
	private BairroDao bairroDao;
	private BairroValidador bairroValidador = new BairroValidador();
	
	public BairroProcessadorLinha(EntityManager em) {
		this.ufDao = new UfDao(em);
		this.cidadeDao = new CidadeDao(em);
		this.bairroDao = new BairroDao(em);
	}
	
	public void processa(String linha) {

		EmpresaCsv csv = new EmpresaCsv(linha);

		Uf uf = ufDao.busca(csv.getSiglaUf());
		if(uf == null) {
			return;
		}

		Cidade cidade = cidadeDao.busca(uf, csv.getCidade());
		if(cidade == null) {
			return;
		}

		Bairro bairroAux = bairroDao.busca(cidade, csv.getBairro());
		if(bairroAux == null) {
			Bairro bairro = new Bairro();
			bairro.setCidade(cidade);
			bairro.setNome(csv.getBairro());
			if (bairroValidador.isNaoValido(bairro)) {
				System.out.println(bairroValidador.getMensagem());
				return;
			}
			bairroDao.inserir(bairro);
		}
	}
}
