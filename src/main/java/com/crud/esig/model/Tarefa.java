package com.crud.esig.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Tarefa")
public class Tarefa
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuarioResponsavel;

    private LocalDate dataCadastro;
    private LocalDate dataConclusaoPrevista;
    private LocalDate dataConclusao;

    public Tarefa() {}

    public Tarefa(String titulo, String descricao, Usuario usuarioResponsavel, LocalDate dataCadastro, LocalDate dataConclusaoPrevista) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuarioResponsavel = usuarioResponsavel;
        this.dataCadastro = dataCadastro;
        this.dataConclusaoPrevista = dataConclusaoPrevista;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDate getDataConclusaoPrevista() {
        return dataConclusaoPrevista;
    }

    public void setDataConclusaoPrevista(LocalDate dataConclusaoPrevista) {
        this.dataConclusaoPrevista = dataConclusaoPrevista;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDate dataConclusao) {
        this.dataConclusao = dataConclusao;
    }
}
