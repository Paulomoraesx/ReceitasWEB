package br.edu.cesmac.si.receita.dto;

import java.util.Objects;

public class IngredienteReceitaDTO {
    private Integer idReceita;
    private Integer idIngrediente;

    public Integer getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(Integer idReceita) {
        this.idReceita = idReceita;
    }

    public Integer getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Integer idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredienteReceitaDTO that = (IngredienteReceitaDTO) o;
        return Objects.equals(idReceita, that.idReceita) &&
                Objects.equals(idIngrediente, that.idIngrediente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReceita, idIngrediente);
    }

    @Override
    public String toString() {
        return "ingredienteReceitaDTO{" +
                "idReceita=" + idReceita +
                ", idIngrediente=" + idIngrediente +
                '}';
    }
}
