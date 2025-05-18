package com.crud.esig.bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class HomeBean
{

    public String home()
    {
        return "home?faces-redirect=true";
    }
}
