package com.zazilweb.model.DAO;

import com.zazilweb.model.Categoria;
import com.zazilweb.model.Colonia;
import com.zazilweb.utils.MysqlConector;

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

    public CategoriaDao(){
        this.con = new MysqlConector().connect();
        this.lista = new ArrayList<>();
        this.resp = false;
    }


    public List findAll() {
        try {
            PreparedStatement stmt =
                    con.prepareStatement(
                            "select * from categoria"
                    );
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                Categoria c = new Categoria();
                c.setId(res.getInt("id"));
                c.setNombre(res.getString("nombre"));
                lista.add(c);
            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
}
