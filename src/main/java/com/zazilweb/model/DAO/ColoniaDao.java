package com.zazilweb.model.DAO;

import com.zazilweb.model.Colonia;
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

public class ColoniaDao {

    public List<Colonia> findAll(int id) {
        List<Colonia> lista = new ArrayList<>();
        String query = "select * from colonias where municipio = ?";
        try(Connection con = DatabaseConnectionManager.getConnection()) {
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, id);
                try (ResultSet res = stmt.executeQuery()) {
                    while (res.next()) {
                        Colonia c = new Colonia();
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

    public Colonia findOne(int id) {
        Colonia c = new Colonia();
        String query = "select c.id, m.id, e.id, p.id " +
                "from colonias as c " +
                "join municipios as m " +
                "on c.municipio = m.id " +
                "join estados as e " +
                "on m.estado = e.id " +
                "join paises as p " +
                "on e.pais = p.id " +
                "where c.id = ?";
        try(Connection con = DatabaseConnectionManager.getConnection()) {
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, id);
                try (ResultSet res = stmt.executeQuery()) {
                    if (res.next()) {
                        Municipio m = new Municipio();
                        Estado e = new Estado();
                        Pais p = new Pais();

                        c.setId(res.getInt(1));
                        m.setId(res.getInt(2));
                        e.setId(res.getInt(3));
                        p.setId(res.getInt(4));

                        e.setPais(p);
                        m.setEstado(e);
                        c.setMunicipio(m);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;
    }
}