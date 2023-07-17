package com.zazilweb.model.DAO;

import com.zazilweb.model.*;
import com.zazilweb.utils.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaisDao implements DaoRepository{

    private Connection con;
    private List<Pais> lista;
    private Pais p;
    private boolean resp;

    public PaisDao(){
        this.con = new MysqlConector().connect();
        this.lista = new ArrayList<>();
        this.p = new Pais();
        this.resp = false;
    }

    @Override
    public List findAll() {
        try {
            PreparedStatement stmt =
                    con.prepareStatement(
                            "select * from paises"
                    );
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                p.setId(res.getInt(1));
                p.setNombre(res.getString(2));

                lista.add(p);
            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    @Override
    public Object findOne(int id) {
        return null;
    }

    @Override
    public boolean update(int id, Object object) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean insert(Object object) {
        return false;
    }
}
