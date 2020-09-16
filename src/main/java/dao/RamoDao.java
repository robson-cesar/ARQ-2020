package dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Ramo;

public class RamoDao implements Serializable{
	private static final long serialVersionUID = 1L;
	private EntityManager em;

	public RamoDao(EntityManager em) {
		this.em = em;
	}

	public void inserir(Ramo ramo) {
		em.persist(ramo);
	}

	public Ramo busca(String ramo) {
		String jpql = "SELECT r FROM Ramo r WHERE r.nome = :rRamo";
		TypedQuery<Ramo> query = em.createQuery(jpql, Ramo.class);
		query.setParameter("rRamo", ramo);

		try {
			return query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
}
