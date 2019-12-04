package br.edu.cesmac.si.receita.dto.builder;

import br.edu.cesmac.si.receita.dto.IngredienteReceitaDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredienteReceitaDTOBuilder {
    private IngredienteReceitaDTO ingredienteReceitaDTO;

    public IngredienteReceitaDTOBuilder() {
        this.ingredienteReceitaDTO = new IngredienteReceitaDTO();
    }

    public IngredienteReceitaDTOBuilder comIdReceita(Integer idReceita) {
        ingredienteReceitaDTO.setIdReceita(idReceita);
        return this;
    }

    public IngredienteReceitaDTOBuilder comIdIngrediente(Integer idIngrediente) {
        ingredienteReceitaDTO.setIdIngrediente(idIngrediente);
        return this;
    }

    public IngredienteReceitaDTO construir() {
        return this.ingredienteReceitaDTO;
    }

    public IngredienteReceitaDTOBuilder mapear(ResultSet rs) throws SQLException {
        return this
                .comIdReceita(rs.getInt("id_receita"))
                .comIdIngrediente(rs.getInt("id_ingrediente"));

    }
}
