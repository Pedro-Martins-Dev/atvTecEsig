package com.crud.esig.service;

import com.crud.esig.model.Tarefa;
import com.crud.esig.model.Usuario;
import com.crud.esig.repository.TarefaRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TarefaService {

    @Inject
    private TarefaRepository tarefaRepository;

    public List<Tarefa> listarTodas() {
        return tarefaRepository.findAll();
    }

    public List<Tarefa> buscarPorTitulo(String titulo) {
        return tarefaRepository.findByTituloContaining(titulo);
    }

    public Tarefa salvar(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public void deletar(Long id) {
        tarefaRepository.deleteById(id);
    }

    public List<Tarefa> listarPorUsuario(Usuario usuario) {
        return tarefaRepository.findAll()
                .stream()
                .filter(t -> t.getUsuarioResponsavel().getId().equals(usuario.getId()))
                .toList();
    }

    public Tarefa buscarPorId(Long id) {
        return tarefaRepository.findAll()
                .stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Tarefa atualizarTarefa(Tarefa tarefaAtualizada) {
        return tarefaRepository.save(tarefaAtualizada);
    }
}
