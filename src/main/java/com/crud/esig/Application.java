package com.crud.esig.config;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@ApplicationScoped
@Named("applicationBean")
public class Application {

	@PostConstruct
	public void init() {
		System.out.println("Aplicação JSF carregada no WildFly.");
	}

}
