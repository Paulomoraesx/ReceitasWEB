package br.edu.cesmac.si.util;

import javax.faces.context.FacesContext;

import br.edu.cesmac.si.receita.model.Usuario;

import java.util.Map;

public class SessionUtil {

	public static void adicionaObjetoUsuarioNaSessao(String nome, Object objeto) {
		getSessionMap().put(nome , objeto);
	}
	
	public static Boolean verificaSeUsuarioEstaNaSessao() {
		Boolean sessaoAtiva = false;
		Usuario usuarioLogado = (Usuario) recuperaObjetoDaSessao("usuario_logado");
		if(usuarioLogado != null) {
			sessaoAtiva = true;
		}
		return sessaoAtiva;
	}
	private static Map<String, Object> getSessionMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	}
	
	public static Object recuperaObjetoDaSessao(String objeto) {
		return getSessionMap().get(objeto);
	}
	public static void excluirVariavelDaSessao(String nomeValor){
		getSessionMap().remove(nomeValor);
	}

}
