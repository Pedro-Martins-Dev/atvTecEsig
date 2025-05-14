package com.crud.esig.repository;

import com.crud.esig.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long>
{
    List<Tarefa> findByTituloContaining(String titulo);
}

