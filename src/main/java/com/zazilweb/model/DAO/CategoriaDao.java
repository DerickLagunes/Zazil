package com.zazilweb.model.DAO;

import com.zazilweb.model.Categoria;
import com.zazilweb.model.Colonia;
import com.zazilweb.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {

    public List<Categoria> findAll() {
        List<Categoria> lista = new ArrayList<>();
        String query = "select * from categoria";
        try(Connection con = DatabaseConnectionManager.getConnection()) {
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                try (ResultSet res = stmt.executeQuery()) {
                    while (res.next()) {
                        Categoria c = new Categoria();
                        c.setId(res.getInt("id"));
                        c.setNombre(res.getString("nombre"));
                        lista.add(c);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public boolean insert(Categoria c){
        String query = "insert into categoria(nombre) values(?)";
        try(Connection con = DatabaseConnectionManager.getConnection()) {
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setString(1, c.getNombre());
                int result = stmt.executeUpdate();
                return result > 0;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
