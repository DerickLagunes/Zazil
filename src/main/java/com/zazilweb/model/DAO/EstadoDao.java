package com.zazilweb.model.DAO;

import com.zazilweb.model.Estado;
import com.zazilweb.model.Municipio;
import com.zazilweb.model.Pais;
import com.zazilweb.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoDao{

    public List findAll(int id) {
        List<Estado> lista = new ArrayList<>();
        String query = "select * from estados where pais = ?";
        try(Connection con = DatabaseConnectionManager.getConnection()) {
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, id);
                try (ResultSet res = stmt.executeQuery()) {
                    while (res.next()) {
                        Estado e = new Estado();
                        e.setId(res.getInt("id"));
                        e.setNombre(res.getString("nombre"));
                        Pais pais = new Pais();
                        pais.setId(1);
                        pais.setNombre("México");
                        e.setPais(pais);

                        lista.add(e);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

}