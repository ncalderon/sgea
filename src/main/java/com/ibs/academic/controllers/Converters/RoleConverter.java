package com.ibs.academic.controllers.Converters;

import com.ibs.academic.controllers.RoleBean;
import com.ibs.academic.dao.DAORole;
import com.ibs.academic.models.Role;
import org.bson.types.ObjectId;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by Josermando on 04/06/14.
 */
@FacesConverter(value="roleConverter", forClass = Role.class)
public class RoleConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s.trim().equals("")){
            return null;
        }
        else{
            DAORole t = DAORole.getInstance();
//            AutoComplete autoComplete = (AutoComplete)component;

            Role role = t.get(new ObjectId());
            return role;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null || o.equals("")) {
            return null;
        } else {
//            System.out.println(((Role) o).getName() + " - ");
            return ((Role) o).getName();
        }
    }
}
