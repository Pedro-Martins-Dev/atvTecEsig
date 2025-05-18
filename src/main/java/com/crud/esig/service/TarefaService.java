package com.crud.esig.service;

import com.crud.esig.enuns.*;
import com.crud.esig.model.Tarefa;
import com.crud.esig.model.Usuario;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Named
@SessionScoped
@Transactional
public class TarefaService implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "SeuPU") // nome do persistence unit configurado no persistence.xml
    private EntityManager em;

    @Inject
    private UsuarioService usuarioService; // injete seu service de usuário também via CDI

    // Propriedades para formularios JSF
    private Tarefa tarefaSelecionada = new Tarefa();
    private String tituloPesquisa;

    // Listar todas tarefas
    public List<Tarefa> listarTodas() {
        return em.createQuery("SELECT t FROM Tarefa t", Tarefa.class)
                .getResultList()
                .stream()
                .sorted(Comparator.comparing(t -> t.getUsuarioResponsavel().getNome()))
                .collect(Collectors.toList());
    }

    // Buscar tarefas por título
    public List<Tarefa> buscarPorTitulo() {
        if (tituloPesquisa == null || tituloPesquisa.trim().isEmpty()) {
            return listarTodas();
        }
        return em.createQuery("SELECT t FROM Tarefa t WHERE LOWER(t.titulo) LIKE :titulo", Tarefa.class)
                .setParameter("titulo", "%" + tituloPesquisa.toLowerCase() + "%")
                .getResultList();
    }

    // Salvar ou atualizar tarefa
    public String salvar() {
        if (tarefaSelecionada.getId() == null) {
            em.persist(tarefaSelecionada);
        } else {
            em.merge(tarefaSelecionada);
        }
        limparFormulario();
        return "listarTarefas?faces-redirect=true"; // redirecionar para a lista após salvar
    }

    // Deletar tarefa
    public void deletar(Tarefa tarefa) {
        Tarefa t = em.find(Tarefa.class, tarefa.getId());
        if (t != null) {
            em.remove(t);
        }
    }

    // Preparar novo cadastro
    public String prepararCadastro() {
        tarefaSelecionada = new Tarefa();
        return "cadastrarTarefa?faces-redirect=true";
    }

    // Preparar edição
    public String prepararEdicao(Tarefa tarefa) {
        this.tarefaSelecionada = tarefa;
        return "editarTarefa?faces-redirect=true";
    }

    // Limpar formulário
    public void limparFormulario() {
        tarefaSelecionada = new Tarefa();
    }

    // Getters e Setters para JSF

    public Tarefa getTarefaSelecionada() {
        return tarefaSelecionada;
    }

    public void setTarefaSelecionada(Tarefa tarefaSelecionada) {
        this.tarefaSelecionada = tarefaSelecionada;
    }

    public String getTituloPesquisa() {
        return tituloPesquisa;
    }

    public void setTituloPesquisa(String tituloPesquisa) {
        this.tituloPesquisa = tituloPesquisa;
    }

    // Métodos para lidar com datas no formato String (para input text em JSF)

    public String getDataConclusaoPrevistaString() {
        if (tarefaSelecionada.getDataConclusaoPrevista() != null) {
            return tarefaSelecionada.getDataConclusaoPrevista().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        return "";
    }

    public void setDataConclusaoPrevistaString(String data) {
        try {
            if (data != null && !data.trim().isEmpty()) {
                tarefaSelecionada.setDataConclusaoPrevista(LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            } else {
                tarefaSelecionada.setDataConclusaoPrevista(null);
            }
        } catch (DateTimeParseException e) {
            // tratar erro no frontend JSF via mensagem
        }
    }

    public String getDataConclusaoString() {
        if (tarefaSelecionada.getDataConclusao() != null) {
            return tarefaSelecionada.getDataConclusao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        return "";
    }

    public void setDataConclusaoString(String data) {
        try {
            if (data != null && !data.trim().isEmpty()) {
                tarefaSelecionada.setDataConclusao(LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            } else {
                tarefaSelecionada.setDataConclusao(null);
            }
        } catch (DateTimeParseException e) {
            // tratar erro no frontend JSF via mensagem
        }
    }

    public Prioridades[] getPrioridades() {
        return Prioridades.values();
    }

    public Status[] getStatus() {
        return Status.values();
    }
}
