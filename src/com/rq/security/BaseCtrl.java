package com.rq.security;

import org.hibernate.Session;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
public class BaseCtrl implements Serializable {


    @PostConstruct
    public void init(){

    }


}
