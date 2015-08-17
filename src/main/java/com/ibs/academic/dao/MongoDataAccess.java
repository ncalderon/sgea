package com.ibs.academic.dao;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.net.UnknownHostException;

/**
 * Created by nathaniel on 3/24/14.
 */

public class MongoDataAccess {

    private static Mongo mongo;
    private static Morphia morphia;
    private static Datastore dataStore;

    public static Mongo getMongo() {
        if (mongo == null) {
            try {
                MongoClientURI clientURI = new MongoClientURI("mongodb://developer:12345678@ds033499.mongolab.com:33499/academic");
                mongo = new MongoClient(clientURI);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return mongo;
    }

    public static Morphia getMorphia(){
        if (morphia == null) {
           morphia = new Morphia();
           morphia.mapPackage("com.ibs.academic.models");
        }
        return morphia;
    }

    public static Datastore getDatastore(){
        if (dataStore == null){
            dataStore = getMorphia().createDatastore(MongoDataAccess.getMongo(), MongoDataAccess.getMongoDB().getName());
        }
        return dataStore;
    }

    public static DB getMongoDB() {
        return getMongo().getDB("academic");
    }


    public static String getNameDB() {
        return "academic";
    }

    public static void closeMongoDB() {
        if (mongo != null)
            try {
                mongo.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

}
