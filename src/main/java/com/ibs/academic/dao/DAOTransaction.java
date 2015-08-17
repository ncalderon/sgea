package com.ibs.academic.dao;

import com.ibs.academic.models.Transaction;
import com.mongodb.Mongo;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by nathaniel on 3/24/14.
 */
public class DAOTransaction extends BasicDAO<Transaction,ObjectId> {

     // Implementacion de singleton

    private static final long serialVersionUID = 1L;

    private static class DAOTransactionImplHolder {
        public static final DAOTransaction INSTANCE = new DAOTransaction();
    }

    public static DAOTransaction getInstance(){
        return DAOTransactionImplHolder.INSTANCE;

    }

    protected Object readResolve(){
        return getInstance();
    }

    private DAOTransaction() {
        this(MongoDataAccess.getMongo(), MongoDataAccess.getMorphia());
    }

    private DAOTransaction(Mongo mongo, Morphia morphia) {
        super(mongo, morphia, MongoDataAccess.getNameDB());
    }

    // fin de implementacion de singleton
    public List<Transaction> getAllTransactions(){
        return find().asList();
    }

    public List<Transaction> getTransactionsByType(String transactionType){
        return createQuery().field("typeTransaction").equal(transactionType).asList();
    }
}
