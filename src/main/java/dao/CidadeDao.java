package dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Cidade;
import model.Uf;

public class CidadeDao implements Serializable{
	private static final long serialVersionUID = 1L;

	private EntityManager em;

	public CidadeDao(EntityManager em) {
		this.em = em;
	}

	public void inserir(Cidade cidade) {
		em.persist(cidade);
	}

	public Cidade busca(Uf uf, String cidade) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT c FROM Cidade c ");
		jpql.append("WHERE c.uf = :pUf ");
		jpql.append("   AND c.nome = :pCidade ");
		TypedQuery<Cidade> query = em.createQuery(jpql.toString(), Cidade.class);
		query.setParameter("pUf", uf);
		query.setParameter("pCidade", cidade);

		try {
			return query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
}
