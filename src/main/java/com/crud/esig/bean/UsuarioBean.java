package com.crud.esig.bean;

import com.crud.esig.model.Usuario;
import com.crud.esig.service.UsuarioService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@Named("usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {
    private Usuario usuario = new Usuario();

    @Autowired
    private UsuarioService usuarioService;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void salvar() {
        usuarioService.salvar(usuario);
        usuario = new Usuario(); // Limpar campos após salvar
    }
}

