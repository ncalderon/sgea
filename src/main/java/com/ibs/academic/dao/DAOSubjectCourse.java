package com.ibs.academic.dao;

import com.ibs.academic.models.*;
import com.mongodb.Mongo;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by nathaniel on 4/9/14.
 */
public class DAOSubjectCourse extends BasicDAO<SubjectCourse, ObjectId> {

   private int max_hours_per_subject;
   private DAOEmployee daoEmployee;

    // Implementacion de singleton

    private static final long serialVersionUID = 1L;


    private static class DAOSubjectCourseHolder {
        public static final DAOSubjectCourse INSTANCE = new DAOSubjectCourse();
    }

    public static DAOSubjectCourse getInstance(){
        return DAOSubjectCourseHolder.INSTANCE;

    }

    protected Object readResolve(){
        return getInstance();
    }

    private DAOSubjectCourse() {
        super(MongoDataAccess.getMongo(), MongoDataAccess.getMorphia(), MongoDataAccess.getNameDB());
    }

    // fin de implementacion de singleton

    public List<SubjectCourse> findByTeacher (Employee employee, String name){
        List<SubjectCourse> subjectCourses = createQuery().field("teacher").equal(employee).asList();
        List<Subject> subjectList = DAOSubject.getInstance().findSubjectByName(name);
        List<SubjectCourse> responselist = new ArrayList<SubjectCourse>();

        for(SubjectCourse subjectCourse: subjectCourses){
            for(Subject subject: subjectList){
                if(subject.getId().toString().equalsIgnoreCase(subjectCourse.getSubject().getId().toString())){
                    responselist.add(subjectCourse);
                }
            }

        }

        return responselist;

    }

    public List<SubjectCourse> list (){
        return find().asList();
    }

    public List<SubjectCourse> listActiveSubjects(){
        return createQuery().field("subject.status").equal("active").asList();
    }

    // Generar Horario para el periodo en curso
    public boolean generateSchedules (){

        DAOConfig daoConfig = DAOConfig.getInstance();
        // Busca la configuracion para saber cual es el maximo de horas por clase
        max_hours_per_subject = Integer.parseInt(daoConfig.findByName("max_hours_per_subject").getValue());

        // REINICIA LAS HORAS RESERVADAS DE LOS HORARIOS DE LOS PROFESORES
        daoEmployee = DAOEmployee.getInstance();
        daoEmployee.resetReservedHours();

        // BUSCA TODAS LAS MATERIAS DE CADA CURSO
        List<SubjectCourse> subjectCourses = list();

        for(SubjectCourse subjectCourse: subjectCourses){
            if(!subjectCourse.getSubject().getStatus().equalsIgnoreCase("active")){
                continue;
            }
            // SE GENERA HORARIO Y SE SETEA A ASIGNATURA
            subjectCourse.setSchedule(generateSchedulesSubject(subjectCourse));
            // ACTUALIZA HORARIO DE ASIGNATURA
            save(subjectCourse);

        }

        return true;
    }

    private  List<Schedule> generateSchedulesSubject(SubjectCourse subjectCourse){
        // HORARIO DE ASIGNATURA
        List<Schedule> schedules = new ArrayList<Schedule>();
        // DISPONIBILIDAD DE PROFESOR
        List<Schedule>  avalaibility_teachers = subjectCourse.getTeacher().getScheduleAvailable();
        Employee teacher = subjectCourse.getTeacher();

        boolean band = true;
        float hours_required = subjectCourse.getHours();

        float hours_to_reserved = 0;
        float hours_schedule = 0;
        float hours_reserved = 0;
        float hours_available = 0;
        float start_time = 0;
        float end_time = 0;
        Schedule subjectSchedule;
        // ITEM DE SCHEDULE QUE SE ESTA ITERANDO
        Schedule scheduleItem;
        for(int i = 0; i < avalaibility_teachers.size(); i++){

            scheduleItem = avalaibility_teachers.get(i);
            // TOTAL DE HORAS
            hours_schedule = scheduleItem.getEndTime() - scheduleItem.getStartTime();
            // HORAS RESERVADAS
            hours_reserved = scheduleItem.getHours_reserved();
            // HORAS DISPONIBLES
            hours_available = hours_schedule - hours_reserved;
            // SI LAS HORAS DISPONIBLE ES = 0, PUES CONTINUA CON EL PROXIMO DIA
            if(hours_available == 0){
                continue;
            }
            // SE CREA UN NUEVO OBJETO DE HORARIO PARA LA MATERIA
            subjectSchedule = new Schedule();

            // SI LAS HORAS DISPONIBLE SON MAYOR A LAS REQUERIDAD, LAS HORAS A RESERVAR SON IGUAL AL VALOR DE LAS REQUERIDAS
            if(hours_available >= hours_required){
                if(hours_required > max_hours_per_subject ){
                    hours_to_reserved = max_hours_per_subject;
                }else{
                    hours_to_reserved = hours_required;
                }
            }else {
                if(hours_required > max_hours_per_subject && max_hours_per_subject <= hours_available){
                    hours_to_reserved = max_hours_per_subject;
                }else{
                    // SI LAS HORAS DISPONIBLE SON MENOR A LAS REQUERIDAD, LAS HORAS A RESERVAR SON IGUAL AL VALOR DE LAS HORAS DISPONIBLES
                    hours_to_reserved = hours_available;
                }
            }
            // DIA
            subjectSchedule.setDay(scheduleItem.getDay());
            //  HORA INICIO
            start_time = scheduleItem.getStartTime() + hours_reserved;
            subjectSchedule.setStartTime(start_time);
            // HORA FIN
            end_time = start_time + hours_to_reserved;
            subjectSchedule.setEndTime(end_time);
            // SE RESTA LAS HORAS QUE SE ACABAN DE RESERVAR A LA CANTIDAD QUE SE REQUIERE
            hours_required -= hours_to_reserved;
            // SE AGREGA HORARIO A LISTA
            schedules.add(subjectSchedule);

            // SE ACTUALIZA HORAS RESERVADAS DE DISPONIBILIDAD DE PROFESOR
            hours_reserved = hours_reserved + hours_to_reserved;
            scheduleItem.setHours_reserved(hours_reserved);
            avalaibility_teachers.set(i, scheduleItem);

            // SI YA NO SE REQUIEREN MAS HORAS, PUES SALE DEL FOR
            if(hours_required == 0){
                break;
            }
        }
        // SE ACTUALIZA HORARIO DE EL PROFESOR
        teacher.setScheduleAvailable(avalaibility_teachers);
        daoEmployee.save(teacher);

        return schedules;

    }

}
