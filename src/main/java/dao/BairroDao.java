package dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Bairro;
import model.Cidade;
import model.Uf;

public class BairroDao implements Serializable{
	private static final long serialVersionUID = 1L;

	private EntityManager em;

	public BairroDao(EntityManager em) {
		this.em = em;
	}

	public void inserir(Bairro bairro) {
		em.persist(bairro);
	}
	
	public Bairro busca(Cidade cidade, String bairro) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT b FROM Bairro b");
		jpql.append("WHERE b.cidade = :pCidade");
		jpql.append("AND b.nome = :pBairro");
		TypedQuery<Bairro> query = em.createQuery(jpql.toString(), Bairro.class);
		query.setParameter("pCidade", cidade);
		query.setParameter("pBairro", bairro);

		try {
			return query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
}
