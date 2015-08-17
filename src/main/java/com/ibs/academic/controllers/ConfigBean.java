package com.ibs.academic.controllers;

import com.ibs.academic.dao.DAOConfig;
import com.ibs.academic.models.Config;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Josermando on 04/11/14.
 */
@ManagedBean(name="configBean")
@ViewScoped
public class ConfigBean implements Serializable {
    private DAOConfig daoConfig;
    private Config item;
    private List<Config> configList;
    private boolean updateItem;

    @PostConstruct
    public void init(){
        item = new Config();
        daoConfig = DAOConfig.getInstance();
        configList = daoConfig.listAllConfigs();
    }

    public void add(){
        daoConfig.save(item);
        configList.add(item);
        item = new Config();
        setUpdateItem(false);
    }

    public void save(){
        daoConfig.save(item);
        item = new Config();
        setUpdateItem(false);
    }

    public void delete(Config item){
        daoConfig.delete(item);
        configList.remove(item);
        setUpdateItem(false);
    }

    public void edit(Config item){
        this.item = item;
        setUpdateItem(true);
    }

    //Getters and Setters


    public Config getItem() {
        return item;
    }

    public void setItem(Config item) {
        this.item = item;
    }

    public List<Config> getConfigList() {
        return configList;
    }

    public void setConfigList(List<Config> configList) {
        this.configList = configList;
    }

    public boolean isUpdateItem() {
        return updateItem;
    }

    public void setUpdateItem(boolean updateItem) {
        this.updateItem = updateItem;
    }
}
