package com.crud.esig.repository;

import com.crud.esig.model.Tarefa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class TarefaRepository {

    @PersistenceContext(unitName = "SeuPU")
    private EntityManager em;

    public List<Tarefa> findAll() {
        return em.createQuery("SELECT t FROM Tarefa t", Tarefa.class).getResultList();
    }

    public List<Tarefa> findByTituloContaining(String titulo) {
        TypedQuery<Tarefa> query = em.createQuery(
                "SELECT t FROM Tarefa t WHERE t.titulo LIKE :titulo", Tarefa.class);
        query.setParameter("titulo", "%" + titulo + "%");
        return query.getResultList();
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
