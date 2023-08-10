package com.zazilweb.model.DAO;

import com.zazilweb.model.*;
import com.zazilweb.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaisDao{

    public List<Pais> findAll() {
        List<Pais> lista = new ArrayList<>();
        String query = "select * from paises";
        try(Connection con = DatabaseConnectionManager.getConnection()) {
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                try (ResultSet res = stmt.executeQuery()) {
                    while(res.next()){
                        Pais p = new Pais();
                        p.setId(res.getInt(1));
                        p.setNombre(res.getString(2));
                        lista.add(p);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

}
