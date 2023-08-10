package com.zazilweb.model.DAO;

import com.zazilweb.model.Colonia;
import com.zazilweb.model.Distribuidor;
import com.zazilweb.model.Usuario;
import com.zazilweb.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DistribuidorDao{

    public List<Distribuidor> findAll() {
        List<Distribuidor> lista = new ArrayList<>();
        String query = "select * from distribuidor";
        try(Connection con = DatabaseConnectionManager.getConnection()) {
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                try (ResultSet res = stmt.executeQuery()) {
                    while (res.next()) {
                        Distribuidor d = new Distribuidor();
                        d.setId(res.getInt(1));
                        d.setNombre_distribuidor(res.getString(2));
                        d.setNombre_contacto(res.getString(3));
                        d.setTelefono(res.getString(4));
                        d.setTelefono2(res.getString(5));
                        d.setCorreo1(res.getString(6));
                        d.setCorreo2(res.getString(7));

                        ColoniaDao colDao = new ColoniaDao();
                        Colonia c = colDao.findOne(res.getInt(11));

                        d.setPais(c.getMunicipio().getEstado().getPais());
                        d.setEstado(c.getMunicipio().getEstado());
                        d.setMunicipio(c.getMunicipio());
                        d.setColonia(c);

                        lista.add(d);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public boolean insert(Object object) {
        boolean flag = false;
        String query = "insert into distribuidor(nombre_distribuidor,nombre_contacto,telefono,telefono2,correo1,correo2,pais,estado,municipio,colonia) values(?,?,?,?,?,?,?,?,?,?)";
        Distribuidor d = (Distribuidor) object;
        try(Connection con = DatabaseConnectionManager.getConnection()) {
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setString(1,d.getNombre_distribuidor());
                stmt.setString(2,d.getNombre_contacto());
                stmt.setString(3,d.getTelefono());
                stmt.setString(4,d.getTelefono2());
                stmt.setString(5,d.getCorreo1());
                stmt.setString(6,d.getCorreo2());
                stmt.setInt(7,d.getPais().getId());
                stmt.setInt(8,d.getEstado().getId());
                stmt.setInt(9,d.getMunicipio().getId());
                stmt.setInt(10,d.getColonia().getId());
                flag = stmt.executeUpdate() == 0 ? false:true;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return flag;
    }
}
