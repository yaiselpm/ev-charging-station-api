package com.killer.evchargingstationapi.config;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomIdGenerator implements IdentifierGenerator{

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        
        String prefix = "stn";
        int ids=101;
        Connection connection = session.connection();

    try {
        Statement statement=connection.createStatement();
        
        ResultSet res=statement.executeQuery("select MAX(id) from charging_station");
        if(res.next())
        {
            String s =res.getString("MAX(id)");
            int newId= Integer.parseInt(s.substring(3));           
            String generatedId = prefix + new Integer(ids).toString();           
            if (res.getString("MAX(id)")==null) {
                return generatedId;
            }
            if (res.getString("Max(id)")!=null) {               
                newId++;
                generatedId=prefix+ new Integer(newId).toString();
            }
            return generatedId;
        }
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    return null;
    }
    
    
}
