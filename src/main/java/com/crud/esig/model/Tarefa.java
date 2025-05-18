package com.crud.esig.model;

import com.crud.esig.enuns.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tarefas")
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private Prioridades prioridade;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuarioResponsavel;

    private LocalDate dataCadastro;
    private LocalDate dataConclusaoPrevista;
    private LocalDate dataConclusao;

    public Tarefa() {}

    public Tarefa(String titulo, String descricao, Prioridades prioridade, Usuario usuarioResponsavel, LocalDate dataConclusaoPrevista) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.status = Status.PENDENTE;
        this.usuarioResponsavel = usuarioResponsavel;
        this.dataCadastro = LocalDate.now();
        this.dataConclusaoPrevista = dataConclusaoPrevista;
    }

    // Getters e setters...

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Prioridades getPrioridade() { return prioridade; }
    public void setPrioridade(Prioridades prioridade) { this.prioridade = prioridade; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Usuario getUsuarioResponsavel() { return usuarioResponsavel; }
    public void setUsuarioResponsavel(Usuario usuarioResponsavel) { this.usuarioResponsavel = usuarioResponsavel; }

    public LocalDate getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDate dataCadastro) { this.dataCadastro = dataCadastro; }

    public LocalDate getDataConclusaoPrevista() { return dataConclusaoPrevista; }
    public void setDataConclusaoPrevista(LocalDate dataConclusaoPrevista) { this.dataConclusaoPrevista = dataConclusaoPrevista; }

    public LocalDate getDataConclusao() { return dataConclusao; }
    public void setDataConclusao(LocalDate dataConclusao) { this.dataConclusao = dataConclusao; }

    @Override
    public String toString() {
        return String.format(
                "Tarefa:\n- ID: %d\n- Título: %s\n- Descrição: %s\n- Prioridade: %s\n- Status: %s\n- Responsável: %s\n- Cadastro: %s\n- Previsão de Conclusão: %s\n- Conclusão: %s",
                id, titulo, descricao, prioridade, status,
                usuarioResponsavel != null ? usuarioResponsavel.getNome() : "N/A",
                dataCadastro, dataConclusaoPrevista,
                (dataConclusao != null ? dataConclusao : "Tarefa ainda não concluída")
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tarefa)) return false;
        Tarefa tarefa = (Tarefa) o;
        return id != null && id.equals(tarefa.id);
    }
}
