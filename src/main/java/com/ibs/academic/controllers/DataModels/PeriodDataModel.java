package com.ibs.academic.controllers.DataModels;

import com.ibs.academic.models.Period;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 * Created by nathaniel on 4/10/14.
 */
public class PeriodDataModel extends ListDataModel<Period> implements SelectableDataModel<Period> {

    public PeriodDataModel() {
    }

    public PeriodDataModel(List<Period> data) {
        super(data);
    }

    @Override
    public Period getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data
        if(rowKey.equals("null")){
            return null;
        }
        List<Period> periods = (List<Period>) getWrappedData();

        for(Period period : periods) {
            if(period.getId().equals(new ObjectId(rowKey)))
                return period;
        }

        return null;
    }

    @Override
    public ObjectId getRowKey(Period car) {
        return car.getId();
    }
}
