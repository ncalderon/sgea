package com.ibs.academic.controllers.Converters;

import com.ibs.academic.dao.DAOStudent;
import com.ibs.academic.dao.DAOTransaction;
import com.ibs.academic.models.Student;
import com.ibs.academic.models.Transaction;
import org.bson.types.ObjectId;
import org.primefaces.component.autocomplete.AutoComplete;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by josesuero on 4/9/14.
 */
@FacesConverter(value="studentConverter", forClass = Student.class)
public class StudentConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s.trim().equals("")){
            return null;
        }
        else{
            DAOStudent t = DAOStudent.getInstance();
            AutoComplete autoComplete = (AutoComplete) uiComponent;

            Student student = t.get(new ObjectId((String)autoComplete.getSubmittedValue()));
            return student;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null || o.equals("")) {
            return "";
        } else {
//            System.out.println(((Role) o).getName() + " - ");
            return String.valueOf(((Student) o).getId());
        }
    }
}
