package br.edu.cesmac.si.noticia.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.edu.cesmac.si.noticia.JPA.JpaUtil;
import br.edu.cesmac.si.noticia.domain.Jornalista;

public class JornalistaJpa {

	public void cadastrar(Jornalista jornalista) {
		EntityManager em = JpaUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(jornalista);
		em.getTransaction().commit();
		em.close();
	}
	
	public void alterar(Jornalista jornalista) {
		EntityManager em = JpaUtil.getEntityManager();
		em.getTransaction().begin();
		jornalista = em.merge(jornalista);
		em.getTransaction().commit();
		em.close();
	}	

	public void remover(Jornalista jornalista) {
		EntityManager em = JpaUtil.getEntityManager();
		em.getTransaction().begin();
		jornalista = em.merge(jornalista);
		em.remove(jornalista);
		em.getTransaction().commit();
		em.close();
	}	
	
	public List<Jornalista> listar() {
		EntityManager em = JpaUtil.getEntityManager();
		List<Jornalista> jornalistas = em.createQuery("select j from jornalista j").getResultList();
		em.close();
		return jornalistas;
	}

}
