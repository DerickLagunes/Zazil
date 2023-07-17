package com.zazilweb.model.DAO;

import com.zazilweb.model.Estado;
import com.zazilweb.model.Pais;
import com.zazilweb.utils.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoDao{

    private Connection con;
    private List<Estado> lista;
    private Estado e;
    private boolean resp;

    public EstadoDao(){
        this.con = new MysqlConector().connect();
        this.lista = new ArrayList<>();
        this.e = new Estado();
        this.resp = false;
    }


    public List findAll(int id) {
        try {
            PreparedStatement stmt =
                    con.prepareStatement(
                            "select * from estados where pais = ?"
                    );
            stmt.setInt(1,id);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                Estado e = new Estado();
                e.setId(res.getInt("id"));
                e.setNombre(res.getString("nombre"));
                Pais pais = new Pais();
                pais.setId(1);
                pais.setNombre("México");
                e.setPais(pais);

                lista.add(e);
            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

}