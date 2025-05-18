package com.crud.esig.bean;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("homeBean")
@ViewScoped
public class IndexBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mensagem = "Bem-vindo ao sistema ESIG!";

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
