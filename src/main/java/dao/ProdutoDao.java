package dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Produto;
import model.Uf;

public class ProdutoDao implements Serializable{
	private static final long serialVersionUID = 1L;
	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}

	public void inserir(Produto produto) {
		em.persist(produto);
	}

	public Produto busca(String produto) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :pProduto";
		TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
		query.setParameter("pProduto", produto);

		try {
			return query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
}
