package br.edu.cesmac.si.receita.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import br.edu.cesmac.si.receita.model.Receita;
import br.edu.cesmac.si.receita.model.Usuario;
import br.edu.cesmac.si.receita.repository.ReceitaJpa;
import br.edu.cesmac.si.util.MensagemUtil;
import br.edu.cesmac.si.util.PagesUtil;
import br.edu.cesmac.si.util.SessionUtil;

import static br.edu.cesmac.si.util.PagesUtil.abrirDialog;
import static br.edu.cesmac.si.util.PagesUtil.atualizarComponente;


@ManagedBean
@ViewScoped
public class ReceitaMB implements Serializable {
    private static final long serialVersionUID = 1L;

    private Receita receita = new Receita();
    private List<Receita> receitas;
    private Receita receitaSelecionada;
    private String nomeBusca;
    private List<Receita> listarTodasReceitas;

    public ReceitaMB() {

        this.receitas = new ArrayList<>();
        this.listarTodasReceitas = new ArrayList<>();
    }

    public void carregarDadosTelaReceita() {
        this.receitas = new ReceitaJpa().listar();
    }

    public void salvar() {
        Usuario usuarioLogado = (Usuario) SessionUtil.recuperaObjetoDaSessao("usuario_logado");
        receita.setUsuario(usuarioLogado);
        if (new ReceitaJpa().cadastrar(receita)) {
            MensagemUtil.sucesso("Cadastrado com sucesso", "Sucesso!");
        } else {
            MensagemUtil.erro("Erro ao cadastrar usuário!");
        }

        receita = new Receita();
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }


    public void remover(Receita receitaDeletar) {
        if (new ReceitaJpa().remover(receitaDeletar)) {
            MensagemUtil.sucesso("Deletado com sucesso", "Sucesso!");
            limparBusca();
            PagesUtil.atualizarComponente("formTela");

        } else {
            MensagemUtil.erro("Erro ao Deletar usuário!");
        }
    }

    public void abrirDialogAlterarReceita(Receita receitaSelecionada) {
        receita = receitaSelecionada;
        atualizarComponente("formAlterarReceita");
        abrirDialog("dlgAlterarReceita");
    }

    public void alterar() {
        if (new ReceitaJpa().alterar(receita)) {
            MensagemUtil.sucesso("Alterado com sucesso", "Sucesso!");
            limparBusca();
        } else {
            MensagemUtil.erro("Erro ao Alterar usuário!");
        }
    }

    public void listarComFiltro() {
        receitas = new ReceitaJpa().listarReceitasFiltro(nomeBusca);
        if(receitas==null || receitas.isEmpty()){
            MensagemUtil.erro("Nada foi encontrado!");
            nomeBusca = "";
            atualizarComponente("formBusca");
        }

    }

    public void limparBusca() {
        receitas = new ReceitaJpa().listar();
    }

    public List<Receita> listarTodasReceitas() {
        if(receitas==null || receitas.isEmpty()){
            limparBusca();
        }
        return receitas;
    }

    public static void redirecionarParaPage(String page) throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        String url = context.getExternalContext().getRequestContextPath();
        context.getExternalContext().redirect(url + "/" + page + ".faces");
    }

    public List<Receita> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<Receita> receitas) {
        this.receitas = receitas;
    }

    public Receita getReceitaSelecionada() {
        return receitaSelecionada;
    }

    public void setReceitaSelecionada(Receita receitaSelecionada) {
        this.receitaSelecionada = receitaSelecionada;
    }

    public String getNomeBusca() {
        return nomeBusca;
    }

    public void setNomeBusca(String nomeBusca) {
        this.nomeBusca = nomeBusca;
    }

    public List<Receita> getListarTodasReceitas() {
        return listarTodasReceitas;
    }

    public void setListarTodasReceitas(List<Receita> listarTodasReceitas) {
        this.listarTodasReceitas = listarTodasReceitas;
    }
}