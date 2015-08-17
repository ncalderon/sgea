package com.ibs.academic.controllers.DataModels;

import com.ibs.academic.models.Employee;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 * Created by Josermando on 04/06/14.
 */
public class EmployeeDataModel extends ListDataModel<Employee> implements SelectableDataModel<Employee> {
    public EmployeeDataModel(){
    }

    public EmployeeDataModel(List<Employee> data){
        super(data);
    }

    @Override
    public Object getRowKey(Employee employee) {
        return employee.getFirstname();
    }

    @Override
    public Employee getRowData(String rowKey) {
        List<Employee> emp = (List<Employee>) getWrappedData();

        for(Employee employee : emp) {
            if(employee.getFirstname().equals(rowKey))
                return employee;
        }

        return null;
    }
}
