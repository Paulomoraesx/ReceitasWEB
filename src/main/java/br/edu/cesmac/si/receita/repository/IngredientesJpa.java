package br.edu.cesmac.si.ingrediente.repository;

import br.edu.cesmac.si.receita.JPA.JpaUtil;
import br.edu.cesmac.si.receita.model.Ingrediente;

import javax.persistence.EntityManager;
import java.util.List;

public class IngredientesJpa {

	public boolean cadastrar(Ingrediente ingrediente) {
		try{
			EntityManager em = JpaUtil.getEntityManager();
			em.getTransaction().begin();
			em.persist(ingrediente);
			em.getTransaction().commit();
			em.close();
			return true;
		}catch (Exception e){
			return false;
		}

	}
	
	public boolean alterar(Ingrediente ingrediente) {
		try {
			EntityManager em = JpaUtil.getEntityManager();
			em.getTransaction().begin();
			ingrediente = em.merge(ingrediente);
			em.getTransaction().commit();
			em.close();
			 return true;
		}catch (Exception e){
			return false;
		}
	}	

	public boolean remover(Ingrediente ingrediente) {
		try {
			EntityManager em = JpaUtil.getEntityManager();
			em.getTransaction().begin();
			ingrediente = em.merge(ingrediente);
			em.remove(ingrediente);
			em.getTransaction().commit();
			em.close();
			return true;
		}catch(Exception e){
			return false;

		}
	}	
	
	public List<Ingrediente> listar() {
		EntityManager em = JpaUtil.getEntityManager();
		List<Ingrediente> ingredientes = em.createQuery("select i from Ingrediente i order by i.id").getResultList();
		em.close();
		return ingredientes;
	}

}
