/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.dao;

import com.esd.cw.model.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author shaun
 */
public class UserDao extends AbstractDao {
 
    public UserDao(){
        super();
    }
    
    public List<User> findAll() {
        
        // define a list of users
        List<User> allUsers = new ArrayList<User>();
        
        // define a hash map to store the result in
        ArrayList<HashMap> result = new ArrayList();
        
        try {
            // run the query and get a hash map of all rows
            result = super.doQuery("SELECT * FROM users");
        } catch (SQLException e) {
            
        }
        
        // create list of users from the hash map generated by running the query
        for (HashMap r : result) {
            allUsers.add(
                    new User(
                            r.get("id").toString(), 
                            r.get("password").toString(), 
                            r.get("status").toString(),
                            Boolean.valueOf(r.get("is_admin").toString())
                    )
            );
        }
        
        // return all the users
        return allUsers;
    }
}
