package com.zazilweb.model.DAO;

import com.zazilweb.model.Estado;
import com.zazilweb.model.Municipio;
import com.zazilweb.model.Pais;
import com.zazilweb.model.SubCategoria;
import com.zazilweb.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MunicipioDao {

    public List findAll(int id) {
        List<Municipio> lista = new ArrayList<>();
        String query = "select * from municipios where estado = ?";
        try(Connection con = DatabaseConnectionManager.getConnection()) {
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1,id);
                try (ResultSet res = stmt.executeQuery()) {
                    while(res.next()){
                        Municipio m = new Municipio();
                        m.setId(res.getInt("id"));
                        m.setNombre(res.getString("nombre"));

                        Estado estado = new Estado();
                        estado.setId(res.getInt("estado"));

                        Pais pais = new Pais();
                        pais.setId(1);
                        pais.setNombre("México");
                        estado.setPais(pais);

                        lista.add(m);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

}