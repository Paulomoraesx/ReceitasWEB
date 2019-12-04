package br.edu.cesmac.si.receita.controller;

import br.edu.cesmac.si.receita.model.Usuario;
import br.edu.cesmac.si.util.MensagemUtil;
import br.edu.cesmac.si.util.SessionUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;


@ManagedBean
public class AcessosMB implements Serializable {
    private Usuario usuarioLogado;


    public static void redirecionarParaPage(String page) throws IOException {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            String url = context.getExternalContext().getRequestContextPath();
            context.getExternalContext().redirect(url + "/" + page + ".faces");
        } catch (IOException e) {
            MensagemUtil.erro("Erro ao sair", e.getLocalizedMessage());
        }
    }

    public void sair() {
        try {
            usuarioLogado = null;
            SessionUtil.excluirVariavelDaSessao("usuario_logado");
            controleSessao();
        } catch (IOException e) {
            MensagemUtil.erro("Erro ao sair", e.getLocalizedMessage());
        }
    }

    public void controleSessao() throws IOException {
        if (SessionUtil.recuperaObjetoDaSessao("usuario_logado") == null) {
            redirecionarParaPage("pages/login");
        } else {
            usuarioLogado = (Usuario) SessionUtil.recuperaObjetoDaSessao("usuario_logado");
        }
    }

    public String obterNomeUsuarioLogado() {
        Usuario usuarioLogaddo = (Usuario) SessionUtil.recuperaObjetoDaSessao("usuario_logado");
        return usuarioLogaddo.getNomeUsuario();
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}