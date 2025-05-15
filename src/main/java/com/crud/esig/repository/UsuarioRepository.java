package com.crud.esig.repository;

import com.crud.esig.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>
{
    Optional<Usuario> findByNome(String nome);

    List<Usuario> findByNomeContainingIgnoreCase(String nomeParcial);
}
