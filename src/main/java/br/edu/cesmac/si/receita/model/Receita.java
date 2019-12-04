package br.edu.cesmac.si.receita.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "receitas")
@Entity(name = "receitas")
public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "modo_de_preparo")
    private String modoDePreparo;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "receita_ingredientes",
            joinColumns = {@JoinColumn(name = "id_receita", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_ingrediente", referencedColumnName = "id")})
    private List<Ingrediente> ingredientes;

    public Receita() {
        this.ingredientes = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getModoDePreparo() {
        return modoDePreparo;
    }

    public void setModoDePreparo(String modoDePreparo) {
        this.modoDePreparo = modoDePreparo;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receita receita = (Receita) o;
        return Objects.equals(id, receita.id) &&
                Objects.equals(nome, receita.nome) &&
                Objects.equals(usuario, receita.usuario) &&
                Objects.equals(modoDePreparo, receita.modoDePreparo) &&
                Objects.equals(ingredientes, receita.ingredientes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, usuario, modoDePreparo, ingredientes);
    }

    @Override
    public String toString() {
        return "Receita{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", usuario=" + usuario +
                ", modoDePreparo='" + modoDePreparo + '\'' +
                ", ingredientes=" + ingredientes +
                '}';
    }
}