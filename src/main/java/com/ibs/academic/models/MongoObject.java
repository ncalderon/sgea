package com.ibs.academic.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Version;

/**
 * Created by nathaniel on 3/19/14.
 */
public abstract class MongoObject {

    @Id
    @Property("id")
    protected ObjectId id;

    @Version
    @Property("version")
    private Long version;

    public MongoObject (){

    }

    public ObjectId getId(){
        return id;
    }

    public void setId(ObjectId id){
        this.id = id;
    }

    public Long getVersion(){
        return version;
    }

    public void setVersion(Long version){
        this.version = version;
    }
}
