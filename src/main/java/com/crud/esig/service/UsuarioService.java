package com.crud.esig.service;

import com.crud.esig.model.Usuario;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Named
@RequestScoped
public class UsuarioService {

    @PersistenceContext(unitName = "EsigPU")
    private EntityManager entityManager;

    public Usuario buscarPorId(Long id) {
        if (id == null) {
            System.err.println("Erro: ID não pode ser nulo.");
            return null;
        }

        try {
            Usuario usuario = entityManager.find(Usuario.class, id);
            if (usuario == null) {
                System.out.println("Usuário com ID " + id + " não encontrado.");
            }
            return usuario;
        } catch (Exception e) {
            System.err.println("Erro ao buscar usuário por ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    @Transactional
    public Usuario salvarUsuario(Usuario usuario) {
        if (usuario.getId() == null) {
            entityManager.persist(usuario);
            return usuario;
        } else {
            return entityManager.merge(usuario);
        }
    }
    @Transactional
    public void deletar(Long id) {
        Usuario usuario = buscarPorId(id);
        if (usuario != null) {
            entityManager.remove(usuario);
        }
    }

    public Optional<Usuario> buscarPorNome(String nome) {
        TypedQuery<Usuario> query = entityManager.createQuery(
                "SELECT u FROM Usuario u WHERE u.nome = :nome", Usuario.class);
        query.setParameter("nome", nome);
        List<Usuario> result = query.getResultList();
        if (result.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(result.get(0));
    }

    public List<Usuario> buscarPorNomeContendo(String nomeParcial) {
        TypedQuery<Usuario> query = entityManager.createQuery(
                "SELECT u FROM Usuario u WHERE LOWER(u.nome) LIKE :nomeParcial", Usuario.class);
        query.setParameter("nomeParcial", "%" + nomeParcial.toLowerCase() + "%");
        return query.getResultList();
    }

    public Usuario buscarUsuarioPorNomeInterativo(String nomeParcial) {
        List<Usuario> usuariosEncontrados = buscarPorNomeContendo(nomeParcial);
        if (usuariosEncontrados.isEmpty()) {
            return null;
        }
        return usuariosEncontrados.get(0);
    }
}
