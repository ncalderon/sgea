package com.ibs.academic.controllers.Converters;

import com.ibs.academic.dao.DAOEmployee;
import com.ibs.academic.dao.DAOSubject;
import com.ibs.academic.dao.DAOTutor;
import com.ibs.academic.models.Employee;
import com.ibs.academic.models.Subject;
import org.bson.types.ObjectId;
import org.primefaces.component.autocomplete.AutoComplete;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by nathaniel on 4/11/14.
 */
@FacesConverter(value = "subjectConverter", forClass = Subject.class)
public class SubjectConverter implements Converter{

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((Subject) value).getId());
        }
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if(value.trim().equals("") || value.equals("null")){
            return null;
        }
        else{
            DAOSubject daoSubject = DAOSubject.getInstance();
            return daoSubject.get(new ObjectId(value));


        }
    }
}
