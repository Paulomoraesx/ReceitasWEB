package br.edu.cesmac.si.receita.controller;

import br.edu.cesmac.si.receita.model.Ingrediente;
import br.edu.cesmac.si.ingrediente.repository.IngredientesJpa;
import br.edu.cesmac.si.util.MensagemUtil;
import br.edu.cesmac.si.util.PagesUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;


@ManagedBean
public class IngredientesMB implements Serializable {
    private static final long serialVersionUID = 1L;

    private Ingrediente ingrediente = new Ingrediente();
    private Ingrediente ingredienteSelecionado;
    private List<Ingrediente> ingredientes;

    public void salvar() {
        if (new IngredientesJpa().cadastrar(ingrediente)) {
            MensagemUtil.sucesso("Cadastrado com sucesso", "Sucesso!");
            PagesUtil.fecharDialog("CadastrarIngrediente");
        } else {
            MensagemUtil.erro("Erro ao cadastrar usuário!");
        }

        ingrediente = new Ingrediente();
    }

    public List<Ingrediente> getListar() {
        ingredientes = new IngredientesJpa().listar();
        return ingredientes;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }


    public void remover(Ingrediente ingredienteDeletar) {
        if (new IngredientesJpa().remover(ingredienteDeletar)) {
            MensagemUtil.sucesso("Deletado com sucesso", "Sucesso!");
        } else {
			MensagemUtil.erro("Erro ao Deletar","Não é possivel deletar um ingrediente que está cadastrado a uma Receita");
        }
    }

    public void alterar() {

        if (new IngredientesJpa().alterar(ingrediente)) {
            MensagemUtil.sucesso("Alterado com sucesso", "Sucesso!");
        } else {
            MensagemUtil.erro("Erro ao Alterar usuário!");
        }
    }
    public void abrirDialogAlterarIngrediente(Ingrediente ingredienteSelecionado){
        ingrediente = ingredienteSelecionado;
        PagesUtil.abrirDialog("dlgAlterarIngrediente");
    }


    public static void redirecionarParaPage(String page) throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        String url = context.getExternalContext().getRequestContextPath();
        context.getExternalContext().redirect(url + "/" + page + ".faces");
    }

}