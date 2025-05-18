package com.crud.esig.bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@ApplicationScoped
@Named("applicationBean")
public class ApplicationBean {
	@PostConstruct
	public void init() {
		System.out.println("Aplicação JSF carregada no WildFly.");
	}
}
