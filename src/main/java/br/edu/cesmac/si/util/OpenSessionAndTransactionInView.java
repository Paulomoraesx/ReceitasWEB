/*
package br.edu.cesmac.si.util;

import br.edu.cesmac.si.receita.JPA.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

@WebFilter(urlPatterns = "/*")
public class OpenSessionAndTransactionInView implements Filter  {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
// inicia a transação antes de processar o request
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
// processa a requisição
            chain.doFilter(request, response);
// faz commit
            tx.commit();
// ou em caso de erro faz o rollback
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void destroy() {
        JpaUtil.closeEntityManagerFactory();
    }

    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }
}*/
