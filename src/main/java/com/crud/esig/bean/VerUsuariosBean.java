package com.crud.esig.bean;

import com.crud.esig.model.Usuario;
import com.crud.esig.service.UsuarioService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named("VerUsuarioBean")
@RequestScoped
public class VerUsuariosBean
{

    @Inject
    private UsuarioService usuarioService;

    public List<Usuario> getUsuarios() {
        return usuarioService.listarTodos();
    }
}
