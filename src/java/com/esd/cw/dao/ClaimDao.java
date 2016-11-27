/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.dao;

import com.esd.cw.enums.Queries;
import com.esd.cw.model.Claim;

import com.esd.cw.util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shaun
 */
public class ClaimDao {

    public ClaimDao() {

    }
    
    public List<Claim> getAllPendingClaims() throws SQLException{
        List<Claim> pendingClaims = new ArrayList<>();
        ArrayList<HashMap> result = new ArrayList<>();

        result = DbBean.getInstance().select(Queries.GET_ALL_PENDING_CLAIMS.getSql());

        for (HashMap r : result) {
            pendingClaims.add(
                    new Claim(
                            (int) r.get("id"),
                            (float) r.get("amount"),
                            (String) r.get("rationale"),
                            (String) r.get("mem_id"),
                            (Date) r.get("date"),
                            (String) r.get("status")
                    )
            );
        }

        return pendingClaims;
    }

    public long getRegistrationDate(String memId) throws SQLException, ParseException {

        // define a hash map to store the result in
        ArrayList<HashMap> result = new ArrayList();
        long dateInMs = 0;
        result = DbBean.getInstance().select(String.format(Queries.SELECT_MEMBER_REG_DATE.getSql(), memId));

        if (result.size() > 0) {
            Date date = Util.getDateFromString(result.get(0).get("dor").toString());

            dateInMs = date.getTime();
        }
        return dateInMs;
    }

    public void makeClaim(Claim claim) throws SQLException {
        String claimDate = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(claim.getClaimDate());

        String query = String.format(Queries.INSERT_CLAIM.getSql(), claim.getMemberId(), claimDate, claim.getRationale(), claim.getStatus(), claim.getAmount());

        DbBean.getInstance().runQuery(query);

    }

    public List<Claim> getClaimsForMember(String memberId) throws SQLException, SQLException, SQLException {

        // define a list of users
        List<Claim> allClaims = new ArrayList<>();

        // define a hash map to store the result in
        ArrayList<HashMap> result = new ArrayList();

        result = DbBean.getInstance().select(String.format(Queries.GET_CLAIMS_MADE_BY_A_MEMBER.getSql(), memberId));

        for (HashMap r : result) {
            allClaims.add(
                    new Claim(
                            (int) r.get("id"),
                            (float) r.get("amount"),
                            (String) r.get("rationale"),
                            (String) r.get("mem_id"),
                            (Date) r.get("date"),
                            (String) r.get("status")
                    )
            );
        }

        // return all the users
        return allClaims;
    }
    
    public String getTotalOfAllClaims() throws SQLException{
        return DbBean.getInstance().doQueryReturningXColumns(Queries.TOTAL_AMOUNT_FOR_ALL_CLAIMS_MADE.getSql(), 1);
    }

    public boolean acceptClaim(String claimId) {
        boolean result = false;
        try{
            DbBean.getInstance().runQuery(String.format(Queries.ACCEPT_CLAIM.getSql(), claimId));
            result = true;
        }catch(Exception e){
            System.out.println("Failed to accpet claim with id = " + claimId);
            e.printStackTrace();
        }
        return result;
    }

}
