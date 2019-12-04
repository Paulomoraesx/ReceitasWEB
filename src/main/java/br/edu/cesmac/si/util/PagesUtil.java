package br.edu.cesmac.si.util;

import org.primefaces.PrimeFaces;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean
public class PagesUtil {

    public static void abrirDialog(String dialog) {
        PrimeFaces.current().executeScript("PF('"+dialog+"').show();");
    }

    public static void fecharDialog(String dialog) {
        PrimeFaces.current().executeScript("PF('"+dialog+"').hide();");
    }

    public static void atualizarComponente(String componente) {
        PrimeFaces.current().ajax().update(componente);
    }

}
