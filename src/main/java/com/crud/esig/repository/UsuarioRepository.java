package com.crud.esig.repository;

import com.crud.esig.model.Usuario;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UsuarioRepository {

    @Inject
    private EntityManager em;

    public Optional<Usuario> findByNome(String nome) {
        try {
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.nome = :nome", Usuario.class);
            query.setParameter("nome", nome);
            Usuario usuario = query.getSingleResult();
            return Optional.of(usuario);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public List<Usuario> findByNomeContainingIgnoreCase(String nomeParcial) {
        TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE LOWER(u.nome) LIKE :nomeParcial", Usuario.class);
        query.setParameter("nomeParcial", "%" + nomeParcial.toLowerCase() + "%");
        return query.getResultList();
    }

    public Usuario save(Usuario usuario) {
        if (usuario.getId() == null) {
            em.persist(usuario);
            return usuario;
        } else {
            return em.merge(usuario);
        }
    }

    public void deleteById(Long id) {
        Usuario usuario = em.find(Usuario.class, id);
        if (usuario != null) {
            em.remove(usuario);
        }
    }

    public List<Usuario> findAll() {
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }

    public Optional<Usuario> findById(Long id) {
        Usuario usuario = em.find(Usuario.class, id);
        return usuario != null ? Optional.of(usuario) : Optional.empty();
    }
}
