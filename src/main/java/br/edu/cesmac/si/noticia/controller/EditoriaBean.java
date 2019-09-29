package br.edu.cesmac.si.noticia.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.edu.cesmac.si.noticia.domain.Editoria;
import br.edu.cesmac.si.noticia.repository.EditoriaJpa;


@ManagedBean
public class EditoriaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Editoria editoria = new Editoria();
	private List<Editoria> editorias;

	public void salvar() {
		new EditoriaJpa().cadastrar(editoria);
	}

	public List<Editoria> getLista() {
		editorias = new EditoriaJpa().listar();
		return editorias;
	}
	
	public Editoria getEditoria() {
		return editoria;
	}

	public void setEditoria(Editoria editoria) {
		this.editoria = editoria;
	}
	

	public void remover(Editoria editoriaDeletar) {
		new EditoriaJpa().remover(editoriaDeletar);
	}

	public void alterar() {
		new EditoriaJpa().alterar(editoria);
	}
	public static void redirecionarParaPage(String page) throws IOException {
		FacesContext context  = FacesContext.getCurrentInstance();
		String url = context.getExternalContext().getRequestContextPath();
		context.getExternalContext().redirect(url+"/"+page+".faces");
	}

}