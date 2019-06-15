package com.rq.converters;

import com.rq.models.security.Rol;
import com.rq.security.HibernateUtil;
import org.hibernate.Session;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("RolConverter")
public class RolConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s==null || s == ""){
            return null;
        }else{

            Session con= HibernateUtil.getSessionFactory().openSession();
            Long id=Long.valueOf(s);

            return con.get(Rol.class,id);
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Rol){
            return ((Rol)value).getId().toString();
        }
        return "";
    }
}
