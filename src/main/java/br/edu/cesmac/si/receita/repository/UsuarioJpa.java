package br.edu.cesmac.si.receita.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import br.edu.cesmac.si.receita.JPA.JpaUtil;
import br.edu.cesmac.si.receita.model.Usuario;

public class UsuarioJpa {
	
private EntityManagerFactory factory = Persistence.createEntityManagerFactory("receitasPU");
private EntityManager em = factory.createEntityManager();
	
	
	public Usuario getUsuario(String nomeUsuario, String senha) {
		try {
			Usuario usuario = (Usuario) em.createQuery("SELECT u from usuarios u where u.login = :name and u.senha = :senha")
					.setParameter("name", nomeUsuario)
					.setParameter("senha", senha).getSingleResult();
			
			return usuario;
		} catch (NoResultException e) {
			return null;
		}
	}
	

	public boolean cadastrar(Usuario Usuario) {
		try {
			EntityManager em = JpaUtil.getEntityManager();
			em.getTransaction().begin();
			em.persist(Usuario);
			em.getTransaction().commit();
			em.close();
			return true;
		}catch (Exception e){
			return false;
		}
	}
	
	public boolean alterar(Usuario Usuario) {
		try {
			EntityManager em = JpaUtil.getEntityManager();
			em.getTransaction().begin();
			Usuario = em.merge(Usuario);
			em.getTransaction().commit();
			em.close();
			return true;
		}catch(Exception e){
			return false;

		}
	}	

	public boolean remover(Usuario Usuario) {
		try {
			EntityManager em = JpaUtil.getEntityManager();
			em.getTransaction().begin();
			Usuario = em.merge(Usuario);
			em.remove(Usuario);
			em.getTransaction().commit();
			em.close();
			return true;
		}catch (Exception e){
			return false;
		}
	}	
	
	public List<Usuario> listar() {
		EntityManager em = JpaUtil.getEntityManager();
		List<Usuario> Usuarios = em.createQuery("select u from usuarios u order by u.id").getResultList();
		em.close();
		return Usuarios;
	}

}
