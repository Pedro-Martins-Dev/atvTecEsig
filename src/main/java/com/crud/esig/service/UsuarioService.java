package com.crud.esig.service;

import com.crud.esig.model.Usuario;
import com.crud.esig.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService
{
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository)
    {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarTodos()
    {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id)
    {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario salvar(Usuario usuario)
    {
        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id)
        {
        usuarioRepository.deleteById(id);
    }
}
