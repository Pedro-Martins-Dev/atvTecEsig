package com.crud.esig.service;

import com.crud.esig.model.Usuario;
import com.crud.esig.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.util.Scanner;

@Service
public class UsuarioService
{
    private static final Scanner scanner = new Scanner(System.in);

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository)
    {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario buscarPorId(Long id)
    {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario salvarUsuario(Usuario usuario)
    {
        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id)
        {
        usuarioRepository.deleteById(id);
    }

    public void cadastrarUsuario()
    {
        System.out.println("Insira o nome do usuário que você deseja cadastrar: ");
        String nome = scanner.nextLine();

        Usuario usuario = new Usuario(nome);
        salvarUsuario(usuario);
    }

    public Usuario buscarPorNome(String nome)
    {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNome(nome);
        return usuarioOptional.orElse(null);
    }

    public List<Usuario> buscarPorNomeContendo(String nomeParcial) {
        return usuarioRepository.findByNomeContainingIgnoreCase(nomeParcial);
    }
}
