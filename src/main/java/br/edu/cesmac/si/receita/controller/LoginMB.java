package br.edu.cesmac.si.receita.controller;


import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


import br.edu.cesmac.si.receita.model.Usuario;
import br.edu.cesmac.si.receita.repository.UsuarioJpa;
import br.edu.cesmac.si.util.PagesUtil;

import static br.edu.cesmac.si.util.SessionUtil.*;

@ManagedBean
@ViewScoped
public class LoginMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private UsuarioJpa usuarioJpa = new UsuarioJpa();
    private Usuario usuario = new Usuario();


    public void envia() throws IOException {
        usuario = usuarioJpa.getUsuario(usuario.getLogin(), usuario.getSenha());
        if (usuario != null) {
            adicionaObjetoUsuarioNaSessao("usuario_logado", usuario);
            new ReceitaMB().redirecionarParaPage("receita/manterReceita");
        } else {
            usuario = new Usuario();
            FacesContext.getCurrentInstance().addMessage(null, new
                    FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario não encontrado!", "Erro no Login!"));
        }
    }


    public Usuario getUsuario() {
        return usuario;
    }


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public static void redirecionarParaPage(String page) throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        String url = context.getExternalContext().getRequestContextPath();
        context.getExternalContext().redirect(url + "/" + page + ".faces");
    }

}