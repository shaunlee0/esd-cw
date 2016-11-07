/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.dao;

import com.esd.cw.model.Member;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author shaun
 */
public class MemberDao extends AbstractDao {
 
    public MemberDao(){
        super();
    }
    
    public List<Member> findAll() {
        
        // define a list of members
        List<Member> allMembers = new ArrayList<Member>();
        
        // define a hash map to store the result in
        ArrayList<HashMap> result = new ArrayList();
        
        try {
            // run the query and get a hash map of all rows
            result = super.select("SELECT * FROM Members");
        } catch (SQLException e) {
            
        }
        
        // create list of members from the hash map generated by running the query
        for (HashMap r : result) {
            allMembers.add(
                    new Member(
                            r.get("id").toString(), 
                            r.get("name").toString(), 
                            r.get("address").toString(), 
                            new Date(r.get("dob").toString()), 
                            new Date(r.get("dor").toString()),
                            r.get("status").toString(), 
                            Float.parseFloat(r.get("balance").toString()), 
                            Integer.parseInt(r.get("claims_remaining").toString())
                    )
            );
        }
        
        // return all the members
        return allMembers;
    }
    
    public Member findById(String memberId) {
        
        // define a hash map to store the result in
        ArrayList<HashMap> result = new ArrayList();
        
        try {
            result = super.select("SELECT * FROM Members WHERE id='" + memberId + "'");
        } catch (SQLException e) {
            
        }
        
        if (result.size() > 0) {
            return new Member(
                    result.get(0).get("id").toString(), 
                    result.get(0).get("name").toString(), 
                    result.get(0).get("address").toString(), 
                    new Date(result.get(0).get("dob").toString()), 
                    new Date(result.get(0).get("dor").toString()),
                    result.get(0).get("status").toString(), 
                    Float.parseFloat(result.get(0).get("balance").toString()), 
                    Integer.parseInt(result.get(0).get("claims_remaining").toString())
            );
        } else {
            return new Member();
        }
    }
    
    boolean insertMember(Member member) {
        return true;
    }
    
    boolean updateMember(Member member) {
        return true;
    }
    
    boolean deleteMember(Member member) {
        return true;
    }
}