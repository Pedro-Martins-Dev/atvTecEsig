package com.crud.esig.rest;

import com.crud.esig.model.Tarefa;
import com.crud.esig.service.TarefaService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/tarefas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TarefaResource
{

    @Inject
    private TarefaService service;

    @GET
    public List<Tarefa> listar() {
        return service.listarTodas();
    }

    @POST
    public Tarefa salvar(Tarefa tarefa) {
        return service.salvar(tarefa);
    }

    @DELETE
    @Path("/{id}")
    public void deletar(@PathParam("id") Long id) {
        service.deletar(id);
    }
}
