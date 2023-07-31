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
    private Connection con;
    private List<Categoria> lista;
    private boolean resp;
    private ResultSet res;

    public CategoriaDao(){
        this.con = new DatabaseConnectionManager().connect();
        this.lista = new ArrayList<>();
        this.resp = false;
    }

    public List findAll() {
        try {
            PreparedStatement stmt =
                    con.prepareStatement(
                            "select * from categoria"
                    );
            res = stmt.executeQuery();
            while(res.next()){
                Categoria c = new Categoria();
                c.setId(res.getInt("id"));
                c.setNombre(res.getString("nombre"));
                lista.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnectionManager.cerrarTodo(res,con);
        }
        return lista;
    }

    public boolean insert(Categoria c){
        try{
            PreparedStatement stmt = con.prepareStatement(
                    "insert into categoria(nombre) values(?)"
            );
            stmt.setString(1,c.getNombre());
            int result = stmt.executeUpdate();
            return result > 0;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DatabaseConnectionManager.cerrarTodo(con);
        }
    }
}
