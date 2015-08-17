package com.ibs.academic.controllers.Converters;

import com.ibs.academic.dao.DAOSubjectCourse;
import com.ibs.academic.models.Subject;
import com.ibs.academic.models.SubjectCourse;
import org.bson.types.ObjectId;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by nathaniel on 4/12/14.
 */
@FacesConverter(value="subjectCourseConverter", forClass = SubjectCourse.class)
public class SubjectCourseConverter implements Converter {

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((SubjectCourse) value).getId());
        }
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if(value.trim().equals("") || value.equals("null")){
            return null;
        }  else{
            return DAOSubjectCourse.getInstance().get(new ObjectId(value));
        }
    }
}
