package com.ibs.academic.controllers.Converters;

import com.ibs.academic.dao.DAOTutor;
import com.ibs.academic.models.Tutor;
import org.bson.types.ObjectId;
import org.primefaces.component.autocomplete.AutoComplete;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by Josermando on 04/11/14.
 */
@FacesConverter(value="tutorConverter", forClass = Tutor.class)
public class TutorConverter implements Converter{
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value.trim().equals("")){
            return null;
        }
        else{
            DAOTutor t = DAOTutor.getInstance();
            AutoComplete autoComplete = (AutoComplete)component;

            Tutor tutor = t.get(new ObjectId((String)autoComplete.getSubmittedValue()));
            return tutor;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
//            System.out.println(((Role) o).getName() + " - ");
            return String.valueOf(((Tutor) value).getId());
        }
    }
}
