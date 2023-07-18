package com.zazilweb.model.DAO;

import com.zazilweb.model.Categoria;
import com.zazilweb.model.SubCategoria;
import com.zazilweb.utils.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubCategoriaDao {
    private Connection con;
    private List<SubCategoria> lista;
    private boolean resp;

    public SubCategoriaDao(){
        this.con = new MysqlConector().connect();
        this.lista = new ArrayList<>();
        this.resp = false;
    }


    public List findAll() {
        try {
            PreparedStatement stmt =
                    con.prepareStatement(
                            "select * from subcategoria"
                    );
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                SubCategoria c = new SubCategoria();
                c.setId(res.getInt("id"));
                c.setNombre(res.getString("nombre"));
                Categoria cat = new Categoria();
                cat.setId(res.getInt("categoria"));
                c.setCategoria(cat);
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
