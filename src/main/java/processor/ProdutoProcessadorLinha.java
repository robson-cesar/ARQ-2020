package processor;

import java.util.List;

import javax.persistence.EntityManager;

import dao.EmpresaDao;
import dao.ProdutoDao;
import model.Empresa;
import model.Produto;
import validador.ProdutoValidador;

public class ProdutoProcessadorLinha implements ProcessadorLinha{
	
	private EmpresaDao empresaDao;
	private ProdutoDao produtoDao;
	private ProdutoValidador produtoValidador = new ProdutoValidador();
	
	public ProdutoProcessadorLinha(EntityManager em) {
		this.empresaDao = new EmpresaDao(em);
		this.produtoDao = new ProdutoDao(em);
	}

	public void processa(String linha) {
		EmpresaCsv csv = new EmpresaCsv(linha);

		List <String> produtoList = csv.getProduto();
		for (int i = 0; i < produtoList.size(); i++) {
			Produto produto = produtoDao.busca(produtoList.get(i));
			if(produto == null) {
				produto = new Produto();
				produto.setNome(produtoList.get(i));
				if (produtoValidador.isNaoValido(produto)) {
					System.out.println(produtoValidador.getMensagem());
					return;
				}
				produtoDao.inserir(produto);
			}
			Empresa empresa = empresaDao.busca(csv.getRazaoSocial());
			if(empresa == null) {
				return;
			}
			produto.setEmpresas(empresa);
		}
	}
}
