package com.rq.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class DescValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o)
            throws ValidatorException {
        if(o==null){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error de validacion",
                    "Debe prorcionar una descripcion"
                    ));
        }else {
            String desc =(String) o;
            if(desc.length()<3 ||desc.length()>254){
             throw new ValidatorException(new FacesMessage(
                     FacesMessage.SEVERITY_ERROR,
                     "Error de validacion",
                     "Una descripcion valida posee [4-255] caracteres."
             ));
            }
        }
    }
}
