package com.crud.esig.repository;

import com.crud.esig.model.Tarefa;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class TarefaRepository {

    @Inject
    private EntityManager em;

    public List<Tarefa> findAll() {
        return em.createQuery("SELECT t FROM Tarefa t", Tarefa.class).getResultList();
    }

    public List<Tarefa> findByTituloContaining(String titulo) {
        return em.createQuery("SELECT t FROM Tarefa t WHERE t.titulo LIKE :titulo", Tarefa.class)
                .setParameter("titulo", "%" + titulo + "%")
                .getResultList();
    }

    public Tarefa save(Tarefa tarefa) {
        if (tarefa.getId() == null) {
            em.persist(tarefa);
            return tarefa;
        } else {
            return em.merge(tarefa);
        }
    }

    public void deleteById(Long id) {
        Tarefa tarefa = em.find(Tarefa.class, id);
        if (tarefa != null) {
            em.remove(tarefa);
        }
    }
}
