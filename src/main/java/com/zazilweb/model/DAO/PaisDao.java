package com.zazilweb.model.DAO;

import com.zazilweb.model.*;
import com.zazilweb.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaisDao{

    private Connection con;
    private List<Pais> lista;
    private boolean resp;
    private ResultSet res;

    public PaisDao(){
        this.con = new DatabaseConnectionManager().connect();
        this.lista = new ArrayList<>();
        this.resp = false;
    }

    public List findAll() {
        try {
            PreparedStatement stmt =
                    con.prepareStatement(
                            "select * from paises"
                    );
            res = stmt.executeQuery();
            while(res.next()){
                Pais p = new Pais();
                p.setId(res.getInt(1));
                p.setNombre(res.getString(2));

                lista.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DatabaseConnectionManager.cerrarTodo(res,con);
        }
        return lista;
    }

}
