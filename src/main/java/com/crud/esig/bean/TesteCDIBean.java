package com.crud.esig.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class TesteCDIBean
{
    private String mensagem = "CDI está funcionando!";

    public String getMensagem() {
        return mensagem;
    }
}
