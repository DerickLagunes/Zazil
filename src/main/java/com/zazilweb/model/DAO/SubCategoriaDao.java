package com.zazilweb.model.DAO;

import com.zazilweb.model.Categoria;
import com.zazilweb.model.SubCategoria;
import com.zazilweb.model.Usuario;
import com.zazilweb.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubCategoriaDao {

    public List<SubCategoria> findAll() {
        List<SubCategoria> lista = new ArrayList<>();
        String query = "select * from subcategoria";
        try(Connection con = DatabaseConnectionManager.getConnection()) {
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                try (ResultSet res = stmt.executeQuery()) {
                    while(res.next()){
                        SubCategoria c = new SubCategoria();
                        c.setId(res.getInt("id"));
                        c.setNombre(res.getString("nombre"));
                        Categoria cat = new Categoria();
                        cat.setId(res.getInt("categoria"));
                        c.setCategoria(cat);
                        lista.add(c);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List findAll(int catid) {
        List<SubCategoria> lista = new ArrayList<>();
        String query = "select * from subcategoria where categoria = ?";
        try(Connection con = DatabaseConnectionManager.getConnection()) {
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1,catid);
                try (ResultSet res = stmt.executeQuery()) {
                    while(res.next()){
                        SubCategoria c = new SubCategoria();
                        c.setId(res.getInt("id"));
                        c.setNombre(res.getString("nombre"));
                        Categoria cat = new Categoria();
                        cat.setId(res.getInt("categoria"));
                        c.setCategoria(cat);
                        lista.add(c);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public boolean insert(SubCategoria c){
        String query = "insert into subcategoria(nombre, categoria) values(?,?)";
        try(Connection con = DatabaseConnectionManager.getConnection()){
            try(PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setString(1, c.getNombre());
                stmt.setInt(2, c.getCategoria().getId());
                return stmt.executeUpdate() > 0;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
