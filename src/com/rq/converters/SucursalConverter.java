package com.rq.converters;


import com.rq.models.security.Rol;
import com.rq.models.share.Sucursal;
import com.rq.security.HibernateUtil;
import org.hibernate.Session;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "SucursalConverter")

public class SucursalConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s==null || s == ""){
            return null;
        }else{

            Session con= HibernateUtil.getSessionFactory().openSession();
            Long id=Long.valueOf(s);

            return con.get(Sucursal.class,id);
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Sucursal){
            return ((Sucursal)value).getId().toString();
        }
        return "";
    }
}
