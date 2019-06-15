package com.rq.validators;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class NameValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object str)
            throws ValidatorException {
        if(str == null){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error de validacion",
                    "El nombre no puede ir vacio"));
        }else {
            String strg=(String) str;

            if(strg.length() < 3 || strg.length() > 100){
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error de validacion",
                        "El campo nombre debe tener almenos 3 caracteres y menso de 100"));
            }
        }


    }
}
