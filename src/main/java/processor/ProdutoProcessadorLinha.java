package processor;

import javax.persistence.EntityManager;

import dao.CidadeDao;
import dao.EmpresaDao;
import dao.ProdutoDao;
import dao.UfDao;
import model.Cidade;
import model.Empresa;
import model.Produto;
import model.Uf;

public class ProdutoProcessadorLinha implements ProcessadorLinha{
	
	private EntityManager em;
	private EmpresaDao empresaDao;
	private ProdutoDao produtoDao;
	
	public ProdutoProcessadorLinha(EntityManager em) {
		this.empresaDao = new EmpresaDao(em);
		this.produtoDao = new ProdutoDao(em);
	}
	
	public void processa(String linha) {
		DadosCsv csv = new DadosCsv(linha);
		
		try {
			em.getTransaction().begin();
			Empresa empresa = empresaDao.busca(csv.getRazaoSocial());
			
			if(empresa == null) {
				em.getTransaction().rollback();
				return;
			}
			
			Produto produtoAux = produtoDao.busca(csv.getProduto());
			if(produtoAux == null) {
				Produto produto = new Produto();
				//produto.setEmpresa(empresa);
				produto.setNome(csv.getProduto());
				produtoDao.inserir(produto);
			}
			em.getTransaction().commit();
			
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		}
	}
}
