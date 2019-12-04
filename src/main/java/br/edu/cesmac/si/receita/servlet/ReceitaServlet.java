package br.edu.cesmac.si.receita.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.cesmac.si.receita.model.Receita;
import br.edu.cesmac.si.receita.repository.ReceitaJpa;

@WebServlet(urlPatterns = "/receitas/listar")
public class ReceitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		ReceitaJpa receitaJpa = new ReceitaJpa();

		List<Receita> receitas = receitaJpa.listar();
		req.setAttribute("receitas", receitas);
		
		RequestDispatcher rd = req.getRequestDispatcher("/receita/receitas.jsp");
		rd.forward(req, res);
	}
}