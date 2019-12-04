package br.edu.cesmac.si.receita.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.cesmac.si.receita.JPA.JpaUtil;
import br.edu.cesmac.si.receita.model.Receita;

public class ReceitaJpa {

    public boolean cadastrar(Receita receita) {
        try {
            EntityManager em = JpaUtil.getEntityManager();
            em.getTransaction().begin();
            em.persist(receita);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean alterar(Receita receita) {
        try {
            EntityManager em = JpaUtil.getEntityManager();
            em.getTransaction().begin();
            receita = em.merge(receita);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean remover(Receita receita) {
        try {
            EntityManager em = JpaUtil.getEntityManager();
            em.getTransaction().begin();
            receita = em.merge(receita);
            em.remove(receita);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            return false;

        }
    }

    public List<Receita> listar() {
        EntityManager em = JpaUtil.getEntityManager();
        List<Receita> receitas = em.createQuery("select r from receitas r").getResultList();
        em.close();
        return receitas;
    }

    public List<Receita> listarReceitasFiltro(String nomeBusca) {
        EntityManager em = JpaUtil.getEntityManager();
        return em.createQuery("select r from receitas r where r.nome like ?1")
                .setParameter(1, "%" + nomeBusca + "%")
                .getResultList();
    }

}
