package com.crud.esig.service;

import com.crud.esig.model.Tarefa;
import com.crud.esig.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService
{
    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> listarTodas()
    {
        return tarefaRepository.findAll();
    }

    public Tarefa buscarPorId(Long id)
    {
        return tarefaRepository.findById(id).orElse(null);
    }

    public List<Tarefa> buscarPorTitulo(String titulo)
    {
        return tarefaRepository.findByTituloContaining(titulo);
    }

    public Tarefa salvar(Tarefa tarefa)
    {
        return tarefaRepository.save(tarefa);
    }

    public void deletar(Long id)
    {
        tarefaRepository.deleteById(id);
    }
}

