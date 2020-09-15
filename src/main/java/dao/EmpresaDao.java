package dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Empresa;

public class EmpresaDao implements Serializable{
	private static final long serialVersionUID = 1L;
	private EntityManager em;

	public EmpresaDao(EntityManager em) {
		this.em = em;
	}

	public void inserir(Empresa empresa) {
		em.persist(empresa);
	}
	
	public Empresa busca(String razaoSocial) {
		String jpql = "SELECT e FROM Empresa e WHERE e.razaoSocial = :razaoSocial";
		TypedQuery<Empresa> query = em.createQuery(jpql, Empresa.class);
		query.setParameter("razaoSocial", razaoSocial);

		try { 
			return  query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
