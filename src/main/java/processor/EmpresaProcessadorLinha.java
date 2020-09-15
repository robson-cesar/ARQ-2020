package processor;

import javax.persistence.EntityManager;

import dao.BairroDao;
import dao.CidadeDao;
import dao.EmpresaDao;
import dao.UfDao;
import model.Bairro;
import model.Cidade;
import model.Empresa;
import model.Uf;

public class EmpresaProcessadorLinha implements ProcessadorLinha{
	
	private EntityManager em;
	private UfDao ufDao;
	private CidadeDao cidadeDao;
	private BairroDao bairroDao;
	private EmpresaDao empresaDao;
	
	public EmpresaProcessadorLinha(EntityManager em) {
		this.ufDao = new UfDao(em);
		this.cidadeDao = new CidadeDao(em);
		this.bairroDao = new BairroDao(em);
		this.empresaDao = new EmpresaDao(em);
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
			
			Bairro bairro = bairroDao.busca(cidade, csv.getBairro());
			if(bairro == null) {
				em.getTransaction().rollback();
				return;
			}
			
			Empresa EmpresaAux = empresaDao.busca(csv.getRazaoSocial());
			if(EmpresaAux == null) {
				Empresa empresa = new Empresa();
				empresa.setUf(uf);
				empresa.setCidade(cidade);
				empresa.setBairro(bairro);
				empresa.setCep(csv.getCep());
				empresa.setLogradouro(csv.getLogradouro());
				empresa.setRamo(csv.getRamo());
				empresa.setRazaoSocial(csv.getRazaoSocial());
				empresa.setEmail(csv.getEmail());
				empresa.setSite(csv.getSite());
				empresa.setFax(csv.getFax());
				empresa.setTelefone(csv.getTelefone());
				empresa.setFuncionario(csv.getFuncionario());
				empresa.setContato(csv.getContato());
				//empresa.setProduto(csv.getProduto());
				bairroDao.inserir(bairro);
			}
			
			em.getTransaction().commit();
			
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		}
	}
}