package com.ibs.academic.controllers;

import com.ibs.academic.dao.DAORole;
import com.ibs.academic.models.Role;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josermando on 03/28/14.
 */
@ManagedBean(name = "roleBean")
@ViewScoped
public class RoleBean implements Serializable{
    private List<Role> roleList;
    private Role roleItem;
    private DAORole daoRole;
    private boolean updateItem;

    @PostConstruct
    public void init(){//Here I initialize everything I need
        roleItem = new Role();
        daoRole = DAORole.getInstance();//This goes here to  avoid NullPointerException
        roleList = daoRole.listAllRoles();

    }

    public void add(){
        daoRole.save(roleItem);
        roleList.add(roleItem);
        roleItem = new Role();
        setUpdateItem(false);
    }

    public void save(){
        daoRole.save(roleItem);
        roleItem = new Role();
        setUpdateItem(false);
    }

    public void delete(Role roleItem){
        daoRole.delete(roleItem);
        roleList.remove(roleItem);
        setUpdateItem(false);
    }

    public void edit(Role roleItem) {
        this.roleItem = roleItem;
        setUpdateItem(true);

    }

    //Getters and Setters
    public List<Role> getRoleList() {

        if (roleList == null){
            roleList = new ArrayList<Role>(daoRole.listAllRoles());
        }
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Role getRoleItem() {
        return roleItem;
    }

    public void setRoleItem(Role roleItem) {
        this.roleItem = roleItem;
    }

    public boolean isUpdateItem() {
        return updateItem;
    }

    public void setUpdateItem(boolean isEditable) {
        this.updateItem = isEditable;
    }
}
