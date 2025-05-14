package com.crud.esig.bean;

import com.crud.esig.model.Usuario;
import com.crud.esig.service.UsuarioService;
import jakarta.annotation.ManagedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class UsuarioBean {
    @Autowired
    private UsuarioService usuarioService;

    private Usuario usuario = new Usuario();

    public void salvar() {
        usuarioService.salvar(usuario);
        usuario = new Usuario(); // Limpa o formulário
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

