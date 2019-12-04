package br.edu.cesmac.si.receita.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.edu.cesmac.si.receita.model.Usuario;
import br.edu.cesmac.si.receita.repository.UsuarioJpa;
import br.edu.cesmac.si.util.MensagemUtil;
import br.edu.cesmac.si.util.PagesUtil;


@ManagedBean
public class UsuarioMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	private Usuario usuarioSelecionado;
	private List<Usuario> usuarios;

	
	public void salvar() throws IOException {
		if (new UsuarioJpa().cadastrar(usuario)) {

			MensagemUtil.sucesso("Cadastrado com sucesso", "Sucesso!");
			redirecionarParaPage("pages/login");
		} else {
			MensagemUtil.erro("Erro ao Cadastrar usuário!");
		}
	}
	
	public void remover(Usuario usuarioDeletar) {
		if (new UsuarioJpa().remover(usuarioDeletar)) {
			MensagemUtil.sucesso("Deletado com sucesso", "Sucesso!");
		} else {
			MensagemUtil.erro("Erro ao Deletar usuário!");
		}
	}

	public void alterar() {
		if (new UsuarioJpa().alterar(usuario)) {
			MensagemUtil.sucesso("Alterado com sucesso", "Sucesso!");
		} else {
			MensagemUtil.erro("Erro ao Alterar usuário!");
		}
	}
	
	public List<Usuario> getListar() {
		usuarios = new UsuarioJpa().listar();
		return usuarios;
	}

	public void abrirDialogAlterarUsuario(Usuario usuarioSelecionado){
		usuario = usuarioSelecionado;
		PagesUtil.abrirDialog("dlgAlterarUsuario");
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public static void redirecionarParaPage(String page) throws IOException {

		try{
			FacesContext context = FacesContext.getCurrentInstance();
			String url = context.getExternalContext().getRequestContextPath();
			context.getExternalContext().redirect(url + "/" + page + ".faces");
		}catch (IOException e) {
			MensagemUtil.erro("Erro ao sair", e.getLocalizedMessage());
		}
	}

}