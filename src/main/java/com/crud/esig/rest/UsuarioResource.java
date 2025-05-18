package com.crud.esig.rest;

import com.crud.esig.model.Usuario;
import com.crud.esig.service.UsuarioService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    private UsuarioService usuarioService;

    @GET
    public List<Usuario> listar() {
        return usuarioService.listarTodos();
    }

    @POST
    public Usuario salvar(Usuario usuario) {
        return usuarioService.salvarUsuario(usuario);
    }

    @DELETE
    @Path("/{id}")
    public void deletar(@PathParam("id") Long id) {
        usuarioService.deletar(id);
    }
}
